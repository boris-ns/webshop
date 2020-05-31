package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.model.Buyer;
import rs.ac.uns.ftn.webshopservice.model.Order;
import rs.ac.uns.ftn.webshopservice.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getMyOrders() {
        Buyer user = (Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ArrayList<>(user.getOrders());
    }
}
