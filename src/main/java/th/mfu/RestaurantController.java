package th.mfu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/restaurants/{id}/menus")
public String addMenu(@PathVariable int id, @ModelAttribute Menu newMenu, @RequestParam("menuPhoto") MultipartFile menuPhoto) {
        try {
            Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
    
            if (restaurant == null) {
                System.err.println("Restaurant not found with ID: " + id);
                return "redirect:/restaurants";
            }
    
            System.out.println("Received request to add menu. Restaurant ID: " + id);
    
            // Create a new Menu instance
            Menu menuToAdd = new Menu();
    
            // Set the Restaurant reference
            menuToAdd.setRestaurant(restaurant);
    
            // Set the properties from the newMenu object
            menuToAdd.setMenu_name(newMenu.getMenu_name());
            menuToAdd.setMenu_price(newMenu.getMenu_price());

            if (menuPhoto != null && !menuPhoto.isEmpty()) {
                String photoFileName = "menu_" + menuToAdd.getId() + ".jpg";
                File photoFile = new File("/path/to/your/photo/directory/" + photoFileName);

                // Ensure the directory exists, create it if not
                if (!photoFile.getParentFile().exists()) {
                    photoFile.getParentFile().mkdirs();
                }

                // Handle the menu photo - save it to a specific directory or storage service
                menuPhoto.transferTo(photoFile);
                menuToAdd.setMenuPhotoUrl("/path/to/your/photo/directory/" + photoFileName);
            }
            // Print information about the new menu
            System.out.println("Received new menu details - Name: " + menuToAdd.getMenu_name()
                    + ", Price: " + menuToAdd.getMenu_price());
    
            // Save the new menu
            menuRepo.save(menuToAdd);
    
            System.out.println("Menu saved successfully.");
    
            return "redirect:/restaurants/{id}/menus";
             } catch (Exception e) {
            System.err.println("Error adding menu: " + e.getMessage());
            return "redirect:/restaurants";
        }
    }

@GetMapping("/restaurants/{id}/delete-menu/{menuId}")
public String deleteMenu(@PathVariable int id, @PathVariable int menuId) {
    menuRepo.deleteById(menuId);

    return "redirect:/restaurants/{id}/menus";
    }

    @GetMapping("/restaurants/{id}/edit-menu/{menuId}")
public String editMenuForm(@PathVariable int id, @PathVariable int menuId, Model model) {
    Restaurant restaurant = restaurantRepo.findById(id).orElse(null);

    if (restaurant == null) {
        return "redirect:/restaurants";
    }

    Menu menu = menuRepo.findById(menuId).orElse(null);

    if (menu == null || menu.getRestaurant().getId() != id) {
        return "redirect:/restaurants/{id}/menus";
    }

    model.addAttribute("restaurant", restaurant);
    model.addAttribute("menu", menu);

    return "edit-menu-form";
}

@PostMapping("/restaurants/{id}/edit-menu/{menuId}")
    public String editMenu(@PathVariable int id, @PathVariable int menuId, @ModelAttribute Menu editedMenu, @RequestParam("menuPhoto") MultipartFile menuPhoto) throws IllegalStateException, IOException {
        Menu existingMenu = menuRepo.findById(menuId).orElse(null);

        if (existingMenu == null || existingMenu.getRestaurant().getId() != id) {
            return "redirect:/restaurants/{id}/menus";
        }

        // Update the fields with the edited values
        existingMenu.setMenu_name(editedMenu.getMenu_name());
        existingMenu.setMenu_price(editedMenu.getMenu_price());

        // Save the photo if provided
        if (menuPhoto != null && !menuPhoto.isEmpty()) {
            String photoFileName = "menu_" + existingMenu.getId() + ".jpg";
            File photoFile = new File("/path/to/your/photo/directory/" + photoFileName);

            // Ensure the directory exists, create it if not
            if (!photoFile.getParentFile().exists()) {
                photoFile.getParentFile().mkdirs();
            }

            // Handle the menu photo - save it to a specific directory or storage service
            menuPhoto.transferTo(photoFile);
            existingMenu.setMenuPhotoUrl("/path/to/your/photo/directory/" + photoFileName);
        }

        // Save the edited menu
        menuRepo.save(existingMenu);

        return "redirect:/restaurants/{id}/menus";
    }

}