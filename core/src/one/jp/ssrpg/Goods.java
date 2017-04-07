package one.jp.ssrpg;

import arch.session.ShipAndCrewCreationSession;
import arch.view.ConsoleIOHandler;
import ship.PlayerShip;

/**
 * Created by Jp on 31/03/2017.
 */

public class Goods {

    public final String name;
    public final String description;
    public final int cost;

    public Goods(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
}
