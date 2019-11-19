package org.cs.dolphin.common.utils;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(),
                        "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String uploadFile(String url, String param, String file) {

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String restult = null;
        try {
            HttpPost httppost = new HttpPost(url);

            FileBody bin2 = new FileBody(new File(file));

            StringBody comment = new StringBody(URLEncoder.encode(param,
                    "UTF-8"), ContentType.APPLICATION_JSON);

            MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
            reqEntity.setCharset(Charset.forName("UTF-8"));// 设置请求的编码格式
            reqEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置浏览器兼容模式

            reqEntity.addPart("file", bin2);// file2为请求后台的File upload;属性
            reqEntity.addPart("param", comment);// filename1为请求后台的普通参数;属性

            HttpEntity entity = reqEntity.build();
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                restult = EntityUtils.toString(resEntity);// httpclient自带的工具类读取返回数据
                EntityUtils.consume(resEntity);
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (Exception ignore) {

            }
        }
        return restult;
    }

    public static String postFile(String url, File file, String params, String filename) {

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String restult = null;
        try {
            HttpPost httppost = new HttpPost(url);

            FileBody bin2 = new FileBody(file, ContentType.DEFAULT_BINARY, filename);

            StringBody comment = new StringBody(URLEncoder.encode(params,
                    "UTF-8"), ContentType.APPLICATION_JSON);

            MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
            reqEntity.setCharset(Charset.forName("UTF-8"));// 设置请求的编码格式
            reqEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置浏览器兼容模式

            reqEntity.addPart("uploadfile", bin2);// file2为请求后台的File upload;属性
            reqEntity.addPart("param", comment);// filename1为请求后台的普通参数;属性

            HttpEntity entity = reqEntity.build();
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                restult = EntityUtils.toString(resEntity);// httpclient自带的工具类读取返回数据
                EntityUtils.consume(resEntity);
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (Exception ignore) {

            }
        }
        return restult;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(doPostJson(
                "http://221.226.156.110/edu/httpResource/queryChannels",
                "{'org_id':1}"));

    }
}
