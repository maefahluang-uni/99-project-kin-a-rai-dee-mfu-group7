package th.mfu.Service;
import th.mfu.DTO.RestaurantOwnerRegistrationDto;
import th.mfu.domain.RestaurantOwner;
import org.springframework.security.core.userdetails.UserDetailsService;





public interface RestOwnerService extends UserDetailsService{
	RestaurantOwner save(RestaurantOwnerRegistrationDto registrationDto);
}