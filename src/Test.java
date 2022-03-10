import com.itheima.game.Game;

import javax.swing.*;

public class Test {
    public static int width = 800;
    public static int height = 800;

    public static void main(String[] args) {

        Input.get();

        width = Input.width;
        height = Input.height;

        point("F1: 暂停\nF2: 继a续\nF3: 重置小球\nF4: 退出\nF5: 重置速度\nF6&F7: 减小(增大)加速度\nF8: 光敏性癫痫警告\nF9: 渐变色\nF10: 已被占用\nF11:纯黑", "提示");
        System.out.println(width + "\n" + height);
        Game.init("小球", width, height, new Engine());

    }

    public static void point(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }
}
