package aspopov.batchsampledata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String skiModelsFile;
    private String vendorsFile;

    public String getSkiModelsFile() {
        return skiModelsFile;
    }

    public void setSkiModelsFile(String skiModelsFile) {
        this.skiModelsFile = skiModelsFile;
    }

    public String getVendorsFile() {
        return vendorsFile;
    }

    public void setVendorsFile(String vendorsFile) {
        this.vendorsFile = vendorsFile;
    }
}
