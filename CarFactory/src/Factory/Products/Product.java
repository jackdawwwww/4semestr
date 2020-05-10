package Factory.Products;

import java.util.UUID;

public class Product implements ProductInterface {
    private UUID id;
    private ProductType type;

    public Product(UUID id) {
        this.id = id;
        this.type = ProductType.Default;
    }

    @Override

    public String toString() {
        return id + " _ " + type;
    }

    public UUID getId(){
        return id;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}

