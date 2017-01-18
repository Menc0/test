package net.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import net.sf.json.JSONObject;
import net.spring.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class Client {
	@Test
	public void HttpPostData() {  
	      try { 
	          HttpClient httpclient = new DefaultHttpClient();  
	          String uri = "http://localhost:8080/springMVC/user/getUserByName/cwh"; 
	          //KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
	          //FileInputStream instream = new FileInputStream(new File("E:/keys/cwhkey"));
	          //密匙库的密码
	          //trustStore.load(instream, "caiwenhao".toCharArray());
	          //注册密匙库
	         // SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
	          //不校验域名
	          //socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	          //Scheme sch = new Scheme("https", 8443, socketFactory);//8443端口
	          //httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	          HttpPost httppost = new HttpPost(uri);   
	          //添加http头信息 
	         // httppost.addHeader("Authorization", "your token"); //认证token 
	          httppost.addHeader("Content-Type", "application/json"); 
	          //httppost.addHeader("User-Agent", "imgfornote");  
	          JSONObject obj = new JSONObject();
	          obj.put("username", "cwh"); 
	          obj.put("password", "password"); 
	          httppost.setEntity(new StringEntity(obj.toString()));     
	          HttpResponse response;  
	          response = httpclient.execute(httppost);  
	          //检验状态码，如果成功接收数据  
	          int code = response.getStatusLine().getStatusCode();  
	          System.out.println(code+"code");
	          if (code == 200) {  
	              String rev = EntityUtils.toString(response.getEntity());//返回json格式： {"id": "","name": ""}         
	              obj= JSONObject.fromObject(rev);
	              System.out.println(obj.get("username"));
	              User user = (User)JSONObject.toBean(obj,User.class);
	              System.out.println("返回数据==="+user.toString());
	          } 
	          } catch (ClientProtocolException e) { 
	        	  e.printStackTrace();
	          } catch (IOException e) {  
	        	  e.printStackTrace();
	          } catch (Exception e) { 
	        	  e.printStackTrace();
	          } 
	     }
}
