package one.jp.ssrpg.gui.windows.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;

import arch.interfaces.MapSessionInterface;
import map.GridPoint;
import map.gridsquares.GridSquare;
import map.gridsquares.OutOfBoundsGridSquare;
import map.gridsquares.Planet;
import one.jp.ssrpg.gui.Assets;

/**
 * Created by Jp on 02/04/2017.
 */

public class MapGridDrawer {

    static final Drawable gridSquareDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_template.png")));
    static final Drawable outOfBoundsDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_out_of_bounds2.png")));
    static final Drawable planetSquareDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_planet.png")));
    static final Drawable playerDrawable = new SpriteDrawable(new Sprite(new Texture("player_indicator.png")));

    static final Skin s = Assets.skin;

    static final int GRID_COLUMN_WIDTH = 53;
    static final int LEFT_BORDER_PADDING = 20;
    static final int MAP_GRID_BOTTOM_PADDING = 80;

    public static void drawGrid(
            Window window, ArrayList<ArrayList<GridSquare>> visibleMap, MapSessionInterface ifc) {
        GridPoint player = getCentrePointOfArrayListOfArrayListOfGridSquares(visibleMap);
        window.clear();
        drawGridTopBorder(window, visibleMap.get(0));
        drawMap(window, visibleMap, ifc, player);
    }

    private static GridPoint getCentrePointOfArrayListOfArrayListOfGridSquares(ArrayList<ArrayList<GridSquare>> lists) {
        int y = lists.size()/2;
        int x = lists.get(0).size()/2;
        return lists.get(y).get(x).gridPoint;
    }

    private static void drawGridTopBorder(Window window, ArrayList<GridSquare> topRow) {
        int xWidth = topRow.size();
        Label padLabel = new Label("",s);
        padLabel.setWidth(LEFT_BORDER_PADDING);
        window.add(padLabel);
        for (int i = 0; i < xWidth; i++) {
            Label l = new Label(""+topRow.get(i).gridPoint.x,s);
            l.setWidth(GRID_COLUMN_WIDTH);
            l.setHeight(10);
            window.add(l);
        }
        window.row();
    }

    private static void drawMap(Window window,
                                ArrayList<ArrayList<GridSquare>> visibleMap,
                                final MapSessionInterface ifc,
                                GridPoint player) {
        for (ArrayList<GridSquare> mapGridRow : visibleMap) {
            drawLeftBorder(mapGridRow.get(0).gridPoint.y, window);
            for (final GridSquare gridSquare : mapGridRow) {
                ImageButton button = createButton(gridSquare, player);
                window.add(button);
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        ifc.tryToTravel(gridSquare.gridPoint);
                    }
                });
            }
            newRowAndApplyPadding(window);
        }
    }

    private static void drawLeftBorder(int row, Window window) {
        Label y = new Label("" + row, s);
        y.setWidth(LEFT_BORDER_PADDING);
        window.add(y);
    }

    private static ImageButton createButton(GridSquare gridSquare, GridPoint player) {
        ImageButton button;
        if (gridSquare instanceof OutOfBoundsGridSquare)
            button = new ImageButton(outOfBoundsDrawable);
        else if (gridSquare instanceof Planet)
            button = new ImageButton(planetSquareDrawable);
        else button = new ImageButton(gridSquareDrawable);
        if (gridSquare.gridPoint.comparePoints(player) == 0) {
            ImageButton playerOverlay = new ImageButton(playerDrawable);
            playerOverlay.setX(button.getX());
            playerOverlay.setY(button.getY());
            button.addActor(playerOverlay);
        }
        return button;
    }

    private static void newRowAndApplyPadding(Window window) {
        window.row();
        window.pad(10, 0, MAP_GRID_BOTTOM_PADDING, 0);
    }

}
