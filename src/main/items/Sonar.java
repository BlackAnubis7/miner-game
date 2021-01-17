package items;

import engine.Assets;
import engine.Engine;

import javax.swing.*;

public class Sonar extends ItemHandling {
    public Sonar() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public Sonar(boolean overrideOpen) {
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iSonar;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().giveSonar();
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
        return "A sonar. Using it, you can scan rocks for gems.";
    }
}
