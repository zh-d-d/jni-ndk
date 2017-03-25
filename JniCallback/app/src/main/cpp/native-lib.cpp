#include <jni.h>
#include <string>
#include "MyLog.h"
#include <pthread.h>
#include <assert.h>

typedef struct tick_context {
    JavaVM *javaVM;
    jclass mainActivityCls;
    jobject mainActivityObj;
    pthread_mutex_t lock;
    int done;
} TickContext;
TickContext g_ctx;


extern "C"
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {

    JNIEnv *env;
    memset(&g_ctx, 0, sizeof(g_ctx));
    g_ctx.javaVM = vm;

    if ((*vm).GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
//        jni version not support
        return JNI_ERR;
    }
    g_ctx.done = 0;
    g_ctx.mainActivityObj = NULL;
    return JNI_VERSION_1_6;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zdd_jnicallback_MainActivity_stopTicks(JNIEnv *env, jobject instance) {

    // TODO

}

/**
 * work thread
 */
void *UpdateTicks(void *context) {
    TickContext *tickContext = (TickContext *) context;

    JavaVM *javaVM = tickContext->javaVM;
    JNIEnv *env;
    int attachResult = javaVM->AttachCurrentThread(&env, NULL);
    if (JNI_OK != attachResult) {
        return NULL;
    }

    jmethodID updateTimerId = (*env).GetMethodID(tickContext->mainActivityCls, "updateTimer",
                                                 "()V");
    if (NULL == updateTimerId) {
        return NULL;
    }
    struct timeval beginTime, curTime, usedTime, leftTime;
    const struct timeval kOneSecond = {
            (__kernel_time_t) 1,
            (__kernel_suseconds_t) 0
    };

    while (1) {
        gettimeofday(&beginTime, NULL);
        pthread_mutex_lock(&tickContext->lock);
        int done = tickContext->done;
        if (tickContext->done) {
            tickContext->done = 0;
        }
        pthread_mutex_unlock(&tickContext->lock);
        if (done) {
            break;
        }
        (*env).CallVoidMethod(tickContext->mainActivityObj, updateTimerId);
        gettimeofday(&curTime, NULL);

        timersub(&beginTime, &curTime, &usedTime);
        timersub(&kOneSecond, &usedTime, &leftTime);
        struct timespec sleepTime;
        sleepTime.tv_sec = leftTime.tv_sec;
        sleepTime.tv_nsec = leftTime.tv_usec * 1000;
        if (sleepTime.tv_sec <= 1) {
            nanosleep(&sleepTime, NULL);
        }
    }

    (*javaVM).DestroyJavaVM();
    return context;
}


extern "C"
JNIEXPORT void JNICALL
Java_com_zdd_jnicallback_MainActivity_startTicks(JNIEnv *env, jobject instance) {

    pthread_t threadInfo;
    pthread_attr_t threadAttr;

    pthread_attr_init(&threadAttr);
    pthread_attr_setdetachstate(&threadAttr, PTHREAD_CREATE_DETACHED);

    pthread_mutex_init(&g_ctx.lock, NULL);

    jclass mainActivityCls = (*env).GetObjectClass(instance);

    g_ctx.mainActivityCls = (jclass) (*env).NewGlobalRef(mainActivityCls);
    g_ctx.mainActivityObj = (*env).NewGlobalRef(instance);

    int result = pthread_create(&threadInfo, &threadAttr,
                                UpdateTicks, &g_ctx);

    assert(result == 0);
    (void) result;
}
