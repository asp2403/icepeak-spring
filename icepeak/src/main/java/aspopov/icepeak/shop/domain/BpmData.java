package aspopov.icepeak.shop.domain;

import org.springframework.lang.Nullable;

public class BpmData {
    @Nullable
    private final BpmAction nextAction;

    @Nullable
    private final BpmAction prevAction;


    public BpmData(@Nullable BpmAction prevAction, @Nullable BpmAction nextAction) {
        this.nextAction = nextAction;
        this.prevAction = prevAction;
    }

    @Nullable
    public BpmAction getNextAction() {
        return nextAction;
    }

    @Nullable
    public BpmAction getPrevAction() {
        return prevAction;
    }
}
