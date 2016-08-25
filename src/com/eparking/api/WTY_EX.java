package com.eparking.api;

import com.eparking.callback.*;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface WTY_EX extends StdCallLibrary 
{
	public static String strFilePath = System.getProperty("user.dir") + "\\lib\\WTY.dll";
	public static WTY_EX INSTANCE = (WTY_EX)Native.loadLibrary(strFilePath, WTY_EX.class);
	/**
	 * 
	 * @param nPort
	 *            连接相机的端口，现默认为8080
	 * @param hWndHandle
	 *            接收消息的窗体句柄，当为NULL时，表示无窗体，可忽略。
	 * @param uMsg
	 *            用户自定义消息，当hWndHandle不为NULL时使用
	 * @param chServerIP
	 *            相机的IP地址
	 * 
	 * @return 0 相机连接成功， 1 相机连接失败
	 */
	public int WTY_InitSDK(int nPort, Void hWndHandle, int uMsg, String chServerIP);
	/**
	 * 断开所有已经连接WTY，释放资源
	 */
	public void WTY_QuitSDK();

	/**
	 * 触发识别
	 * @param pCameraIP
	 *            相机IP
	 * @param nPort
	 *            连接相机的端口，现默认为8080
	 * 
	 * @return 0 成功， 非0 失败
	 */
	public int WTY_SetTrigger(String pCameraIP, int nCameraPort);

	/**
	 * 1：连接状态回调函数的注册，必须在连接设备函数(WTY_InitSDK)之前 调用。 2：此方式是被动获取PC 与设备之间的连接状态
	 * 
	 * @param obj
	 *            回调接口
	 */
	public void WTY_RegWTYConnEvent(IOnConnectStatus obj);

	/**
	 * 1: 一台PC 连接多台设备时，此函数仅需实现一次。当区分不同设备的识别结 果时，可以通过输出参数中recResult 中的chWTYIP
	 * 来区分。
	 * 
	 * @param recResult
	 *            可为NULL
	 * @param obj
	 *            回调类，类名、方法名必须与Demo中的保持一致，否则会注册失败。
	 */
	public void WTY_RegDataExEvent(IOnGetDataEx obj);
	

	/**
	 * 1: 注册获取Jpeg流的回调函数
	 *    
	 * @param obj
	 *            1:此功能目前适用于V6.0.0.0版本,
	 * 				V5.1.2.0、V5.2.1.0、V5.2.2.0不能使用此功能
	 */

	public void WTY_RegJpegEvent(IOnJpegCallback obj);
	
	/*
	 * 获取连接状态
	 */
	public int WTY_CheckStatus(String DevIp);
}
