package aitor.font.puig.changeyourvoice;

/**
 * Created by Aitor Font on 13/3/18.
 */

public class AudioFileClass {

    private String audioTitle;

    public AudioFileClass(String title) {
        audioTitle = title;
    }

    public String getAudioTitle() { return audioTitle; }

    public void setAudioTitle(String title) { audioTitle = title; }
}
