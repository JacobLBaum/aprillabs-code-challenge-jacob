package ai.aprillabs.listingapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private UserType type;
    private Integer age;
    private Integer numOfReferral;

    public User() {
    }

    public User(Integer id, String name, UserType type, Integer age, Integer numOfReferral) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.numOfReferral = numOfReferral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumOfReferral() {
        return numOfReferral;
    }

    public void setNumOfReferral(Integer numOfReferral) {
        this.numOfReferral = numOfReferral;
    }

    public int compareTo(User other) {
        return this.id.compareTo(other.id);
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", age=" + age +
                ", number of referrals=" + numOfReferral +
                '}';
    }
}
