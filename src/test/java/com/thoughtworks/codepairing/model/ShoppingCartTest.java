package com.thoughtworks.codepairing.model;

import com.thoughtworks.codepairing.service.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void addMultiCustomerTest() {
        Customer customer1 = new Customer("customer1");
        Customer customer2 = new Customer("customer2");
        Product product1 = new Product(120, "DIS_15_ABCD", "product1");
        Product product2 = new Product(150, "DIS_10_ABCD", "product2");
        Product product3 = new Product(100, "", "product3");
        Product product4 = new Product(100, "DIS_15_ABCD", "product4");
        Product product5 = new Product(100, "DIS_10_ABCD", "product5");
        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(product1);
        products1.add(product2);
        products1.add(product3);
        shoppingCart.addCustomer(customer1);
        shoppingCart.addProducts(customer1, products1);

        shoppingCart.addCustomer(customer2);
        shoppingCart.addProduct(customer2, product4);
        shoppingCart.addProduct(customer2, product5);

        shoppingCart.show();
        System.out.println(customer1.getOrder().toString());
        System.out.println(customer2.getOrder().toString());

    }

    @Test
    public void delProdectTest(){
        Customer customer1 = new Customer("customer1");
        Customer customer2 = new Customer("customer2");
        Product product1 = new Product(120, "DIS_15_ABCD", "product1");
        Product product2 = new Product(150, "DIS_10_ABCD", "product2");
        Product product3 = new Product(100, "", "product3");
        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(product1);
        products1.add(product2);
        products1.add(product3);
        shoppingCart.addCustomer(customer1);
        shoppingCart.addProducts(customer1, products1);
        shoppingCart.show();
        System.out.printf(customer1.getOrder().toString()+"\n");

        shoppingCart.delProdect(customer1,"product1");
        shoppingCart.show();
        System.out.printf(customer1.getOrder().toString()+"\n");
    }
}
