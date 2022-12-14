package com.example.mipsas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity(name = "CUSTOMERS")
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Name may not be null")
    private String name;

    @NotNull(message = "Surname may not be null")
    private String surname;

    private String companyName;

    private int companyCode;

    @Size(min = 11, max = 11, message = "Personal code must be of 11 digits")
    private String personalCode;

    private String address;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;

}
