package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;

/**
 * Created by Jp on 01/04/2017.
 */

public class ButtonGrid extends Window {

    Drawable gridSquareDrawable;

    public ButtonGrid(String title, Window parent, int x, int y) {
        this(title, parent);
        drawGrid(x,y);
    }

    public ButtonGrid(String title, Window parent, int itemsPerRow) {
        this(title, parent);
        drawGrid(itemsPerRow);
    }

    private ButtonGrid(String title, Window parent) {
        super(title, new Skin(Gdx.files.internal("uiskin.json")));

        this.setX(parent.getX());
        this.setY(parent.getY());
        this.setWidth(parent.getWidth());
        this.setHeight(parent.getHeight());
        this.setMovable(false);
        setVisible(true);


        Label label = this.getTitleLabel();
        label.setColor(Color.LIME);

        defaults().space(8);
        row().fill().expandX();

        gridSquareDrawable = new SpriteDrawable(new Sprite(new Texture("grid_square_template.png")));
    }

    private void drawGrid(int itemsPerRow) {
        int counter = 0;
        for (int i = 0; i < 20; i++ ) {
            if (counter == itemsPerRow) {
                row();
                counter = 0;
            }
            add(new ImageButton(gridSquareDrawable));
            counter ++;
            if (i>0 && i % itemsPerRow == 0) {
                System.out.println(i);
            }
        }
    }

    private void drawGrid(int x, int y) {
        for (int i = 0; i < y; i++ ) {
            for (int j = 0; j < x; j++ ) {
                add(new ImageButton(gridSquareDrawable));
            }
            row();
        }
    }

}
