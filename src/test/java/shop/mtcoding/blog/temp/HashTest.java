package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class HashTest {

    @Test
    public void encode_test() {
        //$2a$10$cT9XgK4tpPMdbI1WqX9ApOdkz3/LHZDmlF14j9iOOJFR0Qv3hvq0C
        //$2a$10$Cuu1XjI1hcMtpxjhDb9Mlu0ZQM1ONCP/TVca/UHX4pCC9C9x8DEVW
        String password = "1234";

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(encPassword);
    }

    @Test
    public void decode_test() {
        //$2a$10$cT9XgK4tpPMdbI1WqX9ApOdkz3/LHZDmlF14j9iOOJFR0Qv3hvq0C
        //$2a$10$Cuu1XjI1hcMtpxjhDb9Mlu0ZQM1ONCP/TVca/UHX4pCC9C9x8DEVW
        String dbPassword = "$2a$10$cT9XgK4tpPMdbI1WqX9ApOdkz3/LHZDmlF14j9iOOJFR0Qv3hvq0C";
        String password = "1234";

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        if (dbPassword.equals(encPassword)) {
            System.out.println("비밀번호가 같아요");
        } else {
            System.out.println("비밀번호가 달라요");
        }
    }

    @Test
    public void decodeV2_test() {
        //$2a$10$cT9XgK4tpPMdbI1WqX9ApOdkz3/LHZDmlF14j9iOOJFR0Qv3hvq0C
        //$2a$10$Cuu1XjI1hcMtpxjhDb9Mlu0ZQM1ONCP/TVca/UHX4pCC9C9x8DEVW
        String dbPassword = "$2a$10$cT9XgK4tpPMdbI1WqX9ApOdkz3/LHZDmlF14j9iOOJFR0Qv3hvq0C";
        String password = "1234";

        Boolean isSame = BCrypt.checkpw(password, dbPassword);
        System.out.println(isSame);
    }
}
