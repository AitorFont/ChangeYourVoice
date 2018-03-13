package aitor.font.puig.changeyourvoice;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class RecordAudioActivity extends AppCompatActivity {

    Boolean folderCreated = true, audioFileCreated = true;
    String title = "AudioFile";

    Button btn_record, btn_stop;
    EditText et_title;

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
        prepareInterface();
    }

    private void prepareInterface() {
        btn_record = findViewById(R.id.btn_record);
        btn_stop = findViewById(R.id.btn_stop);
        et_title = findViewById(R.id.et_title);

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

        et_title.setText(title);
        et_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title = s.toString();
            }
        });
    }

    private void prepareAudioRecorder() {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    private void startRecording() {
        if(folderCreated) {
            audioFile = new File(audioFolder.getAbsolutePath() + "/" + title + ".mp3");
            if(!audioFile.exists()) {
                try {
                    audioFileCreated = audioFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(audioFileCreated) {
            prepareAudioRecorder();
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
        Toast.makeText(this, R.string.audio_created, Toast.LENGTH_SHORT).show();
    }
}
