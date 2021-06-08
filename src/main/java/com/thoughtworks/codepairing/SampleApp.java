package com.thoughtworks.codepairing;

import com.thoughtworks.codepairing.model.Customer;
import com.thoughtworks.codepairing.model.Order;
import com.thoughtworks.codepairing.model.Product;
import com.thoughtworks.codepairing.service.ShoppingCart;

public class SampleApp {
    public static void main(String[] args) {
        Product product1 = new Product(10.0, "DIS_10_PRODUCT1", "product 1");
        Product product2 = new Product(20.0, "DIS_10_PRODUCT2", "product 2");

        Customer customer = new Customer("A Customer");
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        shoppingCart.addCustomer(customer);
        shoppingCart.addProduct(customer, product1);
        shoppingCart.addProduct(customer, product2);

        shoppingCart.show();

        Product product3 = new Product(30.0, "DIS_10_PRODUCT3", "product 3");
        shoppingCart.addProduct(customer, product3);
        shoppingCart.show();

        System.out.println(customer.getOrder().toString());
    }
}
