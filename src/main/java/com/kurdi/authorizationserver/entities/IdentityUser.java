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
@Table(name = "identity_users")
@AllArgsConstructor
@NoArgsConstructor
public class IdentityUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String userName;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
