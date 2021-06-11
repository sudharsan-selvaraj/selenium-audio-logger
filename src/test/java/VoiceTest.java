import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.Arrays;

public class VoiceTest {

    public static void main(String[] args) {
        Voice voice;//Creating object of Voice class
        voice = VoiceManager.getInstance().getVoice("kevin16");//Getting voice
        if (voice != null) {
            voice.allocate();//Allocating Voice
        }
        try {
            voice.setRate(150);//Setting the rate of the voice
            voice.setPitch(100);//Setting the Pitch of the voice
            voice.setVolume(10);//Setting the volume of the voice
            voice.speak("Oops there is No such element found exception");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}