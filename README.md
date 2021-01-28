# openapi-sign
## openapi签名工具使用说明
## [用户信息导入接口文档](https://github.com/173422754/openapi-sign/blob/main/openapi.md)
### 一. 申请开发者权限：

联系管理员获取appkey和secret，用户接口调用时权限验证。

例如：

appKey : 16b213e835ef345bfb5eb1efe1fc43ae

secret : 5fa906be83dca8c379251fe58bb5de9f

### 二. 签名生成算法：

1. 签名的规则: 请求参数按照<span class="colour" style="color:rgb(255, 0, 0)">**字典排序**</span>，然后以 keyvalue 的形式拼
接成字符串;<span class="colour" style="color:rgb(255, 0, 0)">最后 sign=MD5(sortParam+secret)</span>。
2. 注意:如果请求参数中value为NULL的情况，则在拼接字符串时不计
入签名字符串。
3. 例如:

```
- 请求参数：
{
    'clientName': '朱小明',
    'mobileNo': '13311112222',
    'clientIdNo': '421344440987778888',
    'appKey': '16b213e835ef345bfb5eb1efe1fc43ae',
    'time': '1610867408754'
}

- 第一步：字典排序
{
    'appKey': '16b213e835ef345bfb5eb1efe1fc43ae',
    'clientIdNo': '421344440987778888',
    'clientName': '朱小明',
    'mobileNo': '13311112222',
    'time': '1610867408754'
}

- 第二步：排序后拼keyvalue
string = appKey16b213e835ef345bfb5eb1efe1fc43aeclientIdNo421344440987778888clientName朱小明mobileNo13311112222time1610867408754

- 第三步：私钥签名
secret = 5fa906be83dca8c379251fe58bb5de9f
sign = MD5(string+secret) = MD5(appKey16b213e835ef345bfb5eb1efe1fc43aeclientIdNo421344440987778888clientName朱小明mobileNo13311112222time16108674087545fa906be83dca8c379251fe58bb5de9f)
sign = f9fe388a6999cba4b0cb46c70a07c76b

- 第四步：发送post请求
curl -X POST "http://toufang.shpantai.com/open/pingan/user/import" -H "Content-Type: application/json" --data '{"clientName":"朱小明","mobileNo":"13311112222","clientIdNo":"421344440987778888","appKey":"16b213e835ef345bfb5eb1efe1fc43ae","time":"1610867408754"}'
调试和测试阶段可以使用下面test链接（验证接口和签名，不会发送数据到平安）：
http://toufang.shpantai.com/open/pingan/user/import/test
```

### 三. java SDK：

[https://github.com/173422754/openapi-sign](https://github.com/173422754/openapi-sign)

[https://github.com/173422754/openapi-sign/blob/main/src/main/java/com/open/api/utils/OpenApiUtils.java](https://github.com/173422754/openapi-sign/blob/main/src/main/java/com/open/api/utils/OpenApiUtils.java)

工具类：OpenApiUtils.java

### 四. 接口文档：
[用户信息导入接口文档](https://github.com/173422754/openapi-sign/blob/main/openapi.md)
