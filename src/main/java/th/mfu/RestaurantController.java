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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import th.mfu.domain.Menu;
import th.mfu.domain.Restaurant;

@Controller
public class RestaurantController {

    //private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

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
            e.printStackTrace();
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

        @GetMapping("/edit-restaurant/{id}")
    public String editRestaurantForm(@PathVariable int id, Model model) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);

        if (restaurant == null) {
            // Handle the case where the restaurant is not found
            return "redirect:/restaurants";
        }

        model.addAttribute("restaurant", restaurant);
        return "edit-restaurant-form";
    }

    @PostMapping("/edit-restaurant/{id}")
    public String editRestaurant(@PathVariable int id, @ModelAttribute Restaurant editedRestaurant) {
        Restaurant existingRestaurant = restaurantRepo.findById(id).orElse(null);

        if (existingRestaurant == null) {
            // Handle the case where the restaurant is not found
            return "redirect:/restaurants";
        }

        // Update the fields with the edited values
        existingRestaurant.setName(editedRestaurant.getName());
        existingRestaurant.setOpen(editedRestaurant.getOpen());
        existingRestaurant.setDescription(editedRestaurant.getDescription());

        restaurantRepo.save(existingRestaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/{id}/menus")
    public String showMenus(@PathVariable int id, Model model) {
        // Fetch menus for the restaurant with the given id and add them to the model
        List<Menu> menus = menuRepo.findByRestaurantId(id);
        model.addAttribute("menus", menus);
    
        // Fetch restaurant details and add them to the model
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        model.addAttribute("restaurant", restaurant);
    
        // Add an empty menu object to the model (for the form)
        model.addAttribute("newMenu", new Menu());
    
        return "list-menus";
    }

    @GetMapping("/add-menu/{id}")
public String showAddMenuForm(@PathVariable int id, Model model) {
    // Fetch the restaurant with the given id
    Restaurant restaurant = restaurantRepo.findById(id).orElse(null);

    // Make sure the restaurant exists
    if (restaurant == null) {
        // Handle the case where the restaurant is not found
        return "redirect:/restaurants";
    }

    // Add the restaurant object to the model
    model.addAttribute("restaurant", restaurant);

    // Add an empty menu object to the model (for the form)
    model.addAttribute("newMenu", new Menu());

    return "add-menu-form";  // Return the view name, not a redirect
    }

    @PostMapping("/restaurants/{id}/menus")
public String saveMenu(@ModelAttribute Menu newMenu, @PathVariable int id, Model model) {
    // Fetch the restaurant with the given ID
    Restaurant restaurant = restaurantRepo.findById(id).orElse(null);

    // Make sure the restaurant exists
    if (restaurant == null) {
        // Handle the case where the restaurant is not found
        return "redirect:/restaurants";
    }

    // Set the restaurant for the new menu
    newMenu.setRestaurant(restaurant);

    // Save the new menu
    menuRepo.save(newMenu);

    // Redirect to the menu list page for the restaurant
    return "redirect:/restaurants/" + id + "/menus";
}


    @Transactional
    @GetMapping("/delete-menu/{id}")
    public String deleteMenu(@PathVariable int id) {
        Menu menu = menuRepo.findById(id).orElse(null);
        if (menu == null) {
            // Handle the case where the menu is not found
            return "redirect:/restaurants";
        }

        int restaurantId = menu.getRestaurant().getId();
        menuRepo.deleteById(id);

        return "redirect:/restaurants/" + restaurantId + "/menus";
    }
}