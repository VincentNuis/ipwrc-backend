package spring.boot.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {
    @Id

    private String firstName;
    private String lastName;

    public UserEntity(){

    }

    public UserEntity(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getId() {
        return 0;
    }
}
