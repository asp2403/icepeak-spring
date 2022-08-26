package aspopov.batchsampledata.dto;

import javax.persistence.Column;

public class UserDto {
    private long id;
    private int userType;
    private long idRole;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String pwd;

    public UserDto(long id, int userType, long idRole, String name, String surname, String email, String phone, String pwd) {
        this.id = id;
        this.userType = userType;
        this.idRole = idRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.pwd = pwd;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return name + " " +surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
