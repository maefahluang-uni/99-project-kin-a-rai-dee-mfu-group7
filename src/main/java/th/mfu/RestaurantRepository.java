package th.mfu;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
