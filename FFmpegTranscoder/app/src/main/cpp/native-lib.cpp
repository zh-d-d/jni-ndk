#include <jni.h>
#include <string>


extern "C" {
#include <ffmpeg.h>
int ffmpegmain(int argc, char **argv);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_zdd_ffmpegtranscoder_MainActivity_ffmpegcore(JNIEnv *env, jobject instance, jint cmdnum,
                                                      jobjectArray cmdline) {

//    av_log_set_callback(custom_log);

    int argc=cmdnum;
    char** argv=(char**)malloc(sizeof(char*)*argc);

    int i=0;
    for(i=0;i<argc;i++)
    {
        jstring string=(jstring)(*env).GetObjectArrayElement(cmdline,i);
        const char* tmp=(*env).GetStringUTFChars(string,0);
        argv[i]=(char*)malloc(sizeof(char)*1024);
        strcpy(argv[i],tmp);
    }

    ffmpegmain(argc,argv);

    for(i=0;i<argc;i++){
        free(argv[i]);
    }
    free(argv);
    return 0;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zdd_ffmpegtranscoder_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

