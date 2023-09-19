package com.atilla.atmProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="withdraws")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Withdraw {

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
    @JsonIgnore
    User user;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;


}
