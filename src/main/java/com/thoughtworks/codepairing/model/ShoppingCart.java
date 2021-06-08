package com.thoughtworks.codepairing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingCart {
    //    private List<Product> products;
//    private Customer customer;

    private static final int MAX_ACCOUNT_CAPACITY = 100;
    private static final int MAX_PRODUCT_CAPACITY = 20;

    private static HashMap<Customer, List<Product>> account = null;

    private static ShoppingCart instance = null;

    private ShoppingCart() {
        account = new HashMap<Customer, List<Product>>(MAX_ACCOUNT_CAPACITY);
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public boolean addCustomer(Customer customer) {
        if (account.containsKey(customer)) {
            return false;
        } else {
            if (account.size() == MAX_ACCOUNT_CAPACITY) {
                return false;
            } else {
                account.put(customer, null);
                return true;
            }
        }
    }

    public boolean addProducts(Customer customer,List<Product> products){
        for (Product product : products) {
            if(!addProduct(customer,product)){
                return false;
            }
        }
        return true;
    }

    public boolean addProduct(Customer customer, Product product) {
        if (!account.containsKey(customer)) {
            if (!addCustomer(customer)) {
                return false;
            }
        }

        if (account.containsKey(customer)) {
            List<Product> products = account.get(customer);
            if (products==null) {
                products = new ArrayList<Product>(MAX_PRODUCT_CAPACITY);
                products.add(product);
                account.put(customer, products);
                checkout(customer);

            } else {
                if (products.size() == MAX_PRODUCT_CAPACITY) {
                    return false;
                } else {
                    products.add(product);
                    account.put(customer, products);
                    checkout(customer);
                }
            }

        }
        return true;
    }

    public boolean delProdect(Customer customer,String prodectName){
        if(!account.containsKey(customer)){
            return false;
        }
        List<Product> products = account.get(customer);
        for (Product product : products) {
            if (prodectName.equals(product.getName())) {
                if (products.remove(product)) {
                    account.put(customer,products);
                    checkout(customer);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean delCustomer(Customer customer){
        if(account.containsKey(customer)){
            account.remove(customer);
            return true;
        }
        return false;
    }

    public List<Product> getProducts(Customer customer) {
        if (account.containsKey(customer)) {
            return account.get(customer);
        } else {
            return null;
        }
    }

    private void checkout(Customer customer) {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        List<Product> products = getProducts(customer);
        for (Product product : products) {
            double discount = 0;
            if (product.getProductCode().startsWith("DIS_10")) {
                discount = (product.getPrice() * 0.1);
                loyaltyPointsEarned += (product.getPrice() / 10);
            } else if (product.getProductCode().startsWith("DIS_15")) {
                discount = (product.getPrice() * 0.15);
                loyaltyPointsEarned += (product.getPrice() / 15);
            } else {
                loyaltyPointsEarned += (product.getPrice() / 5);
            }

            totalPrice += product.getPrice() - discount;
        }

        customer.setOrder(new Order(totalPrice, loyaltyPointsEarned));
    }

    public void show() {
        for (Map.Entry<Customer, List<Product>> customerListEntry : account.entrySet()) {
            System.out.println("=====================================");
            System.out.println("customer : " + customerListEntry.getKey().getName());
            for (Product product : customerListEntry.getValue()) {
                System.out.println(product.toString());
            }
            System.out.println("=====================================");
        }
    }

    public void destroyAccount() {
        account = new HashMap<Customer, List<Product>>(MAX_ACCOUNT_CAPACITY);
    }

}
