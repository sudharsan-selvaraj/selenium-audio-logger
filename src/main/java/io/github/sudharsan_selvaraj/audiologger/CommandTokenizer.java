package io.github.sudharsan_selvaraj.audiologger;

import io.github.sudharsan_selvaraj.types.driver.DriverCommand;
import io.github.sudharsan_selvaraj.types.driver.DriverCommandResult;
import io.github.sudharsan_selvaraj.types.element.ElementCommand;
import io.github.sudharsan_selvaraj.types.element.ElementCommandResult;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

import static java.lang.String.*;
import static io.github.sudharsan_selvaraj.audiologger.Utils.*;


import java.util.Properties;

public class CommandTokenizer {

    private static Properties tokens = loadTokens();

    public static String tokenize(DriverCommand driverCommand) {
        return tokenizeDriverCommand("wd.before", driverCommand);
    }

    public static String tokenize(DriverCommandResult driverCommandResult) {
        return tokenizeDriverCommand("wd.after", driverCommandResult);
    }

    public static String tokenize(ElementCommand elementCommand) {
        return tokenizeElementCommand("we.before", elementCommand);
    }

    public static String tokenizeException(Throwable e) {
        return format(getToken("exception",""), splitCamelCase(e.getClass().getSimpleName()));
    }

    public static String tokenize(ElementCommandResult elementCommandResult) {
        return tokenizeElementCommand("we.after", elementCommandResult);
    }

    private static String tokenizeDriverCommand(String tokenPrefix, DriverCommand command) {
        String token = getToken(tokenPrefix, command.getMethod().getName());
        String method = command.getMethod().getName();

        switch (method) {
            case "get":
            case "to":
                return format(token, formatUrl((String) command.getArguments()[0]));
            case "setSize":
                Dimension windowSize = (Dimension) command.getArguments()[0];
                return format(token, windowSize.getWidth(), windowSize.getHeight());
            case "maximize":
            case "executeScript":
            case "executeAsyncScript":
                return token;
        }

        return token;
    }

    private static String tokenizeElementCommand(String tokenPrefix, ElementCommand command) {
        String token = getToken(tokenPrefix, command.getMethod().getName());
        String method = command.getMethod().getName();
        String locatorToken = tokenizeLocator(command.getLocator());
        switch (method) {
            case "click":
            case "clear":
                return format(token, tokenizeLocator(command.getLocator()));
            case "sendKeys":
                CharSequence[] sendKeys = (CharSequence[]) command.getArguments()[0];
                for (CharSequence value : sendKeys) {
                    try {
                        Keys.valueOf(value.toString());
                        return format(token, Keys.valueOf(value.toString()) + " key", locatorToken);
                    } catch (Exception e) {
                        return format(token, value.toString(), locatorToken);
                    }
                }
        }

        return token;
    }

    private static String getToken(String tokenPrefix, String method) {
        if (!method.isEmpty()) {
            method = "." + method;
        }
        return tokens.getProperty(tokenPrefix + method);
    }

    private static Properties loadTokens() {
        try {
            Properties prop = new Properties();
            prop.load(CommandTokenizer.class.getClassLoader().getResourceAsStream("tokens.properties"));
            return prop;
        } catch (Exception e) {
            return null;
        }
    }
}
