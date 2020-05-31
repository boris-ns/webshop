package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getMyOrders();
}
