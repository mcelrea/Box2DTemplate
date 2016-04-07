package com.damien;

import com.badlogic.gdx.physics.box2d.*;

public class Player {
    Body body;
    float xvel;
    float yvel;
    float size;

    public Player(World world, float size) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        CircleShape c = new CircleShape();
        c.setRadius(size);
        fixtureDef.shape = c;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.density = 0.1f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.getFixtureList().first().setUserData("player");
        c.dispose();
    }
}
