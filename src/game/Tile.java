package game;

import algorithm.graph.Vertex;

public class Tile {

    private BoundingBox boundingBox;
    private Vec2 pos;

    public Tile(Vec2 pos) {
        this.pos = pos;
        setBoundingBox();
    }

    private void setBoundingBox(){
        if(pos!=null){
            boundingBox = new BoundingBox(pos.x, pos.y,pos.x+70,pos.y+70);
        }
    }
    public BoundingBox getBoundingBox(){
        return this.boundingBox;
    }

    public Vec2 getPos() {
        return pos;
    }
}
