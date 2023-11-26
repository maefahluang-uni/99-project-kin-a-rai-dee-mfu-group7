package th.mfu;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import th.mfu.domain.Menu;
import th.mfu.domain.Restaurant;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        // Add sample restaurants to the list

        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        String viewName = restaurantController.listRestaurants(model);

        assertEquals("list-restaurants", viewName);
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testAddARestaurantForm() {
        String viewName = restaurantController.addARestaurantForm(model);

        assertEquals("add-restaurant-form", viewName);
    }

    @Test
    public void testSaveRestaurant() {
        Restaurant newRestaurant = new Restaurant();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = restaurantController.saveRestaurant(newRestaurant, bindingResult);

        assertEquals("redirect:/restaurants", viewName);
        verify(restaurantRepository, times(1)).save(newRestaurant);
    }

    @Test
    public void testDeleteRestaurant() {
        int restaurantId = 1001;

        String viewName = restaurantController.deleteRestaurant(restaurantId);

        assertEquals("redirect:/restaurants", viewName);
        verify(menuRepository, times(1)).deleteByRestaurant_Rest_id(restaurantId);
        verify(restaurantRepository, times(1)).deleteById(restaurantId);
    }

    @Test
    public void testShowAddMenuForm() {
        int restaurantId = 1;
        List<Menu> menuList = new ArrayList<>();
        // Add sample menus to the list

        when(menuRepository.findByRestaurantRest_id(restaurantId)).thenReturn(menuList);

        String viewName = restaurantController.showAddMenuForm(model, restaurantId);

        assertEquals("menu-mgmt", viewName);
        verify(menuRepository, times(1)).findByRestaurantRest_id(restaurantId);
    }

    @Test
    public void testSaveMenu() {
        int restaurantId = 1;
        Restaurant restaurant = new Restaurant();
        restaurant.setRest_id(restaurantId);

        Menu newMenu = new Menu();
        newMenu.setRestaurant(restaurant);

        when(restaurantRepository.findById(restaurantId)).thenReturn(java.util.Optional.of(restaurant));
        when(menuRepository.save(newMenu)).thenReturn(newMenu);

        String viewName = restaurantController.saveMenu(newMenu, restaurantId);

        assertEquals("redirect:/restaurants/" + restaurantId + "/menus", viewName);
        verify(restaurantRepository, times(1)).findById(restaurantId);
        verify(menuRepository, times(1)).save(newMenu);
    }
}
