package netbeast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class NetBeast extends JFrame {

    public NetBeast() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("NetBeast");


        // Set up menu

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem fileLoad = new JMenuItem("Open", KeyEvent.VK_O);
        fileMenu.add(fileLoad);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        // Set up main window

        Container cp = getContentPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetBeast().setVisible(true));
    }
}
