package sample;

import java.io.Serializable;

abstract public class Character implements Serializable {
    protected int tile_no;
    protected int hp;

    public int getTile_no() {
        return tile_no;
    }

    public int getHp() {
        return hp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }
}
