package com.eparking.data;

import com.sun.jna.Structure;


public class PlateResult extends Structure 
{
	public static class ByReference extends PlateResult implements Structure.ByReference {}  
	public static class ByValue extends PlateResult implements Structure.ByValue {}  
	
	public byte[] chWTYIP = new byte[16]; 				// 相机IP
	public int nFullLen = 0; 								// 全景图像数据大小
	public int nPlateLen; 								// 车牌图像数据大小
	public byte[] chFullImage = new byte[200000 - 312]; // 全景图像数据
	public byte[] chPlateImage = new byte[10000]; 		// 车牌图像数据
	public byte[] chColor = new byte[8]; 				// 车牌颜色
	public byte[] chLicense = new byte[16]; 			// 车牌号码
	public PlateLocation.ByValue pcLocation; 			// 车牌在图像中的坐标
	public CameraTime.ByValue shootTime; 				// 识别出车牌的时间			
	public byte[] reserved = new byte[256];
	
}
