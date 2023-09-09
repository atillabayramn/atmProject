package com.atilla.atmProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="deposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="amount")
    private Long amount;

    @Column(name="newBalance")
    private Long newBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    User user;


}
