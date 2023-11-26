package th.mfu.domain;
import java.util.Collection;

import javax.management.relation.Role;
import javax.persistence.*;




@Entity
@Table(name = "restOwner", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class RestaurantOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rest_Owner_firstname")
    private String firstName;

    @Column(name = "rest_Owner_lastname")
    private String lastName;

    private String email;
    private String password;

    // Many-to-Many relationship with Role entity
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "rests_roles",
        joinColumns = @JoinColumn(name = "restOwner_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    // Default constructor
    public RestaurantOwner() {
    }

    // Constructor with parameters
    public RestaurantOwner(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    // Getter and Setter methods 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}