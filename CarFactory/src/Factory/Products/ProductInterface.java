package Factory.Products;

import java.util.UUID;

public interface ProductInterface {
    abstract UUID getId();
    abstract ProductType getType();
    abstract void setType(ProductType type);
}
