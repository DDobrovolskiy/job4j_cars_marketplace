package ru.job4j.models;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "bodies")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id")
    @Expose
    private int id;
    @Column(name = "body_type", nullable = false, unique = true)
    @Expose
    private String type;
    @OneToMany(mappedBy = "body", cascade = CascadeType.ALL)
    private List<Car> cars = new LinkedList<>();
}
