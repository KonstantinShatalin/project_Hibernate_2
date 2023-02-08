package entity.customer;

import entity.address.Address;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "module_hibernate_2", name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime last_update;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address_id;
    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff manager_staff_id;

    public Store() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    public Address getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Address address_id) {
        this.address_id = address_id;
    }

    public Staff getManager_staff_id() {
        return manager_staff_id;
    }

    public void setManager_staff_id(Staff manager_staff_id) {
        this.manager_staff_id = manager_staff_id;
    }

}
