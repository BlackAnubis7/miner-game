package engine;

import items.*;

public class Field {
    private FieldType type;
    private boolean alwaysVisible = false;
    private Item item = null;

    public Field() {
        this.type = FieldType.random();
        randomiseItem();
    }
    public Field(FieldType overrideType) {
        this.type = overrideType;
        randomiseItem();
    }
    public Field(FieldType overrideType, Item overrideItem) {
        this.type = overrideType;
        this.item = overrideItem;
    }

    public FieldType getType() {
        return type;
    }
    public void setType(FieldType type) {
        this.type = type;
    }
    public boolean isAlwaysVisible() {
        return alwaysVisible;
    }
    public void makeVisible() {
        alwaysVisible = true;
    }

    public boolean mineBlock(boolean hasPickaxe) {
        if(type == FieldType.Rock) {
            setType(FieldType.Corridor);
            return true;
        }
        else if(type == FieldType.Bedrock && hasPickaxe) {
            setType(FieldType.Rock);
            return true;
        }
        else {
            return false;
        }
    }

    public void putItem(Item item) {
        this.item = item;
    }
    public Item getItem() {
        return item;
    }
    public void randomiseItem() {
        if(type == FieldType.Rock) {
            if(Engine.binaryChoice(Assets.GEMINROCKCHANCE)) {
                this.putItem(new Gem(true));
            }
        }
        else if(type == FieldType.Bedrock) {
            if(Engine.binaryChoice(Assets.GEMINBEDROCKCHANCE)) {
                this.putItem(new Gem(true));
            }
        }
        else {
            if(Engine.binaryChoice(Assets.ITEMCHANCE)) {
                int choice = Engine.weighedChoice(Assets.WITEMTYPE);
                this.putItem((Item) switch(choice) {
                    case 0 -> new Eggplant();
                    case 1 -> new Fuel();
                    case 2 -> new Gem();
                    case 3 -> new Glasses();
                    case 4 -> new MapItem();
                    case 5 -> new Measure();
                    case 6 -> new Nothing();
                    case 7 -> new Pickaxe();
                    case 8 -> new Radar();
                    case 9 -> new Scanner();
                    case 10 -> new Sonar();
                    default -> null;
                });
            }
        }
    }
}
