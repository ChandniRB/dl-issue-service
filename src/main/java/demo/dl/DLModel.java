package demo.dl;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "dl_issued")
public class DLModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID dl_no;

    private String name;
    private String mobile;
    private String address;
    

    
    public DLModel(String applicationid, String name, String mobile, String address) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        
    }

    public UUID getDl_no() {
        return dl_no;
    }

    public void setDl_no(UUID dl_no) {
        this.dl_no = dl_no;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    

    public DLModel() {
    }

    

    

}
