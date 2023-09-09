package com.atilla.atmProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="transfers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name = "transfer";

    @Column(name="amount")
    private Long amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    User user;

    @Column(name="getMoneyUserId")
    private Long getMoneyUserId;


}
