package items;

import engine.Assets;
import engine.Engine;

import javax.swing.*;

public class Radar extends ItemHandling {
    public Radar() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public Radar(boolean overrideOpen) {
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iRadar;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().giveRadar();
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
        return "A radar. Allows you to detect items where you can't see them.";
    }
}
