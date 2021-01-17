package items;

import engine.Assets;
import engine.Engine;

import javax.swing.*;

public class MapItem extends ItemHandling {

    public MapItem() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public MapItem(boolean overrideOpen) {
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iMap;
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
        Engine.getMap().lookOnMap();
    }

    public String getDescription() {
        return "A map with a plan of the corridors nearby.";
    }
}
