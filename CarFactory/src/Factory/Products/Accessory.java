package Factory.Products;

import java.util.UUID;

public class Accessory extends Product {
    public Accessory(UUID id) {
        super(id);
        setType(ProductType.Accessory);
    }
}
