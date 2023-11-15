package th.mfu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Menu;
import th.mfu.domain.Restaurant;

@Controller
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private MenuRepository menuRepo;

    @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantRepo.findAll());
        return "list-restaurants";
    }

    @GetMapping("/add-restaurant")
    public String addARestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "add-restaurant-form";
    }

    @PostMapping("/restaurants")
public String saveRestaurant(@ModelAttribute Restaurant restaurant) {
    try {
        restaurantRepo.save(restaurant);
    } catch (Exception e) {
        // Log the exception for debugging purposes
        e.printStackTrace();
        // You can add more detailed logging as needed
    }
    return "redirect:/restaurants";
}

    @Transactional
    @GetMapping("/delete-restaurant/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        menuRepo.deleteByRestaurantId(id);
        restaurantRepo.deleteById(id);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/{id}/menus")
    public String showAddMenuForm(Model model, @PathVariable int id) {
        model.addAttribute("menus", menuRepo.findByRestaurantId(id));

        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant == null) {
            // Handle the case where the restaurant is not found
            return "redirect:/restaurants";
        }

        Menu menu = new Menu();
        menu.setRestaurant(restaurant);
        model.addAttribute("newMenu", menu);
        return "menu-mgmt";
    }

    @PostMapping("/restaurants/{id}/menus")
    public String saveMenu(@ModelAttribute Menu newMenu, @PathVariable int id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant == null) {
            // Handle the case where the restaurant is not found
            return "redirect:/restaurants";
        }

        newMenu.setRestaurant(restaurant);
        menuRepo.save(newMenu);
        return "redirect:/restaurants/" + id + "/menus";
    }
}