package com.company;
import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

/**
 * Created by jackl on 2016/9/20.
 */
public class HttpClientTool {


    public void uploadFile(File file, String url) {
        if (!file.exists()) {
            return;
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            //FilePart�������ϴ��ļ�����
            FilePart fp = new FilePart("filedata", file);
            Part[] parts = { fp };

            //����MIME���͵�����httpclient����ȫ��MulitPartRequestEntity���а�װ
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// ��������ʱ��
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                System.out.println(postMethod.getResponseBodyAsString());
            } else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ͷ�����
            postMethod.releaseConnection();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpClientTool test = new HttpClientTool();
        test.uploadFile(new File("e:/1G520.jpg"),
                "http://localhost:8000/wservice/tricheerDeviceService/iv8899/video/2234567898765432/1G520.JPG");
    }

}
