package aspopov.icepeak.warehouse.repository.projection;

import aspopov.icepeak.warehouse.domain.Vendor;

public interface ModelShortView {
    long getId();
    short getCategory();
    String getName();
    Vendor getVendor();
    int getPrice();
    byte[] getImageSmall();
}
