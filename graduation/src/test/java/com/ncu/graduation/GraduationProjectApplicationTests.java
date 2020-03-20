package com.ncu.graduation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

@SpringBootTest
class GraduationProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void print(){
        byte width = 85;
        byte height = 28;
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        Random random = new Random();
        g.setColor(getRandomColor());
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Microsoft YaHei", 2, 24));
        String sRand = "";
        for(int responseOutputStream = 0; responseOutputStream < 4; ++responseOutputStream) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
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
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("D://1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "png", fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
        return color;
    }

}
