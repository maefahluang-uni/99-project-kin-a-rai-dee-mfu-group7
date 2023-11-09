package th.mfu.domain;

import javax.persistence.CascadeType;

//import java.util.Date;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menu_id;
    //private int rest_id;
    private String menu_name;
    private float menu_price;
    //private String description; จะบอกว่าตรงหน้าเว็บไม่ขึ้นให้ใส่หน้าร้าน ลองดูใน DOC หน้า 29
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Restaurant restaurant;
 
        // Getter and Setter methods for menu_id
        public int getMenu_id() {
            return menu_id;
        }
    
        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }
    
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
    
        // Getter and Setter methods for description
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }

        public Restaurant getRestaurant() {
            return null;
        }

        public void setRestaurant(Restaurant restaurant2) {
        }

        public void setRestauran(Restaurant restaurant2) {
        }
    }