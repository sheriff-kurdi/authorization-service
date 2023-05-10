package com.kurdi.authorizationserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import lombok.AccessLevel;


import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
public class Authority implements Serializable {
    @Id
    @Setter(AccessLevel.PRIVATE) 
    private String name;

    @ManyToMany(mappedBy = "authorities" ,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    @ManyToOne
    @JoinColumn(name="action_id", nullable=false)
    private Action action;

    public void setName() {
        if (this.project != null && this.module != null && this.action != null) {
            this.name = this.project.getName() + this.module.getName() + this.action.getName();
        }
    }

}
