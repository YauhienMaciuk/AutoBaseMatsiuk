package com.epam.autobasematsiuk.encoder;

import com.epam.autobasematsiuk.exception.ServiceException;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class Coder for hashing the password or login
 */
public class Coder {

    /**
     * The method for hashing login or password
     *
     * @param value is login or password
     * @return the hashed login or the password
     * @throws ServiceException
     */
    public String doHash(String value) throws ServiceException {
        String hashValue = null;
        byte[] array = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(value.getBytes("utf-8"));
            array = messageDigest.digest();
            BASE64Encoder baseEncoder = new BASE64Encoder();
            hashValue = baseEncoder.encode(array);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException("Exception in method Coder.doHash", e);
        }
        return hashValue;
    }

}
