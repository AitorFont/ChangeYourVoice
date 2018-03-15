cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99 -Wall")

add_library(changeyourvoice-jni SHARED
            changeyourvoice-jni.c)

# Include libraries needed for changeyourvoice-jni lib
target_link_libraries(changeyourvoice-jni
                      android
                      log
                      OpenSLES)
