package Factory.Products;

import java.util.UUID;

public class ProductFactory {

    public ProductInterface getProduct(ProductType type) {
        UUID id = UUID.randomUUID();
        return switch (type) {
            case Body -> new Body(id);
            case Engine -> new Engine(id);
            case Accessory -> new Accessory(id);
            case Default -> new Product(id);
        };
    }
}
