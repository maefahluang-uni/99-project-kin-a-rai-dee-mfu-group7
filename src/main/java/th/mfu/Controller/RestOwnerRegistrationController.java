package th.mfu.Contrller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.LoginAndRegistWeb.Entity.RestaurantOwnerRegistrationDto;
import com.example.LoginAndRegistWeb.Service.RestOwnerService;


// For NEW Resttaurant Owner registration
@Controller
@RequestMapping("/rest-registration")
public class RestOwnerRegistrationController {

    private RestOwnerService restOwnerService;

    // Constructor injection of RestOwnerService
    public RestOwnerRegistrationController(RestOwnerService restOwnerService) {
        super();
        this.restOwnerService = restOwnerService;
    }

    // Model attribute for binding the form data
    @ModelAttribute("restaurantOwner")
    public RestaurantOwnerRegistrationDto restaurantOwnerRegistrationDto() {
        return new RestaurantOwnerRegistrationDto();
    }

    // Displaying the registration form
    @GetMapping("/rest-registration")
    public String showRegistrationForm() {
        return "rest-registration"; // Return the restaurant registration view
    }

    // Processing the registration form submission
    @PostMapping
    public String registerUserAccount(@ModelAttribute("restaurantOwner") RestaurantOwnerRegistrationDto registrationDto) {
        restOwnerService.save(registrationDto); // Save the registration data using the RestOwnerService
        return "redirect:/rest-registration?success"; // Redirect to the registration form with success parameter
    }
}
