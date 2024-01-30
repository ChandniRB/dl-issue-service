package demo.dl;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import demo.model.ApiResponse;

public interface DLIssueService {
    
    
    ApiResponse applyDL(DLApplicationModel request) throws IOException, ParseException, InterruptedException;

    ApiResponse generateDL(String request) throws  IOException, ParseException, InterruptedException, java.text.ParseException;

    ApiResponse getApplicationStatus(String applicationId) throws  IOException, ParseException, InterruptedException, java.text.ParseException;

}
