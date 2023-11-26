package th.mfu.DTO;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOwnerRegistrationDto {
    // Properties to hold registration data
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Default constructor
    public RestaurantOwnerRegistrationDto() {

    }

    // Constructor with parameters for convenient object creation
    public RestaurantOwnerRegistrationDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods for each property

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
