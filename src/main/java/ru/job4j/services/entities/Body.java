package ru.job4j.services.entities;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString(of = {"id", "type"})
@Table(name = "bodies")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;
    @Column(name = "type", nullable = false, unique = true)
    @Expose
    @NonNull
    @NaturalId
    private String type;
    @OneToMany(mappedBy = "body", cascade = CascadeType.ALL)
    private List<Car> cars = new LinkedList<>();

    private Body() {
    }

    public Body(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return Objects.equals(type, body.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
