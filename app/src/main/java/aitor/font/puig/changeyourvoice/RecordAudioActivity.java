package aitor.font.puig.changeyourvoice;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class RecordAudioActivity extends AppCompatActivity {

    Boolean folderCreated = true, audioFileCreated = true;

    Button btn_record, btn_stop;

    MediaRecorder audioRecorder;
    File audioFolder, audioFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);

        // Taking music folder reference
        audioFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/ChangeYourVoice/");
        if(!audioFolder.exists()) {
            folderCreated = audioFolder.mkdir();
        }

        prepareAudioRecorder();
        prepareInterface();
    }

    private void prepareAudioRecorder() {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    private void prepareInterface() {
        btn_record = findViewById(R.id.btn_record);
        btn_stop = findViewById(R.id.btn_stop);

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_record.setEnabled(false);
                startRecording();
                btn_stop.setEnabled(true);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_stop.setEnabled(false);
                stopRecording();
                btn_record.setEnabled(true);
            }
        });
    }

    private void startRecording() {
        if(folderCreated) {
            audioFile = new File(audioFolder.getAbsolutePath() + "/audioFile.mp3");
            if(!audioFile.exists()) {
                try {
                    audioFileCreated = audioFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(audioFileCreated) {
            audioRecorder.setOutputFile(audioFile.getAbsolutePath());
            try {
                audioRecorder.prepare();
                audioRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopRecording() {
        audioRecorder.stop();
    }
}
