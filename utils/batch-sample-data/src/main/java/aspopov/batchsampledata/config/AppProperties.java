package aspopov.batchsampledata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String skiModelsFile;
    private String vendorsFile;
    private String skiImagesLarge;
    private String skiImagesSmall;

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

    public String getSkiImagesLarge() {
        return skiImagesLarge;
    }

    public void setSkiImagesLarge(String skiImagesLarge) {
        this.skiImagesLarge = skiImagesLarge;
    }

    public String getSkiImagesSmall() {
        return skiImagesSmall;
    }

    public void setSkiImagesSmall(String skiImagesSmall) {
        this.skiImagesSmall = skiImagesSmall;
    }
}
