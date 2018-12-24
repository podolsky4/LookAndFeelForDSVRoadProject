/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.intetics.laf;


import com.intetics.laf.component.DBSMVTabbedPane2;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/* Used by InternalFrameDemo.java. */
public class DocumentFrame extends JInternalFrame {
  static int openFrameCount = 0;
  static final int xOffset = 30, yOffset = 30;
  static final int FPS_MIN = 0;
  static final int FPS_MAX = 30;
  static final int FPS_INIT = 15;    //initial frames per second

  public DocumentFrame() {
    super("Document №" + (++openFrameCount),
            true, //resizable
            true, //closable
            true, //maximizable
            true);//iconifiable

    //...Create the GUI and put it in the window...
    createRootPanel();

    //...Then set the window size or call pack...
    setSize(1460,800);
//    pack();

    //Set the window's location.
    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
  }


  private void createRootPanel() {
    FlowLayout layout = new FlowLayout();
    JPanel rootPane = new JPanel(layout);
    rootPane.setBorder(BorderFactory.createTitledBorder("Controls"));

    String[] data = {"Swing", "JavaFX", "Java", "Spring", "Hibernate", "JUnit", "Mockito", "Camel", "ActiveMQ", "JHipster"};
    JList<String> list = new JList<>(data); //data has type Object[]
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list.setLayoutOrientation(JList.VERTICAL);
    list.setVisibleRowCount(5);
    JScrollPane listScroller = new JScrollPane(list);
    listScroller.setPreferredSize(new Dimension(120, 100));
    rootPane.add(listScroller);
    String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig", "Horse", "Chicken", "Cow", "Goat" };
    //Create the combo box, select item at index 4.
    //Indices start at 0, so 4 specifies the pig.
    JComboBox<String> comboBox = new JComboBox<>(petStrings);
    comboBox.setMaximumRowCount(5);
    comboBox.setSelectedIndex(0);
    comboBox.setEnabled(false);
    rootPane.add(comboBox);
    JButton testBtn = new JButton("Test 204");
//    testBtn.setName("DarkButton");
    rootPane.add(testBtn);
    JRadioButton radioButton1 = new JRadioButton("Agile");
    JRadioButton radioButton2 = new JRadioButton("Waterfall");
    radioButton2.setEnabled(false);
    JRadioButton radioButton3 = new JRadioButton("Scrum");
    JRadioButton radioButton4 = new JRadioButton("XP");
    //Group the radio buttons.
    ButtonGroup radioGroup = new ButtonGroup();
    radioGroup.add(radioButton1);
    radioGroup.add(radioButton2);
    radioGroup.add(radioButton3);
    radioGroup.add(radioButton4);

    //Put the radio buttons in a column in a panel.
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.setBorder(BorderFactory.createTitledBorder("Development"));
    radioPanel.setPreferredSize(new Dimension(180, 120));
    radioPanel.add(radioButton1);
    radioPanel.add(radioButton2);
    radioPanel.add(radioButton3);
    radioPanel.add(radioButton4);
    rootPane.add(radioPanel);

    //In a container that uses a BorderLayout:
    JTextArea textArea = new JTextArea();
    textArea.setColumns(20);
    textArea.setLineWrap(true);
    textArea.setRows(5);
    textArea.setWrapStyleWord(true);
//    textArea.setEditable(false);
    JScrollPane scrollPane1 = new JScrollPane(textArea);
//    scrollPane1.setPreferredSize(new Dimension(250, 110));
    rootPane.add(scrollPane1);

    String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};

    Object[][] dataTable = {
            {"Kathy", "Smith",
                    "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
                    "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
                    "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
                    "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
                    "Pool", new Integer(10), new Boolean(false)}
    };

    final JTable table = new JTable(dataTable, columnNames);
    table.setPreferredScrollableViewportSize(new Dimension(550, 120));
    table.setFillsViewportHeight(true);
    table.getTableHeader().setName("DarkTableHeader");
//    table.setEnabled(false);

    //Create the scroll pane and add the table to it.
    JScrollPane tableScrollPane = new JScrollPane(table);

    //Add the scroll pane to this panel.
    rootPane.add(tableScrollPane);

    //Create the label.
    JLabel sliderLabel = new JLabel("Frames Per Second", JLabel.CENTER);
    sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    //Create the slider.
    JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);

    //Turn on labels at major tick marks.

    framesPerSecond.setMajorTickSpacing(10);
    framesPerSecond.setMinorTickSpacing(1);
    framesPerSecond.setPaintTicks(true);
    framesPerSecond.setPaintLabels(true);
    framesPerSecond.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
//    framesPerSecond.setEnabled(false);
//    Font font = new Font("Serif", Font.ITALIC, 15);
//    framesPerSecond.setFont(font);
    rootPane.add(sliderLabel);
    rootPane.add(framesPerSecond);
    JLabel label = new JLabel("Select your skills:");
    JCheckBox checkBox1 = new JCheckBox("Java 8", true);
    JCheckBox checkBox2 = new JCheckBox("Spring");
    JCheckBox checkBox3 = new JCheckBox("Hibernate");
    JCheckBox checkBox4 = new JCheckBox("Web services");
    JCheckBox checkBox5 = new JCheckBox("Java EE", true);
    checkBox4.setEnabled(false);
    checkBox5.setEnabled(false);
    JPanel skillsPanel = new JPanel();
    skillsPanel.setBorder(BorderFactory.createTitledBorder("Skills"));
    skillsPanel.setLayout(new BoxLayout(skillsPanel, BoxLayout.Y_AXIS));
    skillsPanel.add(label);
    skillsPanel.add(checkBox1);
    skillsPanel.add(checkBox2);
    skillsPanel.add(checkBox3);
    skillsPanel.add(checkBox4);
    skillsPanel.add(checkBox5);
    rootPane.add(skillsPanel);
    JLabel labelTextField = new JLabel("First name:");
    JTextField textField = new JTextField(15);
    rootPane.add(labelTextField);
    rootPane.add(textField);
    JLabel jLabel2 = new JLabel("Due to: ");
    JFormattedTextField jFormattedTextField = new JFormattedTextField();
    jFormattedTextField.setColumns(15);
    rootPane.add(jLabel2);
    rootPane.add(jFormattedTextField);
    JLabel yourPassword = new JLabel("Password: ");
    JPasswordField passwordField = new JPasswordField(15);
    rootPane.add(yourPassword);
    rootPane.add(passwordField);
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setBorder(BorderFactory.createTitledBorder("Tabbed pane"));
    JTabbedPane tabbedPane2 = new DBSMVTabbedPane2();
    tabbedPane2.updateUI();

    JComponent panel1_inner = makeTextPanel("Place your personal info here.");
    tabbedPane2.addTab("Dorozna", panel1_inner);
    tabbedPane2.setMnemonicAt(0, KeyEvent.VK_1);

    JComponent panel2_inner = makeTextPanel("Describe your skills.");
    tabbedPane2.addTab("Skizhy", panel2_inner);
    tabbedPane2.setMnemonicAt(1, KeyEvent.VK_2);

    JComponent panel1 = makeTextPanel("Place your personal info here.");
    panel1.add(tabbedPane2);
    tabbedPane.addTab("Profile", panel1);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    JComponent panel2 = makeTextPanel("Describe your skills.");
    tabbedPane.addTab("Skills", panel2);
    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

    JComponent panel3 = makeTextPanel("Customize your environment ->");
    tabbedPane.addTab("Settings", panel3);
    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

    JComponent panel4 = makeTextPanel(
            "Panel #4 (has a preferred size of 500 x 300).");
    panel4.setPreferredSize(new Dimension(500, 300));
    tabbedPane.addTab("Department", panel4);
    tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

    JComponent panel5 = makeTextPanel("This is your team workspace");
    tabbedPane.addTab("Team", panel5);
    tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
    JLabel labelAge = new JLabel("Age:");
    JSpinner spinner = new JSpinner();
    spinner.setValue(28);
    rootPane.add(labelAge);
    rootPane.add(spinner);
    JToolBar toolBar = new JToolBar("Toolbar");
    addButtons(toolBar);
    rootPane.add(toolBar);
//    tabbedPane.setEnabled(false);

    //Add the tabbed pane to this panel.
    rootPane.add(tabbedPane);

    //The following line enables to use scrolling tabs.
    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
    createNodes(top);
    JTree tree = new JTree(top);
    JScrollPane treeView = new JScrollPane(tree);
    treeView.setPreferredSize(new Dimension(380, 330));
    rootPane.add(treeView);

    //Create an editor pane.
    JEditorPane editorPane = createEditorPane();
    JScrollPane editorScrollPane = new JScrollPane(editorPane);
    editorScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    editorScrollPane.setPreferredSize(new Dimension(250, 145));
    editorScrollPane.setMinimumSize(new Dimension(10, 10));

    //Create a text pane.
    JTextPane textPane = createTextPane();
    JScrollPane paneScrollPane = new JScrollPane(textPane);
    paneScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    paneScrollPane.setPreferredSize(new Dimension(250, 155));
    paneScrollPane.setMinimumSize(new Dimension(10, 10));

    JPanel spPanel1 = new JPanel();
    spPanel1.setBorder(BorderFactory.createTitledBorder("EditorPane"));
    spPanel1.add(editorScrollPane);

    //Put the editor pane and the text pane in a split pane.
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            spPanel1,
            paneScrollPane);
    splitPane.setOneTouchExpandable(true);
    splitPane.setResizeWeight(0.5);

    JPanel rightPane = new JPanel(new GridLayout(1,0));
    rightPane.add(splitPane);
    rightPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Styled Text"),
            BorderFactory.createEmptyBorder(5,5,5,5)));
    rootPane.add(rightPane);
    JButton dialogBtn = new JButton("Show dialog");
    dialogBtn.setName("TransparentButton");
    dialogBtn.setToolTipText("Click this button to disable the middle button.");
    dialogBtn.addActionListener(e -> {
      //ok dialog
      JOptionPane.showMessageDialog(this,"Eggs aren't supposed to be green.");
    });
    rootPane.add(dialogBtn);
    JProgressBar progressBar = new JProgressBar(0, 100);
    progressBar.setValue(63);
//    progressBar.setIndeterminate(true);
    progressBar.setPreferredSize(new Dimension(250, 10));
    progressBar.setStringPainted(true);
    rootPane.add(progressBar);
    JButton openFrame = new JButton("Create frame", new ImageIcon("/resource/issueTicket-icon.png"));
    openFrame.setName("TransparentButton");
    openFrame.setToolTipText("Click this button to create a new frame.");
    openFrame.addActionListener(e -> {

    });
    rootPane.add(openFrame);

    //Create a file chooser
    final JFileChooser fc = new JFileChooser();

    JButton openFile = new JButton("Open File");
    openFile.setName("TransparentButton");
    openFile.setToolTipText("Click this button to open a file.");
    openFile.addActionListener(e -> {
      //Handle open button action.
      if (e.getSource() == openFile) {
        int returnVal = fc.showOpenDialog(DocumentFrame.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          //This is where a real application would open the file.
          System.out.println("Opening: " + file.getName() + ".");
        } else {
          System.out.println("Open command cancelled by user.");
        }
      }
    });
    rootPane.add(openFile);

    JToggleButton confBtn = new JToggleButton("Load General Shipment");
//    confBtn.setEnabled(false);
    rootPane.add(confBtn);
    JToggleButton transportBtn = new JToggleButton("Transport Order");
    rootPane.add(transportBtn);
    ButtonGroup bg = new ButtonGroup(); // создаем группу взаимного исключения
    bg.add(confBtn);
    bg.add(transportBtn); // сделали кнопки tButton1 и tButton2 взаимоисключающими
    JButton blueBtn = new JButton("Changed button");
    blueBtn.setName("BlueButton");
    rootPane.add(blueBtn);
    JLabel justaLabel = new JLabel("This is just a orange label");
    justaLabel.setName("OrangeLabel");
    rootPane.add(justaLabel);
    /*JTabbedPane customTabbedPane = new CustomTabbedPane();
    JComponent panelForCustom = makeTextPanel("This is panel in custom Tabbed pane.");
    customTabbedPane.addTab("Tab 1", panelForCustom);
    customTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    panelForCustom.setPreferredSize(new Dimension(400, 100));
    rootPane.add(customTabbedPane);*/

    getContentPane().add(rootPane);
//    setJMenuBar(menuBar);
  }

  private JComponent makeTextPanel(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(filler);
    return panel;
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = SwingDemo.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }

  private void createNodes(DefaultMutableTreeNode top) {
    DefaultMutableTreeNode category = null;
    DefaultMutableTreeNode book = null;

    category = new DefaultMutableTreeNode("Books for Java Programmers");
    top.add(category);

    //original Tutorial
    book = new DefaultMutableTreeNode("The Java Tutorial: A Short Course on the Basics");
    category.add(book);

    //Tutorial Continued
    book = new DefaultMutableTreeNode("The Java Tutorial Continued: The Rest of the JDK");
    category.add(book);

    //Swing Tutorial
    book = new DefaultMutableTreeNode("The Swing Tutorial: A Guide to Constructing GUIs");
    category.add(book);

    //...add more books for programmers...

    category = new DefaultMutableTreeNode("Books for Java Implementers");
    top.add(category);

    //VM
    book = new DefaultMutableTreeNode("The Java Virtual Machine Specification");
    category.add(book);

    //Language Spec
    book = new DefaultMutableTreeNode("The Java Language Specification");
    category.add(book);
  }

  protected void addButtons(JToolBar toolBar) {
    JButton button;

    //first button
    button = makeNavigationButton("help-icon",
            "Back to previous something-or-other",
            "");
    button.setName("TransparentButton");
    toolBar.add(button);

    //second button
    button = makeNavigationButton("Up24",
            "Up to something-or-other",
            "Up");
    toolBar.add(button);

    //similar code for creating and adding the third button...
  }

  protected JButton makeNavigationButton(String imageName,
                                         String toolTipText,
                                         String altText) {
    //Look for the image.
    String imgLocation = "resource/"
            + imageName
            + ".png";
    URL imageURL = SwingDemo.class.getResource(imgLocation);

    //Create and initialize the button.
    JButton button = new JButton();
    button.setToolTipText(toolTipText);

    if (imageURL != null) {                      //image found
      button.setIcon(new ImageIcon(imageURL, altText));
    } else {                                     //no image found
      button.setText(altText);
      System.err.println("Resource not found: " + imgLocation);
    }

    return button;
  }

  private JEditorPane createEditorPane() {
    JEditorPane editorPane = new JEditorPane();
    editorPane.setEditable(true);
    return editorPane;
  }

  private JTextPane createTextPane() {
    String[] initString =
            { "This is an editable JTextPane, ",            //regular
                    "another ",                                   //italic
                    "styled ",                                    //bold
                    "text ",                                      //small
                    "component, ",                                //large
                    "which supports embedded components..." + "\n",//regular
                    " " + "\n",                                //button
                    "...and embedded icons..." + "\n",         //regular
                    " ",                                          //icon
                    "\n" + "JTextPane is a subclass of JEditorPane that " +
                            "uses a StyledEditorKit and StyledDocument, and provides " +
                            "cover methods for interacting with those objects."
            };

    String[] initStyles =
            { "regular", "italic", "bold", "small", "large",
                    "regular", "button", "regular", "icon",
                    "regular"
            };

    JTextPane textPane = new JTextPane();
    StyledDocument doc = textPane.getStyledDocument();
    addStylesToDocument(doc);

    try {
      for (int i=0; i < initString.length; i++) {
        doc.insertString(doc.getLength(), initString[i],
                doc.getStyle(initStyles[i]));
      }
    } catch (BadLocationException ble) {
      System.err.println("Couldn't insert initial text into text pane.");
    }

    return textPane;
  }

  protected void addStylesToDocument(StyledDocument doc) {
    //Initialize some styles.
    Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);

    Style regular = doc.addStyle("regular", def);
    StyleConstants.setFontFamily(def, "SansSerif");

    Style s = doc.addStyle("italic", regular);
    StyleConstants.setItalic(s, true);

    s = doc.addStyle("bold", regular);
    StyleConstants.setBold(s, true);

    s = doc.addStyle("small", regular);
    StyleConstants.setFontSize(s, 10);

    s = doc.addStyle("large", regular);
    StyleConstants.setFontSize(s, 16);

    s = doc.addStyle("icon", regular);
    StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

    s = doc.addStyle("button", regular);
    StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
  }
}