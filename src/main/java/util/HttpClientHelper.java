package util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : suiyuan
 * @description : http工具
 * @date : Created in 2020-01-29 18:20
 * @modified by :
 **/
public class HttpClientHelper {

    private static final Logger logger = Logger.getLogger(HttpClientHelper.class);

    //##################获取（get）方法##############################

    public static void main(String[] args) throws IOException {
        String html = HttpClientHelper.get("http://news.163.com/special/epidemic/");
        System.out.println(html);
    }


    /**
     * HTTP GET请求
     *
     * @param url 请求URL
     * @return 响应内容
     */
    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * HTTP GET请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return 响应内容
     */
    public static String get(String url, Map<String, String> headers) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach(httpGet::setHeader);
        }
        return execute(httpClient, httpGet);
    }


    public static void download(String urlString, String outputPath) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.connect();

        File file = new File(outputPath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            String line;
            int num = 1;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                if ((num++ % 1000) == 0) {
                    writer.flush();
                }
            }
        }
    }

    //##################提交（post）方法##############################


    /**
     * HTTP POST请求
     *
     * @param url 请求URL
     * @return 响应内容
     */
    public static String post(String url) throws IOException {
        return postRaw(url, null, null);
    }

    /**
     * HTTP POST请求提交JSON数据
     *
     * @param url      请求URL
     * @param jsonData JSON类型的数据
     * @return 响应内容
     */
    public static String postJson(String url, String jsonData) throws IOException {
        return postJson(url, null, jsonData);
    }

    /**
     * HTTP POST请求提交JSON数据
     *
     * @param url      请求URL
     * @param headers  请求头
     * @param jsonData JSON类型的数据
     * @return 响应内容
     */
    public static String postJson(String url, Map<String, String> headers, String jsonData) throws IOException {
        if (headers == null || headers.size() <= 0) {
            headers = new HashMap<>(1);
        }
        headers.put("Content-type", "application/json");
        return postRaw(url, headers, jsonData);
    }

    /**
     * HTTP POST请求---参数以原生字符串进行提交
     *
     * @param url  请求URL
     * @param data 提交数据
     * @return 响应内容
     */
    public static String postRaw(String url, String data) throws IOException {
        return postRaw(url, null, data);
    }

    /**
     * HTTP POST请求---参数以原生字符串进行提交
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param data    提交数据
     * @return 响应内容
     */
    public static String postRaw(String url, Map<String, String> headers, String data) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        if (data != null && data.trim().length() > 0) {
            //设置参数
            StringEntity requestEntity = new StringEntity(data, "utf-8");
            httpPost.setEntity(requestEntity);
        }
        if (headers != null && headers.size() > 0) {
            headers.forEach((key, value) -> httpPost.setHeader(key, value));
        }
        return execute(httpClient, httpPost);
    }

    /**
     * HTTP POST请求---参数以Form表单键值对的形式提交
     *
     * @param url  请求URL
     * @param data 表单数据
     * @return 响应内容
     */
    public static String postForm(String url, Map<String, String> data) throws IOException {
        return postForm(url, null, data);
    }

    /**
     * HTTP POST请求---参数以Form表单键值对的形式提交
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param data    表单数据
     * @return 响应内容
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, String> data) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((key, value) -> httpPost.setHeader(key, value));
        }
        if (data != null && data.size() > 0) {
            //设置参数
            List<NameValuePair> params = new ArrayList<>(data.size());
            data.forEach((key, value) -> params.add(new BasicNameValuePair(key, value)));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(entity);
        }
        return execute(httpClient, httpPost);
    }

    /**
     * HTTP POST请求---二进制文件
     *
     * @param url      请求URL
     * @param filePath 文件路径
     * @return 响应内容
     */
    public static String postBinaryFile(String url, String filePath) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/java-archive");
        httpPost.setEntity(new ByteArrayEntity(Files.readAllBytes(Paths.get(filePath))));
        return execute(httpClient, httpPost);
    }


    //##################删除（delete）方法##############################

    /**
     * HTTP DELETE请求
     *
     * @param url 请求URL
     * @return 响应内容
     */
    public static String delete(String url) throws IOException {
        return delete(url, null);
    }

    /**
     * HTTP DELETE请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return 响应内容
     */
    public static String delete(String url, Map<String, String> headers) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((key, value) -> httpDelete.setHeader(key, value));
        }
        return execute(httpClient, httpDelete);
    }


    //##################通用执行方法##############################

    private static String execute(CloseableHttpClient httpClient, HttpUriRequest request) throws IOException {
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity, "UTF-8");
        } finally {
            close(httpClient, response);
        }
    }

    /**
     * 关闭方法
     **/
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        // 释放资源
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            logger.error("CloseableHttpClient close exception: " + e.toString());
        }
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            logger.error("CloseableHttpResponse close exception: " + e.toString());
        }

    }
}
