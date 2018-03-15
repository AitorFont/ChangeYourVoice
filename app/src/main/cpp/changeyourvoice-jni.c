#include <string.h>
#include <jni.h>

// for native audio
#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

JNIEXPORT jstring JNICALL
Java_aitor_font_puig_changeyourvoice_ChangeYourVoiceJNI_stringFromJNI( JNIEnv* env,
                                                  jobject thiz )
{
    return (*env)->NewStringUTF(env, "Hello from JNI!");
}
