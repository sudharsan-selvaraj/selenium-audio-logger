package io.github.sudharsan_selvaraj.audiologger;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.Locale;

public class SpeechManager {

    private Voice speaker;

    public SpeechManager(SpeechOptions options) {
        initializeVoice(options);
    }

    private void initializeVoice(SpeechOptions options) {
        speaker = VoiceManager.getInstance().getVoice(options.getVoice().toString().toLowerCase(Locale.ROOT));
        speaker.setPitch(options.getPitch());
        speaker.setRate(options.getRate());
        speaker.setVolume(options.getVolume());
        speaker.allocate();
    }

    public void speak(String message) {
        if(message !=null) {
            speaker.speak(message);
        }
    }
}
