package util;
import javax.swing.*;
public class UI {
    public static void msg(java.awt.Component parent, String text){
        JOptionPane.showMessageDialog(parent, text);
    }
    public static boolean confirm(java.awt.Component parent, String text){
        int r = JOptionPane.showConfirmDialog(parent, text, "Confirm", JOptionPane.YES_NO_OPTION);
        return r == JOptionPane.YES_OPTION;
    }
}
