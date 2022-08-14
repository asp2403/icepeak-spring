package aspopov.icepeak.security.dto;

import aspopov.icepeak.security.domain.Role;
import aspopov.icepeak.security.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class UserDetailsDto {

    private long id;

    private String roleName;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String authToken;

    public static UserDetailsDto fromDomainObject(User user) {
        return new UserDetailsDto(user.getId(), user.getRole().getRoleName().substring(5), user.getName(), user.getSurname(), user.getEmail(), user.getPhone(), user.getAuthToken());
    }

    public UserDetailsDto(long id, String roleName, String name, String surname, String email, String phone, String authToken) {
        this.id = id;
        this.roleName = roleName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
