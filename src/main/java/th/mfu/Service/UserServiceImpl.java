package th.mfu.Service;


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

import th.mfu.DTO.UserRegistrationDto;
import th.mfu.Repository.UserRepository;
import th.mfu.domain.User;
import th.mfu.domain.role;
import th.mfu.role.Role;


@Service
public class UserServiceImpl implements UserService {

    // Injecting UserRepository using @Autowired annotation
    @Autowired
    private UserRepository userRepository;

    // Injecting BCryptPasswordEncoder using @Autowired annotation
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

	


    // Method for saving user registration information
    @Override
    public User save(UserRegistrationDto registrationDto) {
       
            // Creating a new User with the provided registration information
            // and adding the "USER_ROLE" to its roles
            User user = new User(
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail(),
                    passwordEncoder.encode(registrationDto.getPassword()),
                    Arrays.asList(new Role("USER_ROLE"))
            );
            return userRepository.save(user);
        
    }

    // Method for loading user details by username for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Finding the user by email in the userRepository
        User user = userRepository.findByEmail(username);
        // If user not found, throwing an exception
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        // Creating UserDetails object using user information and roles
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    // Private method to map roles to authorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        // Mapping roles to SimpleGrantedAuthority and collecting them into a list
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

	



}
