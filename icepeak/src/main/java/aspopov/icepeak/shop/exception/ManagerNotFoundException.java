package aspopov.icepeak.shop.exception;

public class ManagerNotFoundException extends RuntimeException{
    private long idManager;

    public ManagerNotFoundException(long idManager) {
        super(String.format("Manager not found (%d)", idManager));
        this.idManager = idManager;
    }

    public long getIdManager() {
        return idManager;
    }
}
