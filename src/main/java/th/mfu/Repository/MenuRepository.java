package th.mfu.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByRestaurantId(int restaurantId);
    void deleteByRestaurantId(int restaurantId);
}
