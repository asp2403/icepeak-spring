package aspopov.batchsampledata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String vendorsFile;

    public String getVendorsFile() {
        return vendorsFile;
    }

    public void setVendorsFile(String vendorsFile) {
        this.vendorsFile = vendorsFile;
    }
}
