LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional
LOCAL_STATIC_JAVA_LIBRARIES := zxing-core \
         scandium \
		 californium-core \
		 element-connector \
		 leshan-client-cf \
		 leshan-client-core \
		 leshan-core \
		 leshan-core-cf \
		 slf4j-api \
		 android-support-v7-appcompat \
		 android-support-v4 guava \

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := LeshanAndroidLib
LOCAL_CERTIFICATE := platform
LOCAL_DEX_PREOPT := false

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

############################################################### 
include $(CLEAR_VARS)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := zxing-core:libs/zxing-core-3.3.0.jar \
         scandium:libs/scandium-2.0.0-M5_bak.jar \
		 californium-core:libs/californium-core-2.0.0-M5.jar \
		 element-connector:libs/element-connector-2.0.0-M5.jar \
		 leshan-client-cf:libs/leshan-client-cf-1.0.0-SNAPSHOT.jar \
		 leshan-client-core:libs/leshan-client-core-1.0.0-SNAPSHOT.jar \
		 leshan-core:libs/leshan-core-1.0.0-SNAPSHOT.jar \
		 leshan-core-cf:libs/leshan-core-cf-1.0.0-SNAPSHOT.jar \
#		 slf4j-api:libs/slf4j-api-1.7.10.jar \
#		 android-support-v7-appcompat:libs/android-support-v7-appcompat.jar \

include $(BUILD_MULTI_PREBUILT)

# Use the folloing include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
