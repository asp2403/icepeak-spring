package aspopov.icepeak.shop.exception;

public class UserNotFoundException extends RuntimeException{
    private long idUser;

    public UserNotFoundException(long idUser) {
        super(String.format("User not found (%d)", idUser));
        this.idUser = idUser;
    }

    public long getIdUser() {
        return idUser;
    }
}
