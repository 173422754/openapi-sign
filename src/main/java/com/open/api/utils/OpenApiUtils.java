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
        // 替换管理员分配的appKey
        String appKey = "16b213e835ef345bfb5eb1efe1fc43ae";
        String sceret = "5fa906be83dca8c379251fe58bb5de9f";
        // 调试和测试阶段可以使用下面test链接（验证接口和签名，不会发送数据到平安）：
        String requestUrl = "http://toufang.shpantai.com/open/pingan/user/import";
//        String requestUrl = "http://toufang.shpantai.com/open/pingan/user/import/test";
        HashMap<String, String> header = Maps.newHashMap();
        header.put("Content-Type","application/json; charset=utf-8");
        HashMap<String, String> params = Maps.newHashMap();
        params.put("clientName", "测试");
        params.put("mobileNo", "13311112222");
        params.put("clientGender", "男");
        params.put("dateClientBirth", "2000-01-02");
        params.put("clientIdNo", "4123321231111");
        params.put("clientIdType", "身份证");
        params.put("city", "上海");
        params.put("requestId", "requestId");
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
