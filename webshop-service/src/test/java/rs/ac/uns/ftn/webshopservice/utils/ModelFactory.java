package rs.ac.uns.ftn.webshopservice.utils;

import rs.ac.uns.ftn.webshopservice.model.*;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelFactory {

    public static final List<ProductCategory> productCategories = List.of(
            new ProductCategory(1L, "Home"),
            new ProductCategory(2L, "Garden"),
            new ProductCategory(3L, "Car")
    );

    public static final List<Store> stores = List.of(
            new Store(1L, "Home store", 0.0f, null),
            new Store(1L, "Garden store", 0.1f, null),
            new Store(1L, "Car store", 0.2f, null)
    );

    public static final List<Product> products = List.of(
            new Product(1L, "Lamp", Double.valueOf(100f), Double.valueOf(0), 100, 10, 3, 0.1f, 3, 0f, 0.5f, "asd15", 0.05f, false, stores.get(0), productCategories.get(0)),
            new Product(2L, "Sofa", Double.valueOf(100f), Double.valueOf(10), 100, 10, 3, 0.1f, 3, 0f, 0.5f, "asd15", 0.05f, false, stores.get(1), productCategories.get(1)),
            new Product(3L, "Monitor", Double.valueOf(100f), Double.valueOf(0), 100, 10, 3, 0.1f, 3, 0.1f, 0.5f, "asd15", 0.05f, false, stores.get(2), productCategories.get(2)),
            new Product(4L, "Stereo", Double.valueOf(100f), Double.valueOf(0), 100, 10, 3, 0.1f, 3, 0f, 0.5f, "asd15", 0.05f, false, stores.get(0), productCategories.get(0)),
            new Product(5L, "Table", Double.valueOf(100f), Double.valueOf(0), 100, 10, 3, 0.1f, 3, 0f, 0.5f, "asd15", 0.05f, false, stores.get(1), productCategories.get(1))
    );

    public static Buyer getBuyerForSilverCategory() {
        Buyer buyer = new Buyer();
        buyer.setCategory(BuyerCategory.REGULAR);

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            Order order = new Order(Long.valueOf(i), new Date(), 1, Double.valueOf(100f), 0.1f, "asdf", products.get(0));
            orders.add(order);
        }

        buyer.setOrders(orders);
        return buyer;
    }

    public static Buyer getBuyerForGoldCategory() {
        Buyer buyer = new Buyer();
        buyer.setCategory(BuyerCategory.SILVER);

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            Order order = new Order(Long.valueOf(i), new Date(), 1, Double.valueOf(100f), 0.1f, "asdf", products.get(0));
            orders.add(order);
        }

        buyer.setTotalMoneySpent(Double.valueOf(3100));
        buyer.setOrders(orders);
        return buyer;
    }

    public static Buyer getBuyerForDiamondCategory() {
        Buyer buyer = new Buyer();
        buyer.setCategory(BuyerCategory.GOLD);

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 500; ++i) {
            Order order = new Order(Long.valueOf(i), new Date(), 1, Double.valueOf(100f), 0.1f, "asdf", products.get(0));
            orders.add(order);
        }

        buyer.setOrders(orders);
        buyer.setTotalMoneySpent(Double.valueOf(8100));
        return buyer;
    }

    private ModelFactory() {
    }
}
