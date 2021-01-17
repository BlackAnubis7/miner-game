package items;

import engine.*;

import javax.swing.*;

public class Gem extends ItemHandling {
    private final int subtype;

    public Gem() {
        this.subtype = Engine.weighedChoice(Assets.WGEMTYPE);
        setOpen(Engine.binaryChoice(Assets.OPENCHANCE));
    }
    public Gem(int overrideSubtype, boolean overrideOpen) {
        this.subtype = overrideSubtype;
        setOpen(overrideOpen);
    }
    public Gem(boolean overrideOpen) {
        this.subtype = Engine.weighedChoice(Assets.WGEMTYPE);
        setOpen(overrideOpen);
    }

    public ImageIcon draw() {
        if(isOpen()) {
            return Engine.getImages().iGem[this.subtype];
        }
        else {
            return Engine.getImages().iChest;
        }
    }

    public boolean collect() {
        if(isOpen()) {
            Engine.getPlayer().giveMoney(Assets.GEMVALUES[subtype]);
            return true;
        }
        else {
            return false;
        }
    }

    public void use() {

    }

    public String getDescription() {
        //noinspection SwitchStatementWithTooFewBranches
        return switch(subtype) {
            case 4 -> "A diamond! Someone's lucky today...";
            default -> null;
        };
    }
}
