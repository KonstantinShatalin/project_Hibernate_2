package entity.film;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(schema = "module_hibernate_2", name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    @Convert(converter = YearAttributeConverter.class)
    private Year release_year;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language_id;
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language original_language_id;
    @Column(name = "rental_duration")
    private Integer rental_duration;
    @Column(name = "rental_rate")
    private Double rental_rate;
    @Column(name = "length")
    private Integer length;
    @Column(name = "replacement_cost")
    private Double replacement_cost;
    @Column(columnDefinition = "enum ('G', 'PG', 'PG-13, 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;
    @Column(name = "special_features")
    private String special_features;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime last_update;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actorSet;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categorySet;


    public Film() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Year getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Year release_year) {
        this.release_year = release_year;
    }

    public Language getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Language language_id) {
        this.language_id = language_id;
    }

    public Language getOriginal_language_id() {
        return original_language_id;
    }

    public void setOriginal_language_id(Language original_language_id) {
        this.original_language_id = original_language_id;
    }

    public Integer getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(Integer rental_duration) {
        this.rental_duration = rental_duration;
    }

    public Double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(Double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(Double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Feature> getSpecial_features() {
        if (special_features == null || special_features.isEmpty()){
            return null;
        }
        Set<Feature> result = new HashSet<>();
        String[] features = special_features.split(",");
        for (String feature : features) {
            result.add(Feature.getFeatureByValues(feature));
        }
        result.remove(null);
        return result;
    }

    public void setSpecial_features(Set<Feature> features) {
        if (features == null){
            special_features = null;
        }else {
           special_features = features.stream().map(Feature::getValues).collect(Collectors.joining(","));
        }
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    public Set<Actor> getActorSet() {
        return actorSet;
    }

    public void setActorSet(Set<Actor> actorSet) {
        this.actorSet = actorSet;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

}
