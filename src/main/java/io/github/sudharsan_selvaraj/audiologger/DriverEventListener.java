package io.github.sudharsan_selvaraj.audiologger;

import io.github.sudharsan_selvaraj.SpyDriverListener;
import io.github.sudharsan_selvaraj.types.BaseCommand;
import io.github.sudharsan_selvaraj.types.driver.DriverCommand;
import io.github.sudharsan_selvaraj.types.driver.DriverCommandException;
import io.github.sudharsan_selvaraj.types.driver.DriverCommandResult;
import io.github.sudharsan_selvaraj.types.element.ElementCommand;
import io.github.sudharsan_selvaraj.types.element.ElementCommandException;
import io.github.sudharsan_selvaraj.types.element.ElementCommandResult;


public class DriverEventListener implements SpyDriverListener {

    private final LogOptions options;
    private final SpeechManager speechManager;

    public DriverEventListener(LogOptions logOptions) {
        this.options = logOptions;
        this.speechManager = new SpeechManager(logOptions);
    }

    @Override
    public void beforeDriverCommandExecuted(DriverCommand command) {
        if (options.isLogBeforeCommand() && !skipLog(command)) {
            speechManager.speak(CommandTokenizer.tokenize(command));
        }
    }

    @Override
    public void afterDriverCommandExecuted(DriverCommandResult command) {
        if (options.isLogAfterCommand() && !skipLog(command)) {
            speechManager.speak(CommandTokenizer.tokenize(command));
        }
    }

    @Override
    public void onException(DriverCommandException command) {
        if (options.isLogException()) {
            speechManager.speak(CommandTokenizer.tokenizeException(command.getException()));
        }
    }

    @Override
    public void beforeElementCommandExecuted(ElementCommand command) {
        if (options.isLogBeforeCommand() && !skipLog(command)) {
            speechManager.speak(CommandTokenizer.tokenize(command));
        }
    }

    @Override
    public void afterElementCommandExecuted(ElementCommandResult command) {
        if (options.isLogAfterCommand() && !skipLog(command)) {
            speechManager.speak(CommandTokenizer.tokenize(command));
        }
    }

    @Override
    public void onException(ElementCommandException command) {
        speechManager.speak(CommandTokenizer.tokenizeException(command.getException()));
    }

    private boolean skipLog(BaseCommand command) {
        return this.options.getExcludedMethods().contains(command.getMethod().getName());
    }
}
