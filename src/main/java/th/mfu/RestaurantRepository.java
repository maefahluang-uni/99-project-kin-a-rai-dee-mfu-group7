package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Restaurant;


public interface ConcertRepository extends CrudRepository<Restaurant, Int> {
      
}