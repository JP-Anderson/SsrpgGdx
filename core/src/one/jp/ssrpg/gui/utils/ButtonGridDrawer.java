package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Jp on 13/04/2017.
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

}
