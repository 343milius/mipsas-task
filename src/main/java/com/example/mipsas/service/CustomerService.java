package com.example.mipsas.service;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.repository.CustomerRepository;
import com.example.mipsas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id)
    {
        return customerRepository.getById(id);
    }

    public Customer createCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id)
    {
        customerRepository.deleteById(id);
    }

}
