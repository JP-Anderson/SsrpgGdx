package one.jp.ssrpg.gui.loaders;

import java.util.ArrayList;

import arch.sessions.MapSession;
import arch.view.ConsoleIOHandler;
import goods.Goods;
import goods.GoodsForSale;
import goods.GoodsList;
import map.GridMap;
import map.gridsquares.GridSquare;
import ship.PlayerShip;
import util.dataload.csv.CSV;
import util.dataload.csv.CSVReader;

/**
 * Created by Jp on 04/04/2017.
 */

public class Loader {

    public static void loadSomeStuff() {

        CSV csv = CSVReader.readCSV("goods");
        int i = csv.cols + 1;

        PlayerShip playerShip = new PlayerShip.PlayerShipBuilder(new ConsoleIOHandler(), "TestShip",12).build();


        MapSession sesh = new MapSession();
        sesh.start(playerShip);

        ArrayList<ArrayList<GridSquare>> mapSegment = sesh.gridMap();
    }

}
