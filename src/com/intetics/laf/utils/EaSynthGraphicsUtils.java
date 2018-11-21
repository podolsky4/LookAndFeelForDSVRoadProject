/*
package com.intetics.laf.utils;

*/
/*
 * Decompiled with CFR 0_132.
 *//*

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthGraphicsUtils;

public class EaSynthGraphicsUtils extends SynthGraphicsUtils {

  public void drawLine(SynthContext synthContext, Object object, Graphics graphics, int n, int n2, int n3, int n4) {
    UIDefaults uIDefaults = UIManager.getDefaults();
    int n5 = uIDefaults.getInt("EaSynth.graphicsutils.drawline.arc.width");
    int n6 = uIDefaults.getInt("EaSynth.graphicsutils.drawline.arc.height");
    Color color = uIDefaults.getColor("EaSynth.graphicsutils.drawline.color");
    if (color != null) {
      graphics.setColor(color);
    }
    if ("Tree.verticalLine".equals(object)) {
      int n7 = n4 - n2 - n6 / 2;
      if (n7 > 0) {
        super.drawLine(synthContext, object, graphics, n, n2, n3, n2 + n7);
      } else {
        super.drawLine(synthContext, object, graphics, n, n2, n3, n4);
      }
    } else if ("Tree.horizontalLine".equals(object)) {
      if (Math.abs(n3 - n) >= n5 / 2) {
        if (n < n3) {
          graphics.drawArc(n, n4 - n6, n5, n6, 180, 90);
          graphics.drawLine(n + n5 / 2, n4, n3, n4);
        } else {
          super.drawLine(synthContext, object, graphics, n, n2, n3, n4);
        }
      } else {
        super.drawLine(synthContext, object, graphics, n, n2, n3, n4);
      }
    } else {
      super.drawLine(synthContext, object, graphics, n, n2, n3, n4);
    }
  }

  public static void drawImageWith9Grids(Graphics graphics, Image image, int n, int n2, int n3, int n4, Insets insets, boolean bl) {
    int n5 = image.getWidth(null);
    int n6 = image.getHeight(null);
    graphics.drawImage(image, n, n2, n + insets.left, n2 + insets.top, 0, 0, insets.left, insets.top, null);
    graphics.drawImage(image, n3 - insets.right, n2, n3, n2 + insets.top, n5 - insets.right, 0, n5, insets.top, null);
    graphics.drawImage(image, n, n4 - insets.bottom, n + insets.left, n4, 0, n6 - insets.bottom, insets.left, n6, null);
    graphics.drawImage(image, n3 - insets.right, n4 - insets.bottom, n3, n4, n5 - insets.right, n6 - insets.bottom, n5, n6, null);
    graphics.drawImage(image, n + insets.left, n2, n3 - insets.right, n2 + insets.top, insets.left, 0, n5 - insets.right, insets.top, null);
    graphics.drawImage(image, n + insets.left, n4 - insets.bottom, n3 - insets.right, n4, insets.left, n6 - insets.bottom, n5 - insets.right, n6, null);
    graphics.drawImage(image, n, n2 + insets.top, n + insets.left, n4 - insets.bottom, 0, insets.top, insets.left, n6 - insets.bottom, null);
    graphics.drawImage(image, n3 - insets.right, n2 + insets.top, n3, n4 - insets.bottom, n5 - insets.right, insets.top, n5, n6 - insets.bottom, null);
    if (bl) {
      graphics.drawImage(image, n + insets.left, n2 + insets.top, n3 - insets.right, n4 - insets.bottom, insets.left, insets.top, n5 - insets.right, n6 - insets.bottom, null);
    }
  }
}*/
