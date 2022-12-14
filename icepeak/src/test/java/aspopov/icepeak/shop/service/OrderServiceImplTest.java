package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.Customer;
import aspopov.icepeak.shop.repository.CustomerRepository;
import aspopov.icepeak.shop.repository.ManagerRepository;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.domain.OrderItem;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderItemDto;
import aspopov.icepeak.shop.exception.*;
import aspopov.icepeak.shop.repository.OrderRepository;
import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.domain.Product;
import aspopov.icepeak.warehouse.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private ManagerRepository managerRepository;

    @Test
    @DisplayName("должен корректно создавать заказ")
    void shouldCorrectCreateOrder() {
        var orderItemDtos = List.of(new OrderItemDto(1, 1), new OrderItemDto(2, 2));
        var orderDto = new OrderDto();
        orderDto.setIdCustomer(1L);
        orderDto.setItems(orderItemDtos);
        var product1 = new Product();
        product1.setQtyAvailable(10);
        product1.setQtyReserved(0);
        var product2 = new Product();
        product2.setQtyAvailable(20);
        product2.setQtyReserved(0);
        var model1 = new Model();
        model1.setPrice(100);
        var model2 = new Model();
        model2.setPrice(200);
        product1.setModel(model1);
        product2.setModel(model2);
        var customer = new Customer();
        var expectedOrder = new Order();
        expectedOrder.setId(20);
        expectedOrder.setOrderItems(List.of(new OrderItem(expectedOrder, 1, product1, 1, 100), new OrderItem(expectedOrder, 2, product2, 2, 200)));
        expectedOrder.setState(OrderState.NEW);
        expectedOrder.setCustomer(customer);

        given(productRepository.findById(eq(1L))).willReturn(Optional.of(product1));
        given(productRepository.findById(eq(2L))).willReturn(Optional.of(product2));
        given(customerRepository.findById(eq(1L))).willReturn(Optional.of(customer));
        //given(orderRepository.save(eq(expectedOrder))).willReturn(expectedOrder);
        when(orderRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());

        var actualOrder = orderService.createOrder(orderDto);

        assertThat(product1.getQtyAvailable()).isEqualTo(9);
        assertThat(product2.getQtyAvailable()).isEqualTo(18);
        assertThat(product1.getQtyReserved()).isEqualTo(1);
        assertThat(product2.getQtyReserved()).isEqualTo(2);

        assertThat(actualOrder).isNotNull();
        assertThat(actualOrder.getState()).isEqualTo(OrderState.NEW);
        assertThat(actualOrder.getOrderDate()).isNotNull();
        assertThat(actualOrder.getOrderItems().size()).isEqualTo(2);
        assertThat(actualOrder.getOrderItems().get(0).getProduct().getId()).isEqualTo(product1.getId());
        assertThat(actualOrder.getOrderItems().get(0).getSalePrice()).isEqualTo(product1.getModel().getPrice());
        assertThat(actualOrder.getOrderItems().get(1).getProduct().getId()).isEqualTo(product2.getId());
        assertThat(actualOrder.getOrderItems().get(1).getSalePrice()).isEqualTo(product2.getModel().getPrice());
    }

    @Test
    @DisplayName("должен выбрасывать ProductNotAvailableException")
    void shouldThrowProductNotAvailableException() {
        var orderItemDtos = List.of(new OrderItemDto(1, 1));
        var orderDto = new OrderDto();
        orderDto.setItems(orderItemDtos);
        var product1 = new Product();
        product1.setQtyAvailable(0);
        product1.setQtyReserved(0);
        var model1 = new Model();
        model1.setPrice(100);
        product1.setModel(model1);
        given(productRepository.findById(eq(1L))).willReturn(Optional.of(product1));

        assertThatExceptionOfType(ProductNotAvailableException.class).isThrownBy(() -> orderService.createOrder(orderDto));
    }

    @Test
    @DisplayName("должен выбрасывать ProductNotFoundException")
    void shouldThrowProductNotFoundException() {
        var orderItemDtos = List.of(new OrderItemDto(1, 1), new OrderItemDto(2, 2));
        var orderDto = new OrderDto();
        orderDto.setItems(orderItemDtos);
        given(productRepository.findById(eq(1L))).willReturn(Optional.empty());

        assertThatExceptionOfType(ProductNotFoundException.class).isThrownBy(() -> orderService.createOrder(orderDto));

    }

    @Test
    @DisplayName("должен выбрасывать OrderIsEmptyException")
    void shouldThrowOrderIsEmptyException() {
        var orderItemDtos = new ArrayList<OrderItemDto>();
        var orderDto = new OrderDto();
        orderDto.setItems(orderItemDtos);

        assertThatExceptionOfType(OrderIsEmptyException.class).isThrownBy(() -> orderService.createOrder(orderDto));
    }

    @Test
    @DisplayName("должен выбрасывать CustomerNotFoundException")
    void shouldThrowCustomerNotFoundException() {
        var orderItemDtos = List.of(new OrderItemDto(1, 1));
        var orderDto = new OrderDto();
        orderDto.setIdCustomer(1L);
        orderDto.setItems(orderItemDtos);
        var product1 = new Product();
        product1.setQtyAvailable(10);
        product1.setQtyReserved(0);
        var model1 = new Model();
        model1.setPrice(100);
        product1.setModel(model1);
        given(productRepository.findById(eq(1L))).willReturn(Optional.of(product1));
        given(customerRepository.findById(eq(1L))).willReturn(Optional.empty());

        assertThatExceptionOfType(CustomerNotFoundException.class).isThrownBy(() -> orderService.createOrder(orderDto));
    }



}