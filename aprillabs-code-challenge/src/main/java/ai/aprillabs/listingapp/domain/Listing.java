package ai.aprillabs.listingapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String address;
    private Integer beds;
    private Integer baths;
    private BigDecimal price;

    public Listing() {
    }

    public Listing(Integer id, String address, Integer beds, Integer baths, BigDecimal price) {
        this.id = id;
        this.address = address;
        this.beds = beds;
        this.baths = baths;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int compareTo(Listing other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", beds=" + beds +
                ", baths=" + baths +
                ", price=" + price +
                '}';
    }
}
