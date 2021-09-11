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
@ToString(of = {"id", "name"})
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;
    @Column(name = "mark_name", nullable = false, unique = true)
    @Expose
    @NonNull
    @NaturalId
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mark")
    private List<Car> cars = new LinkedList<>();

    private Mark() {
    }

    public Mark(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark mark = (Mark) o;
        return Objects.equals(name, mark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
