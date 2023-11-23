package th.mfu.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Changed from menu_id to id

    private String menu_name;
    private float menu_price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "restaurant_id") 
    private Restaurant restaurant;

    // Getter and Setter methods for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter methods for menu_name
    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    // Getter and Setter methods for menu_price
    public float getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(float menu_price) {
        this.menu_price = menu_price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}