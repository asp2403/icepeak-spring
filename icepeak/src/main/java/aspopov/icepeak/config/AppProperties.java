package aspopov.icepeak.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private int cancelOldOrdersInterval;

    public int getCancelOldOrdersInterval() {
        return cancelOldOrdersInterval;
    }

    public void setCancelOldOrdersInterval(int cancelOldOrdersInterval) {
        this.cancelOldOrdersInterval = cancelOldOrdersInterval;
    }
}
