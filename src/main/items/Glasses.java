package items;

import engine.Assets;
import engine.Engine;

import javax.swing.*;

public class Glasses extends ItemHandling {

    public Glasses() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public Glasses(boolean overrideOpen) {
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iGlasses;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().giveGlasses();
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
        return "Glasses. Better sight. Isn't it straightforward?";
    }
}
