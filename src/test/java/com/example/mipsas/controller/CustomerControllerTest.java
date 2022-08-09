package com.example.mipsas.controller;

import com.example.mipsas.MipsasApplication;
import com.example.mipsas.entity.Customer;
import com.example.mipsas.entity.Order;
import com.example.mipsas.entity.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MipsasApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerTest
{
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerController customerController;

    @Test
    public void testGetAllCustomers() throws Exception
    {
        Customer customer = getCustomer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        given(customerController.getCustomers()).willReturn(customers);
        mvc.perform(get("/customer/").contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(customer.getName())));
    }

    @Test
    public void testGetCustomer() throws Exception
    {
        Customer customer = getCustomer();
        given(customerController.getCustomerById(1)).willReturn(customer);
        mvc.perform(get("/customer/" + customer.getId()).contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("name", is(customer.getName())));
    }

    @Test
    public void testCreateCustomer() throws Exception
    {
        Customer customer = getCustomer();
        when(customerController.createCustomer(customer)).thenReturn(customer);
        mvc.perform(post("/customer/").content(asJson(customer)).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }

    private Customer getCustomer()
    {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Martynas");
        customer.setSurname("Milius");
        customer.setCompanyName("Test company name");
        customer.setCompanyCode(111);
        customer.setPersonalCode(222);
        customer.setAddress("Test address");
        return customer;
    }

    private static String asJson(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
