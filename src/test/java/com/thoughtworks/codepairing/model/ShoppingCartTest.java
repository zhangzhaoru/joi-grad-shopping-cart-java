package com.thoughtworks.codepairing.model;

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
        Order order = shoppingCart.checkout(customer);
        assertEquals(100.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        shoppingCart.addProducts(customer, products);
        Order order = shoppingCart.checkout(customer);

        assertEquals(20, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        Order order = shoppingCart.checkout(customer);
        assertEquals(90.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        Order order = shoppingCart.checkout(customer);

        assertEquals(10, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        Order order = shoppingCart.checkout(customer);

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        customer = new Customer("test");
        shoppingCart.addCustomer(customer);
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        shoppingCart.addProducts(customer, products);
        Order order = shoppingCart.checkout(customer);

        assertEquals(6, order.getLoyaltyPoints());
    }
}
