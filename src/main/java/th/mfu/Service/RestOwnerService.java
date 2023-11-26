package th.mfu.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.LoginAndRegistWeb.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     //Finds a user by their email address.
    User findByEmail(String email);
}
