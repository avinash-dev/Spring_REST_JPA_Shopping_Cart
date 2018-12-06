package com.example.demo.controller;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(path = "/{id}")
    public Order getOrders(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @GetMapping(path="/")
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path="/search")
    public List<Order> getSearchResultOrders(@RequestParam("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate, @RequestParam("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate){
        return orderService.getSearchResultOrders(startDate,endDate);
    }

    @PostMapping(path="/")
    public Order saveOrder(@RequestBody Order order) throws CustomException {
        return orderService.insertOrder(order);
    }

    /*@PutMapping(path="/{id}")
    public Order updateOrder(@PathVariable Long id,@RequestBody Order order) throws CustomException {
        return orderService.updateOrder(id,order);
    }*/

    @DeleteMapping(path="/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }

}