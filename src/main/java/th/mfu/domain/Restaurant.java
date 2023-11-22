package th.mfu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Changed from rest_id to id
    private String name;
    //private int user_id;
    //private int restOwner_id;
    //private int review_id;
    //private int menu_id; 
    //private int rest_analytics;

    private String location;
    private Date open;
    private String description;
    private String restaurantPhotoUrl;
    //private float rate;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus;


    // Getter and Setter methods for id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Additional fields, getters, and setters
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
    
    public String getRestaurantPhotoUrl() {
        return restaurantPhotoUrl;
    }

    public void setRestaurantPhotoUrl(String restaurantPhotoUrl) {
        this.restaurantPhotoUrl = restaurantPhotoUrl;
    }
    
}