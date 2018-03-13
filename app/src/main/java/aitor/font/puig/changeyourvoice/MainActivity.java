package aitor.font.puig.changeyourvoice;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Boolean folderCreated = true;

    private RecyclerView audioList;
    private File audioFolder;
    private List<AudioFileClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Taking music folder reference
        audioFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/ChangeYourVoice/");
        if(!audioFolder.exists()) {
            folderCreated = audioFolder.mkdir();
        }

        prepareInterface();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initializeData();
        setAudioList();
    }

    private void prepareInterface() {
        Button btn_record = findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordAudioActivity.class);
                startActivity(intent);
            }
        });

        audioList = findViewById(R.id.audioList);
        audioList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        audioList.setLayoutManager(llm);
    }

    private void initializeData(){
        list = new ArrayList<>();
        if(folderCreated) {
            File[] files = audioFolder.listFiles();

            for (File file : files) {
                list.add(new AudioFileClass(file.getName()));
            }
        }
    }

    private void setAudioList() {
        AudioAdapter adapter = new AudioAdapter(list);
        audioList.setAdapter(adapter);
    }
}
