package th.mfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Menu;
import th.mfu.domain.Restaurant;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/add-restaurant")
    public String showAddRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "add-restaurant-form";
    }

    @PostMapping("/add-restaurant")
    public String addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/add-menu/" + restaurant.getRest_id();
    }

    @GetMapping("/add-menu/{rest_id}")
    public String showAddMenuForm(Model model, @PathVariable int rest_id) {
        Restaurant restaurant = restaurantRepository.findById(rest_id).orElse(null);
        if (restaurant != null) {
            Menu menu = new Menu();
            menu.setRestauran(restaurant);
            model.addAttribute("menu", menu);
            return "add-menu-form";
        }
        else {
            // Handle restaurant not found error
            return "redirect:/add-restaurant";
        }
    }

    @PostMapping("/add-menu")
    public String addMenu(Menu menu) {
        menuRepository.save(menu);
        return "redirect:/restaurant/" + menu.getRestaurant().getRest_id();
    }
}