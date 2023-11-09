package th.mfu;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
}