package aspopov.icepeak.shop.exception;

public class ManagerNotAssignedException extends RuntimeException{
    public ManagerNotAssignedException() {
        super("Manager not assigned");
    }
}
