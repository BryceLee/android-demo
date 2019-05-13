LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := lzxjni
LOCAL_SRC_FILES := com_android_jnimodule_jni_JNIEntity.c

include $(BUILD_SHARED_LIBRARY)

