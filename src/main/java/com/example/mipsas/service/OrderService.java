package com.example.mipsas.service;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.entity.Order;
import com.example.mipsas.repository.CustomerRepository;
import com.example.mipsas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService
{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id)
    {
        return orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Order not found with id :" + id));
    }

    public Order createOrder(Order order)
    {
        return orderRepository.save(order);
    }

    public Order updateOrderCustomer(Integer orderId, Integer customerId)
    {
        Order order =
                orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Order not found with id : " + orderId));

        Customer customer =
                customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id : " + orderId));

        order.setCustomer(customer);
        orderRepository.save(order);
        return order;
    }

    public Order updateOrder(Order order)
    {
        orderRepository.findById(order.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Order not found with id : " + order.getId()));
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id)
    {
        orderRepository.deleteById(id);
    }
}

