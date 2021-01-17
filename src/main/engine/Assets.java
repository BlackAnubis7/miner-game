package engine;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Assets {
    public static int DISPLAYWIDTH = 17;  // has to be odd
    public static int DISPLAYHEIGHT = 13;  // has to be odd

    public static int[] deltaX = {0, 1, 0, -1};
    public static int[] deltaY = {1, 0, -1, 0};

    // colors
    public static Color CDARKNESS = new Color(0, 0, 0);
    public static Color CROCK = new Color(78, 78, 78);
    public static Color CBEDROCK = new Color(47, 47, 47);
    public static Color CLAMP = new Color(203, 159, 14);
    public static Color CCORRIDOR = new Color(64, 49, 39);
    public static Color CDARKCORRIDOR = new Color(21, 16, 11);
    public static Color CBACKGROUND = CDARKNESS;
    public static Color CINFOFONT = new Color(255, 255, 255);

    // borders
    public static Color CBORDER = new Color(12, 12, 12);
    public static LineBorder BNORMAL = new LineBorder(CBORDER, 1);
    public static Color CCLICKABLE = new Color(182, 9, 9);
    public static LineBorder BCLICKABLE = new LineBorder(CCLICKABLE, 3);
    public static Color CINVENTORYBORDER = new Color(97, 97, 97);
    public static Border BINVENTORYBORDER = new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(CINVENTORYBORDER, 3));

    public static int[] GEMVALUES = {100, 200, 500, 1000, 3000};

    // weighs
    public static int[] WFIELDTYPE = {50, 25, 4, 1};  // corridor, rock, bedrock, lamp
    public static int[] WGEMTYPE = {30, 15, 6, 3, 1};
    public static int[] WFUELLOAD = {0, 10, 20, 30, 40, 20};
    public static int[] WITEMTYPE = {
            1,  // Eggplant
            100,  // Fuel
            400,  // Gem
            50,  // Glasses
            10,  // MapItem
            20,  // Measure
            40,  // Nothing
            10,  // Pickaxe
            10,  // Radar
            10,  // Scanner
            10   // Sonar
    };

    // chances
    public static int OPENCHANCE = 65;  // default: 65
    public static int ITEMCHANCE = 5;  // default: 5
    public static int GEMINROCKCHANCE = 10;  // default: 10
    public static int GEMINBEDROCKCHANCE = 25;  // default: 25

    // game data
    public static final double MINVIEW = 1.0;  // default: 1.0
    public static final double MAXVIEW = 5.0;  // default: 5.0
    public static final int MOVEMENTCOST = 1;  // default: 1
    public static final int DIGCOST = 1;  // default: 1
    public static final int OPENCOST = 1;  // default: 1
    public static final int STARTFUEL = 270;  // default: 270
    public static final int MAXFUEL = 300;  // default: 300
    public static final int MINSCANNERUSES = 2;  // default: 2
    public static final int MAXSCANNERUSES = 10;  // default: 10, maximum: 10
    public static final int FUELTANKSIZE = countFairFuel() / 4;  // default: countFairFuel() / 4

    // methods
    public static int countFairFuel() {
        double fuelChanceDiv = 0;
        double valueChanceDiv = 0;
        for(int chance : WITEMTYPE) {
            fuelChanceDiv += chance;
        }
        for(int chance : WFUELLOAD) {
            valueChanceDiv += chance;
        }
        double sum = 0;
        for(int i=0; i<=5; i++) {
            sum += i * WFUELLOAD[i] / valueChanceDiv;
        }
        double efx5 = 5.0 * MOVEMENTCOST;
        double fuelChance = WITEMTYPE[1] / fuelChanceDiv;
        double itemChance = ITEMCHANCE / 100.0;
        double targetF = efx5 / (itemChance * fuelChance * sum);
        targetF = 10 * Math.ceil(targetF / 10);
        return (int) targetF;
    }
}
