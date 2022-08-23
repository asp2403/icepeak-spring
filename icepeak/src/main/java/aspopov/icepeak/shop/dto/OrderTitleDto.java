package aspopov.icepeak.shop.dto;

import aspopov.icepeak.security.dto.ManagerDto;
import aspopov.icepeak.shop.domain.Order;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

public class OrderTitleDto {
    private Long idOrder;

    private Integer state;

    private String contactName;

    private String contactSurname;

    private String contactEmail;

    private String contactPhone;

    private Long idCustomer;

    private ManagerDto manager;

    private Timestamp orderDate;

    private Timestamp assignDate;

    private Timestamp readyDate;

    private Timestamp finalDate;

    public OrderTitleDto(
            Long idOrder,
            Integer state,
            String contactName,
            String contactSurname,
            String contactEmail,
            String contactPhone,
            Long idCustomer,
            ManagerDto manager,
            Timestamp orderDate,
            Timestamp assignDate,
            Timestamp readyDate,
            Timestamp finalDate) {
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
    }

    public static OrderTitleDto fromDomain(Order order) {
        return new OrderTitleDto(
                order.getId(),
                order.getState(),
                order.getContactName(),
                order.getContactSurname(),
                order.getContactEmail(),
                order.getContactPhone(),
                order.getCustomer() != null ? order.getCustomer().getId() : null,
                order.getManager() != null ? ManagerDto.fromDomain(order.getManager()) : null,
                order.getOrderDate(),
                order.getAssignDate(),
                order.getReadyDate(),
                order.getFinalDate());
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public void setContactSurname(String contactSurname) {
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

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Timestamp assignDate) {
        this.assignDate = assignDate;
    }

    public Timestamp getReadyDate() {
        return readyDate;
    }

    public void setReadyDate(Timestamp readyDate) {
        this.readyDate = readyDate;
    }

    public Timestamp getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Timestamp finalDate) {
        this.finalDate = finalDate;
    }
}
