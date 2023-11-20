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
    
        List<Menu> menus = menuRepo.findByRestaurantId(id);
    
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("menus", menus);
    
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
    public String viewManageMenus(@PathVariable int id, Model model) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
    
        if (restaurant == null) {
            // Handle the case where the restaurant is not found
            return "redirect:/restaurants";
        }
    
        List<Menu> menus = restaurant.getMenus();
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("menus", menus);
    
        return "list-menus";
    }
    
    @GetMapping("/restaurants/{id}/add-menu")
public String addMenuForm(@PathVariable int id, Model model) {
    Restaurant restaurant = restaurantRepo.findById(id).orElse(null);

    if (restaurant == null) {
        // Handle the case where the restaurant is not found
        return "redirect:/restaurants";
    }

    model.addAttribute("newMenu", new Menu());
    model.addAttribute("restaurant", restaurant);

    return "add-menu-form";
    }

    @PostMapping(value = "/restaurants/{id}/menus", produces = "text/html")
    public String addMenu(@PathVariable int id, @ModelAttribute Menu newMenu) {
    Restaurant restaurant = restaurantRepo.findById(id).orElse(null);

    if (restaurant == null) {
        // Handle the case where the restaurant is not found
        return "redirect:/restaurants";
    }

    newMenu.setRestaurant(restaurant);
    menuRepo.save(newMenu);

    return "redirect:/restaurants/{id}/menus";
    }

@GetMapping("/restaurants/{id}/delete-menu/{menuId}")
public String deleteMenu(@PathVariable int id, @PathVariable int menuId) {
    menuRepo.deleteById(menuId);

    return "redirect:/restaurants/{id}/menus";
    }

}