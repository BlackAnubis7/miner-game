package items;

import engine.*;

import javax.swing.*;

public interface Item {
    boolean isOpen();
    boolean open();
    ImageIcon draw();
    boolean collect();  // returns true if the item should disappear
    void use();

    String getDescription();
}
