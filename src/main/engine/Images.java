package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Images {
    private final int fieldSize;
    private final String imagePath = "images/";

    public final ImageIcon iChest;
    public final ImageIcon iSonar;
    public final ImageIcon iPickaxe;
    public final ImageIcon[] iMiner = {null, null, null, null};
    public final ImageIcon[] iGem = {null, null, null, null, null};
    public final ImageIcon[] iScanner = {null, null, null, null, null, null, null, null, null, null, null};
    public final ImageIcon[] iFuel = {null, null, null, null, null, null};
    public final ImageIcon iRadarDetect;
    public final ImageIcon iEggplant;
    public final ImageIcon iNothing;
    public final ImageIcon iRadar;
    public final ImageIcon iGlasses;
    public final ImageIcon iMeasure;
    public final ImageIcon iMap;
    public final ImageIcon iLetA;
    public final ImageIcon iLetE;
    public final ImageIcon iLetG;
    public final ImageIcon iLetM;
    public final ImageIcon iLetO;
    public final ImageIcon iLetR;
    public final ImageIcon iLetV;

    public Images(int fieldSize) {
        this.fieldSize = fieldSize;
        iChest = getResized(imagePath + "chest.png", fieldSize);
        iPickaxe = getResized(imagePath + "pickaxe.png", fieldSize);
        iMiner[0] = getResized(imagePath + "miner_up.png", fieldSize);
        iMiner[1] = getResized(imagePath + "miner_right.png", fieldSize);
        iMiner[2] = getResized(imagePath + "miner_down.png", fieldSize);
        iMiner[3] = getResized(imagePath + "miner_left.png", fieldSize);
        iGem[0] = getResized(imagePath + "gem0.png", fieldSize);
        iGem[1] = getResized(imagePath + "gem1.png", fieldSize);
        iGem[2] = getResized(imagePath + "gem2.png", fieldSize);
        iGem[3] = getResized(imagePath + "gem3.png", fieldSize);
        iGem[4] = getResized(imagePath + "gem4.png", fieldSize);
        iSonar = getResized(imagePath + "sonar.png", fieldSize);
        iScanner[0] = getResized(imagePath + "scanner0.png", fieldSize);
        iScanner[1] = getResized(imagePath + "scanner1.png", fieldSize);
        iScanner[2] = getResized(imagePath + "scanner2.png", fieldSize);
        iScanner[3] = getResized(imagePath + "scanner3.png", fieldSize);
        iScanner[4] = getResized(imagePath + "scanner4.png", fieldSize);
        iScanner[5] = getResized(imagePath + "scanner5.png", fieldSize);
        iScanner[6] = getResized(imagePath + "scanner6.png", fieldSize);
        iScanner[7] = getResized(imagePath + "scanner7.png", fieldSize);
        iScanner[8] = getResized(imagePath + "scanner8.png", fieldSize);
        iScanner[9] = getResized(imagePath + "scanner9.png", fieldSize);
        iScanner[10] = getResized(imagePath + "scanner10.png", fieldSize);
        iRadarDetect = getResized(imagePath + "radar_detect.png", fieldSize);
        iEggplant = getResized(imagePath + "eggplant.png", fieldSize);
        iNothing = getResized(imagePath + "nothing.png", fieldSize);
        iRadar = getResized(imagePath + "radar.png", fieldSize);
        iGlasses = getResized(imagePath + "glasses.png", fieldSize);
        iFuel[0] = getResized(imagePath + "fuel0.png", fieldSize);
        iFuel[1] = getResized(imagePath + "fuel20.png", fieldSize);
        iFuel[2] = getResized(imagePath + "fuel40.png", fieldSize);
        iFuel[3] = getResized(imagePath + "fuel60.png", fieldSize);
        iFuel[4] = getResized(imagePath + "fuel80.png", fieldSize);
        iFuel[5] = getResized(imagePath + "fuel100.png", fieldSize);
        iMeasure = getResized(imagePath + "measure.png", fieldSize);
        iMap = getResized(imagePath + "map.png", fieldSize);
        iLetA = getResized(imagePath + "letter_a.png", fieldSize);
        iLetE = getResized(imagePath + "letter_e.png", fieldSize);
        iLetG = getResized(imagePath + "letter_g.png", fieldSize);
        iLetM = getResized(imagePath + "letter_m.png", fieldSize);
        iLetO = getResized(imagePath + "letter_o.png", fieldSize);
        iLetR = getResized(imagePath + "letter_r.png", fieldSize);
        iLetV = getResized(imagePath + "letter_v.png", fieldSize);
    }

    private static ImageIcon getFromPath(String path) {
        return new ImageIcon(Images.class.getResource(path));
    }

    public static ImageIcon getResized(ImageIcon nativeIcon, int size) {
        Image nativeImg = nativeIcon.getImage();
        Image resizedImg = nativeImg.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    public static ImageIcon getResized(String path, int size) {
        ImageIcon nativeIcon = getFromPath(path);
        return getResized(nativeIcon, size);
    }

    public ImageIcon combine(ImageIcon lower, ImageIcon upper) {
        if(lower == null) {
            return upper;
        }
        else if(upper == null) {
            return lower;
        }
        else {
            Image under = lower.getImage();
            Image over = upper.getImage();
            BufferedImage newImage = new BufferedImage(fieldSize, fieldSize, TYPE_INT_ARGB);
            Graphics2D g2 = newImage.createGraphics();
            g2.drawImage(under, 0, 0, null);
            g2.drawImage(over, 0, 0, null);
            g2.dispose();
            return new ImageIcon(newImage);
        }
    }
}
