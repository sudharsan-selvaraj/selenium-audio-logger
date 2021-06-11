package io.github.sudharsan_selvaraj.audiologger;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class SpeechManager {

    private Voice speaker;

    public SpeechManager(SpeechOptions options) {
        initializeVoice(options);
    }

    private void initializeVoice(SpeechOptions options) {
        speaker = getSpeaker(options.getVoice()).get();
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

    private Optional<Voice> getSpeaker(io.github.sudharsan_selvaraj.audiologger.Voice voice) {
        return Arrays.stream(new KevinVoiceDirectory().getVoices()).filter(v -> v.getName().equals(voice.name().toLowerCase(Locale.ROOT))).findFirst();
    }
}
