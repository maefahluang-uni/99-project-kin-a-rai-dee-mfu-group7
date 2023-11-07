package th.mfu.domain;

import java.util.Date; 

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    //private int review_id;
    //private int rest_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private Date user_birthday;
    private String user_gender;

 // Getter and Setter methods for user_id
 public int getUser_id() {
    return user_id;
}

public void setUser_id(int user_id) {
    this.user_id = user_id;
}

// Getter and Setter methods for user_name
public String getUser_name() {
    return user_name;
}

public void setUser_name(String user_name) {
    this.user_name = user_name;
}

// Getter and Setter methods for user_email
public String getUser_email() {
    return user_email;
}

public void setUser_email(String user_email) {
    this.user_email = user_email;
}

// Getter and Setter methods for user_password
public String getUser_password() {
    return user_password;
}

public void setUser_password(String user_password) {
    this.user_password = user_password;
}

// Getter and Setter methods for user_birthday
public Date getUser_birthday() {
    return user_birthday;
}

public void setUser_birthday(Date user_birthday) {
    this.user_birthday = user_birthday;
}

// Getter and Setter methods for user_gender
public String getUser_gender() {
    return user_gender;
}

public void setUser_gender(String user_gender) {
    this.user_gender = user_gender;
}


// 1.เจ้าของร้านอาหารเข้าสู่ระบบหรือลงทะเบียน  
// 2. คลิกที่รูปโปรไฟล์ในแถบนำทาง
// 3. เพิ่มร้านอาหารและกรอกข้อมูล
// 4. เพิ่มเมนูอาหารและกรอกข้อมูล
// 5. กด "บันทึก" เพื่อบันทึกข้อมูลร้านอาหาร

//เจ้าของร้านอาหารสามารถเพิ่มร้านอาหาร(สูงสุด 3 ร้าน)หรือลบร้านอาหารได้ รวมถึงการเพิ่มเมนูหรือการลบเมนูในร้านอาหาร
//hello
// hiyo
