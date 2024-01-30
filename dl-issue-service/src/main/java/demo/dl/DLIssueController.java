package demo.dl;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import demo.model.ApiResponse;

@Controller
@RequestMapping("/dl")
// @CrossOrigin(origins = "http://localhost:8000")
public class DLIssueController {

    @Autowired
    DLIssueService dlIssueService;

    

    @GetMapping("/apply")
    public String applicationForm(Model model) {
        model.addAttribute("dlApplicationModel", new DLApplicationModel());
        return "applicationForm";
    }

    @PostMapping("/apply")
    public String applyDL(@ModelAttribute DLApplicationModel dlApplicationModel, Model model)
            throws IOException, ParseException, InterruptedException {
        ApiResponse response = dlIssueService.applyDL(dlApplicationModel);
        
        model.addAttribute("applicationId", response.getParams().getMsg());
    
        return "applicationSubmitted";
    }

    @GetMapping(value = "/status/{applicationId}")
    public String getApplicationStatus(@PathVariable("applicationId") String applicationId, Model model)
            throws Exception {

        ApiResponse response = dlIssueService.getApplicationStatus(applicationId);
        model.addAttribute("applicationStatus", response.getParams().getMsg());
    
        return "applicationStatus";
    }

}
