/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ucan Kartal
 */
public class PasswordManager
{

    public static String getMD5Hex(final String inputString)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputString.getBytes());
            
            byte[] digest = md.digest();
            
            return convertByteToHex(digest);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(PasswordManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private static String convertByteToHex(byte[] byteData)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++)
        {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
