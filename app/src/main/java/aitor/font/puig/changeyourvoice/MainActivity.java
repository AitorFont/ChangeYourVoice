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
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Boolean folderCreated = true;

    private RecyclerView audioList;
    private TextView tv_empty;

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
        Button btn_newAudio = findViewById(R.id.btn_record);
        btn_newAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordAudioActivity.class);
                startActivity(intent);
            }
        });

        tv_empty = findViewById(R.id.tv_empty);

        audioList = findViewById(R.id.audioList);
        audioList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        audioList.setLayoutManager(llm);
    }

    private void initializeData(){
        list = new ArrayList<>();
        if(folderCreated) {
            File[] files = audioFolder.listFiles();
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File file1, File file2) {
                    if (file1.lastModified() < file2.lastModified()) return 1;
                    else if(file1.lastModified() > file2.lastModified()) return -1;
                    else return 0;
                }
            });

            for (File file : files) {
                list.add(new AudioFileClass(file.getName()));
            }

            if (files.length > 0) tv_empty.setVisibility(View.GONE);
            else tv_empty.setVisibility(View.VISIBLE);
        }
    }

    private void setAudioList() {
        if (list.size() > 0) {
            AudioAdapter adapter = new AudioAdapter(list);
            audioList.setAdapter(adapter);
        }
    }
}
