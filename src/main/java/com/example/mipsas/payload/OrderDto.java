package com.example.mipsas.payload;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.entity.Type;
import lombok.Data;

@Data
public class OrderDto
{
    private String name;
    private Type type;
    private Customer customer;
}