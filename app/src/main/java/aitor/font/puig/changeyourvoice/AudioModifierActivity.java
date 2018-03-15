package aitor.font.puig.changeyourvoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AudioModifierActivity extends AppCompatActivity {

    private ChangeYourVoiceJNI changeYourVoiceJNI = ChangeYourVoiceJNI.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_modifier);

        String fileName = getIntent().getStringExtra("fileName");
        String hello = changeYourVoiceJNI.getStringFromJNI();

        Log.d("HOLA", fileName + " - " + hello);
    }
}
