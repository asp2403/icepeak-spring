package aspopov.icepeak.shop.dto;

import aspopov.icepeak.security.dto.ManagerDto;
import aspopov.icepeak.shop.domain.Order;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @Nullable
    private Long idOrder;

    @Nullable
    private Integer state;

    private String contactName;

    @Nullable
    private String contactSurname;

    private String contactEmail;

    private String contactPhone;

    @Nullable
    private Long idCustomer;

    @Nullable
    private ManagerDto manager;

    @Nullable
    private Timestamp orderDate;

    @Nullable
    private Timestamp assignDate;

    @Nullable
    private Timestamp readyDate;

    @Nullable
    private Timestamp finalDate;

    private List<OrderItemDto> items;

    public static OrderDto fromDomain(Order order) {
        var orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::fromDomain).collect(Collectors.toList());
        var orderDto = new OrderDto(
                order.getId(),
                order.getState(),
                order.getContactName(),
                order.getContactSurname(),
                order.getContactEmail(),
                order.getContactPhone(),
                order.getCustomer() == null ? null : order.getCustomer().getId(),
                order.getManager() == null ? null : ManagerDto.fromDomain(order.getManager()),
                order.getOrderDate(),
                order.getAssignDate(),
                order.getReadyDate(),
                order.getFinalDate(),
                orderItemDtos);
        return orderDto;
    }

    public OrderDto(@Nullable Long idOrder, @Nullable Integer state, String contactName, @Nullable String contactSurname,
                    String contactEmail, String contactPhone, @Nullable Long idCustomer, @Nullable ManagerDto manager,
                    @Nullable Timestamp orderDate, @Nullable Timestamp assignDate, @Nullable Timestamp readyDate,
                    @Nullable Timestamp finalDate, List<OrderItemDto> items) {
        this.idOrder = idOrder;
        this.state = state;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.idCustomer = idCustomer;
        this.manager = manager;
        this.orderDate = orderDate;
        this.assignDate = assignDate;
        this.readyDate = readyDate;
        this.finalDate = finalDate;
        this.items = items;
    }

    public OrderDto() {
    }

    @Nullable
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@Nullable Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Nullable
    public Timestamp getReadyDate() {
        return readyDate;
    }

    public void setReadyDate(@Nullable Timestamp readyDate) {
        this.readyDate = readyDate;
    }

    @Nullable
    public Timestamp getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(@Nullable Timestamp finalDate) {
        this.finalDate = finalDate;
    }

    @Nullable
    public Timestamp getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(@Nullable Timestamp assignDate) {
        this.assignDate = assignDate;
    }

    @Nullable
    public Integer getState() {
        return state;
    }

    public void setState(@Nullable Integer state) {
        this.state = state;
    }

    @Nullable
    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(@Nullable ManagerDto manager) {
        this.manager = manager;
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
