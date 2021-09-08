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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Expose
    private int id;
    @Column(name = "user_name", nullable = false)
    @Expose
    private String name;
    @Column(name = "user_email", nullable = false, unique = true)
    @Expose
    private String email;
    @Column(name = "user_password", nullable = false)
    @Expose
    private String password;
    @Column(name = "user_phone", nullable = false)
    @Expose
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Car> cars = new LinkedList<>();
}
