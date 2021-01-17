package engine;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class DisplayField {
    private final JButton but;
    private final int x;
    private final int y;

    public DisplayField(int x, int y) {
        this.x = x;
        this.y = y;
        but = new JButton();
        but.setBackground(Assets.CDARKNESS);
        but.setBorder(Assets.BNORMAL);
        but.setEnabled(false);
        but.setFocusable(false);
        but.addActionListener(e -> {
            Engine.getMap().onClick(this);
        });
//        but.setText("(" + x + ", " + y + ")");
    }

    public JButton getButton() {
        return but;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void changeIcon(ImageIcon icon) {
        this.but.setIcon(icon);
        this.but.setDisabledIcon(icon);
    }
    public void addIcon(ImageIcon upperIcon) {
        ImageIcon newIcon = Engine.getImages().combine((ImageIcon) this.but.getIcon(), upperIcon);
        this.changeIcon(newIcon);
    }

    public void drawField(Field field) {
        this.but.setBackground(field.getType().toColor());
        if(field.getItem() != null) {
            if((field.getType() == FieldType.Rock || field.getType() == FieldType.Bedrock) && !Engine.getPlayer().hasSonar()) {
                this.changeIcon(null);
            }
            else {
                this.changeIcon(field.getItem().draw());
            }
        }
        else {
            this.changeIcon(null);
        }
    }
    public void hideField(Field field) {
        this.but.setBackground(Assets.CDARKNESS);
        if(Engine.getPlayer().hasRadar() && field.getType() == FieldType.Corridor && field.getItem() != null) {
            this.changeIcon(Engine.getImages().iRadarDetect);
        }
        else {
            this.changeIcon(null);
        }
    }
    public void showCorridorOnMap(Field field) {
        if(field.getType() == FieldType.Corridor) {
            this.but.setBackground(Assets.CDARKCORRIDOR);
        }
    }

    public void setClickable(boolean clickable) {
        if(clickable) {
            but.setBorder(Assets.BCLICKABLE);
            but.setEnabled(true);
        }
        else {
            but.setBorder(Assets.BNORMAL);
            but.setEnabled(false);
        }
    }
}
