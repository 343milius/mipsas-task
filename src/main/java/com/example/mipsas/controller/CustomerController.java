package com.example.mipsas.controller;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.payload.CustomerDto;
import com.example.mipsas.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController
{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id)
    {
        Customer customer = customerService.getCustomerById(id);
        CustomerDto customerResponse = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok().body(customerResponse);
    }

    @GetMapping
    public List<CustomerDto> getCustomers()
    {
        return customerService.getAllCustomers().stream().map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto)
    {
        Customer customerRequest = modelMapper.map(customerDto, Customer.class);
        Customer customer = customerService.createCustomer(customerRequest);
        CustomerDto customerResponse = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok().body(customerResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@Valid @RequestBody int id)
    {
        CustomerDto customerResponse = modelMapper.map(customerService.getCustomerById(id), CustomerDto.class);
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(customerResponse);
    }

}