package cn.klv.auth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BryptTest {

    @Test
    public void testCrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "password";
        System.out.println(encoder.encode(pass));
    }

    @Test
    public void testCrypt2() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String pass = "password";
        System.out.println(encoder.encode(pass));
    }
}
