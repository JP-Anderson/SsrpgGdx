package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import one.jp.ssrpg.GdxGame;
import one.jp.ssrpg.gui.Styles;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;

/**
 * Created by Jp on 11/04/2017.
 */

public class ShipScreenMenuBar {

    private ArrayList<TextButton> buttonIndexer;
    private ShipScreenWindow shipScreenWindow;
    private GdxGame gdxGame;

    // adding the game variable temporarily to make refactoring possible in two commits.
    // TODO refactor enableScreen method in GdxGame to ShipScreenWindow so GdxGame is not needed
    public ShipScreenMenuBar(ShipScreenWindow shipScreenWindow, GdxGame gdxGame) {
        buttonIndexer = new ArrayList<TextButton>();
        this.shipScreenWindow = shipScreenWindow;
        this.gdxGame = gdxGame;
    }

    public HorizontalGroup generateMenuBar(ArrayList<String> options) {
        final HorizontalGroup group = new HorizontalGroup();
        float buttonWidths = 0;
        for (String menuOption : options) {
            final TextButton optionButton = new TextButton(menuOption, Styles.menuButtonStyle());
            optionButton.pad(GdxGame.PADDING);
            group.addActor(optionButton);

            optionButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    for (TextButton tb : buttonIndexer) {
                        tb.setChecked(false);
                    }
                    optionButton.setChecked(true);
                    System.out.println(optionButton.getText());
                    gdxGame.enableScreen(optionButton.getText().toString());
                    shipScreenWindow.changeApplication(optionButton.getText().toString());

                }
            });

            addButton(optionButton);
            buttonWidths += optionButton.getWidth();
        }

        group.setX(GdxGame.WIDTH/2 -(buttonWidths/2) - (GdxGame.PADDING * options.size()));
        group.setY(15);


        System.out.println("group X " + group.getX());

        System.out.println("group Y " + group.getY());

        return group;
    }

    public ArrayList<TextButton> getButtons() {
        ArrayList<TextButton> copy = new ArrayList<TextButton>();
        for (TextButton button : buttonIndexer) {
            copy.add(button);
        }
        return copy;
    }

    private void addButton(TextButton button) {
        buttonIndexer.add(button);
    }

}
