package com.eparking.ui;

import com.eparking.api.*;
import com.eparking.callback.*;
import com.eparking.data.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Wintone 
{
	private static String ip1;
	private static String ip2;
	private short port = 8080;
	public final static int max_jpg_len = 200000 - 312;

	public static IOnJpegCallback jpegStream = new OnJpegStream();
	public static IOnConnectStatus connStatus = new OnConnectStatus();
	public static IOnGetDataEx getData = new OnGetDataEx();
	public static PhotoFrame pF;
	public static byte[] byteA = new byte[max_jpg_len];
	public static byte[] byteB = new byte[max_jpg_len];

	public static boolean InitOrNot = false;

	// JPEG 流显示
	public static class OnJpegStream implements IOnJpegCallback 
	{
		public void callback(DevData_info.ByReference JpegInfo) {
			byte bzero = 0;
			String strJpegIp = new String(JpegInfo.chWTYIP).replaceAll(
					"[^0-9.]", " ").trim();
			String trueJpegIp = strJpegIp;

			while (trueJpegIp.endsWith(".")) 
			{
				trueJpegIp = strJpegIp.substring(0, trueJpegIp.length() - 2);
			}
			// 设备IP1的动态图像显示
			if ((ip1.trim()).equals(strJpegIp)) {
				if ((JpegInfo.nStatus == 0)) {
					// 正常状态
					if ((JpegInfo.nLen > 0) && (JpegInfo.pchBuf != null)) {
						Arrays.fill(byteA, bzero);
						System.arraycopy(JpegInfo.pchBuf.getByteArray(0,
								JpegInfo.nLen), 0, byteA, 0, JpegInfo.nLen);

						// 控件显示动态图像
						if (pF != null && pF.jPanel1 != null) {
							pF.jpegShow(pF.jPanel1, byteA);
						}
					}
				}
				// 设备IP2的动态图像显示
			} else if ((ip2.trim()).equals(strJpegIp)) {
				if ((JpegInfo.nStatus == 0)) {
					// 正常状态
					if ((JpegInfo.nLen > 0) && (JpegInfo.pchBuf != null)) {
						Arrays.fill(byteB, bzero);
						System.arraycopy(JpegInfo.pchBuf.getByteArray(0,
								JpegInfo.nLen), 0, byteB, 0, JpegInfo.nLen);
						// 控件显示动态图像
						if (pF != null && pF.jPanel2 != null) {
							pF.jpegShow(pF.jPanel2, byteB);

						}
					}
				}
			}

		}
	}

	// 连接状态显示
	public static class OnConnectStatus implements IOnConnectStatus {
		public void callback(String chWTYIP, int nStatus) 
		{
			// 设备的连接状态
			if (nStatus != 1) {
				System.out.println(chWTYIP + "连接断开");
			}else{
				System.out.println(chWTYIP + "连接变化");
			}
		}
	}

	// 识别结果的接收
	public static class OnGetDataEx implements IOnGetDataEx {
		public void callback(PlateResult.ByReference plateResult) {
			String strPlateIp = new String(plateResult.chWTYIP).trim();
			// 设备IP1的识别结果处理
			if ((ip1.trim()).equals(strPlateIp)) {
				// 识别出车牌号码
				if ((new String(plateResult.chLicense)).trim().length() > 0) {
					System.out.println("识别结果:" + strPlateIp + "," + "车牌："
							+ new String(plateResult.chLicense).trim() + ","
							+ "颜色：" + new String(plateResult.chColor).trim());
					pF.jTextField5.setText(new String(plateResult.chLicense).trim());
				} else {
					System.out.println("识别结果:" + strPlateIp + " 无牌车");
					pF.jTextField5.setText("无牌车");
				}
				// 控件显示图像
				if (pF != null && pF.jPanel1 != null) {
					pF.jpegShow(pF.jPanel1, plateResult.chFullImage);
				}
				// 设备IP2的识别结果处理
			} else if ((ip2.trim()).equals(strPlateIp)) {
				// 识别出车牌号码
				if ((new String(plateResult.chLicense)).trim().length() > 0) {
					System.out.println("识别结果:" + strPlateIp + "," + "车牌："
							+ new String(plateResult.chLicense).trim() + ","
							+ "颜色：" + new String(plateResult.chColor).trim());

					pF.jTextField6.setText(new String(plateResult.chLicense).trim());
				} else {
					System.out.println("识别结果:" + strPlateIp + " 无牌车");
					pF.jTextField6.setText("无牌车");
				}
				// 控件显示图像
				if (pF != null && pF.jPanel2 != null) {
					pF.jpegShow(pF.jPanel2, plateResult.chFullImage);
				}
			}
		}
	}

	// 相机设备的初始化
	public void init() {

		ip1 = pF.jTextField1.getText();
		ip2 = pF.jTextField2.getText();

		try {
			WTY_EX.INSTANCE.WTY_RegWTYConnEvent(connStatus);
			WTY_EX.INSTANCE.WTY_RegDataExEvent(getData);
			WTY_EX.INSTANCE.WTY_RegJpegEvent(jpegStream);

			// 初始化设备IP1
			int errorCode = WTY_EX.INSTANCE.WTY_InitSDK(port, null, 0, ip1);
			if (errorCode == 0) {
				System.out.println("初始化Wintone成功，IP=" + ip1);
				InitOrNot = true;
			} else {
				System.out.println("初始化Wintone失败，IP=" + ip1+"  errorCode:"+errorCode);
			}

			// 初始化设备IP2
			errorCode = WTY_EX.INSTANCE.WTY_InitSDK(port, null, 0, ip2);
			if (errorCode == 0) {
				System.out.println("初始化Wintone成功，IP=" + ip2);
				InitOrNot = true;
			} else {
				System.out.println("初始化Wintone失败，IP=" + ip2+"  errorCode:"+errorCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 断开所有相机，释放资源
	public void Exit() 
	{
		WTY_EX.INSTANCE.WTY_QuitSDK();
//		System.gc();
	}

	// 模拟地感，触发相机
	public void triger(String pCameraIP) {
		int errorCode = WTY_EX.INSTANCE.WTY_SetTrigger(pCameraIP, port);
		if (errorCode == 0) {
			System.out.println(pCameraIP + "模拟触发成功");
		} else {
			System.out.println(pCameraIP + "模拟触发失败");
		}
	}

	
	public final static void main(String[] args) {
		final Wintone wintone = new Wintone();

		pF = new PhotoFrame();
		pF.setVisible(true);
		System.out.println(WTY_EX.strFilePath);
		pF.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (InitOrNot) {
					wintone.Exit();
					System.out.println("连接已断开！");
					pF.jPanel1 = null;
				}
				InitOrNot = false;
				super.windowClosing(e);
			}
		});

		// “开始”按钮触发事件
		pF.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wintone.init();
			}
		});

		// “结束”按钮触发事件
		pF.jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (InitOrNot) {
					wintone.Exit();
					System.out.println("连接已断开！");
				}
				InitOrNot = false;
			}
		});

		// “模拟触发”按钮触发事件
		pF.jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wintone.triger(ip1);
				wintone.triger(ip2);
			}
		});
	}
}
