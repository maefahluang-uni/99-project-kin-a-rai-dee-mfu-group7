package th.mfu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import th.mfu.DTO.RestaurantOwnerRegistrationDto;
import th.mfu.DTO.UserRegistrationDto;


@Controller
public class MainController {
    
    // Handler for "/login" endpoint
    @GetMapping("/login")
    public String login() {
        return "login"; // Return the login view
    }
    
    // Handler for "/registration" endpoint
    @GetMapping("/registration")
    public String selectRole() {
        return "registration"; // Return the registration view
    }

    // Handler for "/user-registration" endpoint
    @GetMapping("/user-registration")
    public String userRegistration(Model model) {
        model.addAttribute("user", new UserRegistrationDto()); // Add an empty UserRegistrationDto to the model
        return "user-registration"; // Return the user registration view
    }

    // Handler for "/rest-registration" endpoint
    @GetMapping("/rest-registration")
    public String restRegistration(Model model) {
        model.addAttribute("restaurantOwner", new RestaurantOwnerRegistrationDto()); // Add an empty RestaurantOwnerRegistrationDto to the model
        return "rest-registration"; // Return the restaurant registration view
    }

    // Handler for "/" (home) endpoint
    @GetMapping("/")
    public String home() {
        return "index"; // Return the home (index) view
    }
    
}