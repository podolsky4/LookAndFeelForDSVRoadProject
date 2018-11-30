package com.intetics.laf;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

public class SwingDemo extends JFrame implements ActionListener {

  private JDesktopPane desktop;

  //Create and set up the window.
  public SwingDemo() {
    super("Simple Demo Application");

    //Make the big window be indented 50 pixels from each edge
    //of the screen.
    int inset = 50;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(inset, inset,screenSize.width  - inset*2,screenSize.height - inset*2);

    //Set up the GUI.
    desktop = new JDesktopPane(); //a specialized layered pane
    createFrame(); //create first "window"
    setContentPane(desktop);
    setJMenuBar(createMenuBar());

    //Make dragging a little faster but perhaps uglier.
    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    createAndShowGUI();
  }

  protected JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    //Build the first menu.
    JMenu menu = new JMenu("File");
    JMenu submenu = new JMenu("Options");

    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription(
            "The only menu in this program that has menu items");
    menuBar.add(menu);

//a group of JMenuItems
    JMenuItem menuItem = new JMenuItem("A text-only menu item",
            KeyEvent.VK_T);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_1, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
            "This doesn't really do anything");
    menu.add(menuItem);

    menuItem = new JMenuItem("Both text and icon",
            new ImageIcon("images/middle.gif"));
    menuItem.setMnemonic(KeyEvent.VK_B);
    menu.add(menuItem);

    menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
    menuItem.setMnemonic(KeyEvent.VK_D);
    menu.add(menuItem);

    //a group of radio button menu items
    menu.addSeparator();
    ButtonGroup group = new ButtonGroup();
    JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
    rbMenuItem.setSelected(true);
    rbMenuItem.setMnemonic(KeyEvent.VK_R);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Another one");
    rbMenuItem.setMnemonic(KeyEvent.VK_O);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    //a group of check box menu items
    menu.addSeparator();
    JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
    cbMenuItem.setMnemonic(KeyEvent.VK_C);
    menu.add(cbMenuItem);

    cbMenuItem = new JCheckBoxMenuItem("Another one");
    cbMenuItem.setMnemonic(KeyEvent.VK_H);
    menu.add(cbMenuItem);

    //a submenu
    menu.addSeparator();
    submenu.setMnemonic(KeyEvent.VK_S);

    menuItem = new JMenuItem("An item in the submenu");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_2, ActionEvent.ALT_MASK));
    submenu.add(menuItem);

    menuItem = new JMenuItem("Another item");
    submenu.add(menuItem);
    menu.add(submenu);

    //Build second menu in the menu bar.
    menu = new JMenu("Edit");
    menu.setMnemonic(KeyEvent.VK_N);
    menu.getAccessibleContext().setAccessibleDescription(
            "This menu does nothing");
    menuBar.add(menu);

    //Set up the lone menu.
    menu = new JMenu("Document");
    menu.setMnemonic(KeyEvent.VK_D);
    menuBar.add(menu);

    //Set up the first menu item.
    menuItem = new JMenuItem("New window");
    menuItem.setMnemonic(KeyEvent.VK_N);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_N, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("new");
    menuItem.addActionListener(this);
    menu.add(menuItem);

    //Set up the second menu item.
    menuItem = new JMenuItem("Quit");
    menuItem.setMnemonic(KeyEvent.VK_Q);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("quit");
    menuItem.addActionListener(this);
    menu.add(menuItem);

    menu = new JMenu("Loads");
    menu.setMnemonic(KeyEvent.VK_L);
    menu.getAccessibleContext().setAccessibleDescription(
            "This menu does nothing");
    menuBar.add(menu);

    menu = new JMenu("Orders");
    menu.setMnemonic(KeyEvent.VK_S);
    menu.getAccessibleContext().setAccessibleDescription(
            "This menu does nothing");
    menuBar.add(menu);

    return menuBar;
  }

  //React to menu selections.
  public void actionPerformed(ActionEvent e) {
    if ("new".equals(e.getActionCommand())) { //new
      createFrame();
    } else { //quit
      quit();
    }
  }

  //Create a new internal frame.
  protected void createFrame() {
    DocumentFrame frame = new DocumentFrame();
    frame.setVisible(true); //necessary as of 1.3
    desktop.add(frame);
    try {
      frame.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {}
  }

  //Quit the application.
  protected void quit() {
    System.exit(0);
  }

  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event-dispatching thread.
   */
  private static void createAndShowGUI() {

    // Set custom defined look and feel through external XML
    /*SynthLookAndFeel synth = new SynthLookAndFeel();
    try {
      synth.load(SwingDemo.class.getResourceAsStream("synth.xml"), SwingDemo.class);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    try {
      UIManager.setLookAndFeel(synth);
    } catch (UnsupportedLookAndFeelException e1) {
      e1.printStackTrace();
      System.out.println("Look and Feel file not found");
    }*/
    //Ask for window decorations provided by the look and feel.
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    SwingDemo frame = new SwingDemo();

    //Create and set up the window.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Display the window.
    frame.setVisible(true);

  }



  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }


}

