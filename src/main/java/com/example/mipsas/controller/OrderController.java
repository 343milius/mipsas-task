package com.example.mipsas.controller;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.entity.Order;
import com.example.mipsas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController
{

    @Autowired
    OrderService orderService;

    @GetMapping()
    public List<Order> getOrders()
    {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id)
    {
        Order order = orderService.getOrderById(id);
        return order;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order)
    {
        return orderService.createOrder(order);
    }

    @PutMapping()
    public Order updateOrder(@RequestBody Order order)
    {
        return orderService.updateOrder(order);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable("id") Integer id)
    {
        orderService.deleteOrder(id);
    }

    @PatchMapping("/{orderId}/customer/{customerId}")
    public Order updateOrderCustomer(@PathVariable("orderId") Integer orderId,
                                     @PathVariable("customerId") Integer customerId)
    {
        return orderService.updateOrderCustomer(orderId, customerId);
    }

    @PatchMapping("/{orderId}")
    public Order updateOrderCustomer(@PathVariable("orderId") Integer orderId, @RequestBody Customer customer)
    {
        return orderService.updateOrderCustomer(orderId, customer.getId());
    }

}

