package one.jp.ssrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import arch.interfaces.MapSessionInterface;
import arch.sessions.MapSession;
import arch.view.ConsoleIOHandler;
import map.GridPoint;
import map.gridsquares.GridSquare;
import one.jp.ssrpg.gui.loaders.Loader;
import one.jp.ssrpg.gui.utils.ShipScreenMenuBar;
import ship.PlayerShip;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;
import one.jp.ssrpg.gui.windows.ShipMapScreen;

import java.util.ArrayList;

public class GdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	Texture img2;
	private Table screen;
	MapSessionInterface sesh;
	ShipMapScreen mapScreen;

	GridPoint lastSeenPoint;

	public static int PADDING = 30;

	ShipScreenMenuBar menuBar;
	ShipScreenWindow shipScreenWindow;

	Stage stage;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;

	float posX;
	float posY;

	String selectedMenuItem;


	public static final int HEIGHT = 400;
	public static final int WIDTH = 800;

	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		font = new BitmapFont();
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.CYAN;
		textButtonStyle.checkedFontColor = Color.WHITE;

		createShipScreen();

		ArrayList<String> menuItems = new ArrayList<String>();
		menuItems.add("MAP");
		menuItems.add("SHIP");
		menuItems.add("CREW");
		menuItems.add("CARGO");
		menuItems.add("MODULES");
		menuItems.add("LAND");

		stage.addActor(menuBar.generateMenuBar(menuItems));

		batch = new SpriteBatch();
		//img = new Texture("space-03.jpg");
		img = new Texture("hst_carina_ngc3372_0006.jpg");
		img2 = new Texture("ship_mock2.png");


		screen = new Table();
		stage.addActor(screen);

		Loader.loadSomeStuff();

	}

	private void createShipScreen() {
		PlayerShip playerShip = new PlayerShip.PlayerShipBuilder(new ConsoleIOHandler(), "TestShip",12).build();
		sesh = new MapSession();
		sesh.start(playerShip);
		ArrayList<ArrayList<GridSquare>> mapSegment = sesh.gridMap();
		lastSeenPoint = getCentrePointOfArrayListOfArrayListOfGridSquares(mapSegment);
		shipScreenWindow = new ShipScreenWindow(new Skin(Gdx.files.internal("uiskin.json")));
		stage.addActor(shipScreenWindow);
		menuBar = new ShipScreenMenuBar(shipScreenWindow, this);
		mapScreen = new ShipMapScreen("Ship Map Screen", sesh);
		mapScreen.drawMap(mapSegment);
		stage.addActor(mapScreen);
		mapScreen.setY(150);
	}

	private GridPoint getCentrePointOfArrayListOfArrayListOfGridSquares(ArrayList<ArrayList<GridSquare>> lists) {
		int y = lists.size();
		int x = lists.get(0).size();
		return new GridPoint(x/2,y/2);
	}

	public void enableScreen(String text) {
		stage.getActors().get(stage.getActors().indexOf(screen, true)).remove();
		screen = new Table();
		screen.setX(40);
		screen.setY(28);
		screen.setWidth(720);
		screen.setHeight(135);

		Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGB565);
		pm1.setColor(Color.BLACK);
		pm1.fill();


		screen.setVisible(true);
		screen.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm1))));

		if (selectedMenuItem != text) {
			selectedMenuItem = text;


			if (text.equals("MAP")) {
				TextButton optionButton = new TextButton("Map screen!", textButtonStyle);
				screen.add(optionButton);
			}
			if (text.equals("SHIP")) {
				TextButton optionButton = new TextButton("Ship status and overview", textButtonStyle);
				screen.add(optionButton);
			}
			if (text.equals("CREW")) {
				TextButton optionButton = new TextButton("Crew stats screen!", textButtonStyle);
				screen.add(optionButton);
			}
			if (text.equals("CARGO")) {
				TextButton optionButton = new TextButton("Cargo hold", textButtonStyle);
				screen.add(optionButton);
			}
			stage.addActor(screen);
		}

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 0.1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, 0, 0);
		batch.end();
		if (sesh.changes()) mapScreen.drawMap(sesh.gridMap());
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


			for (TextButton tb : menuBar.getButtons()) {
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
