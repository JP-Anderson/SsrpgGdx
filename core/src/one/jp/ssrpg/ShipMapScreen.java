package one.jp.ssrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Jp on 01/04/2017.
 */

public class ShipMapScreen extends Window {

    public static final int HEIGHT = 400;
    public static final int WIDTH = 800;

    public ShipMapScreen(String title) {
        super(title, new Skin(Gdx.files.internal("uiskin.json")));
        Texture mapImage = new Texture("mapscreen2.png");
        setBackground(new TextureRegionDrawable(new TextureRegion(mapImage)));
        setHeight(HEIGHT);
        setWidth(WIDTH);
        getTitleLabel().setVisible(false);
    }

}
