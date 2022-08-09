package com.example.mipsas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "ORDERS")
public class Order
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String description;

    private LocalDate activeFrom;

    private LocalDate activeTo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}