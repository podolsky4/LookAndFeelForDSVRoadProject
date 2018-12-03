package com.intetics.laf.painter;

import com.intetics.laf.utils.LafSynthGraphicsUtils;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.synth.ColorType;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthGraphicsUtils;
import javax.swing.plaf.synth.SynthPainter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Method;

public class SkinSynthPainter extends SynthPainter {



  @Override
  public void paintPanelBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
    super.paintPanelBorder(context, g, x, y, w, h);
  }

  /**
   * Make an existing colour transparent.
   */
  private static Color createTransparentColor(Color color) {
    return new Color(color.getRed(), color.getGreen(), color.getBlue(), 0x88);
  }

  private int getArc(SynthContext context) {
    return getArc(context.getComponent());
  }

  public static int getArc(Component component) {
    // lists appear to mess up arcs
    return component instanceof JList ? 0 : component.getFont().getSize() / 2;
  }

  @Override
  public void paintTableHeaderBorder(SynthContext synthContext, Graphics graphics, int x, int y, int w, int h) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    JTableHeader jTableHeader = (JTableHeader)synthContext.getComponent();
    TableColumnModel tableColumnModel = jTableHeader.getColumnModel();
    int columns = tableColumnModel.getColumnCount();
    graphics2D.setPaint(Color.BLACK);
    int start = 0;
    graphics2D.drawLine(x + start, y, x + start, y + h - 1);
    for (int i = 0; i < columns; ++i) {
      graphics2D.drawLine(x + start - 1, y, x + start - 1, y + h - 1);
      /*graphics2D.setPaint(color2);
      graphics2D.drawLine(x + start, y, x + (start += tableColumnModel.getColumn(i).getWidth() - 1), y + h - 1);*/
      start += tableColumnModel.getColumn(i).getWidth() - 1;
      start += tableColumnModel.getColumnMargin();
    }
    graphics2D.drawLine(x + start - 1, y, x + start - 1, y + h - 1);
    graphics2D.drawLine(0, 0, x + tableColumnModel.getTotalColumnWidth(), 0);
    graphics2D.drawLine(0, y + h, x + tableColumnModel.getTotalColumnWidth(), y + h);

  }

  @Override
  public void paintArrowButtonForeground(SynthContext context, Graphics graphics, int x, int y, int w, int h, int orientation) {
    StringBuilder stringBuilder = new StringBuilder("LafSynth.arrow.");
    switch (orientation) {
      case 1: {
        stringBuilder.append("up.");
        break;
      }
      case 7: {
        stringBuilder.append("left.");
        break;
      }
      case 5: {
        stringBuilder.append("down.");
        break;
      }
      case 3: {
        stringBuilder.append("right.");
        break;
      }
    }
    if ((context.getComponentState() & 4) != 0) {
      stringBuilder.append("pressed");
    } else if ((context.getComponentState() & 8) != 0) {
      stringBuilder.append("disabled");
    } else if ((context.getComponentState() & 2) != 0) {
      stringBuilder.append("mouseover");
    } else {
      stringBuilder.append("enabled");
    }
    ImageIcon imageIcon = (ImageIcon) context.getStyle().getIcon(context, stringBuilder.toString());
    if (imageIcon != null) {
      int n6 = imageIcon.getIconWidth();
      int n7 = imageIcon.getIconHeight();
      int n8 = (w - n6) / 2;
      int n9 = (h - n7) / 2;
      graphics.drawImage(imageIcon.getImage(), n8, n9, n8 + n6, n9 + n7, 0, 0, n6, n7, null);
    }
  }

  /*@Override
  public void paintTableHeaderBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
    JTableHeader jTableHeader = (JTableHeader) context.getComponent();
    jTableHeader.setBorder(new BorderUIResource.LineBorderUIResource(new ColorUIResource(0, 0, 0)));
  }*/

  @Override
  public void paintSliderThumbBackground(SynthContext context, Graphics g, int x, int y, int w, int h, int orientation) {
    ImageIcon imageIcon;
    imageIcon = (context.getComponentState() & 8) != 0
            ? (ImageIcon) context.getStyle().getIcon(context, "LafSynth.slider.thumb.image.disabled")
            : ((context.getComponentState() & 2) != 0
            ? (ImageIcon) context.getStyle().getIcon(context, "LafSynth.slider.thumb.image.mouseover")
            : (ImageIcon) context.getStyle().getIcon(context, "LafSynth.slider.thumb.image.normal"));
    if (imageIcon != null) {
      int width = imageIcon.getIconWidth();
      int height = imageIcon.getIconHeight();
      int offsetX = x + (w - width) / 2;
      int offsetY = y + (h - height) / 2;
      g.drawImage(imageIcon.getImage(), offsetX, offsetY, offsetX + width, offsetY + height, 0, 0, width, height, null);
    }
  }

  @Override
  public void paintScrollBarBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.scrollbar.bgcolor");
    if (color == null) {
      color = Color.WHITE;
    }
    graphics.setColor(color);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void paintScrollBarBorder(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.scrollbar.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    graphics.setColor(color);
    graphics.drawRect(x, y, w - 1, h - 1);
  }

  @Override
  public void paintScrollBarTrackBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    Color color;
    UIDefaults uIDefaults = UIManager.getDefaults();
    if ((color = uIDefaults.getColor("LafSynth.scrollbar.track.bgcolor")) == null) {
      color = Color.LIGHT_GRAY;
    }
    graphics.setColor(color);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void paintScrollBarTrackBorder(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.scrollbar.track.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    graphics.setColor(color);
    graphics.drawRect(x, y, w - 1, h - 1);
  }

  @Override
  public void paintScrollBarThumbBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h, int orientation) {
    Color color;
    UIDefaults uIDefaults = UIManager.getDefaults();
    if ((color = uIDefaults.getColor("LafSynth.scrollbar.thumb.bgcolor")) == null) {
      color = Color.LIGHT_GRAY;
    }
    graphics.setColor(color);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void paintScrollBarThumbBorder(SynthContext context, Graphics graphics, int x, int y, int w, int h, int orientation) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    int thickness = uIDefaults.getInt("LafSynth.scrollbar.thumb.borderthick");
    Color color = uIDefaults.getColor("LafSynth.scrollbar.thumb.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    if ((context.getComponentState() & 8) != 0) {
      color = Color.LIGHT_GRAY;
    } else if ((context.getComponentState() & 2) != 0) {
      color = color.brighter();
    }
    graphics.setColor(color);
    JScrollBar jScrollBar = (JScrollBar) context.getComponent();
    if (jScrollBar.getOrientation() == JScrollBar.VERTICAL) {
      if (h > thickness * 2) {
        graphics.fillRect(x, y, w, thickness);
//        graphics.drawArc(x, y, w, thickness + 12, 0, 180);
        graphics.fillRect(x, y + h - thickness, w, thickness);
//        graphics.drawRoundRect(x, y, w, h, w, 16);
      }
    } else if (w > thickness * 2) {
      graphics.fillRect(x, y, thickness, h);
      graphics.fillRect(x + w - thickness, y, thickness, h);
    }
  }

  @Override
  public void paintArrowButtonBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.combobox.bordercolor");
    g.setColor(color);
    g.drawLine(x,y, x, y + h);
  }

  @Override
  public void paintComboBoxBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.combobox.bordercolor");
    if (color == null || (context.getComponentState() & 8) != 0) {
      color = Color.GRAY;
    }
    g.setColor(color);
    JComboBox comboBox = (JComboBox) context.getComponent();
    comboBox.setBorder(new BorderUIResource.LineBorderUIResource(color));
  }

  @Override
  public void paintScrollPaneBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.scrollpane.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    g.setColor(color);
    JScrollPane scrollPane = (JScrollPane) context.getComponent();
    scrollPane.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
  }

  @Override
  public void paintSplitPaneBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.splitpane.border.color");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    g.setColor(color);
    g.drawRect(x, y, w - 1, h - 1);
  }

  @Override
  public void paintSplitPaneDividerBackground(SynthContext synthContext, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.splitpane.divider.maincolor");
    if (color == null) {
      color = Color.LIGHT_GRAY;
    }
    graphics.setColor(color);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void paintSplitPaneDragDivider(SynthContext context, Graphics graphics, int x, int y, int w, int h, int orientation) {
    this.paintSplitPaneDividerBackground(context, graphics, x, y, w, h);
  }

  @Override
  public void paintToolBarBackground(SynthContext synthContext, Graphics graphics, int x, int y, int w, int h) {
    Color color;
    UIDefaults uIDefaults = UIManager.getDefaults();
    if ((color = uIDefaults.getColor("LafSynth.toolbar.bgcolor")) == null) {
      color = Color.LIGHT_GRAY;
    }
    graphics.setColor(color);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void paintRootPaneBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    ImageIcon imageIcon = (ImageIcon)context.getStyle().getIcon(context, "LafSynth.rootpanel.bg.image");
    if (imageIcon != null) {
      Image image = imageIcon.getImage();
      int width = image.getWidth(null);
      int height = image.getHeight(null);
      if (width > 0 && height > 0) {
        BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);
        TexturePaint texturePaint = new TexturePaint(bufferedImage, new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        graphics2D.setPaint(texturePaint);
        graphics2D.fill(graphics2D.getClip());
      }
    }
  }

  @Override
  public void paintProgressBarBorder(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    block8 : {
      JProgressBar jProgressBar = (JProgressBar)context.getComponent();
      if (jProgressBar.isIndeterminate() && jProgressBar.isStringPainted()) {
        boolean bl = context.getStyle().getBoolean(context, "LafSynth.progressbar.indeterminate.paintstring", false);
        String string = jProgressBar.getString();
        if (bl && string != null) {
          Class<?> class_ = jProgressBar.getUI().getClass();
          try {
            Method method = class_.getDeclaredMethod("paintText", SynthContext.class, Graphics.class, String.class);
            if (method == null) break block8;
            try {
              Font font = context.getStyle().getFont(context);
              FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
              int stringWidth = context.getStyle().getGraphicsUtils(context).computeStringWidth(context, font, fontMetrics, string);
              Rectangle rectangle = jProgressBar.getBounds();
              Rectangle rectangle2 = new Rectangle(rectangle.width / 2 - stringWidth / 2, (rectangle.height - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2, stringWidth, fontMetrics.getAscent() + fontMetrics.getDescent());
              Insets insets = (Insets)context.getStyle().get(context, "LafSynth.progressbar.bg.insets");
              if (insets == null) {
                insets = new Insets(0, 0, 0, 0);
              }
              if (jProgressBar.getOrientation() == 0 && rectangle2.y >= insets.top || jProgressBar.getOrientation() == 1 && rectangle2.x >= insets.left) {
                graphics.setColor(new Color(0, 38, 100));
                graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
              }
            }
            catch (Exception exception) {
              // empty catch block
            }
            method.setAccessible(true);
            method.invoke(jProgressBar.getUI(), context, graphics, string);
            method.setAccessible(false);
          }
          catch (Exception exception) {
            exception.printStackTrace();
          }
        }
      }
    }
  }

  @Override
  public void paintProgressBarBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    ImageIcon imageIcon = (ImageIcon)context.getStyle().getIcon(context, "LafSynth.progressbar.background.image");
    Insets insets = (Insets)context.getStyle().get(context, "LafSynth.progressbar.bg.insets");
    if (imageIcon != null) {
      LafSynthGraphicsUtils.drawImageWith9Grids(graphics, imageIcon.getImage(), x, y, (x + w), (y + h), (insets == null ? new Insets(0, 0, 0, 0) : insets), (boolean)true);
    } else {
      UIDefaults uIDefaults = UIManager.getDefaults();
       Color color = uIDefaults.getColor("LafSynth.progressbar.background.color");
      if (color != null) {
        graphics2D.setColor(color);
        graphics2D.fillRect(x, y, w, h);
      }
    }
    JProgressBar progressBar = (JProgressBar) context.getComponent();
    ImageIcon image = (ImageIcon)context.getStyle().getIcon(context, "LafSynth.progressbar.indication.image");
    if (image != null && w > 0 && h > 0) {
      int iconWidth = image.getIconWidth();
      int iconHeight = image.getIconHeight();
      if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
        graphics2D.setPaint(new Color(0, 0, 0, 130));
        graphics2D.drawLine((x + w) / 2 - 1, y + iconHeight / 2, (x + w) / 2 - 1, y + h - iconHeight / 2);
        graphics2D.setPaint(new Color(255, 255, 255, 130));
        graphics2D.drawLine((x + w) / 2 + 1, y + iconHeight / 2, (x + w) / 2 + 1, y + h - iconHeight / 2);
        graphics2D.setPaint(new Color(0, 0, 0, 180));
        graphics2D.drawLine((x + w) / 2, y + iconHeight / 2, (x + w) / 2, y + h - iconHeight / 2);
      } else {
        graphics2D.setPaint(new Color(0, 0, 0, 130));
        graphics2D.drawLine(x + iconWidth / 2, (y + h) / 2 - 1, x + w - iconWidth / 2, (y + h) / 2 - 1);
        graphics2D.setPaint(new Color(255, 255, 255, 130));
        graphics2D.drawLine(x + iconWidth / 2, (y + h) / 2 + 1, x + w - iconWidth / 2, (y + h) / 2 + 1);
        graphics2D.setPaint(new Color(0, 0, 0, 180));
        graphics2D.drawLine(x + iconWidth / 2, (y + h) / 2, x + w - iconWidth / 2, (y + h) / 2);
      }
    }
  }

  @Override
  public void paintProgressBarForeground(SynthContext context, Graphics graphics, int x, int y, int w, int h, int orientation) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    ImageIcon imageIcon = (ImageIcon)context.getStyle().getIcon(context, "LafSynth.progressbar.indication.image");
    JProgressBar jProgressBar = (JProgressBar)context.getComponent();
    boolean bl = (context.getComponentState() & 8) != 0;
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.progressbar.line.color");
    if (color == null) {
      color = new Color(255, 255, 255, 130);
    }
    if (imageIcon != null && w > 0 && h > 0) {
      int iconWidth = imageIcon.getIconWidth();
      int iconHeight = imageIcon.getIconHeight();
      if (0 == orientation) {
        if (!jProgressBar.isIndeterminate() && w >= iconWidth) {
          graphics2D.setPaint(color.brighter());
          graphics2D.drawLine(x + iconWidth / 2, (y + h) / 2 - 1, x + w - iconWidth / 2, (y + h) / 2 - 1);
          graphics2D.setPaint(color.darker());
          graphics2D.drawLine(x + iconWidth / 2, (y + h) / 2 + 1, x + w - iconWidth / 2, (y + h) / 2 + 1);
          graphics2D.setPaint(color);
          graphics2D.drawLine(x + iconWidth / 2, (y + h) / 2, x + w - iconWidth / 2, (y + h) / 2);
        }
        if (!bl) {
          int n8;
          int n9 = x + w - iconWidth;
          if (jProgressBar.isIndeterminate()) {
            n8 = jProgressBar.getWidth() - w;
            int n10 = iconWidth / 2 + (jProgressBar.getWidth() - iconWidth) * x / n8;
            n9 = n10 - iconWidth / 2;
          }
          if (n9 >= 0) {
            n8 = y + (h - iconHeight) / 2;
            graphics.drawImage(imageIcon.getImage(), n9, n8, n9 + iconWidth, n8 + iconHeight, 0, 0, iconWidth, iconHeight, null);
          }
        }
      } else {
        if (!jProgressBar.isIndeterminate() && h >= iconHeight) {
          graphics2D.setPaint(color.brighter());
          graphics2D.drawLine((x + w) / 2 - 1, y + iconHeight / 2, (x + w) / 2 - 1, y + h - iconHeight / 2);
          graphics2D.setPaint(color.darker());
          graphics2D.drawLine((x + w) / 2 + 1, y + iconHeight / 2, (x + w) / 2 + 1, y + h - iconHeight / 2);
          graphics2D.setPaint(color);
          graphics2D.drawLine((x + w) / 2, y + iconHeight / 2, (x + w) / 2, y + h - iconHeight / 2);
        }
        if (!bl) {
          int n11 = x + (w - iconWidth) / 2;
          int n12 = y;
          if (jProgressBar.isIndeterminate()) {
            int n13 = jProgressBar.getHeight() - h;
            int n14 = iconHeight / 2 + (jProgressBar.getHeight() - iconHeight) * y / n13;
            n12 = n14 - iconHeight / 2;
          }
          graphics.drawImage(imageIcon.getImage(), n11, n12, n11 + iconWidth, n12 + iconHeight, 0, 0, iconWidth, iconHeight, null);
        }
      }
    }
  }

  @Override
  public void paintMenuBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    Color color;
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color2 = uIDefaults.getColor("LafSynth.menu.bg.color.normal");
    if (color2 == null) {
      color2 = Color.LIGHT_GRAY;
    }
    if ((color = uIDefaults.getColor("LafSynth.menu.bg.color.selected")) == null) {
      color = Color.GRAY;
    }
    JMenu jMenu = (JMenu)context.getComponent();
    if ((context.getComponentState() & 512) != 0) {
      graphics.setColor(color);
      graphics.fillRect(x, y, w, h);
    } else if ((context.getComponentState() & 8) != 0) {
      if (!(jMenu.getParent() instanceof JMenuBar)) {
        graphics.setColor(color2);
        graphics.fillRect(x, y, w, h);
      }
    } else if (!(jMenu.getParent() instanceof JMenuBar)) {
      graphics.setColor(color2);
      graphics.fillRect(x, y, w, h);
    }
  }

  @Override
  public void paintMenuItemBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.menuitem.bgcolor");
    if (color == null) {
      color = Color.WHITE;
    }
    g.setColor(color);
    g.fillRect(x, y, w, h);
  }

  @Override
  public void paintPopupMenuBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    ImageIcon imageIcon;
    JPopupMenu jPopupMenu = (JPopupMenu)context.getComponent();
    JPanel jPanel = (JPanel)jPopupMenu.getParent();
    BufferedImage bufferedImage = (BufferedImage)jPanel.getClientProperty("POPUP_BACKGROUND_IMAGE");
    if (bufferedImage != null) {
      graphics.drawImage(bufferedImage, x, y, null);
    }
    if ((imageIcon = (ImageIcon)context.getStyle().getIcon(context, "LafSynth.popup.menu.bg")) != null) {
      LafSynthGraphicsUtils.drawImageWith9Grids(graphics, imageIcon.getImage(), x, y, (x + w), (y + h), context.getStyle().getInsets(context, null), true);
    }
  }

  @Override
  public void paintInternalFrameBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.internalframe.main.color");
    if (color == null) {
      color = Color.GRAY;
    }
    graphics.setColor(color);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void paintInternalFrameBorder(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.internalframe.main.color");
    if (color == null) {
      color = Color.GRAY;
    }
    graphics.setColor(new ColorUIResource(0, 38, 100));
    graphics.drawRoundRect(x, y, w - 1, h - 1, 10, 10);
  }

  @Override
  public void paintInternalFrameTitlePaneBorder(SynthContext context, Graphics graphics, int x, int y, int w, int h) {

  }

  @Override
  public void paintInternalFrameTitlePaneBackground(SynthContext context, Graphics graphics, int x, int y, int w, int h) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("LafSynth.internalframe.title.main.color");
    if (color == null) {
      color = Color.GRAY;
    }
    graphics.setColor(color.darker());
    graphics.fillRect(x, y, w - 1, h - 1);
    String string = System.getProperty("java.version");
    /*if (string.compareTo("1.6") < 0) {
      Container container;
      JInternalFrame jInternalFrame;
      int n5 = context.getStyle().getInt(context, "InternalFrameTitlePane.titleSpacing", 0);
      for (container = context.getComponent(); !(container instanceof JInternalFrame) && container != null; container = container.getParent()) {
      }
      if (container instanceof JInternalFrame && (jInternalFrame = (JInternalFrame)container).getTitle() != null) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(jInternalFrame.getTitle(), 20 + n5, 15);
      }
      for (container = context.getComponent(); !(container instanceof JInternalFrame.JDesktopIcon) && container != null; container = container.getParent()) {
      }
      if (container instanceof JInternalFrame.JDesktopIcon && (jInternalFrame = ((JInternalFrame.JDesktopIcon)container).getInternalFrame()).getTitle() != null) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(jInternalFrame.getTitle(), 20 + n5, 15);
      }
    }*/
  }
}
