package one.jp.ssrpg.gui.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import arch.interfaces.TradeSessionInterface;
import one.jp.ssrpg.gui.Styles;

/**
 * Created by Jp on 17/04/2017.
 */

public class ShipTradeScreen extends SsrpgWindow {

    public ShipTradeScreen() {
        super("", new Skin(Gdx.files.internal("uiskin.json")));
        setToStandardWindowSize();
        getTitleLabel().setVisible(false);
        setMovable(false);
        setColor(Color.BLACK);
        add(new TextButton("BUY", Styles.menuButtonStyle()));
        add(new TextButton("SELL", Styles.menuButtonStyle()));
    }

}
