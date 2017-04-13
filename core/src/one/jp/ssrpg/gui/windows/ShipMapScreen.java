package one.jp.ssrpg.gui.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import arch.interfaces.MapSessionInterface;
import map.gridsquares.GridSquare;
import one.jp.ssrpg.gui.utils.MapGridDrawer;

/**
 * Created by Jp on 01/04/2017.
 */

public class ShipMapScreen extends SsrpgWindow {

    public static final int HEIGHT = 400;
    public static final int WIDTH = 800;

    private final MapSessionInterface mapSessionInterface;

    public ShipMapScreen(String title, MapSessionInterface mapSessionInterface) {
        super(title, new Skin(Gdx.files.internal("uiskin.json")));
        Texture mapImage = new Texture("mapscreen2.png");
        setBackground(new TextureRegionDrawable(new TextureRegion(mapImage)));
        setHeight(HEIGHT);
        setWidth(WIDTH);
        getTitleLabel().setVisible(false);
        this.mapSessionInterface = mapSessionInterface;
    }

    public void drawMap(ArrayList<ArrayList<GridSquare>> mapSegment) {
        MapGridDrawer.drawGrid(this, mapSegment, mapSessionInterface);
    }

}
