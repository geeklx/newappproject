package com.haier.pay.zfb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.haier.pay.Pay;
import com.haier.pay.zfb.util.OrderInfoUtil2_0;

import java.util.Map;

public class AliPay implements Pay {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018061260413128";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCVyeWB+K7tVjlZpMTPJsET5OHQ28jX89Vko4IBgzWJlpPx7zRd3FLTnu/jEN07BwVbM0dEhf1KspxBToQoDdPNh4TSewAiYaWYDAmFsqsoyFsebJw3KSzgyHKbbO7QVqXRd8yTeyqABf+gF3lZXcmtk/i2xkNsTF33i8q6Ye+gL65L+JEOTZRhUV1D4CBmItqfcbKWHBvMc9o34ZrkbXj1i7i8Jab/c7Va4N8poYHGpw8bG/rOVyjJzD/TqzZr2ruZM2Dj/5AJVfEWJvGXTEgCVkvLEBqmD2562ryv7MWFrdG8oo0Yap6R29BoJpHL0dnI1Jszt4C/rDrcn0DD+r7jAgMBAAECggEAOqW+UtFHzG2CLvMwi9skpeFr+RbU4Jj76FeMw4LYJ3LpokSCQG3PkEQesM0dkcsB8d91/AJqViDwxTE01Wn0j/iLrM20E8If0BSGde6qzWEFLDhiQcbBZ5GULf7M0o3ahtVdKDGFRnMSz1/hh3gNSwUw0H94TqLn99CoI6nM5O6szAJaWFS6wpFCKljYGTsgl24FgBaYml+ok43/U/WtnJIyOJ5VsyTsfqWGttS3L0g/K+al/6fmRDYXE0ccDkBGUeZRrTHsGeuSpCivRrJuoTBK8LwsXfH45VWUiqQfZ2GP2/Bu5Sqn6N7nKLbkLkLv3fjp8k63nwBG+CieS6LfIQKBgQDNus4THh6ByOJgn3PwFUPjPNvtrbP3V3iFZksfiV9U9FhVJOrRa2G4/uTaGDjZkITX4+djx47LxXvYJWvC0oxDypUgLJI+Pa8mE95ejcIwm0bkk1j1ELeSBRLIn9/CCUppKN6+jHVRXv4RkQw3dxk80i0uvxwdeWOoUF7P1HdEFwKBgQC6Y8LKnJbMcmcJxoM680ITafEQ3mOqfjzJ1RLr31XMB0S4cZesa4n89lKQN+JkQDIdJ75UACOmcZMOUQEqCaPSfikNPfSrWOI/f6LtoZDm9byOWwqewhhNPiBye/OIa1avmILFWi3rCJPCMjqWnjzXiYCEAhGche9xD94MFuO/FQKBgQCqHovjsSdG6QeRIKmtYNhHYv719op3wKQPo3wBcwYa6QbWOXXXgHVJlbVGR3tTJTdEkte3YcBJ7l6vaJpIevSz5iQUe+begwF/ejSKeayXpv8jaljIJW+21m0m61ULTcVzWfF/HgSxKdzsR8YTY2/CJIZqPmwQT13CQ9ty/l01NwKBgQCIxoQ+4kHWrWznQ4ExVSj7zhZUZp2EkzZQdcd4x17SZdML9m9y20dbFfboVdoP7vvXYT7NrXx7u4RKUS8lhgVthZ7iVMCirIr6RdNtxc3rbhB79lsIvS3QIPL2j/bZ+MqX+Lad09s2SgkDzJApztye3gws0sfRsIdQ1HABNxrRMQKBgQC/R/VHKb4fBUaIeo55DdkiTX2iZZw6JSscIcFQQ4mXOfBXY/5d5uuy+iOnid2Nh0iqmzRhROvdfD8EyGE6Xl7yP3tWMADZJLoGK08t6vdbQl/Pcf7ocJtmKwzeOMpBjed6/3SEXwy971icnVF34vf/I1LXU5wO17cDr0Z1nPI2Mg==";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private Context mContext;


    public AliPay(Context context) {
        mContext = context;
    }

    // ali start
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    @Override
    public void pay() {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void error(String s) {

    }

    //ali end
}
