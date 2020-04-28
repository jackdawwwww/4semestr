package Factory.Production;

import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;

import java.util.ArrayList;
import java.util.UUID;

public class Car {
    private Engine engine;
    private Body body;
//    private ArrayList<Accessory> acc;
    private Accessory acc;
    private UUID id;

    public Car(Engine engine, Body body, Accessory acc) {
        this.engine = engine;
        this.body = body;
        this.id = UUID.randomUUID();
        this.acc = acc;
    }

    public UUID getId() { return id; }
    public UUID getEngId() { return engine.getId(); }
    public UUID getBodyId() { return body.getId(); }
//    public ArrayList<UUID> getAccId() {
//        ArrayList<UUID> uuIds = new ArrayList<>();
//
//        for (int i = 0; i < acc.size(); i++) {
//            uuIds.add(acc.get(i).getId());
//        }
//
//        return uuIds;
//    }

}
