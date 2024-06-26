package com.study.idea.demos.web.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfig {
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000138627670";//2021000116660598

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCoaYFHoY4Uzw5e53bYpamAja7efw7QSbDEBtFrkeTvXHokp6HaqMicxZzQ02ydexVzd5z3uOhsrVqhzYIBK/7sMqL1J1w2ds3UIT/2JTawEi/oMwyTnJOh/KPlcxN6aja7hVALxkB6qPsCMegPqjfbJiFb2uqryxdlLv970zduvZniF8dA5FxitmKrwSeiUjtCl5RHKpjDcz1q3Ccsvi7lCaYW7FlrJ+JiYI3ITupdx7QdoM6PsxRH+3a8hlAPu7W1ZDozq94p2vLulvOlk1QSjFLb7QjH0FI0cmqYvKK11TYtPrA7j9+VN7L+tebohup6TIf0KMeRyOZYhWEdm5zzAgMBAAECggEBAJDgQiCW8B0iiiBGSkzRJmG6gKZS8zFnUzMvElTRavomucRymJ/79qg2r1JNrvBChA+S5GuOnBNxhbgPjDjp2iDi5qCyvXb0+Nf1yoo7NTJ+UswTrkwFtFLzfSLTvfvfBzqF0nhukeVzHIuk1xcyvV4/DQ9x4dLroTKgHZQt/U+7UIlv4eRYvf8t+rZCkyDrfueqxtJRgKEOYOTUlKOc73JmSInUJ2VWoqYcfATFzHdPwAOXy+DN90zTtCXzh14ohn5PxFC9d58jBjUSvw80IoYR58qzVYsf0qMA5KS4EORgn5ckW9zfljde0g9Ob9OwDwt7dKuJ+YboW9pvzvqi6dECgYEA5NCtBMOCub8JEFA669wTsRQun+A4rYt/TQDEex4JJQtyKt3pb0pTppQfV8weboPrYrkaqjGaDl0eKLqni4W1xIaxAn2TJVOwKKkz6QF6tGWzdn7y3X0tzhZ/qUZ2YgDbLL8K9uu69tUb1dEcOCKSNqCS8kfPtYDoawQQ+NpX1N0CgYEAvGux47fYd0Z5HxT7aBFDoqRH7EBBj001TvKsIXaC5Pa1WvuP+14J4OC0C+w3PB7lywZirAHI0HgIX8W7zDk78novTh9OsStEecozR/NouWuju/wUu0cM7P8CcMwHEvqysTjuC60MGhhizXdpt8XEA1NFLTQdQS1SIky++oTqdA8CgYAP67UeL7xY0EIBMJPo9Vcpaw4TcbZ/3MTuxq7lIiHgwfsfoHEtnS5U2NmVck5rwiIOrHJ+hRUDCteeXd5qhtMu6XBHjBVA86G+Moe6HrA6/RiPliWv45XcURRw4sY563VUl7Zbl+taYVmedv5PqZgi1OlkkjqRlnaVe6Lyrb6OwQKBgQCk/3IBxcarfG0oiMk3cpbV4CKTghlnEedywRN3Ij6xnlFl4RFOwCTKZjuEjVA5N51nUqopkk52MAa1R4SS7DPQvKqcMNJKV2dZd3W8tiyo4te5EKBe93McPpu2hxRSzHKauw3J77m/SEeNQdqz8V0axFq5oP9VSgi4vJgo7SgahQKBgH+3iqq3egkIdzyaPsBzG9HjUN2zO8oRmCCK/qUM25PvCKV9k+bRTqe+WzpjpdiIqhjjmXFrA71cAXUc8raJn6kHXJB4QMhthpAqZh02aicCMjkvTIMLLpgYK7oQIa5/7H/tqxI0zi3AXXhVIK/KoxM5E0tTMLyzJucR00K7E3C5";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/develop/sandbox/app 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBXBvbBr7E/UPbobr/lHpyo0s8u4FYWeIotsX2fxg3vKbfN/DYV8ATItmUbqWwTZr6ANMnHJGHW9cwRc7fPYvfs3HabJVkcf7RKjoV2ky2nU+VqYjZgAVYrE+OAniiEVYJNk7meG4o2p0JbVomv75VfBsbzYTi7L0OK1F6Jf1dDjad/Vg1WbGF65bJuriBEUW7fROFAD6XK7zQtDl/pfBYPDcPc2GNWx+UK7MjuMNOHYW0IFGxueSG3dytC4ntxM4gnRN/7gSXuk4rf/7L3blksewiDau7E9E6K+64GqXy4IuBpTKnu8WsBx7xhfhmINzfVmxqEAbLRgVKvBsl7/9QIDAQAB";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
    public static String return_url = "http://jgj3fw.natappfree.cc/alipay/return";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://jgj3fw.natappfree.cc/alipay/notify";

    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayurl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    //public static String gatewayurl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
//    public static String gatewayurl = "https://openapi.alipay.com/gateway.do";
//    public static String gatewayurl = "https://openapi.alipaydev.com/gateway.do";


}
