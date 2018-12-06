package com.example.demo.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User email is mandatory!")
    @Basic(optional = false)
    @Email
    private String email;

    private double orderTotalPrice;

    private boolean paymentSuccessFul;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(targetEntity = ShoppingCartItems.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ShoppingCartItems> shoppingCartItemsList;

    /*@ManyToMany(targetEntity = Product.class)
    List<Product> productList;*/


    public Order(@NotNull(message = "User email is mandatory!") String email, double orderTotalPrice, boolean paymentSuccessFul, List<ShoppingCartItems> shoppingCartItemsList) {
        this.email = email;
        this.orderTotalPrice = orderTotalPrice;
        this.paymentSuccessFul = paymentSuccessFul;
        this.shoppingCartItemsList = shoppingCartItemsList;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void calculateOrderTotalPrice(){
        Double price = this.shoppingCartItemsList.stream().mapToDouble(x -> x.getPrice()*x.getQuantity()).sum();
        setOrderTotalPrice(price);
    }

    public boolean getPaymentSuccessFul() {
        return paymentSuccessFul;
    }

    public void setPaymentSuccessFul(boolean paymentSuccessFul) {
        this.paymentSuccessFul = paymentSuccessFul;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<ShoppingCartItems> getShoppingCartItemsList() {
        return shoppingCartItemsList;
    }

    public void setShoppingCartItemsList(List<ShoppingCartItems> shoppingCartItemsList) {
        this.shoppingCartItemsList = shoppingCartItemsList;
    }

    /*public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
