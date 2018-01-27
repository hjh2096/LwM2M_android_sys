package com.cwtcn.leshanandroidlib.resources;

import com.cwtcn.leshanandroidlib.utils.DebugLog;

import org.eclipse.leshan.core.node.LwM2mResource;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;

/**
 * 这个类用于实现免打扰模式
 1、服务器对这个Object中的某个Text Resource执行Write操作时，向Client发送命令，可以设置免打扰时间段和一周中的生效日期。命令是一个json字符串，格式如下：
 {
 "sec1": "07:00-09:30",
 "sec2": "10:00-13:40",
 "repeatExpression": "0111110"
 }
 sec1：表示第一个免打扰时间段
 sec2：表示第二个免打扰时间段
 repeatExpression:表示一周中的那些天，免打扰模式是生效的。从周日开始，0表示免打扰模式不生效，1表示免打扰模式生效。

 2、服务器可以对Object中的Text Resource执行Read操作，Client接收到后向服务器返回当前的免打扰设置信息。

 */
public class NoDisturbMode extends ExtendBaseInstanceEnabler {
    public static final String KEY_NO_DISTURB_MODE_MSG = "NoDisturbModeMsg";
    public static final int EVENT_IDENTIFIER = 5823;
    public static final int TEXT = 5527;

    @Override
    public synchronized ReadResponse read(int resourceId) {
        switch (resourceId) {
            case TEXT:
                //读联系人信息，提交到服务器
                return ReadResponse.success(resourceId, getNoDisturbModeMsg());
            default:
                return super.read(resourceId);
        }
    }

    @Override
    public WriteResponse write(int resourceid, LwM2mResource value) {
        switch (resourceid) {
            case TEXT:
                String settingMsg = (String) value.getValue();
                DebugLog.d("settingMsg:" + settingMsg);
                setNonDisturbMode(settingMsg);
                return WriteResponse.success();
            default:
                return super.write(resourceid, value);
        }
    }

    /**
     * 根据设置Write过来的信息设置免打扰模式。
     * @param settingMsg
     */
    private void setNonDisturbMode(String settingMsg) {
        mOnWriteReadListener.setStringToPreference(KEY_NO_DISTURB_MODE_MSG, settingMsg);
    }

    /**
     * 获取免打扰模式的设置信息，以如下格式返回
     {
     "sec1": "07:00-09:30",
     "sec2": "10:00-13:40",
     "repeatExpression": "0111110"
     }
     * @return
     */
    private String getNoDisturbModeMsg() {
        return mOnWriteReadListener.getStringFromPreferemce(KEY_NO_DISTURB_MODE_MSG);
    }
}
