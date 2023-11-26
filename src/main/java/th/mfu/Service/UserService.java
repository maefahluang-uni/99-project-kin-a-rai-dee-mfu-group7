package th.mfu.Service;


import org.springframework.security.core.userdetails.UserDetailsService;

import th.mfu.DTO.UserRegistrationDto;
import th.mfu.domain.User;





public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto userRegistrationDto);

}