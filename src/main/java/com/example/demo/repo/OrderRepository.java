package com.example.demo.repo;

import com.example.demo.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> findAllByOrderDateBetween(Date startDate, Date endDate);
}
