package entity.customer;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "module_hibernate_2", name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer id;
    @Column(name = "rental_date")
    private LocalDateTime rental_date;
    @Column(name = "return_date")
    private LocalDateTime return_date;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDate last_update;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory_id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_id;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff_id;

    public Rental() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getRental_date() {
        return rental_date;
    }

    public void setRental_date(LocalDateTime rental_date) {
        this.rental_date = rental_date;
    }

    public LocalDateTime getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDateTime return_date) {
        this.return_date = return_date;
    }

    public LocalDate getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDate last_update) {
        this.last_update = last_update;
    }

    public Inventory getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Inventory inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public Staff getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Staff staff_id) {
        this.staff_id = staff_id;
    }

}
