package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(10)
    private int quantity;

    //Stores a copy of the product price during the time of order placement
    private double price;

    @OneToOne(targetEntity = Product.class,fetch = FetchType.EAGER)
    private Product product;

    public ShoppingCartItems() {

    }

    public ShoppingCartItems(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        System.out.println("Price:"+product.getPrice());
        this.price = product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItems that = (ShoppingCartItems) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
