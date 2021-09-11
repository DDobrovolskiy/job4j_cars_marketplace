package ru.job4j.services.entities;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;
    @Column(name = "car_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Expose
    private Date create;
    @Column(name = "car_description", nullable = false)
    @Expose
    private String description;
    @Column(name = "car_price", nullable = false)
    @Expose
    private int price;
    @Column(name = "car_status", nullable = false)
    @Expose
    private boolean status;
    @Column(name = "car_photo")
    @Expose
    private String photo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mark_id", nullable = false)
    @Expose
    private Mark mark;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "body_id", nullable = false)
    @Expose
    private Body body;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    @Expose
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car other = (Car) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
