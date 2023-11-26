
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LoginAndRegistWeb.Entity.RestaurantOwner;
import com.example.LoginAndRegistWeb.Entity.RestaurantOwnerRegistrationDto;

import com.example.LoginAndRegistWeb.Repo.RestaurantOwnerRepository;
import com.example.LoginAndRegistWeb.Role.Role;

@Service
public class RestOwnerServiceImpl implements RestOwnerService{

    // Injecting UserRepository using @Autowired annotation
   @Autowired
	private RestaurantOwnerRepository restaurantOwnerRepository;

    // Injecting BCryptPasswordEncoder using @Autowired annotation
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

	



    // Method for saving user registration information
    @Override
    public RestaurantOwner save(RestaurantOwnerRegistrationDto registrationDto) {
        // Creating a new User or RestaurantOwner with the provided registration information
        // and saving it to the userRepository

        // Checking if the registration is for a RestaurantOwner
            // and adding the "REST_ROLE" to its roles
            RestaurantOwner restaurantOwner = new RestaurantOwner(
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail(),
                    passwordEncoder.encode(registrationDto.getPassword()),
                    Arrays.asList(new Role("REST_ROLE"))
            );
            return restaurantOwnerRepository.save(restaurantOwner);
    }
    

    // Method for loading user details by username for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Finding the user by email in the userRepository
       RestaurantOwner restaurantOwner = restaurantOwnerRepository.findByEmail(username);
        // If user not found, throwing an exception
        if (restaurantOwner == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        // Creating UserDetails object using user information and roles
        return new org.springframework.security.core.userdetails.User(
                restaurantOwner.getEmail(),
                restaurantOwner.getPassword(),
                mapRolesToAuthorities(restaurantOwner.getRoles())
        );
    }

    // Private method to map roles to authorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        // Mapping roles to SimpleGrantedAuthority and collecting them into a list
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

	



}