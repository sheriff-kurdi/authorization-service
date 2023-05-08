package com.kurdi.authorizationserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "authorities")
public class Authority implements Serializable{
    @Id
    private String name;

    @ManyToMany
    @JsonIgnore
    private Set<IdentityUser> users;

}
