package com.eparking.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class JpegPanel extends javax.swing.JPanel {

	public static final long serialVersionUID = 1L;
	public  BufferedImage image1;

	public JpegPanel() 
	{
		setBackground(new java.awt.Color(51, 51, 51));
	}
	
	public void RefreshImage(byte[] Image) 
	{
		try 
		{
			//将Bytes数组转为Image：}
			image1 = ImageIO.read(new ByteArrayInputStream(Image));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g1) 
	{
		int x = 0;
		int y = 0;
		Graphics g = (Graphics) g1;
		
		super.paintComponent(g1);
		if (null == image1) 
		{
			System.out.println("image1 == null");
			return;
		}
		g.drawRect(x, y, 423, 367);
		g.drawImage(image1, x, y, 423, 367/*image1.getWidth(this), image1.getHeight(this)*/, this);
		g = null;
	}
}

