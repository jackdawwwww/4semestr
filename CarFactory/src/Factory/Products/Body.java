package Factory.Products;

import java.util.UUID;

public class Body extends Product {
    public Body(UUID id) {
        super(id);
        setType(ProductType.Body);
    }
}
