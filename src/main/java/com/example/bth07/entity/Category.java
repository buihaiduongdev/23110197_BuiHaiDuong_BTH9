package com.example.bth07.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String images;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<User> users;
}
