package demo.dl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.qos.logback.classic.Logger;
import demo.model.ApiResponse;
import demo.util.CommonUtil;
import demo.util.Constants;

import org.json.simple.parser.*;
import org.slf4j.LoggerFactory;

@Service
@SuppressWarnings("unchecked")
public class DLIssueServiceImpl implements DLIssueService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DLIssueServiceImpl.class);

    @Autowired
    DLService dlService;

    @Autowired
    DLApplicationReferenceRepository dlApplicationReferenceRepository;

    @Override
    public ApiResponse applyDL(DLApplicationModel requestBody)
            throws IOException, ParseException, InterruptedException {

        logger.info(String.format("\n\n\nCalling DL Issue Service API: /dl/apply\n\n\n"));

        ApiResponse response = CommonUtil.createResponse(Constants.API_DL_APPLY);

        String name = requestBody.getName();
        String address = requestBody.getAddress();
        String mobile = requestBody.getMobile();
        Date date = requestBody.getTest_date();
        String slot = requestBody.getTest_slot();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> requestMap = mapper.convertValue(requestBody, Map.class);
        String reqBodyData = new ObjectMapper().writeValueAsString(requestMap);
        String testServiceUrl = "http://localhost:9000/dl/slot/check";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(testServiceUrl))
                .header(Constants.CONTENT_TYPE, "application/json")
                .header(Constants.ACCEPT, "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(reqBodyData))
                .build();

        logger.info(String.format("\n\n\nChecking slot availability: dl-test-service/dl/slot/check\n\n\n"));

        HttpResponse<String> httpresponse = HttpClient.newHttpClient().send(request,
                HttpResponse.BodyHandlers.ofString());
        Integer status = httpresponse.statusCode();

        if (status == 200) {
            DLApplicationModel dl = new DLApplicationModel(name, mobile, address, date, slot);
            dl = dlService.createDLApplication(dl);
            Map<String, Object> result = new HashMap<>();
            result.put("applicationId", dl.getApplicationid());
            response.putAll(result);
            response.getParams().setMsg( dl.getApplicationid().toString());
            logger.info(
                    String.format("\n\n\nApplication submitted! Application ID : " + dl.getApplicationid() + "\n\n\n"));

        } else {
            logger.error(Constants.MSG_SLOT_NOT_AVAILABLE);
            response.getParams().setStatus(Constants.FAILED);
            response.getParams().setErr(Constants.MSG_SLOT_NOT_AVAILABLE);
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return response;

    }

    // Create request body

    @Override
    public ApiResponse generateDL(String message)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        ApiResponse response = CommonUtil.createResponse(Constants.API_DL_APPLY);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> dlMap = mapper.readValue(message, Map.class);

        String application_id = dlMap.get("applicationid").toString();
        String name = dlMap.get("name").toString();
        String address = dlMap.get("address").toString();
        String mobile = dlMap.get("mobile").toString();

        if (dlMap.get("test_status").toString().equalsIgnoreCase("Passed")) {
            DLModel dl = new DLModel(application_id, name, mobile, address);
            dl = dlService.createDL(dl);

            DLApplicationReferenceModel dlRef = new DLApplicationReferenceModel(application_id,
                    dl.getDl_no().toString());
            dlRef = dlService.createDLApplicationReference(dlRef);

            logger.info(String.format("\n\n\nDL No.: " + dl.getDl_no() + " generated for Application ID : "
                    + dlMap.get("applicationid") + "\n\n\n"));

            response.getParams()
                    .setMsg("DL No.: " + dl.getDl_no() + " generated for Application ID : "
                            + dlMap.get("applicationId"));

        }

        return response;
    }

    @Override
    public ApiResponse getApplicationStatus(String applicationid)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        logger.info(String.format("\n\n\nCalling DL Issue Service API: /dl/status/<applicationId>\n\n\n"));

        ApiResponse response = CommonUtil.createResponse(Constants.API_DL_STATUS);
        Optional<DLApplicationReferenceModel> dl = dlService.getDLApplicationReferenceById(applicationid);
        Map<String, Optional<DLApplicationReferenceModel>> result = new HashMap<>();
        result.put("result", dl);
        if (dl.isEmpty())
            response.getParams().setMsg("DL not yet generated");
        else {
            logger.info(String.format("\n\n\nDL No.: " + dl.get().getDlno() + " generated\n\n\n"));
            response.getParams().setMsg("DL No.: " + dl.get().getDlno() + " generated");

        }

        return response;
    }

}
