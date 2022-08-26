package aspopov.icepeak.shop.service;

import aspopov.icepeak.warehouse.domain.Product;

public interface ProductService {
    Product reserve(Product product, int qty);
    Product completeDelivery(Product product, int qty);
    Product cancelOrder(Product product, int qty);
}
