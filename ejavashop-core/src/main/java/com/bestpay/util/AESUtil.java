package com.bestpay.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by liangpw on 2015/12/18.
 */
public class AESUtil {
    public static final String AES_CBC_PKCS5 = "AES/CBC/PKCS5Padding";
    public static final String IV = "1234567892546398";

    public static Cipher initDecryptCipher(String key, String algorithm, int mode, String iv) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, secretKeySpec, ivSpec);
        return cipher;
    }

    public static byte[] encrypt(String content, Cipher cipher) throws Exception {
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static byte[] decrypt(byte[] content, Cipher cipher) throws Exception {
        return cipher.doFinal(content);
    }

    /**
     * 加密对象中所有@AES注解的字段
     */
    public static Object aesEncode(Object request, Cipher cipher) throws Exception {
        List<Class<?>> classes = ClassUtils.getClasses(request.getClass());
        for (Class clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue=field.get(request);
                if (fieldValue!= null) {
                    //当字段为Map<String,Bean>,Collection<Bean>,Bean时
                    if (!ClassUtils.isPrimitive(field.getType())) {
                        if(Map.class.isAssignableFrom(field.getType())){
                           Map map= (Map) fieldValue;
                           for(Object obj:map.values()){
                               aesEncode(obj,cipher);
                           }
                        }else if(Collection.class.isAssignableFrom(field.getType())){
                            Collection collection= (Collection) fieldValue;
                            for(Object obj:collection){
                                aesEncode(obj,cipher);
                            }
                        }else{
                            aesEncode(fieldValue, cipher);
                        }

                    }
                    if (field.isAnnotationPresent(AES.class)) {
                        String content = String.valueOf(fieldValue);
                        byte[] encrypt = encrypt(content, cipher);
                        String base64 = Base64Utils.encode(encrypt);
                        field.set(request, base64);
                    }
                }
            }
        }
        return request;
    }

    /**
     * 解密对象中所有@AES注解的字段
     */
    public static Object aesDecode(Object request, Cipher cipher) throws Exception {
        List<Class<?>> classes = ClassUtils.getClasses(request.getClass());
        for (Class clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue=field.get(request);
                if (fieldValue != null) {
                    //当字段为Map<String,Bean>,Collection<Bean>,Bean时
                    if (!ClassUtils.isPrimitive(field.getType())) {
                        if(Map.class.isAssignableFrom(field.getType())){
                            Map map= (Map) fieldValue;
                            for(Object object:map.values()){
                                aesDecode(object,cipher);
                            }
                        }else if(Collection.class.isAssignableFrom(field.getType())){
                            Collection collection= (Collection) fieldValue;
                            for(Object obj:collection){
                                aesDecode(obj,cipher);
                            }
                        }else{
                            aesDecode(fieldValue, cipher);
                        }

                    }
                    if (field.isAnnotationPresent(AES.class)) {
                        String base64 = String.valueOf(fieldValue);
                        byte[] bytes = Base64Utils.decode(base64);
                        byte[] decrypt = decrypt(bytes, cipher);
                        String content = new String(decrypt, "utf-8");
                        field.set(request, content);
                    }
                }
            }
        }
        return request;
    }


}
