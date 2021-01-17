package items;

import engine.Assets;
import engine.Engine;

import javax.swing.*;

public class Measure extends ItemHandling {
    public Measure() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public Measure(boolean overrideOpen) {
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iMeasure;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().giveMeasure();
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
        return "A measure, which allows to check how much fuel you hae left.";
    }
}
