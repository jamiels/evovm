import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.security.Key;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.PublicKey;

import javax.crypto.*;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class BitMartClient {
	
	public static void main(String[] args) {


        String privateKeyString = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCz61IlRCUQovFGIopG6RzTltyW7MvF4t72Nh9f6quP9ObZwml1gEagrIq3L8O5b+AHVdgYK6EXUnx2zf95G9tR8NvMcpjDJdImk/aWn8wsUjqCXAVXPmNEmN5RPP9d8i4f333OTsty8hCyvPBwrIGJJgitNCnqIUzkD+W/RXh+MCrCtch9nBqRlDTLzyhoKlwBbqZ/U/kSMjc30L81wpULcpwBtbRgvSkHG6zMAq3wEhPVF1sDXOaAg3a94o33C2Zk8BqUVzTPAqpw81WHk4TnbJFiTQi/vUk5j/4kvAZIsNh/3NVR+cRDfZPuzceTFQwd99aK0aZbhtGDP36IdyfJAgMBAAECggEBALNUOBpg47jDUE0hnEW1kD/tvIzKFQl2adlqdLc7xijEZ1xhBJBTfRIlKFYSS7mHIvrsu2jMA9KlKt2X0a268E2qDsqm5lCMX/yHul7Dg9QhWaQZaFFfI755yA2dCMNw4jxunIyHyQ0W7UHaRKVpq+sxd7B7r9NnwaYHmm2dJs5OMMMkCQivszV6kqP3YJ/3+ypnVHRfI7k+1bSfl6XqkmvukI0p2x3fyr+e6MdmXOeTaM5NtEaU4MPGyy2Bz6d7SFN0+zZSO/NL6rIueb1b/scLKl4LEF28pJUAiO8fsEP3of/lmPqExtlJeeeiUNgWf4t/BZIMz32nFnpBYK9AaqECgYEA9FM+U+/fbNXz/anAUirKkClwZO0fCTwyOgyO7NhC6uREEh8moPriDQEnsaKTdoKzUNJYeFkS0k5ytVEmZgf2b+NoBLnCkryWHCMHutavgL5gKLvGFi4K4wnZmGAfNe+Gh8cR7jF88nMrM/vBuKguZ+6NsFB5CtcRrjmjbolTpA0CgYEAvIQ4AVaz8IhJhIo55aTnvFinUAiYTs8BlY0uYdftyd8xX8Wge6tKPk0x4IDXI/8wV9Nu0ykK4gV36rTxh2OVahNmt0o6hHgluRmYsXYXIL/ok0j1TGf+h4HsAKRrR40n4LeiVldW6sMdAiWGCGcNFK92n5a5cu4Q11DCLuxct60CgYBiyxY00pXBaHIUbAN8NhlaWac86DTYgyK8Hj/cvUWxvUNZIId6KHgnUgQ+68I89XSUhbmIUFwv9dCkSIACWc3oSEfTQVnQA7me/vstLYohwNkZH3Rlm5CDZYQ6/QMhyAzZ8kDp09D2KGObzXzzse2x0OAyHxgSrgxUKrJPC/BqMQKBgQCCN/rPwept3laaPTkdDjoc+kgomqdK2OdJPMqrP5K7/XaHp2Xhx0JzorMvVxBFh0MHh79oEVO5KPnaoL6uPPW63kf3mEivtKaqI6o7+0yijK3E/4KiErvZMr+o+eo49KJ1MaPrbE1XwQtKrkzVhQmeHk4ckurrHtN3SAu6if7JyQKBgQDyJX+e1puSGYNd6wCbezznxxLWJ36aLZfVXuko8U44YHnw9nHKhXNmt3mOyOOFYekmiDEoj7K94gHNS5Fz3fib7Q5zcynWrbq4VY+vY8JruU/3HYPWztalqQI2uQzgugvEYtHYX94aySImUvAVNGiUmyUOblh/VQGx+Qk5Cj+ziA==";
        try {

            String url = "http://api-v2-testing-d8pvw98nl.bitmart.com/api/v2/access?appId=AAkQ215YS0cBr9cx0fejImxUHpR&accessSecret=fV484tRA87B@RZhqXH3duv";
            String cipherText = encrypt("test",privateKeyString);
            System.out.println(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static String encrypt(String source, String privateKey)
			throws Exception {
		Key key = getPrivateKey(privateKey);
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		
		byte[] b1 = cipher.doFinal(b);
		return new String(Base64.encodeBase64(b1),
				"UTF-8");
    }
    
    public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
}