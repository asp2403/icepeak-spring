package aspopov.icepeak.shop.rest;

import aspopov.icepeak.security.config.AuthenticationProvider;
import aspopov.icepeak.security.config.SecurityConfiguration;
import aspopov.icepeak.security.domain.Role;
import aspopov.icepeak.security.domain.User;
import aspopov.icepeak.security.service.UserService;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.shop.service.BpmService;
import aspopov.icepeak.shop.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkAreaController.class)
@ContextConfiguration(classes = {WorkAreaController.class, SecurityConfiguration.class, AuthenticationProvider.class})
class WorkAreaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @MockBean
    OrderService orderService;

    @MockBean
    BpmService bpmService;

    @MockBean
    private UserService userService;

    @DisplayName("должен корректно авторизовывать пользователей в work-area")
    @ParameterizedTest
    @MethodSource("generateData")
    void shouldCorrectAuthorizeUsersForWorkArea(String authority, ResultMatcher matcher) throws Exception {
        var user = new User();
        var role = new Role();
        role.setRoleName(authority);
        user.setRole(role);
        var orders = new ArrayList<Order>();
        var ordersPage = new PageImpl<Order>(orders);
        given(orderService.search(any(), any())).willReturn(ordersPage);

        given(userService.findByToken(any())).willReturn(Optional.of(user));
        mvc.perform(MockMvcRequestBuilders.get("/api/work-area/orders/search")
                        .header("AUTHORIZATION", "Bearer XXXXXXXXXXXXX"))
                .andExpect(matcher)
        ;
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("ROLE_ANONYMOUS", status().isForbidden()),
                Arguments.of("ROLE_CUSTOMER", status().isForbidden()),
                Arguments.of("ROLE_MANAGER", status().isOk())
        );
    }
}