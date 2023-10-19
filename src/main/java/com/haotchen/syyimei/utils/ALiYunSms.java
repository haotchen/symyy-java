package com.haotchen.syyimei.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 阿里云短信
 */
public class ALiYunSms {

    private static String accessKeyId;
    private static String accessKeySecret;

    {
        Properties properties = new Properties();
        // 读取 INI 文件
        try {
            properties.load(new FileReader("config.ini" ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 获取键值对
        accessKeyId = properties.getProperty("ali-acc");
        accessKeySecret = properties.getProperty("ali-sec");
    }
    private static String regionId = "cn-beijing";

    private static DefaultAcsClient initClient() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    public static void sendSms(String phoneNumber, String templateCode, String templateParam) throws ClientException {
        DefaultAcsClient client = initClient();

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName("丽人美预约小程序表单验证");
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println("res"+ response.getCode() + response.getMessage());
            System.out.println("短信发送成功，短信ID：" + response.getBizId());
        } catch (ServerException e) {
            System.out.println("短信发送失败，错误码：" + e.getErrCode() + "，错误信息：" + e.getErrMsg());
        } catch (ClientException e) {
            System.out.println("短信发送失败，错误码：" + e.getErrCode() + "，错误信息：" + e.getErrMsg());
        }
    }
}
