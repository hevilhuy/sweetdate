/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ucan Kartal
 */
public class URLValidator
{

    public static boolean validate(String url)
    {
        String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)+[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";
        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(url);
        return m.find();
    }
}
