# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(lib_src_DIR ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp)




add_library(libavcodec-56 SHARED IMPORTED)
set_target_properties(libavcodec-56 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libavcodec-56.so)

add_library(libavdevice-56 SHARED IMPORTED)
set_target_properties(libavdevice-56 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libavdevice-56.so)

add_library(libavfilter-5 SHARED IMPORTED)
set_target_properties(libavfilter-5 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libavfilter-5.so)

add_library(libavformat-56 SHARED IMPORTED)
set_target_properties(libavformat-56 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libavformat-56.so)

add_library(libavutil-54 SHARED IMPORTED)
set_target_properties(libavutil-54 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libavutil-54.so)

add_library(libpostproc-53 SHARED IMPORTED)
set_target_properties(libpostproc-53 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libpostproc-53.so)

add_library(libsfftranscoder SHARED IMPORTED)
set_target_properties(libsfftranscoder PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libsfftranscoder.so)

add_library(libswresample-1 SHARED IMPORTED)
set_target_properties(libswresample-1 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libswresample-1.so)

add_library(libswscale-3 SHARED IMPORTED)
set_target_properties(libswscale-3 PROPERTIES IMPORTED_LOCATION ${lib_src_DIR}/libswscale-3.so)


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # src/main/cpp/ffmpeg_jni.c
             src/main/cpp/cmdutils.c
             src/main/cpp/ffmpeg_filter.c
             src/main/cpp/ffmpeg_mod.c
             src/main/cpp/ffmpeg_opt.c
             src/main/cpp/native-lib.cpp
             )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

include_directories(src/main/jniLibs/include)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                       libavcodec-56
                       libavdevice-56
                       libavfilter-5
                       libavformat-56
                       libavutil-54
                       libpostproc-53
                       libsfftranscoder
                       libswresample-1
                       libswscale-3
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )