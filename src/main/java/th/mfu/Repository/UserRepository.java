package th.mfu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.mfu.domain.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     //Finds a user by their email address.
    User findByEmail(String email);
}