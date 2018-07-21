package util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class MoblieMessageUtil {

    // ��Ʒ����:��ͨ�Ŷ���API��Ʒ,�����������滻
    private static final String product = "Dysmsapi";
    // ��Ʒ����,�����������滻
    private static final String domain = "dysmsapi.aliyuncs.com";

    // �˴���Ҫ�滻�ɿ������Լ���AK(�ڰ����Ʒ��ʿ���̨Ѱ��)
    private static String accessKeyId = "LTAIjGaCQVmSmFVe";
    private static String accessKeySecret = "LHewF77fJzAYB5IJpcPxKd63BlLxmf";
    private static String signName = "������";
    private static String identifyingTempleteCode = "666666";
    private static String registTempleteCode = "\t\n" + "SMS_136690194";

    public static void init(String accessKeyId, String accessKeySecret, String signName, String identifyingTempleteCode,
                            String registTempleteCode) {
        MoblieMessageUtil.accessKeyId = accessKeyId;
        MoblieMessageUtil.accessKeySecret = accessKeySecret;
        MoblieMessageUtil.signName = signName;
        MoblieMessageUtil.identifyingTempleteCode = identifyingTempleteCode;
        MoblieMessageUtil.registTempleteCode = registTempleteCode;
    }

    public static void sendCode(String phone,String code) throws ClientException {
        MoblieMessageUtil.init("LTAIjGaCQVmSmFVe", "LHewF77fJzAYB5IJpcPxKd63BlLxmf", "������", "SMS_136690194", "SMS_136690194");
        // ������
        SendSmsResponse response = MoblieMessageUtil.sendIdentifyingCode(phone, code);
        System.out.println("���Žӿڷ��ص�����----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
    }

    public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode)
            throws ClientException {

        // ������������ʱʱ��
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // ��ʼ��acsClient,�ݲ�֧��region��
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // ��װ�������-��������������̨-�ĵ���������
        SendSmsRequest request = new SendSmsRequest();

        // ����:�������ֻ���
        request.setPhoneNumbers(mobile);
        // ����:����ǩ��-���ڶ��ſ���̨���ҵ�
        request.setSignName(signName);
        // ����:����ģ��-���ڶ��ſ���̨���ҵ�
        request.setTemplateCode(templateCode);

        // ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
        request.setTemplateParam(templateParam);

        // ѡ��-���ж�����չ��(�����������û�����Դ��ֶ�)
        // request.setSmsUpExtendCode("90997");

        // ��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
        request.setOutId("yourOutId");

        // hint �˴����ܻ��׳��쳣��ע��catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    public static SendSmsResponse sendNewUserNotice(String mobile, String username, String password) throws ClientException {

            return sendSms(mobile, "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}",
                    registTempleteCode);

    }

    public static SendSmsResponse sendIdentifyingCode(String mobile, String code) throws ClientException {

            return sendSms(mobile, "{\"code\":\"" + code + "\"}", identifyingTempleteCode);

    }
}
