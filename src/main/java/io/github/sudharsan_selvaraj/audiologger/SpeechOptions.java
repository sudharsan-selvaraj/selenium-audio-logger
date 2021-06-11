package io.github.sudharsan_selvaraj.audiologger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechOptions {

    private Voice voice = Voice.Kevin16;
    private float rate = 140;
    private float pitch = 125;
    private float volume = 100;

}
