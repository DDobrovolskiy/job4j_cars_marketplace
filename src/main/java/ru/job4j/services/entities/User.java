package ru.job4j.services.entities;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = {"id", "name", "email", "password", "phone"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;
    @Column(name = "user_name", nullable = false)
    @Expose
    @NonNull
    private String name;
    @Column(name = "user_email", nullable = false, unique = true)
    @Expose
    @NonNull
    @NaturalId
    private String email;
    @Column(name = "user_password", nullable = false)
    @Expose
    @NonNull
    private String password;
    @Column(name = "user_phone", nullable = false)
    @Expose
    @NonNull
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Car> cars = new LinkedList<>();

    private User() {
    }

    public User(
            @NonNull String name,
            @NonNull String email,
            @NonNull String password,
            @NonNull String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
