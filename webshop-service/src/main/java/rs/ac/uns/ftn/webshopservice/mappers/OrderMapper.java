package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.response.OrderDTO;
import rs.ac.uns.ftn.webshopservice.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static List<OrderDTO> toListDto(List<Order> orders) {
        return orders.stream()
                .map(OrderDTO::new).collect(Collectors.toList());
    }

    private OrderMapper() {
    }
}
