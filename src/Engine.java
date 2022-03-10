import com.itheima.game.GameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.System.exit;

public class Engine extends GameEngine {

    int ovalX = 0, ovalY = 0;
    int speed = (Test.width * Test.height) / 400000 + 1;
    long ovalSpeedX = speed, ovalSpeedY = speed;
    final int firstOvalWidth = Test.width / 10;
    final int firstOvalHeight = Test.height / 10;
    int ovalWidth = firstOvalWidth, ovalHeight = firstOvalHeight;
    int red = 255, green = 0, blue = 0;
    int colorMode = 1;
    int ovalMoveMode = 0;
    long H = 1, W = 1;

    boolean game = true;

    @Override
    public void updateLogic() {

        if (game) {

            switch (ovalMoveMode) {
                case 0 -> ovalMove_QUANTUM();
                case 1 -> ovalMove_NORMLE();
            }
            ovalChange();

        }

    }

    @Override
    public void renderUI(Graphics2D g2d) {
        if (game) {

            switch (colorMode) {
                case 0 -> flash();
                case 1 -> gradualChange();
                case 2 -> black();
            }

        }
        keySet();
        g2d.setColor(new Color(red, green, blue));
        g2d.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);

    }

    public void keySet() {
        switch (getCurrentPressedKeyCode()) {
            case KeyEvent.VK_UP -> {
                ovalY -= 5;
                if (ovalY <= 0) {
                    ovalY = 0;
                }
            }
            case KeyEvent.VK_F1 -> game = false;
            case KeyEvent.VK_F2 -> game = true;
            case KeyEvent.VK_F3 -> {
                ovalX = 0;
                ovalY = 0;
                ovalSpeedX = 5;
                ovalSpeedY = 5;
                ovalWidth = 50;
                ovalHeight = 50;
                red = 255;
                green = 0;
                blue = 0;
                colorMode = 1;
                H = 1;
                W = 1;
                game = false;
            }
            case KeyEvent.VK_F4 -> exit(0);
            case KeyEvent.VK_F5 -> {
                if (ovalSpeedY < 0) {
                    ovalSpeedY = -speed;
                } else ovalSpeedY = speed;
                if (ovalSpeedX < 0) {
                    ovalSpeedX = -speed;
                } else ovalSpeedX = speed;
            }
            case KeyEvent.VK_F6 -> speed = (int) Math.ceil((Test.width * Test.height) / 400000.0);
            case KeyEvent.VK_F7 -> speed = (Test.width * Test.height) / 400000 + 2;
            case KeyEvent.VK_F8 -> {
                red = 255;
                colorMode = 0;
            }
            case KeyEvent.VK_F9 -> {
                red = 255;
                colorMode = 1;
            }
            case KeyEvent.VK_F11 -> colorMode = 2;
            case KeyEvent.VK_N -> ovalMoveMode = 1;
            case KeyEvent.VK_Q -> ovalMoveMode = 0;
        }

    }

    public void ovalMove_QUANTUM() {
        ovalX += ovalSpeedX;
        ovalY += ovalSpeedY;

        if (ovalX <= 0) {
            ovalX = 0;
            ovalSpeedX = -(ovalSpeedX - (int) ((Math.random() + 0.5) * speed));
        } else if (ovalX >= Test.width - ovalWidth) {
            ovalX = Test.width - ovalWidth;
            ovalSpeedX = -(ovalSpeedX + (int) ((Math.random() + 0.5) * speed));
        }
        if (ovalY <= 0) {
            ovalY = 0;
            ovalSpeedY = -(ovalSpeedY - (int) ((Math.random() + 0.5) * speed));
        } else if (ovalY >= Test.height - ovalHeight) {
            ovalY = Test.height - ovalHeight;
            ovalSpeedY = -(ovalSpeedY + (int) ((Math.random() + 0.5) * speed));
        }
    }

    public void ovalMove_NORMLE() {
        ovalX += ovalSpeedX;
        ovalY += ovalSpeedY;

        if (ovalX <= 0) {
            ovalX = 0;
            ovalSpeedX = 1;
        } else if (ovalX >= Test.width - ovalWidth) {
            ovalX = Test.width - ovalWidth;
            ovalSpeedX = -1;
        }
        if (ovalY <= 0) {
            ovalY = 0;
            ovalSpeedY = 1;
        } else if (ovalY >= Test.height - ovalHeight) {
            ovalY = Test.height - ovalHeight;
            ovalSpeedY = -1;
        }
    }

    public void ovalChange() {
        ovalHeight += H;
        ovalWidth += W;

        if (ovalWidth >= firstOvalWidth * 2) {
            ovalWidth = firstOvalWidth * 2;
            W = -Math.abs(ovalSpeedX / ((Test.width + Test.height) / 2) + 1);
        }
        if (ovalWidth <= firstOvalWidth) {
            ovalWidth = firstOvalWidth;
            W = Math.abs(ovalSpeedX / ((Test.width + Test.height) / 2) + 1);
        }
        if (ovalHeight >= firstOvalHeight * 2) {
            ovalHeight = firstOvalHeight * 2;
            H = -Math.abs(ovalSpeedY / ((Test.width + Test.height) / 2) + 1);
        }
        if (ovalHeight <= firstOvalHeight) {
            ovalHeight = firstOvalHeight;
            H = Math.abs(ovalSpeedY / ((Test.width + Test.height) / 2) + 1);
        }
    }

    public void flash() {
        if (red == 255) {
            red = 0;
            green = 255;
        } else if (green == 255) {
            green = 0;
            blue = 255;
        } else if (blue == 255) {
            blue = 0;
            red = 255;
        }
    }

    public void gradualChange() {
        if ((red < 255) && (green == 0) && (blue == 255)) {
            red++;
        } else if ((red > 0) && (green == 0) && (blue > 0)) {
            blue--;
        } else if ((green < 255) && (blue == 0) && (red == 255)) {
            green++;
        } else if ((green > 0) && (blue == 0) && (red > 0)) {
            red--;
        } else if ((blue < 255) && (red == 0) && (green == 255)) {
            blue++;
        } else if ((blue > 0) && (red == 0) && (green > 0)) {
            green--;
        }
    }

    public void black() {
        red = 0;
        green = 0;
        blue = 0;
    }
}
