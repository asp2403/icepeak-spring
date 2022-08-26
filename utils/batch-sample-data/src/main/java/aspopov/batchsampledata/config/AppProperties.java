package aspopov.batchsampledata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String modelsFile;
    private String vendorsFile;
    private String imagesLarge;
    private String imagesSmall;
    private String skiFile;
    private String bootsFile;
    private String usersFile;


    public String getUsersFile() {
        return usersFile;
    }

    public void setUsersFile(String usersFile) {
        this.usersFile = usersFile;
    }

    public String getModelsFile() {
        return modelsFile;
    }

    public void setModelsFile(String modelsFile) {
        this.modelsFile = modelsFile;
    }

    public String getVendorsFile() {
        return vendorsFile;
    }

    public void setVendorsFile(String vendorsFile) {
        this.vendorsFile = vendorsFile;
    }

    public String getImagesLarge() {
        return imagesLarge;
    }

    public void setImagesLarge(String imagesLarge) {
        this.imagesLarge = imagesLarge;
    }

    public String getImagesSmall() {
        return imagesSmall;
    }

    public void setImagesSmall(String imagesSmall) {
        this.imagesSmall = imagesSmall;
    }

    public String getSkiFile() {
        return skiFile;
    }

    public void setSkiFile(String skiFile) {
        this.skiFile = skiFile;
    }

    public String getBootsFile() {
        return bootsFile;
    }

    public void setBootsFile(String bootsFile) {
        this.bootsFile = bootsFile;
    }
}
