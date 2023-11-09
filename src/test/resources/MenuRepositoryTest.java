import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testSaveAndGetMenu() {
        // Create a Menu entity
        Menu menu = new Menu();
        menu.setMenu_name("Test Menu");
        menu.setMenu_price(10.99f);
        menu.setDescription("This is a test menu.");

        // Save the menu to the repository
        Menu savedMenu = menuRepository.save(menu);

        // Retrieve the menu from the repository
        Menu retrievedMenu = menuRepository.findById(savedMenu.getMenu_id()).orElse(null);

        // Check if the retrieved menu matches the saved menu
        assertNotNull(retrievedMenu);
        assertEquals(savedMenu.getMenu_name(), retrievedMenu.getMenu_name());
        assertEquals(savedMenu.getMenu_price(), retrievedMenu.getMenu_price());
        assertEquals(savedMenu.getDescription(), retrievedMenu.getDescription());
    }
}