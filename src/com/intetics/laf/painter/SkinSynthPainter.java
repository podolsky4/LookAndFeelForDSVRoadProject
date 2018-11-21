package com.intetics.laf.painter;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.synth.ColorType;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthPainter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class SkinSynthPainter extends SynthPainter {

  public void paintPanelBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
    Color start = UIManager.getColor("Panel.startBackground");
    Color end = UIManager.getColor("Panel.endBackground");
    Graphics2D g2 = (Graphics2D) g;
    GradientPaint grPaint = new GradientPaint(
            (float) x, (float) y, start,
            (float) w, (float) h, end);
    g2.setPaint(grPaint);
    g2.fillRect(x, y, w, h);
    g2.setPaint(null);
    g2.setColor(new Color(255, 255, 255, 120));
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }

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

  /*@Override
  public void paintTableHeaderBorder(SynthContext synthContext, Graphics graphics, int x, int y, int w, int h) {
    Graphics2D graphics2D = (Graphics2D) graphics.create();
    Color color2 = new Color(0, 0, 0, 0);
    JTableHeader jTableHeader = (JTableHeader) synthContext.getComponent();
    jTableHeader.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
    TableColumnModel tableColumnModel = jTableHeader.getColumnModel();
    int columnCount = tableColumnModel.getColumnCount();
    int pointX = 0;
    for (int i = 0; i < columnCount; ++i) {
      graphics2D.setPaint(Color.BLACK);
      graphics2D.drawLine(x + pointX, y, x + pointX, y + h - 1);
      graphics2D.setPaint(color2);
      graphics2D.drawLine(x + pointX, y, x + (pointX += tableColumnModel.getColumn(i).getWidth() - 1), y + h - 1);
      pointX += tableColumnModel.getColumnMargin();
    }

  }*/

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

  @Override
  public void paintTableHeaderBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
    JTableHeader jTableHeader = (JTableHeader) context.getComponent();
    jTableHeader.setBackground(new ColorUIResource(214, 224, 238));
  }

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
    JScrollBar jScrollBar;
    Color color;
    boolean bl;
    UIDefaults uIDefaults = UIManager.getDefaults();
    int thickness = uIDefaults.getInt("LafSynth.scrollbar.thumb.borderthick");

    if ((color = uIDefaults.getColor("LafSynth.scrollbar.thumb.bgcolor")) == null) {
      color = Color.LIGHT_GRAY;
    }
    boolean bl2 = bl = (jScrollBar = (JScrollBar) context.getComponent()).getOrientation() == JScrollBar.VERTICAL;

    graphics.setColor(color);
    graphics.fillRect(x, y, w - 1, h - 1);
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
        graphics.fillRect(x, y + h - thickness, w, thickness);
      }
    } else if (w > thickness * 2) {
      graphics.fillRect(x, y, thickness, h);
      graphics.fillRect(x + w - thickness, y, thickness, h);
    }
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

}
