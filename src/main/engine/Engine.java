package engine;

import items.*;

import java.util.Random;
import java.util.Scanner;

public class Engine {
    private static Random generator;
    private static Player player = null;
    private static Map map = null;
    private static Images images = null;

    private static int walkDistance = 0;

    public static void initGen() {
        generator = new Random();
    }
    public static void initGen(long seed) {
        generator = new Random(seed);
    }

    public static void initPlayer() {
        player = new Player();
    }
    public static Player getPlayer() {
        if(player == null) {
            initPlayer();
        }
        return player;
    }

    public static void initMap() {
        map = new Map();
        for(int x=-1; x<=1; x++) for(int y=-1; y<=1; y++) {
            map.getField(x, y).setType(FieldType.Corridor);
            map.getField(x, y).putItem(null);
        }
        map.getField(0, 0).setType(FieldType.Lamp);
        map.redraw();
    }
    public static Map getMap() {
        if(map == null) {
            initMap();
        }
        return map;
    }

    public static void initImages() {
        images = new Images(60);
    }
    public static Images getImages() {
        if(images == null) {
            initImages();
        }
        return images;
    }

    public static int randInt(int min, int max) {
        int range = max - min + 1;
        return (Math.abs(generator.nextInt()) % range) + min;
    }
    public static int weighedChoice(int[] chances) {
        int chanceSum = 0;
        for(int chance : chances) chanceSum += chance;
        int choice = Engine.randInt(1, chanceSum);
        for(int i=0; i<chances.length; i++) {
            if(choice <= chances[i]) {
                return i;
            }
            else {
                choice -= chances[i];
            }
        }
        return chances.length - 1;
    }
    public static boolean binaryChoice(int percent) {
        return Engine.randInt(1, 100) <= percent;
    }

    public static void executeCommand(String inputString) {
        String[] input = inputString.split(" ");
        if(input.length > 0 && input[0].equals("/give")) {
            if(input.length < 2) {
                System.out.println("error: object not given");
            }
            else switch(input[1]) {
                case "sonar":
                    Item sonar = new Sonar();
                    sonar.collect();
                    break;
                case "measure":
                    Item measure = new Measure();
                    measure.collect();
                    break;
                case "pickaxe":
                    Item pick = new Pickaxe();
                    pick.collect();
                    break;
                case "radar":
                    Item radar = new Radar();
                    radar.collect();
                    break;
                case "money":
                    if(input.length < 3) {
                        System.out.println("error: amount not given");
                    }
                    else {
                        try {
                            player.giveMoney(Integer.parseInt(input[2]));
                        }
                        catch (NumberFormatException e) {
                            System.out.println("error: amount incorrect");
                        }
                    }
                    break;
                default:
                    System.out.println("error: unknown object");
            }
        }
        else if(input[0].equals("/move")) {
            if(input.length < 2) {
                System.out.println("error: direction not given");
            }
            else switch(input[1]) {
                case "0", "up" -> player.move(0);
                case "1", "right" -> player.move(1);
                case "2", "down" -> player.move(2);
                case "3", "left" -> player.move(3);
                default -> System.out.println("error: invalid direction");
            }
        }
        else {
            System.out.println("error: command unknown");
        }
    }

    public static void walk1() {
        walkDistance += 1;
    }
    public static String getFormattedWalkDistance() {
        if(walkDistance < 1000) {
            return "" + walkDistance + "m";
        }
        else {
            int km = walkDistance / 1000;
            int m = walkDistance % 1000;
            return "" + km + "km " + m + "m";
        }
    }

    public static void main(String[] args) {
        initGen();
        initPlayer();
        initMap();

        Scanner myScan = new Scanner(System.in);
        /*for(int i=0; i<100; i++) {
            map.getField(2, i).setType(FieldType.Corridor);
            map.getField(2, i).putItem(new Fuel());
            map.getField(2, i).getItem().open();
        }
        map.redraw();*/
//        System.out.println(Assets.FUELTANKSIZE);

        //noinspection InfiniteLoopStatement
        while(true) {
            String input = myScan.nextLine();
            if(input.charAt(0) == '/') {
                executeCommand(input);
            }
        }
    }
}
