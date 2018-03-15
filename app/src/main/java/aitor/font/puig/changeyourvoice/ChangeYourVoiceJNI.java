package aitor.font.puig.changeyourvoice;

/**
 * Created by Aitor Font on 15/3/18.
 */

class ChangeYourVoiceJNI {

    private native String stringFromJNI();

    private static ChangeYourVoiceJNI instance = null;
    static ChangeYourVoiceJNI getInstance() {
        if(instance == null) instance = new ChangeYourVoiceJNI();

        return instance;
    }

    String getStringFromJNI() {
        return stringFromJNI();
    }

    static {
        System.loadLibrary("changeyourvoice-jni");
    }
}
