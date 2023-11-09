import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private Model model;

    @Test
    public void testShowAddRestaurantForm() {
        String viewName = restaurantController.showAddRestaurantForm(model);
        assertEquals("add-restaurant-form", viewName);
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setLocation("Test Location");

        String viewName = restaurantController.addRestaurant(restaurant);

        verify(restaurantRepository).save(restaurant);
        assertEquals("redirect:/add-menu/" + restaurant.getRest_id(), viewName);
    }

    @Test
    public void testShowAddMenuForm() {
        int rest_id = 1;
        Restaurant restaurant = new Restaurant();
        restaurant.setRest_id(rest_id);

        when(restaurantRepository.findById(rest_id)).thenReturn(java.util.Optional.of(restaurant));

        String viewName = restaurantController.showAddMenuForm(model, rest_id);

        verify(model).addAttribute("menu", any(Menu.class));
        assertEquals("add-menu-form", viewName);
    }
}