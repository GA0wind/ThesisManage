package com.ncu.graduation.util;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Data
public class VerifyCode {
    private String code;
    private BufferedImage image;

    public VerifyCode(String code, BufferedImage image) {
        this.code = code;
        this.image = image;
    }

    public static VerifyCode print(){
        byte width = 85;
        byte height = 28;
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        Random random = new Random();
        g.setColor(getRandomColor());
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Microsoft YaHei", 2, 24));
        StringBuilder sRand = new StringBuilder();
        for(int responseOutputStream = 0; responseOutputStream < 4; ++responseOutputStream) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(getRandomColor());
            g.drawString(rand, 13 * responseOutputStream + 16, 23);
        }
        // 6.画干扰线
        for (int i = 0; i < 4; i++) {
            // 设置随机颜色
            g.setColor(getRandomColor());
            // 随机画线
            g.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }
        g.dispose();

        return new VerifyCode(sRand.toString(),image);

    }
    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
    }

}
