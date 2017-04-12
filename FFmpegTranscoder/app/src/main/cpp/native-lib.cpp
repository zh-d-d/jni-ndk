#include <jni.h>
#include <string>


extern "C" {


JNIEXPORT jint JNICALL
Java_com_zdd_ffmpegtranscoder_MainActivity_ffmpegcore(JNIEnv *env, jobject instance, jint argc,
                                                      jobjectArray argv) {

    // TODO

}


JNIEXPORT jstring JNICALL
Java_com_zdd_ffmpegtranscoder_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

}
