package items;

import engine.Engine;

import javax.swing.*;

public class Eggplant extends ItemHandling {

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iEggplant;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().giveEggplant();
            Engine.getMap().redraw();
            return true;
        }
        else {
            return false;
        }
    }

    public void use() {

    }

    public String getDescription() {
        return "Really? There's less than 0.2% for finding one of these!";
    }
}
