package com.example.protocolsrgr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String details;

    @ManyToMany
    @JoinTable(
            name = "usr_to_list",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "usr_id") }
    )
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Issue> issues;

    public Project(String name, String details, List<User> users) {
        this.name = name;
        this.users = users;
        this.details = details;
    }
}
