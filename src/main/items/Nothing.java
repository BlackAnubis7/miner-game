package items;

import engine.Engine;

import javax.swing.*;

public class Nothing extends ItemHandling {
    public Nothing() {
        setOpen(false);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iNothing;
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        return false;
    }

    public void use() {

    }

    public String getDescription() {
        return "Empty...";
    }
}
