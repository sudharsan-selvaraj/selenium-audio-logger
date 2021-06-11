package io.github.sudharsan_selvaraj.audiologger;

import io.github.sudharsan_selvaraj.SpyDriver;
import org.openqa.selenium.WebDriver;

public class SeleniumAudioLogger<T extends WebDriver> {

    private Boolean pause;
    private final SpyDriver<T> spyDriver;
    private LogOptions options;


    public SeleniumAudioLogger(T driver, LogOptions options) {
        this(new SpyDriver<T>(driver), options);
    }

    public SeleniumAudioLogger(T driver) {
        this(new SpyDriver<T>(driver), new LogOptions());
    }

    public SeleniumAudioLogger(SpyDriver<T> spyDriver) {
        this(spyDriver, new LogOptions());
    }

    public SeleniumAudioLogger(SpyDriver<T> spyDriver, LogOptions options) {
        this.spyDriver = spyDriver;
        DriverEventListener listener = new DriverEventListener(options);
        spyDriver.addListener(listener);
    }

    public T getDriver() {
        return spyDriver.getSpyDriver();
    }

    public void pause() {

    }

    public void resume() {

    }
}


