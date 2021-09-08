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
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    @Expose
    private int id;
    @Column(name = "mark_name", nullable = false, unique = true)
    @Expose
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mark")
    private List<Car> cars = new LinkedList<>();
}
