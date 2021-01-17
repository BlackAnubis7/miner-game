package items;

import engine.*;

import javax.swing.*;

public class Scanner extends ItemHandling {
    private int usesLeft;

    public Scanner() {
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
        usesLeft = Engine.randInt(Assets.MINSCANNERUSES, Assets.MAXSCANNERUSES);
    }
    public Scanner(boolean overrideOpen, int overrideUses) {
        setOpen(overrideOpen);
        usesLeft = overrideUses;
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iScanner[usesLeft];
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen() && usesLeft > 0) {
            enableInvisible();
        }
        return false;
    }

    public void use() {
        usesLeft -= 1;
        if(usesLeft > 0) {
            enableInvisible();
        }
    }

    private void enableInvisible() {
        int w = Assets.DISPLAYWIDTH;
        int h = Assets.DISPLAYHEIGHT;
        Map map = Engine.getMap();
        Player player = Engine.getPlayer();
        for(int x=-w/2; x<=w/2; x++) {
            for(int y=-h/2; y<=h/2; y++) {
                Field targetField = map.getField(x+player.getX(), y+player.getY());
                if(!(player.isVisible(x, y) || targetField.getType() == FieldType.Lamp || targetField.isAlwaysVisible())) {
                    map.getDisplayField(x, y).setClickable(true);
                }
            }
        }
    }

    public String getDescription() {
        return "With this scanner you can scan some locations of your choice.";
    }
}
