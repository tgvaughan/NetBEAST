/*
 * Copyright (c) 2016 Tim Vaughan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package netbeast;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import netbeast.utils.ExtendedAddOnManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;

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

        fileMenu.addSeparator();

        JMenuItem fileExit = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExit.addActionListener(e -> System.exit(0));
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        fileMenu.add(fileExit);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        // Set up main window

        Container cp = getContentPane();

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.setAllowDanglingEdges(false);
        graph.setDisconnectOnMove(true);
        graph.setPortsEnabled(true);

        // Sets the default edge style
		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.ElbowConnector);

        graph.getModel().beginUpdate();
        try
        {
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
                    30);
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
                    80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        cp.add(graphComponent);
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> new NetBeast().setVisible(true));

//        AddOnManager.loadExternalJars();
//        java.util.List<String> classes = AddOnManager.find("beast.core.BEASTObject", "bacter");
//        System.out.println(classes);

        System.out.println(ExtendedAddOnManager.getInstalledPackageNames());
    }
}
