package aspopov.icepeak.security.dto;

import aspopov.icepeak.security.domain.Manager;

public class ManagerDto {
    private long id;

    private String fullName;

    public ManagerDto(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public static ManagerDto fromDomain(Manager manager) {
        return new ManagerDto(manager.getId(), manager.getFullName());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
