package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Menu;


public interface ConcertRepository extends CrudRepository<Menu, Int> {
      //add function...
}