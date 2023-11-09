package th.mfu;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Menu;

public interface MenuRepository extends CrudRepository<Menu, Integer> {
}