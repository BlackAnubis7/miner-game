package engine;

import items.MapItem;
import items.Scanner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map {

    private final ArrayList<Row> pos;
    private final ArrayList<Row> neg;
    private final DisplayField[] b;
    private final SideMenu side = new SideMenu();

    public Map() {
        pos = new ArrayList<>();
        neg = new ArrayList<>();
        int width = Assets.DISPLAYWIDTH;
        int height = Assets.DISPLAYHEIGHT;

        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(height, width));
        mapPanel.setBackground(Assets.CBACKGROUND);
        b = new DisplayField[height * width];
        for(int i = 0; i< height * width; i++) {
            b[i] = new DisplayField((i%width)-width/2, height/2-(i/width));
            mapPanel.add(b[i].getButton());
        }

        JFrame f = new JFrame("The Miner");
        f.setLayout(new BorderLayout());
        f.addKeyListener(new KeyListener());
        f.add(mapPanel, BorderLayout.CENTER);
        f.add(side.getPanel(), BorderLayout.LINE_END);
        f.setSize(1000 * width / height + 200,1000);
        f.getContentPane().setBackground(Assets.CBACKGROUND);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Row getRow(int y) {
        if(y < 0) {
            while(-y > neg.size()) {
                Row df = new Row(1,1);
                neg.add(df);
            }
            return neg.get(-y-1);
        }
        else {
            while(y > pos.size() - 1) {
                Row df = new Row(1, 1);
                pos.add(df);
            }
            return pos.get(y);
        }
    }

    public Field getField(int x, int y) {
        return this.getRow(y).getField(x);
    }

    public DisplayField getDisplayField(int x, int y) {
        int w = Assets.DISPLAYWIDTH;
        int h = Assets.DISPLAYHEIGHT;
        return this.b[w * (h / 2 - y) + x + w / 2];
    }
    public DisplayField getDisplayField(int index) {
        return this.b[index];
    }

    public SideMenu getSide() {
        return side;
    }

    public void onClick(DisplayField dfield) {
        int x = dfield.getX();
        int y = dfield.getY();
        Player player = Engine.getPlayer();
        Field targetField = getField(x+player.getX(), y+player.getY());
        Field playerField = getField(player.getX(), player.getY());
        if(Math.abs(x) + Math.abs(y) == 1) {
            if(targetField.mineBlock(player.hasPickaxe())) {
                player.alterFuel(-Assets.DIGCOST);
            }
            else if(targetField.getItem() != null) {
                if(targetField.getItem().open()) {
                    player.alterFuel(-Assets.OPENCOST);
                }
            }
            this.redraw();
            if(playerField.getItem() instanceof Scanner) {
                playerField.getItem().collect();
            }
        } // one of the neighbouring fields
        else if(!(x == 0 && y == 0)) {
            targetField.makeVisible();
            this.redraw();
            if(playerField.getItem() instanceof Scanner) {
                playerField.getItem().use();
            }
        }
        if(player.fuelAmount() <= 0) {
            player.endGame();
        }
    }

    public void redraw() {
        Player player = Engine.getPlayer();
        int w = Assets.DISPLAYWIDTH;
        int h = Assets.DISPLAYHEIGHT;
        for(int x=-w/2; x<=w/2; x++) {
            for(int y=-h/2; y<=h/2; y++) {
                getDisplayField(x, y).setClickable(false);
//                getDisplayField(x, y).getButton().setText("(" + x + ", " + y + ")");  // write
//                getDisplayField(x, y).getButton().setText("(" + (x+player.getX()) + ", " + (y+player.getY()) + ")");
                Field targetField = getField(x+player.getX(), y+player.getY());
                if(player.isVisible(x, y) || targetField.getType() == FieldType.Lamp || targetField.isAlwaysVisible()) {
                    getDisplayField(x, y).drawField(targetField);
                }
                else {
                    getDisplayField(x, y).hideField(targetField);
                }
            }
        }
        for(int i=0; i<4; i++) {
            int x = Assets.deltaX[i];
            int y = Assets.deltaY[i];
            Field field = getField(x+player.getX(), y+player.getY());
            if(field.getType() == FieldType.Rock || (field.getType() == FieldType.Bedrock && player.hasPickaxe())) {
                getDisplayField(x, y).setClickable(true);
            }
            else if(field.getItem() != null && !field.getItem().isOpen()) {
                getDisplayField(x, y).setClickable(true);
            }
        }
        getDisplayField(0, 0).addIcon(player.display());
        if(getField(player.getX(), player.getY()).getItem() instanceof MapItem) {
            getField(player.getX(), player.getY()).getItem().collect();
        }
    }
    public void lookOnMap() {
        Player player = Engine.getPlayer();
        int w = Assets.DISPLAYWIDTH;
        int h = Assets.DISPLAYHEIGHT;
        for(int x=-w/2; x<=w/2; x++) {
            for (int y=-h/2; y<=h/2; y++) {
                Field targetField = getField(x+player.getX(), y+player.getY());
                if (!player.isVisible(x, y) && targetField.getType() != FieldType.Lamp && !targetField.isAlwaysVisible()) {
                    getDisplayField(x, y).showCorridorOnMap(targetField);
                }
            }
        }
    }

    public void showGameOver() {
        side.setDefaultMessage("GAME OVER!\nYou've walked:\n  " + Engine.getFormattedWalkDistance());
        side.printInfo();
        int w = Assets.DISPLAYWIDTH;
        Images img = Engine.getImages();
        for(int x=-w/2; x<=w/2; x++) {
            if(x != 0) {
                getDisplayField(x, 0).getButton().setBackground(Assets.CDARKNESS);
                getDisplayField(x, 0).changeIcon((ImageIcon) switch(x) {
                    case -5 -> img.iLetG;
                    case -4 -> img.iLetA;
                    case -3 -> img.iLetM;
                    case -2, 4 -> img.iLetE;
                    case 2 -> img.iLetO;
                    case 3 -> img.iLetV;
                    case 5 -> img.iLetR;
                    default -> null;
                });
            }
        }
    }
}
