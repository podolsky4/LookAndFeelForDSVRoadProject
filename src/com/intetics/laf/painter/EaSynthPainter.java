/*
package com.intetics.laf.painter;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthPainter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;


 Decompiled with CFR 0_132.
 *
 * Could not load the following classes:
 *  com.easynth.designer.laf.popup.EaSynthComboBoxPopupMenuListener
 *  com.easynth.designer.laf.popup.EaSynthPopupFactory
 *  com.easynth.designer.laf.utils.EaSynthGraphicsUtils





        import com.easynth.designer.laf.popup.EaSynthComboBoxPopupMenuListener;
        import com.easynth.designer.laf.popup.EaSynthPopupFactory;
        import com.easynth.designer.laf.utils.EaSynthGraphicsUtils;
        import java.awt.Color;
        import java.awt.Container;
        import java.awt.Font;
        import java.awt.FontMetrics;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.Image;
        import java.awt.Insets;
        import java.awt.Paint;
        import java.awt.Rectangle;
        import java.awt.Shape;
        import java.awt.TexturePaint;
        import java.awt.Toolkit;
        import java.awt.geom.Rectangle2D;
        import java.awt.image.BufferedImage;
        import java.awt.image.ImageObserver;
        import java.io.Serializable;
        import java.lang.reflect.Method;
        import java.util.Map;
        import java.util.WeakHashMap;
        import javax.swing.Icon;
        import javax.swing.ImageIcon;
        import javax.swing.JComboBox;
        import javax.swing.JComponent;
        import javax.swing.JInternalFrame;
        import javax.swing.JMenu;
        import javax.swing.JMenuBar;
        import javax.swing.JPanel;
        import javax.swing.JPopupMenu;
        import javax.swing.JProgressBar;
        import javax.swing.JScrollBar;
        import javax.swing.JSplitPane;
        import javax.swing.JToolBar;
        import javax.swing.UIDefaults;
        import javax.swing.UIManager;
        import javax.swing.event.PopupMenuListener;
        import javax.swing.plaf.ProgressBarUI;
        import javax.swing.plaf.synth.SynthContext;
        import javax.swing.plaf.synth.SynthGraphicsUtils;
        import javax.swing.plaf.synth.SynthPainter;
        import javax.swing.plaf.synth.SynthStyle;
        import javax.swing.table.JTableHeader;
        import javax.swing.table.TableColumn;
        import javax.swing.table.TableColumnModel;

public class EaSynthPainter extends SynthPainter {
  private static final Map<JComponent, String> MANAGED_OBJECT_MAP = new WeakHashMap<JComponent, String>();

  public EaSynthPainter() {
    EaSynthPopupFactory.install();
  }

  public static void gradientFillRect(Graphics graphics, int n, int n2, int n3, int n4, Color color, Color color2, boolean bl) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    double d = color2.getRed() - color.getRed();
    double d2 = color2.getGreen() - color.getGreen();
    double d3 = color2.getBlue() - color.getBlue();
    double d4 = color2.getAlpha() - color.getAlpha();
    if (bl) {
      for (int i = 1; i <= n4; ++i) {
        double d5 = (double)i / (double)n4;
        Color color3 = new Color(color.getRed() + (int)(d * d5), color.getGreen() + (int)(d2 * d5), color.getBlue() + (int)(d3 * d5), color.getAlpha() + (int)(d4 * d5));
        graphics2D.setPaint(color3);
        graphics2D.drawLine(n, n2 + i - 1, n + n3 - 1, n2 + i - 1);
      }
    } else {
      for (int i = 1; i <= n3; ++i) {
        double d6 = (double)i / (double)n3;
        Color color4 = new Color(color.getRed() + (int)(d * d6), color.getGreen() + (int)(d2 * d6), color.getBlue() + (int)(d3 * d6), color.getAlpha() + (int)(d4 * d6));
        graphics2D.setPaint(color4);
        graphics2D.drawLine(n + i - 1, n2, n + i - 1, n2 + n4 - 1);
      }
    }
  }

  public void paintButtonBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    int n5 = uIDefaults.getInt("EaSynth.button.arc.width");
    int n6 = uIDefaults.getInt("EaSynth.button.arc.height");
    Color color = Color.BLACK;
    color = (synthContext.getComponentState() & 1024) != 0 ? ((synthContext.getComponentState() & 4) != 0 ? uIDefaults.getColor("EaSynth.button.border.color.default.pressed") : ((synthContext.getComponentState() & 8) != 0 ? uIDefaults.getColor("EaSynth.button.border.color.default.disabled") : ((synthContext.getComponentState() & 2) != 0 ? uIDefaults.getColor("EaSynth.button.border.color.default.mouseover") : uIDefaults.getColor("EaSynth.button.border.color.default.enabled")))) : ((synthContext.getComponentState() & 4) != 0 ? uIDefaults.getColor("EaSynth.button.border.color.pressed") : ((synthContext.getComponentState() & 8) != 0 ? uIDefaults.getColor("EaSynth.button.border.color.disabled") : ((synthContext.getComponentState() & 2) != 0 ? uIDefaults.getColor("EaSynth.button.border.color.mouseover") : uIDefaults.getColor("EaSynth.button.border.color.enabled"))));
    graphics.setColor(color);
    graphics.drawRoundRect(n, n2, n3 - 1, n4 - 1, n5, n6);
  }

  public void paintToggleButtonBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    this.paintButtonBorder(synthContext, graphics, n, n2, n3, n4);
  }

  public void paintArrowButtonForeground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4, int n5) {
    StringBuilder stringBuilder = new StringBuilder("EaSynth.arrow.");
    switch (n5) {
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
    if ((synthContext.getComponentState() & 4) != 0) {
      stringBuilder.append("pressed");
    } else if ((synthContext.getComponentState() & 8) != 0) {
      stringBuilder.append("disabled");
    } else if ((synthContext.getComponentState() & 2) != 0) {
      stringBuilder.append("mouseover");
    } else {
      stringBuilder.append("enabled");
    }
    ImageIcon imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, stringBuilder.toString());
    if (imageIcon != null) {
      int n6 = imageIcon.getIconWidth();
      int n7 = imageIcon.getIconHeight();
      int n8 = (n3 - n6) / 2;
      int n9 = (n4 - n7) / 2;
      graphics.drawImage(imageIcon.getImage(), n8, n9, n8 + n6, n9 + n7, 0, 0, n6, n7, null);
    }
  }

  public void paintRootPaneBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    ImageIcon imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.rootpanel.bg.image");
    if (imageIcon != null) {
      Image image = imageIcon.getImage();
      int n5 = image.getWidth(null);
      int n6 = image.getHeight(null);
      if (n5 > 0 && n6 > 0) {
        BufferedImage bufferedImage = new BufferedImage(n5, n6, 1);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);
        TexturePaint texturePaint = new TexturePaint(bufferedImage, new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
        Graphics2D graphics2D = (Graphics2D)graphics.create();
        graphics2D.setPaint(texturePaint);
        graphics2D.fill(graphics2D.getClip());
      }
    }
  }

  public void paintProgressBarBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    block8 : {
      JProgressBar jProgressBar = (JProgressBar)synthContext.getComponent();
      if (jProgressBar.isIndeterminate() && jProgressBar.isStringPainted()) {
        boolean bl = synthContext.getStyle().getBoolean(synthContext, "EaSynth.progressbar.indeterminate.paintstring", false);
        String string = jProgressBar.getString();
        if (bl && string != null) {
          Class<?> class_ = jProgressBar.getUI().getClass();
          try {
            Method method = class_.getDeclaredMethod("paintText", SynthContext.class, Graphics.class, String.class);
            if (method == null) break block8;
            try {
              Font font = synthContext.getStyle().getFont(synthContext);
              FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
              int n5 = synthContext.getStyle().getGraphicsUtils(synthContext).computeStringWidth(synthContext, font, fontMetrics, string);
              Rectangle rectangle = jProgressBar.getBounds();
              Rectangle rectangle2 = new Rectangle(rectangle.width / 2 - n5 / 2, (rectangle.height - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2, n5, fontMetrics.getAscent() + fontMetrics.getDescent());
              Insets insets = (Insets)synthContext.getStyle().get(synthContext, "EaSynth.progressbar.bg.insets");
              if (insets == null) {
                insets = new Insets(0, 0, 0, 0);
              }
              if (jProgressBar.getOrientation() == 0 && rectangle2.y >= insets.top || jProgressBar.getOrientation() == 1 && rectangle2.x >= insets.left) {
                graphics.setColor(new Color(224, 212, 192));
                graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
              }
            }
            catch (Exception exception) {
              // empty catch block
            }
            method.setAccessible(true);
            method.invoke(jProgressBar.getUI(), synthContext, graphics, string);
            method.setAccessible(false);
          }
          catch (Exception exception) {
            exception.printStackTrace();
          }
        }
      }
    }
  }

  public void paintProgressBarBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    Serializable serializable;
    int n5;
    int n6;
    Serializable serializable2;
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    ImageIcon imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.progressbar.background.image");
    Insets insets = (Insets)synthContext.getStyle().get(synthContext, "EaSynth.progressbar.bg.insets");
    if (imageIcon != null) {
      EaSynthGraphicsUtils.drawImageWith9Grids((Graphics)graphics, (Image)imageIcon.getImage(), (int)n, (int)n2, (int)(n + n3), (int)(n2 + n4), (Insets)(insets == null ? new Insets(0, 0, 0, 0) : insets), (boolean)true);
    } else {
      serializable = UIManager.getDefaults();
      serializable2 = serializable.getColor("EaSynth.progressbar.background.color");
      if (serializable2 != null) {
        n6 = serializable.getInt("EaSynth.progressbar.arc.width");
        n5 = serializable.getInt("EaSynth.progressbar.arc.height");
        graphics2D.setPaint((Paint)((Object)serializable2));
        graphics2D.fillRoundRect(n, n2, n3, n4, n6, n5);
      }
    }
    serializable = (JProgressBar)synthContext.getComponent();
    serializable2 = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.progressbar.indication.image");
    if (serializable2 != null && n3 > 0 && n4 > 0) {
      n6 = serializable2.getIconWidth();
      n5 = serializable2.getIconHeight();
      if (0 == serializable.getOrientation()) {
        graphics2D.setPaint(new Color(0, 0, 0, 130));
        graphics2D.drawLine(n + n6 / 2, (n2 + n4) / 2 - 1, n + n3 - n6 / 2, (n2 + n4) / 2 - 1);
        graphics2D.setPaint(new Color(255, 255, 255, 130));
        graphics2D.drawLine(n + n6 / 2, (n2 + n4) / 2 + 1, n + n3 - n6 / 2, (n2 + n4) / 2 + 1);
        graphics2D.setPaint(new Color(0, 0, 0, 180));
        graphics2D.drawLine(n + n6 / 2, (n2 + n4) / 2, n + n3 - n6 / 2, (n2 + n4) / 2);
      } else {
        graphics2D.setPaint(new Color(0, 0, 0, 130));
        graphics2D.drawLine((n + n3) / 2 - 1, n2 + n5 / 2, (n + n3) / 2 - 1, n2 + n4 - n5 / 2);
        graphics2D.setPaint(new Color(255, 255, 255, 130));
        graphics2D.drawLine((n + n3) / 2 + 1, n2 + n5 / 2, (n + n3) / 2 + 1, n2 + n4 - n5 / 2);
        graphics2D.setPaint(new Color(0, 0, 0, 180));
        graphics2D.drawLine((n + n3) / 2, n2 + n5 / 2, (n + n3) / 2, n2 + n4 - n5 / 2);
      }
    }
  }

  public void paintProgressBarForeground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4, int n5) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    ImageIcon imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.progressbar.indication.image");
    JProgressBar jProgressBar = (JProgressBar)synthContext.getComponent();
    boolean bl = (synthContext.getComponentState() & 8) != 0;
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.progressbar.line.color");
    if (color == null) {
      color = new Color(255, 255, 255, 130);
    }
    if (imageIcon != null && n3 > 0 && n4 > 0) {
      int n6 = imageIcon.getIconWidth();
      int n7 = imageIcon.getIconHeight();
      if (0 == n5) {
        if (!jProgressBar.isIndeterminate() && n3 >= n6) {
          graphics2D.setPaint(color.brighter());
          graphics2D.drawLine(n + n6 / 2, (n2 + n4) / 2 - 1, n + n3 - n6 / 2, (n2 + n4) / 2 - 1);
          graphics2D.setPaint(color.darker());
          graphics2D.drawLine(n + n6 / 2, (n2 + n4) / 2 + 1, n + n3 - n6 / 2, (n2 + n4) / 2 + 1);
          graphics2D.setPaint(color);
          graphics2D.drawLine(n + n6 / 2, (n2 + n4) / 2, n + n3 - n6 / 2, (n2 + n4) / 2);
        }
        if (!bl) {
          int n8;
          int n9 = n + n3 - n6;
          if (jProgressBar.isIndeterminate()) {
            n8 = jProgressBar.getWidth() - n3;
            int n10 = n6 / 2 + (jProgressBar.getWidth() - n6) * n / n8;
            n9 = n10 - n6 / 2;
          }
          if (n9 >= 0) {
            n8 = n2 + (n4 - n7) / 2;
            graphics.drawImage(imageIcon.getImage(), n9, n8, n9 + n6, n8 + n7, 0, 0, n6, n7, null);
          }
        }
      } else {
        if (!jProgressBar.isIndeterminate() && n4 >= n7) {
          graphics2D.setPaint(color.brighter());
          graphics2D.drawLine((n + n3) / 2 - 1, n2 + n7 / 2, (n + n3) / 2 - 1, n2 + n4 - n7 / 2);
          graphics2D.setPaint(color.darker());
          graphics2D.drawLine((n + n3) / 2 + 1, n2 + n7 / 2, (n + n3) / 2 + 1, n2 + n4 - n7 / 2);
          graphics2D.setPaint(color);
          graphics2D.drawLine((n + n3) / 2, n2 + n7 / 2, (n + n3) / 2, n2 + n4 - n7 / 2);
        }
        if (!bl) {
          int n11 = n + (n3 - n6) / 2;
          int n12 = n2;
          if (jProgressBar.isIndeterminate()) {
            int n13 = jProgressBar.getHeight() - n4;
            int n14 = n7 / 2 + (jProgressBar.getHeight() - n7) * n2 / n13;
            n12 = n14 - n7 / 2;
          }
          graphics.drawImage(imageIcon.getImage(), n11, n12, n11 + n6, n12 + n7, 0, 0, n6, n7, null);
        }
      }
    }
  }

  public void paintInternalFrameBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.internalframe.main.color");
    if (color == null) {
      color = Color.GRAY;
    }
    graphics.setColor(color.darker());
    graphics.drawLine(n, n2 + 1, n, n4 - 2);
    graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n4 - 2);
    graphics.drawLine(n + 1, n2, n + n3 - 2, n2);
    graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
    graphics.setColor(color.brighter());
    graphics.drawLine(n + 2, n2 + 3, n + 2, n4 - 4);
    graphics.drawLine(n + n3 - 3, n2 + 3, n + n3 - 3, n4 - 4);
    graphics.drawLine(n + 3, n2 + 2, n + n3 - 4, n2 + 2);
    graphics.drawLine(n + 3, n2 + n4 - 3, n + n3 - 4, n2 + n4 - 3);
  }

  public void paintInternalFrameTitlePaneBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.internalframe.main.color");
    if (color == null) {
      color = Color.GRAY;
    }
    graphics.setColor(color.darker());
    graphics.drawLine(n, n2 + n4 - 1, n + n3, n2 + n4 - 1);
  }

  public void paintInternalFrameBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.internalframe.main.color");
    if (color == null) {
      color = Color.GRAY;
    }
    graphics.setColor(color);
    graphics.fillRect(n, n2, n3, n4);
  }

  public void paintInternalFrameTitlePaneBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    this.paintInternalFrameBackground(synthContext, graphics, n, n2, n3, n4);
    String string = System.getProperty("java.version");
    if (string.compareTo("1.6") < 0) {
      Container container;
      JInternalFrame jInternalFrame;
      int n5 = synthContext.getStyle().getInt(synthContext, "InternalFrameTitlePane.titleSpacing", 0);
      for (container = synthContext.getComponent(); !(container instanceof JInternalFrame) && container != null; container = container.getParent()) {
      }
      if (container instanceof JInternalFrame && (jInternalFrame = (JInternalFrame)container).getTitle() != null) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(jInternalFrame.getTitle(), 20 + n5, 15);
      }
      for (container = synthContext.getComponent(); !(container instanceof JInternalFrame.JDesktopIcon) && container != null; container = container.getParent()) {
      }
      if (container instanceof JInternalFrame.JDesktopIcon && (jInternalFrame = ((JInternalFrame.JDesktopIcon)container).getInternalFrame()).getTitle() != null) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(jInternalFrame.getTitle(), 20 + n5, 15);
      }
    }
  }

  public void paintScrollBarTrackBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    JScrollBar jScrollBar;
    Color color;
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color2 = uIDefaults.getColor("EaSynth.scrollbar.track.sidecolor");
    if (color2 == null) {
      color2 = Color.white;
    }
    if ((color = uIDefaults.getColor("EaSynth.scrollbar.track.centercolor")) == null) {
      color = Color.LIGHT_GRAY;
    }
    if ((jScrollBar = (JScrollBar)synthContext.getComponent()).getOrientation() == 1) {
      EaSynthPainter.gradientFillRect(graphics, n, n2, n3 / 2, n4, color2, color, false);
      EaSynthPainter.gradientFillRect(graphics, n + n3 / 2, n2, n3 / 2, n4, color, color2, false);
    } else {
      EaSynthPainter.gradientFillRect(graphics, n, n2, n3, n4 / 2, color2, color, true);
      EaSynthPainter.gradientFillRect(graphics, n, n2 + n4 / 2, n3, n4 / 2, color, color2, true);
    }
  }

  public void paintScrollBarTrackBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.scrollbar.track.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    graphics.setColor(color);
    graphics.draw3DRect(n, n2, n3 - 1, n4 - 1, false);
  }

  public void paintScrollBarThumbBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4, int n5) {
    JScrollBar jScrollBar;
    Color color;
    boolean bl;
    UIDefaults uIDefaults = UIManager.getDefaults();
    int n6 = uIDefaults.getInt("EaSynth.scrollbar.thumb.borderthick");
    Color color2 = uIDefaults.getColor("EaSynth.scrollbar.thumb.sidecolor");
    if (color2 == null) {
      color2 = Color.LIGHT_GRAY;
    }
    if ((color = uIDefaults.getColor("EaSynth.scrollbar.thumb.centercolor")) == null) {
      color = Color.white;
    }
    boolean bl2 = bl = (jScrollBar = (JScrollBar)synthContext.getComponent()).getOrientation() == 1;
    if (bl) {
      EaSynthPainter.gradientFillRect(graphics, n, n2, n3 / 2, n4, color2, color, false);
      EaSynthPainter.gradientFillRect(graphics, n + n3 / 2, n2, n3 / 2, n4, color, color2, false);
    } else {
      EaSynthPainter.gradientFillRect(graphics, n, n2, n3, n4 / 2, color2, color, true);
      EaSynthPainter.gradientFillRect(graphics, n, n2 + n4 / 2, n3, n4 / 2, color, color2, true);
    }
    ImageIcon imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.scrollbar.thumb.image");
    if (imageIcon != null) {
      int n7 = imageIcon.getIconWidth();
      int n8 = imageIcon.getIconHeight();
      if (!bl && n3 >= n7 + n6 * 2 || bl && n4 >= n8 + n6 * 2) {
        int n9 = n + (n3 - n7) / 2;
        int n10 = n2 + (n4 - n8) / 2;
        graphics.drawImage(imageIcon.getImage(), n9, n10, n9 + n7, n10 + n8, 0, 0, n7, n8, null);
      }
    }
  }

  public void paintScrollBarThumbBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4, int n5) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    int n6 = uIDefaults.getInt("EaSynth.scrollbar.thumb.borderthick");
    Color color = uIDefaults.getColor("EaSynth.scrollbar.thumb.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    if ((synthContext.getComponentState() & 8) != 0) {
      color = Color.LIGHT_GRAY;
    } else if ((synthContext.getComponentState() & 2) != 0) {
      color = color.brighter();
    }
    graphics.setColor(color);
    JScrollBar jScrollBar = (JScrollBar)synthContext.getComponent();
    if (jScrollBar.getOrientation() == 1) {
      if (n4 > n6 * 2) {
        graphics.fill3DRect(n, n2, n3, n6, true);
        graphics.fill3DRect(n, n2 + n4 - n6, n3, n6, true);
      }
    } else if (n3 > n6 * 2) {
      graphics.fill3DRect(n, n2, n6, n4, true);
      graphics.fill3DRect(n + n3 - n6, n2, n6, n4, true);
    }
  }

  public void paintScrollBarBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.scrollbar.bgcolor");
    if (color == null) {
      color = Color.WHITE;
    }
    graphics.setColor(color);
    graphics.fill3DRect(n, n2, n3, n4, true);
  }

  public void paintScrollBarBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.scrollbar.bordercolor");
    if (color == null) {
      color = Color.DARK_GRAY;
    }
    graphics.setColor(color);
    graphics.draw3DRect(n, n2, n3 - 1, n4 - 1, true);
  }

  public void paintSplitPaneDividerBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color = uIDefaults.getColor("EaSynth.splitpane.divider.maincolor");
    if (color == null) {
      color = Color.LIGHT_GRAY;
    }
    graphics.setColor(color);
    graphics.fill3DRect(n, n2, n3, n4, true);
    ImageIcon imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.splitpane.divider.image");
    if (imageIcon != null) {
      int n5 = imageIcon.getIconWidth();
      int n6 = imageIcon.getIconHeight();
      JSplitPane jSplitPane = (JSplitPane)synthContext.getComponent();
      if (jSplitPane.getOrientation() == 1 && n4 >= n6 * 9) {
        int n7 = n + (n3 - n5) / 2;
        int n8 = n2 + (n4 - n6 * 3) / 2;
        graphics.drawImage(imageIcon.getImage(), n7, n8, n7 + n5, n8 + n6, 0, 0, n5, n6, null);
        graphics.drawImage(imageIcon.getImage(), n7, n8 + n6, n7 + n5, n8 + n6 * 2, 0, 0, n5, n6, null);
        graphics.drawImage(imageIcon.getImage(), n7, n8 + n6 * 2, n7 + n5, n8 + n6 * 3, 0, 0, n5, n6, null);
      } else if (n3 >= n5 * 9) {
        int n9 = n + (n3 - n5 * 3) / 2;
        int n10 = n2 + (n4 - n6) / 2;
        graphics.drawImage(imageIcon.getImage(), n9, n10, n9 + n5, n10 + n6, 0, 0, n5, n6, null);
        graphics.drawImage(imageIcon.getImage(), n9 + n5, n10, n9 + n5 * 2, n10 + n6, 0, 0, n5, n6, null);
        graphics.drawImage(imageIcon.getImage(), n9 + n5 * 2, n10, n9 + n5 * 3, n10 + n6, 0, 0, n5, n6, null);
      }
    }
  }

  public void paintSplitPaneDragDivider(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4, int n5) {
    this.paintSplitPaneDividerBackground(synthContext, graphics, n, n2, n3, n4);
  }

  public void paintTableHeaderBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    Graphics2D graphics2D = (Graphics2D)graphics.create();
    Color color = new Color(255, 255, 255, 90);
    Color color2 = new Color(60, 60, 60, 90);
    JTableHeader jTableHeader = (JTableHeader)synthContext.getComponent();
    TableColumnModel tableColumnModel = jTableHeader.getColumnModel();
    int n5 = tableColumnModel.getColumnCount();
    int n6 = 0;
    for (int i = 0; i < n5; ++i) {
      graphics2D.setPaint(color);
      graphics2D.drawLine(n + n6, n2, n + n6, n2 + n4 - 1);
      graphics2D.setPaint(color2);
      graphics2D.drawLine(n + n6, n2, n + (n6 += tableColumnModel.getColumn(i).getWidth() - 1), n2 + n4 - 1);
      n6 += tableColumnModel.getColumnMargin();
    }
  }

   * WARNING - Removed try catching itself - possible behaviour change.


  private void fixComboBoxPopup(SynthContext synthContext) {
    Map<JComponent, String> map = MANAGED_OBJECT_MAP;
    synchronized (map) {
      if (!MANAGED_OBJECT_MAP.containsKey(synthContext.getComponent()) && synthContext.getComponent() instanceof JComboBox) {
        JComboBox jComboBox = (JComboBox)synthContext.getComponent();
        boolean bl = false;
        for (PopupMenuListener popupMenuListener : jComboBox.getPopupMenuListeners()) {
          if (!(popupMenuListener instanceof EaSynthComboBoxPopupMenuListener)) continue;
          bl = true;
          break;
        }
        if (!bl) {
          jComboBox.addPopupMenuListener((PopupMenuListener)new EaSynthComboBoxPopupMenuListener());
          MANAGED_OBJECT_MAP.put(jComboBox, jComboBox.getClass().getName());
        }
      }
    }
  }

  public void paintComboBoxBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    this.fixComboBoxPopup(synthContext);
    ImageIcon imageIcon = null;
    imageIcon = (synthContext.getComponentState() & 8) != 0 ? (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.combobox.bg.image.disabled") : (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.combobox.bg.image.enabled");
    if (imageIcon != null) {
      Image image = imageIcon.getImage();
      int n5 = image.getWidth(null);
      int n6 = image.getHeight(null);
      graphics.drawImage(imageIcon.getImage(), n, n2, n + n3, n2 + n4, 0, 0, n5, n6, null);
    }
  }

  public void paintComboBoxBorder(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    this.fixComboBoxPopup(synthContext);
    super.paintComboBoxBorder(synthContext, graphics, n, n2, n3, n4);
  }

  public void paintMenuBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    Color color;
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color2 = uIDefaults.getColor("EaSynth.menu.bg.color.normal");
    if (color2 == null) {
      color2 = Color.LIGHT_GRAY;
    }
    if ((color = uIDefaults.getColor("EaSynth.menu.bg.color.selected")) == null) {
      color = Color.GRAY;
    }
    JMenu jMenu = (JMenu)synthContext.getComponent();
    if ((synthContext.getComponentState() & 512) != 0) {
      graphics.setColor(color);
      graphics.fillRect(n, n2, n3, n4);
    } else if ((synthContext.getComponentState() & 8) != 0) {
      if (!(jMenu.getParent() instanceof JMenuBar)) {
        graphics.setColor(color2);
        graphics.fillRect(n, n2, n3, n4);
      }
    } else if (!(jMenu.getParent() instanceof JMenuBar)) {
      graphics.setColor(color2);
      graphics.fillRect(n, n2, n3, n4);
    }
  }

  public void paintSliderThumbBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4, int n5) {
    ImageIcon imageIcon;
    imageIcon = (synthContext.getComponentState() & 8) != 0 ? (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.slider.thumb.image.disabled") : ((synthContext.getComponentState() & 2) != 0 ? (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.slider.thumb.image.mouseover") : (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.slider.thumb.image.normal"));
    if (imageIcon != null) {
      int n6 = imageIcon.getIconWidth();
      int n7 = imageIcon.getIconHeight();
      int n8 = n + (n3 - n6) / 2;
      int n9 = n2 + (n4 - n7) / 2;
      graphics.drawImage(imageIcon.getImage(), n8, n9, n8 + n6, n9 + n7, 0, 0, n6, n7, null);
    }
  }

  public void paintToolBarBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    JToolBar jToolBar;
    Color color;
    Color color2;
    UIDefaults uIDefaults = UIManager.getDefaults();
    Color color3 = uIDefaults.getColor("EaSynth.toolbar.bg.color1");
    if (color3 == null) {
      color3 = Color.LIGHT_GRAY;
    }
    if ((color2 = uIDefaults.getColor("EaSynth.toolbar.bg.color2")) == null) {
      color2 = Color.WHITE;
    }
    if ((color = uIDefaults.getColor("EaSynth.toolbar.bg.color3")) == null) {
      color = Color.LIGHT_GRAY;
    }
    if ((jToolBar = (JToolBar)synthContext.getComponent()).getOrientation() == 1) {
      EaSynthPainter.gradientFillRect(graphics, n, n2, n3 / 2, n4, color3, color2, false);
      EaSynthPainter.gradientFillRect(graphics, n + n3 / 2, n2, n3 / 2, n4, color2, color, false);
    } else {
      EaSynthPainter.gradientFillRect(graphics, n, n2, n3, n4 / 2, color3, color2, true);
      EaSynthPainter.gradientFillRect(graphics, n, n2 + n4 / 2, n3, n4 / 2, color2, color, true);
    }
  }

  public void paintPopupMenuBackground(SynthContext synthContext, Graphics graphics, int n, int n2, int n3, int n4) {
    ImageIcon imageIcon;
    JPopupMenu jPopupMenu = (JPopupMenu)synthContext.getComponent();
    JPanel jPanel = (JPanel)jPopupMenu.getParent();
    BufferedImage bufferedImage = (BufferedImage)jPanel.getClientProperty("POPUP_BACKGROUND_IMAGE");
    if (bufferedImage != null) {
      graphics.drawImage(bufferedImage, n, n2, null);
    }
    if ((imageIcon = (ImageIcon)synthContext.getStyle().getIcon(synthContext, "EaSynth.popup.menu.bg")) != null) {
      EaSynthGraphicsUtils.drawImageWith9Grids((Graphics)graphics, (Image)imageIcon.getImage(), (int)n, (int)n2, (int)(n + n3), (int)(n2 + n4), (Insets)synthContext.getStyle().getInsets(synthContext, null), (boolean)true);
    }
  }
}
*/
