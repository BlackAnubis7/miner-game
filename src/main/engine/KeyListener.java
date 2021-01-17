package engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("FieldCanBeLocal")
public class KeyListener extends KeyAdapter {
    private final Player player = Engine.getPlayer();

    private final int up1 = KeyEvent.VK_UP;
    private final int right1 = KeyEvent.VK_RIGHT;
    private final int down1 = KeyEvent.VK_DOWN;
    private final int left1 = KeyEvent.VK_LEFT;

    private final int up2 = KeyEvent.VK_W;
    private final int right2 = KeyEvent.VK_D;
    private final int down2 = KeyEvent.VK_S;
    private final int left2 = KeyEvent.VK_A;

    @Override
    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();
        switch(code) {
            case up1, up2 -> player.move(0);
            case right1, right2 -> player.move(1);
            case down1, down2 -> player.move(2);
            case left1, left2 -> player.move(3);
        }
    }
}
