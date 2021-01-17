package engine;

import items.Fuel;
import items.Sonar;

import javax.swing.*;

public class Player {
    private int x = 0;
    private int y = 0;
    private int fuel;
    private boolean gameEnded = false;

    private int money = 0;
    private boolean betterPick = false;
    private boolean sonar = false;
    private boolean radar = false;
    private boolean glasses = false;
    private boolean eggplant = false;
    private boolean measure = false;

    private int direction = 0;

    public Player() {
        this.fuel = Assets.STARTFUEL;
    }

    // DISPLAYING THE PLAYER
    public ImageIcon display() {
        return Engine.getImages().iMiner[direction];
    }

    // MOVEMENT AND SIGHT
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getViewRange() {
        if(fuel <= 0) {
            return 0.0;
        }
        double viewRange = fuel * (Assets.MAXVIEW + 0.5 - Assets.MINVIEW) / Assets.STARTFUEL;
        viewRange += Assets.MINVIEW;
        if(viewRange < Assets.MINVIEW) {
            viewRange = Assets.MINVIEW;
        }
        else if(viewRange > Assets.MAXVIEW) {
            viewRange = Assets.MAXVIEW;
        }
        if(hasGlasses()) {
            viewRange += 0.5;
        }
        return viewRange;
    }
    public boolean isVisible(int x, int y) {
        double delta = 0.1;
        return (x * x + y * y <= getViewRange() * getViewRange() + delta);
    }
    public void move(int dir) {
        if(!gameEnded) {
            this.direction = dir;
            int newX = this.x + Assets.deltaX[dir];
            int newY = this.y + Assets.deltaY[dir];
            FieldType taretType = Engine.getMap().getField(newX, newY).getType();
            if (taretType == FieldType.Corridor) {
                x = newX;
                y = newY;
                Engine.walk1();
                alterFuel(-Assets.MOVEMENTCOST);
                Engine.getMap().redraw();
                collectItem(x, y);
            } else if (taretType == FieldType.Lamp) {
                x = newX;
                y = newY;
                Engine.walk1();
                Engine.getMap().redraw();
                collectItem(x, y);
            } else {
                Engine.getMap().getDisplayField(0, 0).drawField(Engine.getMap().getField(getX(), getY()));
                Engine.getMap().getDisplayField(0, 0).addIcon(this.display());
            }
            if(fuel <= 0) {
                endGame();
            }
        }
    }
    private void collectItem(int x, int y) {
        Field targetField = Engine.getMap().getField(x, y);
        if(targetField.getItem() != null) {
            String desc = targetField.getItem().getDescription();
            if(targetField.getItem().isOpen() && desc != null) {
                Engine.getMap().getSide().printInfo(desc);
            }
            if(targetField.getItem().collect()) {
                Engine.getMap().getSide().equip(targetField.getItem());
                targetField.putItem(null);
            }
        }
    }

    // MONEY
    public int getMoney() {
        return money;
    }
    public void giveMoney(int delta) {
        this.money += delta;
        Engine.getMap().getSide().recountStats(money, fuel, hasMeasure());
    }

    // EQUIPMENT
    public int fuelAmount() {
        return fuel;
    }
    public int alterFuel(int delta) {
        this.fuel += delta;
        if(delta > 0) {
            if(fuel > Assets.MAXFUEL) {
                int over = fuel - Assets.MAXFUEL;
                fuel -= over;
                Engine.getMap().getSide().recountStats(money, fuel, hasMeasure());
                return over;
            }
        }
        else if(delta < 0) {
            if(Engine.getMap().getField(getX(), getY()).getItem() instanceof Fuel) {
                Engine.getMap().getField(getX(), getY()).getItem().collect();
            }
            if(fuel < 0) {
                fuel = 0;
            }
        }
        Engine.getMap().getSide().recountStats(money, fuel, hasMeasure());
        return 0;
    }  // returns how much fuel didn't fit in the lamp
    public boolean hasSonar() {
        return sonar;
    }
    public void giveSonar() {
        sonar = true;
    }
    public boolean hasPickaxe() {
        return betterPick;
    }
    public void givePickaxe() {
        this.betterPick = true;
    }
    public boolean hasRadar() {
        return radar;
    }
    public void giveRadar() {
        this.radar = true;
    }
    public boolean hasGlasses() {
        return glasses;
    }
    public void giveGlasses() {
        glasses = true;
    }
    public boolean hasEggplant() {
        return eggplant;
    }
    public void giveEggplant() {
        eggplant = true;
    }
    public boolean hasMeasure() {
        return measure;
    }
    public void giveMeasure() {
        measure = true;
        Engine.getMap().getSide().recountStats(money, fuel, hasMeasure());
    }

    public void endGame() {
        gameEnded = true;
        Map map = Engine.getMap();
        int w = Assets.DISPLAYWIDTH;
        int h = Assets.DISPLAYHEIGHT;
        for(int x=-w/2; x<=w/2; x++) {
            for(int y=-h/2; y<=h/2; y++) {
                map.getDisplayField(x, y).setClickable(false);
            }
        }
        map.showGameOver();
    }
}
