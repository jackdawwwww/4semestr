package Factory.Products;

import java.util.UUID;

public interface ProductInterface {
    UUID getId();
    void setType(ProductType type);
}
