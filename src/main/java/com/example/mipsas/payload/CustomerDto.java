package com.example.mipsas.payload;

import lombok.Data;

@Data
public class CustomerDto
{
    private int id;
    private String name;
    private String surname;
    private String personalCode;
}
