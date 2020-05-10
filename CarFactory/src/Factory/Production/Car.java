package Factory.Production;

import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;

import java.util.ArrayList;
import java.util.UUID;

public class Car {
    private Engine engine;
    private Body body;
    private ArrayList<Accessory> acc;
    private UUID id;

    public Car(Engine engine, Body body, ArrayList<Accessory> acc) {
        this.engine = engine;
        this.body = body;
        this.id = UUID.randomUUID();
        this.acc = acc;
    }

    @Override
    public String toString() {
        StringBuilder ac = new StringBuilder(" ");

        for (Accessory accessory : acc) {
            UUID ui = accessory.getId();
            ac.append(" ").append(ui);
        }

        return " Auto: " + id + "(Body: " + body.getId() +
                ", Engine: " + engine.getId() + ", Accessory:" + ac + ")\n";
    }

}
