package aspopov.icepeak.shop.exception;

public class WrongOrderStateException extends RuntimeException{
    private int state;

    public WrongOrderStateException(int state) {
        super(String.format("Wrong state (%d)", state));
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
