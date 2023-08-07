package com.atilla.atmProject.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="userId")
    private Long userId;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="balance")
    private Long balance;



}
