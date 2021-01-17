package com.open.api.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * openapi请求和签名工具
 *
 * @author pantai
 * @Date 2021/1/17 下午6:15
 **/
public class OpenApiUtils {

    public static void main(String[] args) {
        String appKey = "16b213e835ef345bfb5eb1efe1fc43ae";
        String sceret = "5fa906be83dca8c379251fe58bb5de9f";
        String requestUrl = "http://localhost:6789/open/pingan/user/import";
        HashMap<String, String> header = Maps.newHashMap();
        header.put("Content-Type","application/json; charset=utf-8");
        HashMap<String, String> params = Maps.newHashMap();
        params.put("name", "朱小明");
        params.put("mobileNumber", "13311112222");
        params.put("certNo", "8888");
        params.put("appKey", appKey);
        params.put("time", String.valueOf(System.currentTimeMillis()));
        params.put("sign", getSign(params, sceret));
        System.out.println(JSON.toJSONString(params));
        String result = HttpUtils.postWithHead(requestUrl, header, JSON.toJSONString(params));
        System.out.println(result);
    }

    /**
     * 签名校验
     * @return
     */
    public static String getSign(Map<String,String> params, String sceret){
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"keyvalue"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            if(param.getKey().equals("sign")){//去掉签名字段
                continue;
            }

            if(!StringUtils.isBlank(param.getValue())) {
                basestring.append(param.getKey());
                basestring.append(param.getValue());
            }
        }
        basestring.append(sceret);

        // 使用MD5对待签名串求签
        String curSign = MD5Util.getMD5(basestring.toString());
        return curSign;
    }

}
