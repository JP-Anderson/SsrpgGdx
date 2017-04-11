package one.jp.ssrpg.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
/**
 * Created by Jp on 11/04/2017.
 */

public class Styles {

    private static TextButtonStyle menuButtonStyle;

    public static TextButtonStyle menuButtonStyle() {
        if (menuButtonStyle == null) {
            menuButtonStyle = new TextButtonStyle();
            menuButtonStyle.font = new BitmapFont();
            menuButtonStyle.fontColor = Color.CYAN;
            menuButtonStyle.checkedFontColor = Color.WHITE;
        }
        return menuButtonStyle;
    }
}
