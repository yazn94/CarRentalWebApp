package com.atypon.showresults;


import com.atypon.showresults.secondaryClasses.AnalyticsEntity;
import com.atypon.showresults.secondaryClasses.AuthenticationService;
import com.atypon.showresults.secondaryClasses.CarRentingEntity;
import com.atypon.showresults.secondaryClasses.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class ShowResultsServiceController {
    private final DataService dataService;
    private final AuthenticationService authenticationService;

    @Autowired
    public ShowResultsServiceController(DataService dataService, AuthenticationService authenticationService) {
        this.dataService = dataService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "authentication";
    }

    @PostMapping("/check-credentials")
    public String authenticateUser(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = authenticationService.authenticate(new UserInfo(email, password));
        if (isAuthenticated) {
            return "redirect:/home";
        } else {
            return "redirect:/authentication-failed";
        }
    }


    @GetMapping("/authentication-failed")
    public String authenticationFailed(Model model) {
        return "authentication-failed";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/all-data")
    public String getAllData(Model model) {
        List<CarRentingEntity> dataList = dataService.getAllData();
        model.addAttribute("dataList", dataList);
        return "all-data";
    }

    @GetMapping("/analytics")
    public String analytics(Model model) {
        List<AnalyticsEntity> analyticsList = dataService.getAnalyticsEntities();
        Collections.reverse(analyticsList);
        model.addAttribute("analyticsList", analyticsList);
        return "analytics";
    }
}
