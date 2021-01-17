package engine;

import items.Fuel;
import items.Gem;
import items.Item;
import items.Nothing;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SideMenu {
    private String defaultMessage = "";
    private final JPanel panel = new JPanel();
    private int firstFree = 0;
    private final JPanel equipment = new JPanel();
    private final JTextArea infolabel = new JTextArea("");
    private final JTextArea statlabel = new JTextArea("Money:\n  $ -\n\nFuel:\n  E[         |]F\n");
    private final JButton[] slots = new JButton[10];
    private final Item[] inventory = new Item[10];
    private final Set<Class <?>> nonCollectable = new HashSet<>();

    public SideMenu() {
        createLayout();
        nonCollectable.add(Gem.class);
        nonCollectable.add(Fuel.class);
        nonCollectable.add(Nothing.class);

        infolabel.setWrapStyleWord(true);
        infolabel.setLineWrap(true);
        infolabel.setOpaque(false);
        infolabel.setEditable(false);
        infolabel.setFocusable(false);

        statlabel.setWrapStyleWord(true);
        statlabel.setLineWrap(true);
        statlabel.setOpaque(false);
        statlabel.setEditable(false);
        statlabel.setFocusable(false);
    }

    private void createLayout() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(200,10)));
        panel.setBackground(Assets.CBACKGROUND);

        equipment.setLayout(new GridLayout(5, 2));
        for(int i=0; i<10; i++) {
            slots[i] = new JButton("");
            slots[i].setFocusable(false);
            slots[i].setBackground(Assets.CBACKGROUND);
            slots[i].setBorder(Assets.BINVENTORYBORDER);
            int finalI = i;
            slots[i].addActionListener(e -> {
                if(inventory[finalI] != null) {
                    printInfo(inventory[finalI].getDescription());
                }
                else {
                    printInfo(defaultMessage);
                }
            });
            equipment.add(slots[i]);
        }
        equipment.setMaximumSize(new Dimension(200, 400));
        equipment.setPreferredSize(new Dimension(200, 400));

        infolabel.setForeground(Assets.CINFOFONT);
        infolabel.setPreferredSize(new Dimension(200, 200));
        infolabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        JPanel infobox = new JPanel();
        infobox.setBackground(Assets.CBACKGROUND);
        infobox.add(infolabel);
        infobox.setMaximumSize(new Dimension(200, 200));

        statlabel.setForeground(Assets.CINFOFONT);
        statlabel.setPreferredSize(new Dimension(200, 200));
        statlabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        JPanel statbox = new JPanel();
        statbox.setBackground(Assets.CBACKGROUND);
        statbox.add(statlabel);
        statbox.setMaximumSize(new Dimension(200, 200));

        panel.add(equipment);
        panel.add(infobox);
        panel.add(statbox);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void equip(Item item) {
        if(!nonCollectable.contains(item.getClass())) {
            boolean alreadyHas = false;
            for (int i = 0; i < 10; i++) {
                if (inventory[i] != null && inventory[i].getClass() == item.getClass()) {
                    alreadyHas = true;
                    break;
                }
            }
            if (firstFree < 10 && !alreadyHas) {
                inventory[firstFree] = item;
                //            slots[firstFree].setIcon(Images.getResized(item.draw(), 60));
                slots[firstFree].setIcon(item.draw());
                firstFree += 1;
            }
        }
    }

    public void printInfo() {
        infolabel.setText(defaultMessage);
    }
    public void printInfo(String info) {
        infolabel.setText(info);
    }
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void recountStats(int money, int fuel, boolean hasMeasure) {
        StringBuilder stats = new StringBuilder("Money:\n");
        stats.append("  $ ").append(money > 0 ? money : "-").append("\n\n");
        int tenthsOfFuel = (int) Math.ceil((fuel * 10.0) / Assets.MAXFUEL);
        if(tenthsOfFuel < 1) {
            tenthsOfFuel = 1;
        }
        stats.append("Fuel:\n");
        stats.append("  E[").append(" ".repeat(tenthsOfFuel - 1));
        stats.append("|").append(" ".repeat(10 - tenthsOfFuel)).append("]F\n");
        if(hasMeasure) {
            stats.append("  ").append(fuel).append(" / ").append(Assets.MAXFUEL);
        }
        statlabel.setText(String.valueOf(stats));
    }
}
