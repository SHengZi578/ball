import javax.swing.*;

public class Input {
    public static int width, height;

    static void get() {

        while (true) {
            try {
                height = Integer.parseInt(JOptionPane.showInputDialog("输入程序的高", 800));
                width = Integer.parseInt(JOptionPane.showInputDialog("输入程序的宽", 800));
                if (width < 130) {
                    width = 130;
                }
                if (height == 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(null, "错误信息\n请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}