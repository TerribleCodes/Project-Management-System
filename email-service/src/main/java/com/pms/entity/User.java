package com.pms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED User Model
 * */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue(strategy = GenerationType.IDENTITY) - FOR TESTING
    private Long id; // private Long userId; - FOR TESTING

    private String password;
    private String email;
    private String role;
    private String name;
}
