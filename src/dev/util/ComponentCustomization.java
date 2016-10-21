package dev.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ComponentCustomization {
	public static void buttonCustomization(JButton button, int fontSize){
		button.setForeground(new Color(62,120,204));
		button.setBackground(Color.white);
		button.setFont(new Font("Tahoma", Font.BOLD, fontSize));
//		button.setFocusPainted(false);
	}

//	public static void buttonHorizontalCentralization(JButton button, int buttons_qtd, int place){
//
//		int fontSize = button.getFont().getSize();
//
//		int textSize = button.getText().length();
//
//		int xPosition = MainFrame.MAIN_DIMENSION.width/(1 + buttons_qtd);
//		xPosition = xPosition*(place + 1);
//
//		int yPosition = MainFrame.MAIN_DIMENSION.height/2;
//
//		button.setBounds(xPosition - fontSize*textSize/2,
//				yPosition - fontSize/2,
//				fontSize*textSize,
//				fontSize + 10);
//	}
//
//	public static void buttonVerticalCentralization(JButton button, int buttons_qtd, int place){
//
//		int fontSize = button.getFont().getSize();
//
//		int textSize = button.getText().length();
//
//		int xPosition = MainFrame.MAIN_DIMENSION.width/2;
//
//		int yPosition = MainFrame.MAIN_DIMENSION.height/(1 + buttons_qtd);
//		yPosition = yPosition*(place+1);
//
//		button.setBounds(xPosition - fontSize*textSize/2,
//				yPosition - fontSize/2,
//				fontSize*textSize,
//				fontSize + 10);
//	}
//
//	public static void panelCustomization(JPanel panel){
//		panel.setBackground(new Color(0,0,255));
//
//		int x = MainFrame.MAIN_DIMENSION.width/2;
//		int y = MainFrame.MAIN_DIMENSION.height/3;
//
//		int width = MainFrame.MAIN_DIMENSION.width/3;
//		int height = MainFrame.MAIN_DIMENSION.height/3;
//
//		panel.setBounds(x - width/2, y - height/2, width, height);
//	}
}
