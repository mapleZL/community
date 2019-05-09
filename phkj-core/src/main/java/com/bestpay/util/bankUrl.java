package com.bestpay.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class bankUrl {

    public static void main(String[] args) {

        try {

            String URL="https://101.231.204.80:5000/gateway/api/frontTransReq.do";//测试地址
            String  bizType="000201";
            String txnSubType="01";
            String backUrl="http://183.62.49.172:10804/cpwap?method=cpwap&procCode=86000024";
            String orderId="1607080239043224";
            String signature="YKxMdeIveeFEjXjfGtJQ1wFQqoBrOpOzDHvE9bF7OV6wHRn5OVN+pkZ/o3DrGuV0UiltvN/eVFqckXM1ZDWlqhNQGm8j2t0ckvWd1E5C4Dfx65dUD9/sM2vKw6fjSKI4Cs9K1jEj/HUCXlf0Ype8hot6+zg6DRsDrUWZlI4t9oM=";
            String txnType="01";
            String channelType="08";
            String frontUrl="http://183.62.49.171:8091/payment_plugin/bestPay/wapPayReceive.do";
            String certId="40220995861346480087409489142384722381";
            String encoding="UTF-8";
            String version="5.0.0";
            String accessType="0";
            String reserved="{cardNumberLock=1}";
            String reqReserved="1607080239043224";
            String txnTime="20160708162715";
            String merId="700000000000001";
            String payTimeout="20160708163515";
            String currencyCode="156";
            String signMethod="01";
            String txnAmt="10";

            //SIGNSTR原串
            StringBuilder data = new StringBuilder();
            data.append("bizType=").append(bizType);
            data.append("&txnSubType=").append(txnSubType);
            data.append("&backUrl=").append(backUrl);
            data.append("&orderId=").append(orderId);
            data.append("&signature=").append(signature);
            data.append("&txnType=").append(txnType);
            data.append("&channelType=").append(channelType);
            data.append("&frontUrl=").append(frontUrl);
            data.append("&certId=").append(certId);
            data.append("encoding=").append(encoding);
            data.append("&version=").append(version);
            data.append("&accessType=").append(accessType);
            data.append("&reserved=").append(reserved);
            data.append("&reqReserved=").append(reqReserved);
            data.append("&txnTime=").append(txnTime);
            data.append("&merId=").append(merId);
            data.append("&payTimeout=").append(payTimeout);
            data.append("&currencyCode=").append(currencyCode);
            data.append("&signMethod=").append(signMethod);
            data.append("&txnAmt=").append(txnAmt);

            String requestBody = data.toString();
            System.out.println("加密后整个请求报文数据:{}" + requestBody);
            HttpPost httpPost = new HttpPost(URL);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            StringEntity se = new StringEntity(requestBody, "UTF-8");
            httpPost.setEntity(se);
            HttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
            String responseStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            System.out.println("请求后反馈报文:{}" + responseStr);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

