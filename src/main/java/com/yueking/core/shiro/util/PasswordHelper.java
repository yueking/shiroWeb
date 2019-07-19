package com.yueking.core.shiro.util;

import com.yueking.core.shiro.entity.User;
import lombok.Data;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

@Data
@Service
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
    }
}
