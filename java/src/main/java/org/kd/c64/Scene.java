package org.kd.c64;

public abstract class Scene {

    protected String id;

    public Scene(String id){
        this.id = id;
    }

    public abstract void create();
    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
