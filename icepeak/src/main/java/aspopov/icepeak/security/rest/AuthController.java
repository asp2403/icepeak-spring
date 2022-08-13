package aspopov.icepeak.security.rest;

import aspopov.icepeak.security.dto.UserDetailsDto;
import aspopov.icepeak.security.dto.UserDto;
import aspopov.icepeak.security.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    public UserDetailsDto getToken(@RequestBody UserDto userDto) {
        var user = userService.login(userDto.getUsername(), userDto.getPassword());
        return user.map(UserDetailsDto::fromDomainObject).orElseThrow(() -> new UsernameNotFoundException("Invalid login or password"));
    }

    @PostMapping("/api/auth/logout")
    public void logout() {
        var userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        userService.logout(userDetails.getUsername());
    }
}
