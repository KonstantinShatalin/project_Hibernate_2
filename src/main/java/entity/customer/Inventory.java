package entity.customer;

import entity.film.Film;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(schema = "module_hibernate_2", name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime last_update;
    @JoinColumn(name = "store_id")
    @ManyToOne
    private Store store_id;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film_id;

    public Inventory() {

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

    public Store getStore_id() {
        return store_id;
    }

    public void setStore_id(Store store_id) {
        this.store_id = store_id;
    }

    public Film getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

}
