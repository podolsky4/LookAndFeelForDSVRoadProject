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

public class SkinSynthPainter extends SynthPainter {

  public void paintPanelBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
    Color start = UIManager.getColor("Panel.startBackground");
    Color end = UIManager.getColor("Panel.endBackground");
    Graphics2D g2 = (Graphics2D)g;
    GradientPaint grPaint = new GradientPaint(
            (float)x, (float)y, start,
            (float)w, (float)h, end);
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

  @Override
  public void paintScrollBarThumbBackground(SynthContext context, Graphics g, int x, int y, int w, int h, int i4) {
    Graphics2D g2 = (Graphics2D)g;
    int arc = getArc(context);

    g2.setColor(createTransparentColor(context.getStyle().getColor(context, ColorType.BACKGROUND).darker().darker()));
    g2.fillRoundRect(x + 1, y + 1, w - 2, h - 2, arc, arc);
  }

  /** Make an existing colour transparent. */
  private static Color createTransparentColor(Color color) {
    return new Color(color.getRed(), color.getGreen(), color.getBlue(), 0x88);
  }

  private int getArc(SynthContext context) {
    return getArc(context.getComponent());
  }

  public static int getArc(Component component) {
    // lists appear to mess up arcs
    return component instanceof  JList ? 0 : component.getFont().getSize() / 2;
  }

  @Override
  public void paintTableHeaderBorder(SynthContext synthContext, Graphics graphics, int x, int y, int w, int h) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    Color color2 = new Color(0, 0, 0, 0);
    JTableHeader jTableHeader = (JTableHeader)synthContext.getComponent();
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
    ImageIcon imageIcon = (ImageIcon)context.getStyle().getIcon(context, stringBuilder.toString());
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
    jTableHeader.setBackground(new ColorUIResource(214, 224,238));
  }

  @Override
  public void paintSliderThumbBackground(SynthContext context, Graphics g, int x, int y, int w, int h, int orientation) {
    ImageIcon imageIcon;
    imageIcon = (context.getComponentState() & 8) != 0
            ? (ImageIcon)context.getStyle().getIcon(context, "LafSynth.slider.thumb.image.disabled")
            : ((context.getComponentState() & 2) != 0
            ? (ImageIcon)context.getStyle().getIcon(context, "LafSynth.slider.thumb.image.mouseover")
            : (ImageIcon)context.getStyle().getIcon(context, "LafSynth.slider.thumb.image.normal"));
    if (imageIcon != null) {
      int width = imageIcon.getIconWidth();
      int height = imageIcon.getIconHeight();
      int offsetX = x + (w - width) / 2;
      int offsetY = y + (h - height) / 2;
      g.drawImage(imageIcon.getImage(), offsetX, offsetY, offsetX + width, offsetY + height, 0, 0, width, height, null);
    }
  }
}
