//
// Created by lucky on 2017/3/25.
//

#include <android/log.h>

const static char *TAG = "MyLogTag";
#define LOGI(...)\
((void)__android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__))
