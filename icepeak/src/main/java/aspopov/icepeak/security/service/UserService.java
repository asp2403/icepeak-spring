package aspopov.icepeak.security.service;


import aspopov.icepeak.security.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
    Optional<User> findByToken(String token);
    void logout(String username);

}
