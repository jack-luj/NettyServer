#ifndef _WTY_H
#define _WTY_H

#if !defined(WIN32) && !defined(__stdcall)
#define __stdcall
#endif

#ifndef WIN32
typedef unsigned int UINT;
typedef void *HWND;
#endif

#ifdef __cplusplus
extern "C" {
#endif
	
#define BIG_PICSTREAM_SIZE 200000-312		//����ϴ�ȫ��ͼռ���ڴ������С
#define SMALL_PICSTREAM_SIZE 10000			//����ϴ����ƽ�ͼռ���ڴ������С

/* ���ʱ�� */
typedef struct 
{
	int Year;			/* �� 	*/
	int Month;			/* �� 	*/
	int Day;			/* �� 	*/
	int Hour;			/* ʱ 	*/
	int Minute;			/* �� 	*/
	int Second;			/* �� 	*/
	int Millisecond;	/* ΢�� */
}camera_time;

/* ʶ�������� */
typedef struct 
{
	int 	Left;	/* �� */
	int 	Top;	/* �� */
	int 	Right;	/* �� */
	int 	Bottom;	/* �� */
}plate_location;

/* ʶ���� */
typedef struct 
{	
	char		chWTYIP[16];						/* ���IP 				*/
	int			nFullLen;							/* ȫ��ͼ�����ݴ�С 	*/
	int			nPlateLen;							/* ����ͼ�����ݴ�С 	*/
	char		chFullImage[BIG_PICSTREAM_SIZE];	/* ȫ��ͼ������ 		*/
	char		chPlateImage[SMALL_PICSTREAM_SIZE];	/* ����ͼ������ 		*/
	char		chColor[8];							/* ������ɫ 			*/
	char		chLicense[16];						/* ���ƺ��� 			*/
	plate_location 	pcLocation;						/* ������ͼ���е����� 	*/
	camera_time		shootTime;						/* ʶ������Ƶ�ʱ�� 	*/
	int			nConfidence;						/* ���ƿ��Ŷ�			*/
	int			nTime;								/* ʶ���ʱ				*/
	int			nDirection;							/*	���Ʒ���			*/
	char		reserved[256-12];
}plate_result;

/* Jpeg���ص�����ÿһ֡jpeg���ݽṹ�� */
typedef struct 
{
	char			chIp[16];				/*���IP												*/
	char			*pchBuf;				/*ÿһ֡jpeg���ݻ�����									*/
	unsigned int	nLen;					/*��ǰjpeg֡�����ݳ���									*/
	int				nStatus;				/* ��ǰjpeg֡����״̬�� 0-����, ��0-������ 				*/
	char			reserved[128];			/* ����		  											*/
}DevData_info;

/************************************************************************/
/* WTY_InitSDK: �������												*/
/*		Parameters:														*/
/*			nPort[in]:		��������Ķ˿ڣ���Ĭ��Ϊ8080				*/
/*			hWndHandle[in]:	������Ϣ�Ĵ���������ΪNULLʱ����ʾ�޴���  */
/*			uMsg[in]:		�û��Զ�����Ϣ����hWndHandle��ΪNULLʱ��	*/
/*							��⵽���µĳ���ʶ������׼���õ�ǰ����	*/
/*							��������Ϣ����::PostMessage ������		*/
/*							hWndHandle����uMsg��Ϣ������WPARAM����Ϊ0��	*/
/*							LPARAM����Ϊ0								*/
/*			chServerIP[in]:	�����IP��ַ								*/
/*		Return Value:   int												*/
/*							0	������ӳɹ�							*/
/*							1	�������ʧ��							*/
/*		Notice:   														*/
/*				������ûص��ķ�ʽ��ȡ����ʱ��hWndHandle���ΪNULL��	*/
/*				uMsgΪ0������ע��ص�������֪ͨ���µ����ݣ�				*/
/*				��֮�����������յ���Ϣʱ������WTY_GetVehicleInfo��ȡ	*/
/*				���ݡ�													*/
/************************************************************************/
int __stdcall WTY_InitSDK(UINT nPort,HWND hWndHandle,UINT uMsg,char *chServerIP);


/************************************************************************/
/* WTY_QuitSDK: �Ͽ������Ѿ������豸���ͷ���Դ							*/
/*		Parameters:														*/
/*		Return Value:   void											*/
/************************************************************************/
void  __stdcall WTY_QuitSDK();


/***********************************************************************************/
/* �ص�����:ѭ����Ⲣ֪ͨ����豸ͨѶ״̬�Ļص�����						       */
/*		Parameters:														           */
/*			chWTYIP[out]:		�����豸IP								           */
/*			nStatus[out]:		�豸״̬��0��ʾ�����쳣���豸�쳣��			       */
/*										  1��ʾ�����������豸������		    	   */
/*										  2��ʾ�����������豸�����ӣ������������  */
/*		Return Value:   void											           */
/***********************************************************************************/
typedef void (*WTYConnectCallback)(char *chWTYIP,UINT nStatus);


/************************************************************************/
/* WTY_RegWTYConnEvent: ע�����ͨѶ״̬�Ļص�����						*/
/*		Parameters:														*/
/*			WTYConnect[in]:		ConnectCallback���ͻص�����				*/
/*		Return Value:   void											*/
/************************************************************************/
void __stdcall WTY_RegWTYConnEvent (WTYConnectCallback WTYConnect);


/************************************************************************/
/* WTY_CheckStatus: �����������豸��ͨѶ״̬							*/
/*		Parameters:														*/
/*			chWTYIP[in]:		Ҫ���������IP						*/
/*		Return Value:   int												*/
/*							0	����									*/
/*							1	���粻ͨ								*/
/************************************************************************/
int __stdcall WTY_CheckStatus (char *chWTYIP);


/************************************************************************/
/* �ص�����: ��ȡʶ�����Ļص�����										*/
/*		Parameters:														*/
/*			chWTYIP[out]:		����ʶ�������豸IP					*/
/*			chPlate[out]:		���ƺ���								*/
/*			chColor[out]:		������ɫ								*/
/*			chFullImage[out]:	ȫ��ͼ����								*/
/*			nFullLen[out]:		ȫ��ͼ���ݳ���							*/
/*			chPlateImage[out]:	����ͼ����								*/
/*			nPlateLen[out]:		����ͼ���ݳ���							*/
/*		Return Value:   void											*/
/************************************************************************/
typedef void (*WTYDataCallback)(char *chWTYIP, 
							    char *chPlate,
							    char *chColor,
								char *chFullImage,
								int nFullLen, 
								char *chPlateImage,
							    int nPlateLen);

								
/************************************************************************/
/* �ص�����: ע�����ʶ�����ݻص�����									*/
/*		Parameters:														*/
/*			recResult[out]:		ʶ��������							*/
/*		Return Value:   void											*/
/************************************************************************/
typedef void (*WTYDataExCallback)(plate_result *recResult);


/************************************************************************/
/* WTY_RegDataEvent: ע���ȡʶ�����Ļص�����							*/
/*		Parameters:														*/
/*			WTYData[in]:		����ʶ�����Ļص�������ָ��			*/
/*		Return Value:   void											*/
/************************************************************************/
void __stdcall WTY_RegDataEvent(WTYDataCallback WTYData);


/************************************************************************/
/* WTY_RegDataExEvent: ע���ȡʶ�����Ļص�����						*/
/*		Parameters:														*/
/*			WTYData[in]:		����ʶ�����Ļص�������ָ��			*/
/*		Return Value:   void											*/
/************************************************************************/
void __stdcall WTY_RegDataExEvent(WTYDataExCallback WTYDataEx);


/************************************************************************/
/* 	����: ��Ϣ��ʽ��ȡָ��IP���ʶ������								*/
/*		  ��WTY_initSDK�����������˴���������Ϣʱ��					*/
/*		  ��Ҫ����Ϣ���������е��ô˺�����������ȡʶ������			*/
/*		Parameters:														*/
/*			chWTYIP[in]:		������Ϣ����ȡָ��IP�豸ʶ������		*/
/*			chPlate[in]:		���ƺ���								*/
/*			chColor[in]:		������ɫ								*/
/*			chFullImage[in]:	ȫ��ͼ����								*/
/*			nFullLen[in]:		ȫ��ͼ���ݳ���							*/
/*			chPlateImage[in]:	����ͼ����								*/
/*			nPlateLen[in]:		����ͼ���ݳ���							*/
/*		Return Value:   int												*/
/*							0	��ȡ�ɹ�								*/
/*							1	��ȡʧ��								*/
/*		Notice:   														*/
/*			�������˴������ݲ���ʱ�����Զ�Ӧ������ΪNULL������Ϊ-1��	*/
/*			��û���γ�����ʱ�����Զ�Ӧ����ΪNULL������Ϊ0				*/
/************************************************************************/
int __stdcall WTY_GetVehicleInfo(char *chWTYIP,
					   char *chPlate,	
					   char *chColor,
					   void *chFullImage ,	
					   int *nFullLen, 
					   void *chPlateImage,
					   int *nPlateLen);


/************************************************************************/
/* 	����: ��Ϣ��ʽ��ȡָ��IP�����ʶ������							*/
/*		  ��WTY_initSDK�����������˴���������Ϣʱ��					*/
/*		  ��Ҫ����Ϣ���������е��ô˺�����������ȡʶ������			*/
/*		Parameters:														*/
/*			chWTYIP[in]:		������Ϣ����ȡָ��IP�豸ʶ������		*/
/*			chPlate[in]:		���ƺ���								*/
/*			chColor[in]:		������ɫ								*/
/*			chFullImage[in]:	ȫ��ͼ����								*/
/*			nFullLen[in]:		ȫ��ͼ���ݳ���							*/
/*			chPlateImage[in]:	����ͼ����								*/
/*			nPlateLen[in]:		����ͼ���ݳ���							*/
/*			chTwoImage[in]:		��ֵ������								*/
/*			nTwoLen[in]:		��ֵ�����ݳ���							*/
/*		Return Value:   int												*/
/*							0	��ȡ�ɹ�								*/
/*							1	��ȡʧ��								*/
/*		Notice:   														*/
/*			�������˴������ݲ���ʱ�����Զ�Ӧ������ΪNULL������Ϊ-1��	*/
/*			��û���γ�����ʱ�����Զ�Ӧ����ΪNULL������Ϊ0				*/
/************************************************************************/
int __stdcall WTY_GetVehicleInfoEx(char *chWTYIP,
								 char *chPlate,	
								 char *chColor,
								 void *chFullImage ,
								 int *nFullLen,
								 void *chPlateImage,
								 int *nPlateLen,
								 void *chTwoImage,
								 int *nTwoLen);


/************************************************************************/
/* WTY_SetSavePath: ����û���Ҫ��̬���Զ�����ͼƬ������ͨ���ú������ñ�*/
/*					��ͼƬ��·����										*/
/*		Parameters:														*/
/*			chSavePath[in]:	�ļ��洢·������"\\"�������磺"D:\\Image\\"	*/
/*		Return Value:   void											*/
/*																		*/
/*		Notice:   														*/
/*			ȫ��ͼ��ָ��Ŀ¼\\�豸IP\\�����գ�YYYYMMDD��\\FullImage\\	*/
/*									ʱ����-����__��ɫ_���ƺ���__.jpg	*/
/*			����ͼ��ָ��Ŀ¼\\�豸IP\\�����գ�YYYYMMDD��\\PlatelImage\\	*/
/*									ʱ����-����__��ɫ_���ƺ���__.jpg	*/
/************************************************************************/
void __stdcall WTY_SetSavePath (char *chSavePath);


/************************************************************************/
/* WTY_SetTrigger: ����ʶ��												*/
/*		Parameters:														*/
/*			pCameraIP[in]:			Ҫ����������豸��IP				*/
/*			nCameraPort[in]:		�˿�,Ĭ��8080						*/
/*		Return Value:													*/
/*					0	�����ɹ�����									*/
/*				  ��0	ʧ��											*/
/************************************************************************/	
int __stdcall WTY_SetTrigger(char *pCameraIP, int nCameraPort);


/************************************************************************/
/* WTY_SetTransContent: ��������豸�ϴ�����					        */
/*		Parameters:														*/
/*			pCameraIP[in]:		Ҫ���õ��豸IP							*/
/*			nCameraPort[in]:	�˿�,Ĭ��8080							*/
/*			nFullImg[in]:		ȫ��ͼ��0��ʾ������1��ʾ��				*/
/*			nPlateImg[in]:		����ͼ��0��ʾ������1��ʾ��				*/
/*		Return Value:   int												*/
/*							0	�ɹ�									*/
/*						  ��0	ʧ��									*/
/************************************************************************/
int __stdcall WTY_SetTransContent (char *pCameraIP, int nCameraPort, int nFullImg, int nPlateImg);


/************************************************************************/
/* ����˵��: ���Ƽ̵����ıպ�											*/
/*		Parameters:														*/
/*			pCameraIP[in]:			���IP								*/
/*			nCameraPort[in]:		�˿�,Ĭ��8080						*/
/*		Return Value:   int												*/
/*							0	���óɹ�								*/
/*						  ��0	ʧ��									*/
/*		Notice:   														*/
/*				ͨ���˹��ܣ�������PC��ͨ��һ����豸�������Ƶ�բ��̧��	*/
/*				�豸�̵�������ź�Ϊ���������źš�						*/
/************************************************************************/
int __stdcall WTY_SetRelayClose(char *pCameraIP, int nCameraPort);


/************************************************************************/
/* �ص�����: ��ȡJpeg���Ļص�����										*/
/*		Parameters:														*/
/*			JpegInfo[out]:		JPEG��������Ϣ							*/
/*		Return Value:   void											*/
/*																		*/
/*		Notice:															*/
/*			1:һ̨PC���Ӷ�̨�豸ʱ���˺�������ʵ��һ�Ρ������ֲ�ͬ		*/
/*			�豸��JPEG��ʱ������ͨ�����������DevData_info�е�chIp��	*/
/*			����.														*/
/*			2:�˹���Ŀǰ������V5.5.3.0��V6.0.0.0�����ϰ汾,				*/
/*			  V5.2.1.0��V5.2.2.0��V5.2.6.0�Ȱ汾����ʹ�ô˹���			*/
/************************************************************************/
typedef void (*WTYJpegCallback)(DevData_info *JpegInfo);


/************************************************************************/
/* WTY_RegJpegEvent: ע���ȡJpeg���Ļص�����							*/
/*		Parameters:														*/
/*			JpegInfo[in]:		WTYJpegCallback���ͻص�����				*/
/*		Return Value:   void											*/
/*																		*/
/*		Notice:															*/
/*			1:һ̨PC���Ӷ�̨�豸ʱ���˺�������ʵ��һ�Ρ������ֲ�ͬ		*/
/*			�豸��JPEG��ʱ������ͨ�����������DevData_info�е�chIp��	*/
/*			����.														*/
/*			2:�˹���Ŀǰ������V5.5.3.0��V6.0.0.0�����ϰ汾,				*/
/*			  V5.2.1.0��V5.2.2.0��V5.2.6.0�Ȱ汾����ʹ�ô˹���			*/
/************************************************************************/
void __stdcall WTY_RegJpegEvent(WTYJpegCallback JpegInfo);


/************************************************************************/
/* �ص�����: ��ȡ������Ϣ�Ļص�����										*/
/*		Parameters:														*/
/*			alarmInfo[out]:		������Ϣ								*/
/*		Return Value:   void											*/
/*																		*/
/*		Notice:															*/
/*			һ̨PC���Ӷ�̨�豸ʱ���˺�������ʵ��һ�Ρ������ֲ�ͬ�豸	*/
/*			��Alarmʱ������ͨ�����������DevData_info�е�chIp������		*/
/*																		*/
/*		Notice:															*/
/*			1:һ̨PC���Ӷ�̨�豸ʱ���˺�������ʵ��һ�Ρ������ֲ�ͬ		*/
/*			�豸��JPEG��ʱ������ͨ�����������DevData_info�е�chIp��	*/
/*			����.														*/
/*			2:�˹���Ŀǰ������V5.5.3.0��V6.0.0.0�����ϰ汾,				*/
/*			  V5.2.1.0��V5.2.2.0��V5.2.6.0�Ȱ汾����ʹ�ô˹���			*/
/************************************************************************/
typedef void (*WTYAlarmCallback)(DevData_info *alarmInfo);


/************************************************************************/
/* WTY_RegAlarmEvent: ע���ȡ������Ϣ�Ļص�����						*/
/*		Parameters:														*/
/*			AlarmInfo[in]:		WTYAlarmCallback���ͻص�����			*/
/*		Return Value:   void											*/
/*																		*/
/*		Notice:															*/
/*			1:һ̨PC���Ӷ�̨�豸ʱ���˺�������ʵ��һ�Ρ������ֲ�ͬ		*/
/*			�豸��JPEG��ʱ������ͨ�����������DevData_info�е�chIp��	*/
/*			����.														*/
/*			2:�˹���Ŀǰ������V5.5.3.0��V6.0.0.0�����ϰ汾,				*/
/*			  V5.2.1.0��V5.2.2.0��V5.2.6.0�Ȱ汾����ʹ�ô˹���			*/
/************************************************************************/

void __stdcall WTY_RegAlarmEvent(WTYAlarmCallback AlarmInfo);


/************************************************************************/
/* WTY_RS485Send: RS485͸������											*/
/*		Parameters:														*/
/*			pCameraIP[in]				����豸IP��ַ					*/
/*			nPort[in]					�˿�,Ĭ��8080					*/
/*			chData[in]					��Ҫ��������ݿ���׵�ַ		*/
/*			nSendLen[in]				��Ҫ��������ݿ���ֽ���		*/
/*		Return Value:   int												*/
/*							0	�ɹ�									*/
/*						  ��0	ʧ��									*/
/*		notice��														*/
/*				1���û�ͨ���˽ӿڣ�������������ݣ�����豸��ԭ��������	*/
/*				ͨ��RS485�ӿ�ת����ȥ�����ͻ����ӵ��ⲿ�豸�ϡ�			*/
/*				2��ʹ�ô˹���ǰ����Ҫ����ʾDEMO�����ý����ϣ���������� */
/*				�ܴ���ʶ����(Ĭ��S485����ʶ����)��					*/
/************************************************************************/
int __stdcall WTY_RS485Send(char *pCameraIP, int nCameraPort, char *chData, int nSendLen);


/************************************************************************/
/* ����: Jpeg����Ϣ������ʼ��											*/
/*		Parameters:														*/
/*			hWndHandle[in]:	������Ϣ�Ĵ�����							*/
/*			uMsg[in]:		�û��Զ�����Ϣ								*/
/*							��⵽�����ݲ�׼���û��������ݺ�			*/
/*							��::PostMessage ������hWndHandle����uMsg	*/
/*							��Ϣ������WPARAM����Ϊ0��LPARAM����Ϊ0		*/
/*			chIp[in]:		���IP��ַ								*/
/*		Return Value:   int												*/
/*							0	��ȡ�ɹ�								*/
/*							1	��ȡʧ��								*/
/************************************************************************/
int __stdcall WTY_JpegMessageInit(HWND hWndHandle,UINT uMsg,char *chIp);


/************************************************************************/
/* 	����: ��Ϣ��ʽ��ȡָ��IP�������Jpeg������							*/
/*		Parameters:														*/
/*			chIp[in]:			���IP��ַ								*/
/*			chJpegBuf[in]:		�洢JPEG��buffer						*/
/*			nJpegBufLen[in]:	��ȡ����JPEG���ݳ���					*/
/*		Return Value:   int												*/
/*							0	��ȡ�ɹ�								*/
/*							1	��ȡʧ��								*/
/*		Notice:   														*/
/*			ʹ�ô˺���ǰ���ȵ���WTY_JpegMessageInit���������Զ�����Ϣ	*/
/************************************************************************/
int __stdcall WTY_GetJpegStream(char *chIp, char *chJpegBuf, char *nJpegBufLen);


/************************************************************************/
/* 	����: ����IP��ַ���Ͽ�ָ���豸����									*/
/*		Parameters:														*/
/*			pCameraIP[in]:			���IP��ַ							*/
/*		Return Value:   int												*/
/*							0	��ȡ�ɹ�								*/
/*							1	��ȡʧ��								*/
/************************************************************************/
int __stdcall WTY_QuitDevice(char *pCameraIP);


/************************************************************************/
/* WTY_SetNetworkCardBind: �ֶ���ָ������IP							*/
/*		Parameters:														*/
/*			pCameraIP[in]		Ҫ�󶨵�����IP��ַ						*/
/*		Return Value:   int												*/
/*							0	�ɹ�									*/
/*						  ��0	ʧ��									*/
/*		notice:��PC�����ڶ����������ʱ���ֲ������Ϊ������ʱ����ͨ����	*/
/*				�����������ͨѶ������IP								*/
/************************************************************************/
int __stdcall WTY_SetNetworkCardBind(char *pCameraIP);


/////////////////////////////////////////////////////////////////////////////

#ifdef __cplusplus
}
#endif

#endif