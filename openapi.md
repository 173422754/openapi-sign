
 <h1 class="curproject-name"> 开放api </h1> 
 


# 用户导入

## 导入用户数据
<a id=导入用户数据> </a>
### 基本信息

**Path：** /open/pingan/user/import

**Method：** POST

**接口描述：**


### 请求参数
**Headers**

| 参数名称  | 参数值  |  是否必须 | 示例  | 备注  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| Content-Type  |  application/json | 是  |  application/json |   |
**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">姓名</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>朱小明</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> mobileNumber</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">手机号码</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>13311112222</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> certNo</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">工号</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>8888</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> appKey</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">appKey</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>16b213e835ef345bfb5eb1efe1fc43ae</span></p></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> time</span></td><td key=1><span>number</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">毫秒时间戳</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>1610867408754</span></p></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">入参字段顺序md5签名</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>35fc81fb1a305ceb6a7a79857e5b9a25</span></p></td></tr>
               </tbody>
              </table>
            
### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> code</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">0成功，10002 --  appKey无效 10003 --  name,mobileNumber,certNo,appKey不能为空 10004 --  签名错误 10005 --  手机号格式错误</span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>0</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> msg</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=5><span style="font-weight: '700'">mock: </span><span>成功</span></p></td></tr>
               </tbody>
              </table>
            
## 签名和调用
<a id=签名和调用> </a>
### 基本信息

**Path：** /readme

**Method：** GET

**接口描述：**
<h3>一. 申请开发者权限：</h3>
<p>联系管理员获取appkey和secret，用户接口调用时权限验证。</p>
<p>例如：<br>
appKey : 16b213e835ef345bfb5eb1efe1fc43ae<br>
secret : 5fa906be83dca8c379251fe58bb5de9f</p>
<h3>二. 签名生成算法：</h3>
<ol>
<li>签名的规则: 请求参数按照<span class="colour" style="color:rgb(255, 0, 0)"><strong>字典排序</strong></span>，然后以 keyvalue 的形式拼<br>
接成字符串;<span class="colour" style="color:rgb(255, 0, 0)">最后 sign=MD5(sortParam+secret)</span>。</li>
<li>注意:如果请求参数中value为NULL的情况，则在拼接字符串时不计<br>
入签名字符串。</li>
<li>例如:</li>
</ol>
<pre><code>请求参数：
{
&nbsp; &nbsp; 'name': '朱小明',
&nbsp; &nbsp; 'mobileNumber': '13311112222',
&nbsp; &nbsp; 'certNo': '8888',
&nbsp; &nbsp; 'appKey': '16b213e835ef345bfb5eb1efe1fc43ae',
&nbsp; &nbsp; 'time': '1610867408754'
}

第一步：字典排序
{
&nbsp; &nbsp; 'appKey': '16b213e835ef345bfb5eb1efe1fc43ae',
&nbsp; &nbsp; 'certNo': '8888',
&nbsp; &nbsp; 'mobileNumber': '13311112222',
&nbsp; &nbsp; 'name': '朱小明',
&nbsp; &nbsp; 'time': '1610867408754'
}

第二步：排序后拼keyvalue
string = appKey16b213e835ef345bfb5eb1efe1fc43aecertNo8888mobileNumber13311112222name

第三步：私钥签名
secret = 5fa906be83dca8c379251fe58bb5de9f
sign = MD5(string+secret) = MD5(appKey16b213e835ef345bfb5eb1efe1fc43aecertNo8888mobileNumber13311112222name朱小明time16108674087545fa906be83dca8c379251fe58bb5de9f)
sign = f9fe388a6999cba4b0cb46c70a07c76b

第四步：发送post请求
curl -X POST "http://toufang.shpantai.com/open/pingan/user/import" -H "Content-Type: application/json" --data '{"name":"朱小明","mobileNumber":"13311112222","certNo":"8888","appKey":"16b213e835ef345bfb5eb1efe1fc43ae","time":"1610867408754","sign":"f9fe388a6999cba4b0cb46c70a07c76b"}'
</code></pre>
<h3>三. java SDK：</h3>
<p><a href="https://github.com/173422754/openapi-sign">https://github.com/173422754/openapi-sign</a><br>
<a href="https://github.com/173422754/openapi-sign/blob/main/src/main/java/com/open/api/utils/OpenApiUtils.java">https://github.com/173422754/openapi-sign/blob/main/src/main/java/com/open/api/utils/OpenApiUtils.java</a><br>
工具类：OpenApiUtils.java</p>
<h3>三. 错误码：</h3>
<pre><code>10002 --  appKey无效
10003 --  name,mobileNumber,certNo,appKey不能为空
10004 --  签名错误
10005 --  手机号格式错误
</code></pre>


### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody">
               </tbody>
              </table>
            