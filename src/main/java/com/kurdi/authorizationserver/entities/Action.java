package com.kurdi.authorizationserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actions")
public class Action implements Serializable{
    @Id
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    private Module module;



}
