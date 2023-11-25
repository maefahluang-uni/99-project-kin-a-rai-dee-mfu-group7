package th.mfu.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Changed from rest_id to id
    private String name;
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    private Date open;

    private String description;
    
    private String restaurantPhotoFileName;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus;

    // Getter and Setter methods for id, name, location, open, description, restPhoto, menus
    // Omitted for brevity, but you should include getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getOpen() {
        return open;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getRestaurantPhotoFileName() {
        return restaurantPhotoFileName;
    }
    
    public void setRestaurantPhotoFileName(String restaurantPhotoFileName) {
        this.restaurantPhotoFileName = restaurantPhotoFileName;
    }
}

