package aspopov.icepeak.shop.service;

import aspopov.icepeak.security.domain.Manager;
import aspopov.icepeak.security.repository.ManagerRepository;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.exception.ManagerNotFoundException;
import aspopov.icepeak.shop.exception.OrderNotFoundException;
import aspopov.icepeak.shop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class BpmServiceImplTest {


    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private BpmService bpmService;

    @Test
    @DisplayName("должен корректно присваивать менеджера")
    void shouldCorrectAssignManager() {
        var manager = new Manager();
        manager.setId(1L);
        var order = new Order();
        order.setId(2L);
        given(orderRepository.findById(any())).willReturn(Optional.of(order));
        given(managerRepository.findById(any())).willReturn(Optional.of(manager));
        when(orderRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        var actualOrder = bpmService.assignManager(2L, 1L);
        assertThat(actualOrder.getManager()).isNotNull();
        assertThat(actualOrder.getManager().getId()).isEqualTo(1l);
        assertThat(actualOrder.getState()).isEqualTo(OrderState.PROCESSING);
        assertThat(actualOrder.getAssignDate()).isNotNull();
    }

    @Test
    @DisplayName("должен выбрасывать ManagerNotFound при присвоении некорректного id")
    void shouldThrowManagerNotFoundExceptionWhenAssignBadId() {
        var manager = new Manager();
        manager.setId(1L);
        given(orderRepository.findById(any())).willReturn(Optional.empty());
        given(managerRepository.findById(any())).willReturn(Optional.of(manager));

        assertThatExceptionOfType(OrderNotFoundException.class).isThrownBy(() -> bpmService.assignManager(2L, 1L));

    }

    @Test
    @DisplayName("должен выбрасывать OrderNotFound при присвоении некорректного id заказа")
    void shouldThrowOrderNotFoundExceptionWhenAssignManagerToBadIdOrder() {
        var order = new Order();
        order.setId(2L);
        given(orderRepository.findById(any())).willReturn(Optional.of(order));
        given(managerRepository.findById(any())).willReturn(Optional.empty());

        assertThatExceptionOfType(ManagerNotFoundException.class).isThrownBy(() -> bpmService.assignManager(2L, 1L));

    }

}