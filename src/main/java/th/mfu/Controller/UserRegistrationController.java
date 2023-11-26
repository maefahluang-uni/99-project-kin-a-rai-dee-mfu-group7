package th.mfu.Controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.mfu.DTO.UserRegistrationDto;
import th.mfu.Service.UserService;



//Registration for NEW user 
@Controller
@RequestMapping("/user-registration")
public class UserRegistrationController {

    private UserService userService;

    // Constructor injection of UserService
    public void setUserService (@Lazy UserService userService) {
        
        this.userService = userService;
    }
    
    // Model attribute for binding the form data
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto(); // Provide an empty UserRegistrationDto to the model
    }
    
    // Handler for displaying the user registration form
    @GetMapping("/user-registration")
    public String showRegistrationForm() {
        return "user-registration"; // Return the user registration view
    }

    // Handler for processing the user registration form submission
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        // The existing code processes the registration by saving the data using the UserService
        userService.save(registrationDto); // Save user registration data using the UserService
        return "redirect:/user-registration?success"; // Redirect to the user registration form with success parameter
    }	
}