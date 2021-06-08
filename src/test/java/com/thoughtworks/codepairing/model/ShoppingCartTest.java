package com.thoughtworks.codepairing.model;

import com.thoughtworks.codepairing.service.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";
    public static ShoppingCart shoppingCart = null;

    Customer customer;

    @Before
    public void setUp() throws Exception {

        shoppingCart = ShoppingCart.getInstance();

    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        shoppingCart.addProducts(customer, products);
        System.out.println(customer.getOrder().toString());
        assertEquals(100.0, customer.getOrder().getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        shoppingCart.addProducts(customer, products);
        System.out.println(customer.getOrder().toString());

        assertEquals(20, customer.getOrder().getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        System.out.println(customer.getOrder().toString());
        assertEquals(90.0, customer.getOrder().getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        System.out.println(customer.getOrder().toString());

        assertEquals(10, customer.getOrder().getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        System.out.println(customer.getOrder().toString());

        assertEquals(85.0, customer.getOrder().getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        System.out.println(customer.getOrder().toString());

        assertEquals(6, customer.getOrder().getLoyaltyPoints());
    }
}
