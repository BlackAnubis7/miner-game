package items;

import engine.Assets;
import engine.Engine;

import javax.swing.*;

public class Fuel extends ItemHandling {
    private int fuelLeft;

    public Fuel() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
        int load = Engine.weighedChoice(Assets.WFUELLOAD);
        fuelLeft = (Assets.FUELTANKSIZE * load) / 5;
    }
    public Fuel(boolean overrideOpen, int overrideFuel) {
        setOpen(overrideOpen);
        fuelLeft = overrideFuel;
    }

    public ImageIcon draw() {
        if(isOpen()) {
            double percent = 100.0 * fuelLeft / Assets.FUELTANKSIZE;
            if(percent <= 0) return Engine.getImages().iFuel[0];
            else if(percent <= 20) return Engine.getImages().iFuel[1];
            else if(percent <= 40) return Engine.getImages().iFuel[2];
            else if(percent <= 60) return Engine.getImages().iFuel[3];
            else if(percent <= 80) return Engine.getImages().iFuel[4];
            else return Engine.getImages().iFuel[5];
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            this.use();
        }
        return false;
    }

    public void use() {
        fuelLeft = Engine.getPlayer().alterFuel(fuelLeft);
        Engine.getMap().redraw();
    }

    public String getDescription() {
        return null;
    }
}
