package com.damien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 4/7/2016.
 */
public class GameplayScreen implements Screen {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    SpriteBatch batch;
    Body floor;
    BitmapFont font;
    Player player;

    @Override
    public void show() {
        world = new World(new Vector2(0,-9.8f),true);
        world.setContactFilter(new MyContactFilter());
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();

        createFloor();
        player = new Player(world, 0.5f);
    }

    private void createFloor() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        ChainShape c = new ChainShape();
        c.createChain(new Vector2[]{new Vector2(-20,-5), new Vector2(20,-5)});
        fixtureDef.shape = c;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        floor = world.createBody(bodyDef);
        floor.createFixture(fixtureDef);
        floor.getFixtureList().first().setUserData("floor");
        c.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(0, 0, 0);
        camera.update();

        world.step(1 / 60f, 8, 3);

        batch.begin();
        font.draw(batch, "Text", 5, 590);
        batch.end();

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/25;
        camera.viewportHeight = height/25;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
