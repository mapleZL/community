package com.bestpay.util;

/**
 * Created by Chenkh on 2014/10/3.
 */
public class Constants {

    public static final String DATA_TYPE_JSON="1";
    public static final String DATA_TYPE_XML="2";

/*    public static final String RESPONSE_MSG_COMMUNICATE_FAIL="ͨѶ�쳣�Ľ��������ǩ";
    public static final String RESPONSE_MSG_VERIFY_FAIL="���ض���������ǩʧ��";
    public static final String RESPONSE_MSG_VERIFY_SUCCESS="���ض���������ǩ�ɹ�";
*/
    public static final String CONFIRM_MSG = "CONFIRM_MSG";

    public static final String SEC_SUCCESS = "SEC12000000";

    public static final String VERIFY_FAIL_CODE = "SIM01999999";
    //public static final String VERIFY_FAIL_MSG= "���ŷ�����Ӧ�����ǩʧ�ܣ�δ֪��֤���Ƿ��ͳɹ�";


    /**
     * 测试环境翼支付公钥
     */
  //public final static String PUBLIC_CERT = "MIIEdTCCA12gAwIBAgIQeOYX576OwdBzGHctPw6PSTANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJDTjEkMCIGA1UEChMbTkVUQ0EgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MRkwFwYDVQQLExBTZXJ2ZXIgQ2xhc3NBIENBMR8wHQYDVQQDExZORVRDQSBTZXJ2ZXIgQ2xhc3NBIENBMB4XDTEyMDMzMTE2MDAwMFoXDTE1MDQwMTE1NTk1OVowZzELMAkGA1UEBhMCQ04xEjAQBgNVBAgTCUd1YW5nZG9uZzErMCkGA1UECh4iZw1SoVZoi8FOZm1Li9UAMgAwADEAMgAwADQAMAAxAC0ANDEXMBUGA1UEAxMOdGVzdDIwMTIwNDAxLTQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDYWtX4YTIYgmXAC6l2E//GQ1SjymJyPGNoDav+MJtUpXzcdub4yfKiLa6HWzzKUl9YyokwGtMUu020+8yy6BUNlmElcgrdqmZyej8fjlzjCw8BLwrxxKcbR9HZ8OEu1VSpy1w/KYpCEqT1gi4T3LP+Ug2SVbIXuFMsPSwywwVtl8mMcXQ3PqjD72IK+cOnWCimFk590uLlnyy6/z3vMa+XbjUG3haoCS8WLsJNufbs8HccTE6FYctCDNuSwhD7YOqKlMoMFnFnEN6wvZ57GS3NM1dd2sSqE4Ma8gE75w3VdOuoKsjnWfrGkbhkUmBiVuACt+/Aw3m8mngx33qyKjURAgMBAAGjggETMIIBDzAfBgNVHSMEGDAWgBS680oFJOb4JMjmV9p4jQxZ5ENkyjAdBgNVHQ4EFgQUKl+CBlrjAlL1DUAJs4M2QqAq2xMwVwYDVR0gBFAwTjBMBgorBgEEAYGSSAEKMD4wPAYIKwYBBQUHAgEWMGh0dHA6Ly93d3cuY25jYS5uZXQvY3Mva25vd2xlZGdlL3doaXRlcGFwZXIvY3BzLzAZBgNVHREEEjAQgg50ZXN0MjAxMjA0MDEtNDAMBgNVHRMBAf8EAjAAMA4GA1UdDwEB/wQEAwIEsDA7BgNVHR8ENDAyMDCgLqAshipodHRwOi8vY2xhc3NhY2ExLmNuY2EubmV0L2NybC9TZXJ2ZXJDQS5jcmwwDQYJKoZIhvcNAQEFBQADggEBAJ4QaFZjJ12Ayvyy0JyNZQ9eeNmCAUt4+aZY6IT/FDfm9HFB7jkVXxDY+eUTHc/cu4fDIidCNz+CTGlRoiVWJkDyQfmBpcXJE1OPJSHzMXXDgGfxstqZDkDJ+NAAX8TSunnd4/y/BjO/uoOxytVmetxWOxt4s6IhITzZS2y7Kntoghd+6IVK20VFS9mhqUL/YiRI4RU+TrjU8zm7+QSTpqfXfOwn1x8Eol+Pwu9+ZdMNLCA8MbbtmMKz/vbxMwBoFv9kedH0ui7d5AQiNGQHxp0IGYc1L48KpLE8bDeyjGNU+VjI/gmzxYM6bwOqLpoENnWm7M3NLhtIDj98Z4Wftrc=";
    //public final static String PUBLIC_CERT = "MIIEdTCCA12gAwIBAgIQeOYX576OwdBzGHctPw6PSTANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJDTjEkMCIGA1UEChMbTkVUQ0EgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MRkwFwYDVQQLExBTZXJ2ZXIgQ2xhc3NBIENBMR8wHQYDVQQDExZORVRDQSBTZXJ2ZXIgQ2xhc3NBIENBMB4XDTEyMDMzMTE2MDAwMFoXDTE1MDQwMTE1NTk1OVowZzELMAkGA1UEBhMCQ04xEjAQBgNVBAgTCUd1YW5nZG9uZzErMCkGA1UECh4iZw1SoVZoi8FOZm1Li9UAMgAwADEAMgAwADQAMAAxAC0ANDEXMBUGA1UEAxMOdGVzdDIwMTIwNDAxLTQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDYWtX4YTIYgmXAC6l2E//GQ1SjymJyPGNoDav+MJtUpXzcdub4yfKiLa6HWzzKUl9YyokwGtMUu020+8yy6BUNlmElcgrdqmZyej8fjlzjCw8BLwrxxKcbR9HZ8OEu1VSpy1w/KYpCEqT1gi4T3LP+Ug2SVbIXuFMsPSwywwVtl8mMcXQ3PqjD72IK+cOnWCimFk590uLlnyy6/z3vMa+XbjUG3haoCS8WLsJNufbs8HccTE6FYctCDNuSwhD7YOqKlMoMFnFnEN6wvZ57GS3NM1dd2sSqE4Ma8gE75w3VdOuoKsjnWfrGkbhkUmBiVuACt+/Aw3m8mngx33qyKjURAgMBAAGjggETMIIBDzAfBgNVHSMEGDAWgBS680oFJOb4JMjmV9p4jQxZ5ENkyjAdBgNVHQ4EFgQUKl+CBlrjAlL1DUAJs4M2QqAq2xMwVwYDVR0gBFAwTjBMBgorBgEEAYGSSAEKMD4wPAYIKwYBBQUHAgEWMGh0dHA6Ly93d3cuY25jYS5uZXQvY3Mva25vd2xlZGdlL3doaXRlcGFwZXIvY3BzLzAZBgNVHREEEjAQgg50ZXN0MjAxMjA0MDEtNDAMBgNVHRMBAf8EAjAAMA4GA1UdDwEB/wQEAwIEsDA7BgNVHR8ENDAyMDCgLqAshipodHRwOi8vY2xhc3NhY2ExLmNuY2EubmV0L2NybC9TZXJ2ZXJDQS5jcmwwDQYJKoZIhvcNAQEFBQADggEBAJ4QaFZjJ12Ayvyy0JyNZQ9eeNmCAUt4+aZY6IT/FDfm9HFB7jkVXxDY+eUTHc/cu4fDIidCNz+CTGlRoiVWJkDyQfmBpcXJE1OPJSHzMXXDgGfxstqZDkDJ+NAAX8TSunnd4/y/BjO/uoOxytVmetxWOxt4s6IhITzZS2y7Kntoghd+6IVK20VFS9mhqUL/YiRI4RU+TrjU8zm7+QSTpqfXfOwn1x8Eol+Pwu9+ZdMNLCA8MbbtmMKz/vbxMwBoFv9kedH0ui7d5AQiNGQHxp0IGYc1L48KpLE8bDeyjGNU+VjI/gmzxYM6bwOqLpoENnWm7M3NLhtIDj98Z4Wftrc=";
    /**
     * 生产环境翼支付公钥
     */
    public final static String PUBLIC_CERT="MIIEAzCCAuugAwIBAgIQaByrVM2PXDnlj+FV8Vik6TANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJDTjEkMCIGA1UEChMbTkVUQ0EgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MRkwFwYDVQQLExBTZXJ2ZXIgQ2xhc3NBIENBMR8wHQYDVQQDExZORVRDQSBTZXJ2ZXIgQ2xhc3NBIENBMB4XDTEyMDUyMzE2MDAwMFoXDTEzMDUyNDE1NTk1OVowbjELMAkGA1UEBhMCQ04xEjAQBgNVBAgTCUd1YW5nZG9uZzEnMCUGA1UECh4eWSl//HU1W1BVRlKhZwmWUFFsU/hef04cUgZRbFP4MSIwIAYDVQQDExllbnRlcnByaXNlLmJlc3RwYXkuY29tLmNuMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXJj30rLaut9yxT+yeuPGIeDhcVGMZuxJ03ygkmckhJwzrefULtSh0XUMheYPpUgZfn7UKNN1cbMUTLMYd5KMSQdltAiTsUcmi2SW27ikVowfjA9yMwYQ/7d+HTcOvalfCd2k6KHByahig02LC7BpNXtPdDPN7A5QjJcfT4lxgwwIDAQABo4IBHjCCARowHwYDVR0jBBgwFoAUuvNKBSTm+CTI5lfaeI0MWeRDZMowHQYDVR0OBBYEFIMIQX6GW79AxsltJr2aYBQZS+GhMFcGA1UdIARQME4wTAYKKwYBBAGBkkgBCjA+MDwGCCsGAQUFBwIBFjBodHRwOi8vd3d3LmNuY2EubmV0L2NzL2tub3dsZWRnZS93aGl0ZXBhcGVyL2Nwcy8wJAYDVR0RBB0wG4IZZW50ZXJwcmlzZS5iZXN0cGF5LmNvbS5jbjAMBgNVHRMBAf8EAjAAMA4GA1UdDwEB/wQEAwIEsDA7BgNVHR8ENDAyMDCgLqAshipodHRwOi8vY2xhc3NhY2ExLmNuY2EubmV0L2NybC9TZXJ2ZXJDQS5jcmwwDQYJKoZIhvcNAQEFBQADggEBAAX3psJhH/4+au2N8PnWsHtk5Nx7sOb6h0kc+tIptof3A1GzvOLh/xVwtB2vDvd4YuZy6oT81JGMocL4+iO/Vi3qF0ut/cN2t6lFNdmNPo7/rkrRDNNVKwix9K+xeW6+SwNnATlc8/9SM5b1z8bTs5JCq9F7fDRyA5AquAPYjXTOw4Tt33NaX0Gv+IbF4rPzI/qTWTGlS63/fmMiCncwHXLfcS3eWxpVMGC7CExtLEwBHDaZWa9yztkKn/rQAduHb7VSpSQ6bvZXONa2ltMTe1xhgP9zpCRtGUXE9Rnzh2ZscG8ajA4kK2cxellzCLshsafxnngbWRWC8wHp4Y/gqK0=";




    public final static String HTTP_METHOD_POST = "POST";

    public final static String HTTP_METHOD_GET = "GET";

    public final static String CONTENT_TYPE = "Content-Type";

    public final static String CONTENT_TYPE_XML = "text/xml";

    public final static String CONTENT_TYPE_JSON = "application/json";
}
