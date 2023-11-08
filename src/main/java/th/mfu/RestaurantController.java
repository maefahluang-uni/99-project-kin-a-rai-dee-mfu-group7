package th.mfu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

import th.mfu.domain.User;
import th.mfu.domain.Restaurant;
import th.mfu.domain.Menu;

@Controller
public class ConcertController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    RestaurantRepository restRepo;

    @Autowired
    MenuRepository menuRepo;

//getmapping to some where
@GetMapping("/restaurant")
public String listRestaurant(Model Model) {
model.addAttribute("restaurant",restRepo.findAll());
    return "list-restaurant";
}
@GetMapping("/add_restaurant")
public String addRestaurant(Model model){
model.addAttribute("newRestaurant",new Restaurant());
    return "add-restaurant-form";
}
@PostMapping("/restaurant")
public String saveRestaurants(@ModelAttribute Restaurant newRestaurant) {
      restRepo.save(newRestaurant);
      return "redirect:/restaurant";
}
@Transactional
@GetMapping("/delete-restaurant/{rest_id}")
public String deleteRestaurant(@PathVariable int rest_id){
    // งงบรรทัดนี้ แปะไว้ก่อน 
    restRepo.deleteByRest_id(rest_id);
    return "redirect:/restaurant";
}
//เหมือนจะข้่ามไรไปสักอย่าง แปะไว้ก่อน
@PostMapping("/restaurant/{rest_id}")
public String saveRestaurant(@ModelAttribute Restaurant newrestaurant,@PathVariable int rest_id) {     
    Restaurant restaurant = rest_Repo.findById(rest_id).get();     
    newrestaurant.setRestaurant(restaurant);    
    restRepo.save(newrestaurant);
    return "redirect:/restaurant/"+rest_id+"";
    // มันดูเเหม่งๆ เเต่ไว้มาเเก้คืนนี้
}
}


 
