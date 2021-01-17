package engine;

import java.awt.*;

public enum FieldType {
    Corridor,
    Rock,
    Bedrock,
    Lamp;

    public static FieldType random() {
        int choice = Engine.weighedChoice(Assets.WFIELDTYPE);
        return switch (choice) {
            case 0 -> Corridor;
            case 1 -> Rock;
            case 2 -> Bedrock;
            case 3 -> Lamp;
            default -> null;
        };
    }
    public String toString() {
        return switch (this) {
            case Corridor -> "Corridor";
            case Rock -> "Rock";
            case Bedrock -> "Bedrock";
            case Lamp -> "Lamp";
        };
    }
    public Color toColor() {
        return switch (this) {
            case Corridor -> Assets.CCORRIDOR;
            case Rock -> Assets.CROCK;
            case Bedrock -> Assets.CBEDROCK;
            case Lamp -> Assets.CLAMP;
        };
    }
}
