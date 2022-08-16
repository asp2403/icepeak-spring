package aspopov.icepeak.shop.dto;

import org.springframework.lang.Nullable;

public class OrderSearchParams {

    @Nullable
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
