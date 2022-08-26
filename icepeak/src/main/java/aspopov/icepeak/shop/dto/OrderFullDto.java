package aspopov.icepeak.shop.dto;

import aspopov.icepeak.shop.domain.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFullDto {
    private OrderTitleDto title;
    private List<OrderItemFullDto> items;

    public OrderFullDto(OrderTitleDto title, List<OrderItemFullDto> items) {
        this.title = title;
        this.items = items;
    }

    public static OrderFullDto fromDomain(Order order) {
        var title = OrderTitleDto.fromDomain(order);
        var items = order.getOrderItems().stream().map(OrderItemFullDto::fromDomain).collect(Collectors.toList());
        return new OrderFullDto(title, items);
    }

    public OrderTitleDto getTitle() {
        return title;
    }

    public void setTitle(OrderTitleDto title) {
        this.title = title;
    }

    public List<OrderItemFullDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemFullDto> items) {
        this.items = items;
    }
}
