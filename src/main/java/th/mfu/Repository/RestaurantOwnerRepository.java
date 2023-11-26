package th.mfu.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LoginAndRegistWeb.Entity.RestaurantOwner;

// Repository interface for the RestaurantOwner entity
@Repository
public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Long> {

    // Custom query method to find a restaurant owner by email
    RestaurantOwner findByEmail(String email);
}
