package com.example.protocolsrgr.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String details;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects;

    public User(String name, String details) {
        this.name = name;
        this.details = details;
    }
}
