package com.kurdi.authorizationserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project implements Serializable{
    @Id
    private String name;

    private String description;

    @OneToMany(mappedBy="project")
    private Set<Module> modules;

}
