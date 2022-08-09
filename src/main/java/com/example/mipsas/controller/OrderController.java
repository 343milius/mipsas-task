package com.example.mipsas.controller;

import com.example.mipsas.entity.Customer;
import com.example.mipsas.entity.Order;
import com.example.mipsas.payload.OrderDto;
import com.example.mipsas.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController
{

    @Autowired
    OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public List<OrderDto> getOrders()
    {
        return orderService.getAllOrders().stream().map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId)
    {
        Order order = orderService.getOrderById(orderId);
        OrderDto orderResponse = modelMapper.map(order, OrderDto.class);
        return ResponseEntity.ok().body(orderResponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto)
    {
        Order orderRequest = modelMapper.map(orderDto, Order.class);
        Order order = orderService.createOrder(orderRequest);
        OrderDto orderResponse = modelMapper.map(order, OrderDto.class);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public Order updateOrder(@RequestBody Order order)
    {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable("orderId") Integer orderId)
    {
        OrderDto orderResponse = modelMapper.map(orderService.getOrderById(orderId), OrderDto.class);
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PatchMapping("/{orderId}/customer/{customerId}")
    public ResponseEntity<OrderDto> updateOrderCustomer(@PathVariable("orderId") Integer orderId,
                                     @PathVariable("customerId") Integer customerId)
    {
        orderService.updateOrderCustomer(orderId, customerId);
        OrderDto orderResponse = modelMapper.map(orderService.getOrderById(orderId), OrderDto.class);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrderCustomer(@PathVariable("orderId") Integer orderId, @RequestBody Customer customer)
    {
        orderService.updateOrderCustomer(orderId, customer.getId());
        OrderDto orderResponse = modelMapper.map(orderService.getOrderById(orderId), OrderDto.class);
        return ResponseEntity.ok().body(orderResponse);
    }

}

