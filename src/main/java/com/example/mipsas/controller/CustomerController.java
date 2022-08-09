package com.example.mipsas.controller;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable Integer id)
    {
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    @GetMapping
    public List<Customer> getCustomers()
    {
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer)
    {
        return customerService.createCustomer(customer);
    }
}