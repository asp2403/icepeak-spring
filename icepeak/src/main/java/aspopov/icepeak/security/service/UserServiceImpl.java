package aspopov.icepeak.security.service;

import aspopov.icepeak.security.domain.User;
import aspopov.icepeak.security.repository.AbstractUserRepository;
import aspopov.icepeak.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<User> login(String username, String password) {
        var user = userRepository.findByEmail(username);
        return user.flatMap(
            u -> {
                var passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(password, u.getPassword())) {
                    var token = UUID.randomUUID().toString();
                    u.setAuthToken(token);
                    userRepository.save(u);
                    return Optional.of(u);
                } else {
                    return Optional.empty();
                }
            }
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByToken(String token) {
        var user = userRepository.findByAuthToken(token);
        return user;
    }

    @Override
    @Transactional
    public void logout(String username) {
        var user = userRepository.findByEmail(username);
        user.ifPresent(
                u -> {
                    u.setAuthToken(null);
                    userRepository.save(u);
                }
        );
    }
}
