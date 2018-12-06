package com.example.demo.service;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCartItems;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    ProductsRepository productRepo;

    public Order getOrder(Long id){
        return orderRepo
                .findById(id)
                .orElse(null);
    }

    public List<Order> getAllOrders(){
        List<Order> orders =  StreamSupport.stream(orderRepo.findAll().spliterator(),false).collect(Collectors.toList());
        return orders;
    }

    public Order insertOrder(Order order) throws CustomException {
        try {
            order.getShoppingCartItemsList().stream().forEach(x -> updateShoppingListDetails(x));
            order.calculateOrderTotalPrice();
            order.setOrderDate(new Date());
            return orderRepo.save(order);
        }catch(Exception e){
            e.printStackTrace();
            throw new CustomException("Order not saved! Please check for valid product Ids.");
        }

    }

    public void deleteOrder(Long id){
        orderRepo.deleteById(id);
    }

    /*public Order updateOrder(Long id,Order order) throws CustomException {
        try{
            Order orderUpdated = orderRepo.findById(id).orElseThrow(() -> new CustomException("Order not available to update!!!"));
            orderUpdated.setEmail(order.getEmail());
            orderUpdated.setPaymentSuccessFul(order.getPaymentSuccessFul());
            orderUpdated.setShoppingCartItemsList(order.getShoppingCartItemsList());
            orderUpdated.getShoppingCartItemsList().stream().forEach(x -> updateShoppingListDetails(x));
            orderUpdated.calculateOrderTotalPrice();
            return orderRepo.save(orderUpdated);
        }catch(Exception e){
            e.printStackTrace();
            throw new CustomException("Order not saved! Please check for valid product Ids.");
        }
    }*/

    private void updateShoppingListDetails(ShoppingCartItems x){
        Product product = productRepo.findById(x.getProduct().getId()).get();
        x.setPrice(product.getPrice());
        x.setProduct(product);//this loads the remaining attributes of product such as name and price
    }

    public List<Order> getSearchResultOrders(Date startDate, Date endDate) {
        return orderRepo.findAllByOrderDateBetween(startDate,endDate);
    }
}
