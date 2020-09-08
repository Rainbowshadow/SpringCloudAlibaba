package com.lpx.springcloud.util;
//package com.rs.util;
//
//import com.alibaba.fastjson.JSONObject;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.*;
//import java.util.Map.Entry;
//
//public class HttpUtil {
//    static Logger log = Logger.getLogger(HttpUtil.class);
//    public static void main(String[] args) {
//        String url = "http://pay.makusci.com/submit.php";
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "测试表单请求");
//        String formResult = HttpUtil.sendxwwwform(url, map);
//        System.out.println(formResult);
//
//    }
//
//
//    /**
//     * form表单提交
//     * @param url
//     * @param paramMap
//     * @return
//     */
//    public static String sendxwwwform(String url, Map<String, Object> paramMap) {
//        CloseableHttpClient httpClient = null;
//        CloseableHttpResponse httpResponse = null;
//        String result = "";
//        // 创建httpClient实例
//        httpClient = HttpClients.createDefault();
//        // 创建httpPost远程连接实例
//        HttpPost httpPost = new HttpPost(url);
//        // 配置请求参数实例
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
//                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
//                .setSocketTimeout(60000)// 设置读取数据连接超时时间
//                .build();
//        // 为httpPost实例设置配置
//        httpPost.setConfig(requestConfig);
//        
////        生成订单号
//        final DateFormat order_d = new DateFormat();
//        
//        Map<String, Object> maps = new BaseBean() {
//            @Override
//            protected Map<String, Object> getMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("pid","1013");
//                map.put("type", "alipay");//alipay:支付宝,tenpay:财付通,qqpay:QQ钱包,wxpay:微信支付
//                map.put("out_trade_no", order_d.dateFormat().get("random").toString());
//                map.put("notify_url", "http:///notify_url.php");
//                map.put("return_url", "http:///return_url.php");
//                map.put("name", "VIP会员");
//                map.put("money", "0.01");
//                return map;
//            }
//        }.toMap();
//        
//        // 设置请求头
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        paramMap.put("pid", "1013");
//        paramMap.put("type", "alipay");//alipay:支付宝,tenpay:财付通,qqpay:QQ钱包,wxpay:微信支付
//        paramMap.put("out_trade_no", "20160806151343349");
//        paramMap.put("notify_url", "http:///notify_url.php");
//        paramMap.put("return_url", "http:///return_url.php");
//        paramMap.put("name", "VIP会员");
//        paramMap.put("money", "0.01");
//        System.out.println( maps.get("sign"));
//        paramMap.put("sign", maps.get("sign"));
//        paramMap.put("sign_type", "MD5");
//        
//        // 封装post请求参数
//        if (null != paramMap && paramMap.size() > 0) {
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            // 通过map集成entrySet方法获取entity
//            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
//            // 循环遍历，获取迭代器
//            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
//            while (iterator.hasNext()) {
//                Entry<String, Object> mapEntry = iterator.next();
//                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
//            }
//
//            // 为httpPost设置封装好的请求参数
//            try {
//                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            // httpClient对象执行post请求,并返回响应参数对象
//            httpResponse = httpClient.execute(httpPost);
//            // 从响应对象中获取响应内容
//            HttpEntity entity = httpResponse.getEntity();
//            result = EntityUtils.toString(entity);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭资源
//            if (null != httpResponse) {
//                try {
//                    httpResponse.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (null != httpClient) {
//                try {
//                    httpClient.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }
//
//
//}