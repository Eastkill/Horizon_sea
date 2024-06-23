package Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private static final String path = "/audio.wav";
    public static void Playmusic(){
        try{
            File musicPath = new File(path);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
                clip.loop(1000);
                clip.start();
            }
            else {
            //System.out.println("lol");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    }
