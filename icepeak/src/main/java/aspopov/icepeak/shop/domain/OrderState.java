package aspopov.icepeak.shop.domain;

public final class OrderState {

    public static final int NEW = 1;

    public static final int PREPARING = 2;

    public static final int READY = 3;

    public static final int COMPLETE = 4;

    public static final int CANCELLED = 5;
}
