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
public class Issue {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String details;

    @ManyToMany
    private List<User> responsible;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Issue(String name, String details, List<User> responsible, Project project) {
        this.name = name;
        this.details = details;
        this.responsible = responsible;
        this.project = project;
    }
}
