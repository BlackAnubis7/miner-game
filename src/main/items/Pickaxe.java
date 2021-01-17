package items;

import engine.Assets;
import engine.Engine;
import engine.Map;

import javax.swing.*;

public class Pickaxe extends ItemHandling {
    public Pickaxe() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public Pickaxe(boolean overrideOpen) {
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iPickaxe;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().givePickaxe();
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
        return "Stronger pickaxe, able to crush even the toughest of rocks.";
    }
}
