package aitor.font.puig.changeyourvoice;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Aitor Font on 14/3/18.
 */

class FileManager {

    private static FileManager instance = null;
    static FileManager getInstance() {
        if(instance == null) instance = new FileManager();

        return instance;
    }

    private File audioFolder = null;
    private boolean audioFolderCreated = true;

    private void createAudioFolder() {
        audioFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/ChangeYourVoice/");
        if(!audioFolder.exists()) {
            audioFolderCreated = audioFolder.mkdir();
        }
    }

    File getAudioFolder() {
        if(audioFolder != null && audioFolderCreated) return audioFolder;

        createAudioFolder();

        return audioFolder;
    }

    File getAudioFile(String fileName) {
        if(audioFolder == null || !audioFolderCreated) createAudioFolder();

        File audioFile = new File(audioFolder.getAbsolutePath() + "/" + fileName + ".mp3");

        if(audioFile.exists()) audioFile.delete();
        try {
            audioFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return audioFile;
    }
}
