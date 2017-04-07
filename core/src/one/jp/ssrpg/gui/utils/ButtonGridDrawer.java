package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;

import arch.interfaces.MapSessionInterface;
import map.GridPoint;
import map.gridsquares.EmptyGridSquare;
import map.gridsquares.GridSquare;
import map.gridsquares.OutOfBoundsGridSquare;
import map.gridsquares.Planet;

/**
 * Created by Jp on 02/04/2017.
 */

public class ButtonGridDrawer {

    public static void drawGrid(Window window, int x, int y) {
        Drawable gridSquareDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_template.png")));
        for (int i = 0; i < y; i++ ) {
            for (int j = 0; j < x; j++ ) {
                window.add(new ImageButton(gridSquareDrawable));
            }
            window.row();
        }
    }

    public static void drawGrid(
            Window window,
            ArrayList<ArrayList<GridSquare>> visibleMap,
            final MapSessionInterface ifc) {
        GridPoint player = getCentrePointOfArrayListOfArrayListOfGridSquares(visibleMap);
        Drawable gridSquareDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_template.png")));
        Drawable outOfBoundsDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_out_of_bounds2.png")));
        Drawable planetSquareDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_planet.png")));
        Drawable playerDrawable = new SpriteDrawable(new Sprite(new Texture("player_indicator.png")));
        window.clear();
        ArrayList<GridSquare> topRow = visibleMap.get(0);
        int xWidth = topRow.size();
        Skin s = new Skin(Gdx.files.internal("uiskin.json"));
        Label padLabel = new Label("",s);
        padLabel.setWidth(20);
        window.add(padLabel);
        for (int i = 0; i < xWidth; i++) {
            Label l = new Label(""+topRow.get(i).gridPoint.x,s);
            l.setWidth(53);
            l.setHeight(10);
            window.add(l);
        }
        window.row();
        for (ArrayList<GridSquare> mapGridRow : visibleMap) {
            Label y = new Label(""+mapGridRow.get(0).gridPoint.y,s);
            y.setWidth(20);
            window.add(y);
            for (final GridSquare gridSquare : mapGridRow) {
                ImageButton button;
                if (gridSquare instanceof OutOfBoundsGridSquare) button = new ImageButton(outOfBoundsDrawable);
                else if (gridSquare instanceof Planet) button = new ImageButton(planetSquareDrawable);
                else button = new ImageButton(gridSquareDrawable);
                if (gridSquare.gridPoint.comparePoints(player) == 0) {
                    ImageButton playerOverlay = new ImageButton(playerDrawable);
                    playerOverlay.setX(button.getX());
                    playerOverlay.setY(button.getY());
                    button.addActor(playerOverlay);
                }
                window.add(button);
                button.addListener(new ClickListener() {
                    @Override public void clicked(InputEvent event, float x, float y) {
                        // When you click the button it will print this value you assign.
                        // That way you will know 'which' button was clicked and can perform
                        // the correct action based on it.
                        ifc.tryToTravel(gridSquare.gridPoint);
                    };
                });
            }
            window.row();
            window.pad(10, 0, 80, 0);
        }
    }

    private static GridPoint getCentrePointOfArrayListOfArrayListOfGridSquares(ArrayList<ArrayList<GridSquare>> lists) {
        int y = lists.size()/2;
        int x = lists.get(0).size()/2;
        return lists.get(y).get(x).gridPoint;
    }

}
