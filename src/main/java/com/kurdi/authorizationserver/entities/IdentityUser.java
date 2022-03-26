package com.kurdi.authorizationserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "identity_Users")
@AllArgsConstructor
@NoArgsConstructor
public class IdentityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String userName;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Authority> authorities;

}
