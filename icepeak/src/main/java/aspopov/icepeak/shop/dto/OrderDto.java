package aspopov.icepeak.shop.dto;

import org.springframework.lang.Nullable;

import java.util.List;

public class OrderDto {

    @Nullable
    private Long idCustomer;

    private List<OrderItemDto> items;

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }
}
