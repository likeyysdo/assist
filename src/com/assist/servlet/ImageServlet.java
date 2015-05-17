package com.assist.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			BufferedImage bi = new BufferedImage(100, 48, BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			Color c = new Color(0, 0, 0);
			g.setColor(c);
			g.fillRect(0, 0, 100, 48);
			
			char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
			Random r = new Random();
			StringBuffer sb = new StringBuffer();
			int index,len=ch.length;
			for(int i=0;i<4;i++){
				index = r.nextInt(len);
				g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				g.drawString(ch[index]+"", (i*20)+5, 30);
				sb.append(ch[index]);
			}
			
			request.getSession().setAttribute("piccode", sb.toString() );
			ImageIO.write(bi, "JPG", response.getOutputStream());
	}
}
