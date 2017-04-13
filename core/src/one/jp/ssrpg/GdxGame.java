package one.jp.ssrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import map.GridPoint;
import map.gridsquares.GridSquare;
import one.jp.ssrpg.gui.states.MainGameState;

import java.util.ArrayList;

public class GdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	Texture img2;

	Stage stage;
	MainGameState mainGameState;

	public static final int HEIGHT = 400;
	public static final int WIDTH = 800;
	public static int PADDING = 30;

	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		mainGameState = new MainGameState(stage);
		mainGameState.createShipScreen();

		batch = new SpriteBatch();
		//img = new Texture("space-03.jpg");
		img = new Texture("hst_carina_ngc3372_0006.jpg");
		img2 = new Texture("ship_mock2.png");
	}

	private GridPoint getCentrePointOfArrayListOfArrayListOfGridSquares(ArrayList<ArrayList<GridSquare>> lists) {
		int y = lists.size();
		int x = lists.get(0).size();
		return new GridPoint(x/2,y/2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 0.1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, 0, 0);
		batch.end();
		mainGameState.render();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		img2.dispose();
		stage.dispose();
	}

	//region InputProcessor

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT){
			System.out.println();
			System.out.println("screenX " + screenX + "    WIDTH " + WIDTH);
			System.out.println("screenY " + (screenY) + "    HEIGHT " + HEIGHT);


			for (TextButton tb : mainGameState.menuButtons()) {
				Vector2 buttonVector = getStageLocation(tb);
				System.out.print(buttonVector);
				if (isInRange(screenX, (int)(buttonVector.x + tb.getWidth()/2),PADDING))
					if (isInRange(screenY, (int)(buttonVector.y + tb.getHeight()/2),PADDING)) {
						System.out.println("in range of " + tb.getText());
					}

			}
		}
		return false;
	}

	public Vector2 getStageLocation(Actor actor) {
		return actor.localToStageCoordinates(new Vector2(0, HEIGHT));
	}

	private boolean isInRange(int testNumber, int target, int range) {
		return target-range <= testNumber && testNumber <= target+range;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	//endregion

}
