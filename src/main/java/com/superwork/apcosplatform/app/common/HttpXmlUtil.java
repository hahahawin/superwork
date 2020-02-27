package com.superwork.apcosplatform.app.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * XML短信接口
 * @author Administrator
 * @date 2014-1-22
 */
public class HttpXmlUtil extends Basic{


	/**
	 * 发送短信方法使用document 对象方法封装XML字符串
	 * @param phone 接收号码
	 * @param content 接收内容
	 * @return smsresult
	 */
	public static SmsResult send(String phone, String content)throws Exception {
		String posturl = url+"/http/sms/Submit";
		content="验证码："+content;
		Map<String, String> params = new LinkedHashMap<String, String>();
		HttpXmlUtil docXml = new HttpXmlUtil();
			String message = "<?xml version='1.0' encoding='UTF-8'?><message>"
				+ "<account>" + userName + "</account>" + "<password>"
				+ MD5Encode(password) + "</password>"
				+ "<msgid></msgid><phones>" + phone + "</phones><content>"
				+ content + "</content>"
				+ "<sign>"+sign+"</sign><subcode></subcode><sendtime></sendtime></message>";
		 message=docXml.DocXml(userName, MD5Encode(password), msgid, phone,content, sign, subcode, sendtime);
		params.put("message", message);
		String resp = doPost(posturl, params);
		System.out.println("发送报告==="+resp);

		SmsResult smsresult = ParserXmlUtil.parserSendXml(resp);
		return smsresult;
	}

	/**
	 * 查询短信状态报告(接收人是否真正接收到短信)
	 * @param msgid 短信编号(发送短信的时候返回，唯一的)
	 * @param phone 接收号码
	 * @return smsresult
	 */
	@SuppressWarnings("unused")
	public static SmsResult getReport(String msgid, String phone)throws Exception {
		String posturl = url+"/http/sms/Report";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message>"
				+ "<account>" + userName + "</account>" + "<password>"
				+ MD5Encode(password) + "</password>"
				+ "<msgid>"+msgid+"</msgid><phone>"+phone+"</phone></message>";
		params.put("message", message);
		String resp = doPost(posturl, params);
		System.out.println("状态报告=========状态报告==================状态报告==============="+resp);
		SmsResult smsresult = ParserXmlUtil.parserReportXml(resp);
		return smsresult;
	}
	/**
	 * 查询账户余额和短信剩余条数
	 * @return smsresult
	 */
	@SuppressWarnings("unused")
	public static SmsResult getBalance()throws Exception {
		String posturl = url+"/http/sms/Balance";
		Map<String, String> params = new LinkedHashMap<String, String>();
		String message = "<?xml version='1.0' encoding='UTF-8'?><message><account>"
				+ userName
				+ "</account>"
				+ "<password>"
				+ MD5Encode(password)
				+ "</password></message>";
		params.put("message", message);
		String resp = doPost(posturl, params);
		System.out.println("余额报告="+resp);
		SmsResult smsresult = ParserXmlUtil.parserBalanceXml(resp);
		return smsresult;
	}

	public static void main(String[] args) {
		try {
			HttpXmlUtil.send("18002587157","excuse me!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 *
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	private static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

		// 设置Post数据
		if (!params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}

			postMethod.setRequestBody(data);

		}
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	/**
	 * 使用document 对象封装XML
	 * @param userName
	 * @param pwd
	 * @param msgid
	 * @param phone
	 * @param contents
	 * @param sign
	 * @param subcode
	 * @param sendtime
	 * @return
	 */
	public String DocXml(String userName, String pwd, String msgid, String phone, String contents, String sign, String subcode, String sendtime) {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element message = doc.addElement("message");
		Element account = message.addElement("account");
		account.setText(userName);
		Element password = message.addElement("password");
		password.setText(pwd);
		Element msgid1 = message.addElement("msgid");
		msgid1.setText(msgid);
		Element phones = message.addElement("phones");
		phones.setText(phone);
		Element content = message.addElement("content");
		content.setText(contents);
		Element sign1 = message.addElement("sign");
		sign1.setText(sign);
		Element subcode1 = message.addElement("subcode");
		subcode1.setText(subcode);
		Element sendtime1 = message.addElement("sendtime");
		sendtime1.setText(sendtime);
		return message.asXML();
		//System.out.println(message.asXML());

	}

	// MD5加密函数
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}



    /**
     * 极速短信API
     */
    public static final String APPKEY = "3c42b7070c722b9f";     // 你的appkey

    public static final String URL = "https://api.jisuapi.com/sms/send";

    public static String doGet(String phone, String name) throws Exception {
        String msgUrl = URL + "?mobile=" + phone + "&content="
                + URLEncoder.encode("尊敬的会员:" + name + "，APCOS标准应用平台祝你身体快乐！【APCOS标准应用平台】", "utf-8")
                + "&appkey=" + APPKEY;
        HttpGet httpGet = new HttpGet(msgUrl);
        return execute(httpGet);
    }

    public static String doPostBy(String url, Map<String, String> param) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
        Set<String> keySet = param.keySet();
        for (String key : keySet) {
            arrayList.add(new BasicNameValuePair(key, param.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
        return execute(httpPost);
    }

    private static String execute(HttpRequestBase request) throws IOException, ClientProtocolException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if (200 == response.getStatusLine().getStatusCode()) {
            System.out.println("短信发送成功");
            return EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
        } else {
            System.out.println("短信发送失败");
            System.out.println(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")));
        }
        return "";
    }
}
