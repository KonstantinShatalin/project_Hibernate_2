package entity.film;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(schema = "module_hibernate_2", name = "film_text")
public class Film_Text {
    @Id
    @Column(name = "film_id")
    private Integer id;
    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;
    @Column(name = "title")
    private String title;
    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film_id;

    public Film_Text() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Film getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

}
