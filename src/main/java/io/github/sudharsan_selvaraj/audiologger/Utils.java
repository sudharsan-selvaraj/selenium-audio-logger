package io.github.sudharsan_selvaraj.audiologger;

import org.openqa.selenium.By;

import java.lang.reflect.Method;
import java.util.*;

public class Utils {

    public static String formatUrl(String url) {
        try {
            return url.split("://")[1].replaceAll("/", " / ");
        } catch (IndexOutOfBoundsException e) {
            return url.replaceAll("/", " / ");
        }
    }

    static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public static String tokenizeLocator(By by) {
        if(!by.toString().contains(".")) {
            return " ";
        }

        Map<String , String> locatorTokens = new HashMap<String, String>() {{
            put("By.id", "with i d ");
            put("By.tagName", "with tag name ");
            put("By.name", "with name ");
            put("By.linkText", "with link text ");
            put("By.partialLinkText", "with partial link text ");
            put("By.className", "with class name ");
        }};

        String byString = by.toString();

        for(String token: locatorTokens.keySet()) {
            if(byString.trim().startsWith(token)) {
                return locatorTokens.get(token) + byString.split(token+":")[1].trim().replaceAll("-"," ");
            }
        }

        return " ";
    }

}
