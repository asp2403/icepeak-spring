package aspopov.icepeak.shop.dto;

import aspopov.icepeak.shop.domain.Order;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    private String contactName;

    @Nullable
    private String contactSurname;

    private String contactEmail;

    private String contactPhone;

    @Nullable
    private Long idCustomer;

    @Nullable
    private Long idOrder;

    @Nullable
    private Long idManager;

    private List<OrderItemDto> items;

    public static OrderDto fromDomain(Order order) {
        var orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::fromDomain).collect(Collectors.toList());
        var orderDto = new OrderDto(
                order.getContactName(),
                order.getContactSurname(),
                order.getContactEmail(),
                order.getContactPhone(),
                order.getId(),
                order.getCustomer() == null ? null : order.getCustomer().getId(),
                order.getManager() == null ? null : order.getManager().getId(),
                orderItemDtos);
        return orderDto;
    }

    public OrderDto(String contactName, @Nullable String contactSurname, String contactEmail, String contactPhone, @Nullable Long idOrder, @Nullable Long idCustomer, @Nullable Long idManager, List<OrderItemDto> items) {
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.idCustomer = idCustomer;
        this.idManager = idManager;
        this.idOrder = idOrder;
        this.items = items;
    }

    public OrderDto() {
    }

    @Nullable
    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(@Nullable Long idManager) {
        this.idManager = idManager;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Nullable
    public String getContactSurname() {
        return contactSurname;
    }

    public void setContactSurname(@Nullable String contactSurname) {
        this.contactSurname = contactSurname;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Nullable
    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(@Nullable Long idOrder) {
        this.idOrder = idOrder;
    }

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
