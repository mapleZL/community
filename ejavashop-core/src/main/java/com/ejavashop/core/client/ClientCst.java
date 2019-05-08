package com.ejavashop.core.client;

/**
 * @author ：zl
 * @date ：Created in 2019/5/8 11:43
 * @description：常量参数
 * @modified By：
 * @version: 0.0.1$
 */
public class ClientCst {
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
    // https://ram.console.aliyun.com 创建。
    public static String oss_endpoint = "http://oss-cn-beijing.aliyuncs.com";
    public static String oss_accessKeyId = "LTAIkSc7CUIulce0";
    public static String oss_accessKeySecret = "jRPzHauftvtRLerrJj57rwEEaY6nY8";
    public static String oss_bucketName = "community-pic";
    public static long validity = -1;
}
