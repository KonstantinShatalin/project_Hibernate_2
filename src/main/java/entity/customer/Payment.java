package entity.customer;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "module_hibernate_2", name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "payment_date")
    private LocalDateTime payment_date;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime last_update;
    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer_id;
    @JoinColumn(name = "staff_id")
    @ManyToOne
    private Staff staff_id;
    @JoinColumn(name = "rental_id")
    @ManyToOne
    private Rental rental_id;

    public Payment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
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

    public Rental getRental_id() {
        return rental_id;
    }

    public void setRental_id(Rental rental_id) {
        this.rental_id = rental_id;
    }

}
