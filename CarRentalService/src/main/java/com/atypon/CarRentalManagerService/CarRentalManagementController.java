package com.atypon.CarRentalManagerService;


import com.atypon.CarRentalManagerService.secondaryClasses.AuthenticationService;
import com.atypon.CarRentalManagerService.secondaryClasses.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class CarRentalManagementController {
    private CarRentalRepository carRentalRepository;
    private AuthenticationService authenticationService;

    @Autowired
    public CarRentalManagementController(CarRentalRepository carRentalRepository, AuthenticationService authenticationService) {
        this.carRentalRepository = carRentalRepository;
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
            return "redirect:/car-rental";
        } else {
            return "redirect:/authentication-failed";
        }
    }

    @GetMapping("/car-rental")
    public String carRental(Model model) {
        return "car-rental";
    }

    @GetMapping("/authentication-failed")
    public String authenticationFailed(Model model) {
        return "authentication-failed";
    }


    @PostMapping("/store-car-rental-data")
    public String storeCarRentalData
            (@RequestParam Long customerSsn, @RequestParam String customerName,
             @RequestParam String rentedCarModel) {

        CarRentingEntity carRentingEntity = new CarRentingEntity(customerSsn, customerName, rentedCarModel);
        carRentalRepository.save(carRentingEntity);
        return "redirect:/successful-rental-data-entry";
    }

    @GetMapping("/successful-rental-data-entry")
    public String successfulRentingDataEntry(Model model) {

        // Sending a post request to the analytics service to notify it when
        // a new entry has been added to MySQL database.

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://analytics:9500/update-analytics";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);

        return "successful-rental-data-entry";
    }
}
