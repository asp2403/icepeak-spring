package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.exception.ProductNotAvailableException;
import aspopov.icepeak.warehouse.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product reserve(Product product, int qty) {
        if (qty <= product.getQtyAvailable()) {
            product.setQtyAvailable(product.getQtyAvailable() - qty);
            product.setQtyReserved(product.getQtyReserved() + qty);
        } else {
            throw new ProductNotAvailableException(product.getId());
        }
        return product;
    }

    @Override
    public Product completeDelivery(Product product, int qty) {
        if (qty <= product.getQtyReserved()) {
            product.setQtyReserved(product.getQtyReserved() - qty);
            product.setQtyAvailable(product.getQtyAvailable() + qty);
        } else {
            throw new ProductNotAvailableException(product.getId());
        }
        return product;
    }

    @Override
    public Product cancelOrder(Product product, int qty) {
        if (qty <= product.getQtyReserved()) {
            product.setQtyReserved(product.getQtyReserved() - qty);
            product.setQtyAvailable(product.getQtyAvailable() + qty);
        } else {
            throw new ProductNotAvailableException(product.getId());
        }
        return product;
    }
}
