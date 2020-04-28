package Factory.Products;

import java.util.UUID;

public class Engine extends Product {
    public Engine(UUID id) {
        super(id);
        setType(ProductType.Engine);
    }
}
