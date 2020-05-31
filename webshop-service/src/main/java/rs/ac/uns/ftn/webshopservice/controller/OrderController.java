package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.webshopservice.dto.response.OrderDTO;
import rs.ac.uns.ftn.webshopservice.mappers.OrderMapper;
import rs.ac.uns.ftn.webshopservice.model.Order;
import rs.ac.uns.ftn.webshopservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/my")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<List<OrderDTO>> getMyOrders() {
        List<Order> orders = orderService.getMyOrders();
        return new ResponseEntity<>(OrderMapper.toListDto(orders), HttpStatus.OK);
    }
}
