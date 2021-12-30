package org.now.mas.util;

import com.sun.jna.Structure;
import org.now.mas.sdk.HCNetSDK;

/**
 * @author Administrator
 */
public final class MasUtil {
    /**
     * CALL SDK FAIL
     */
    public static final int CALL_FAIL = -1;
    /**
     * LOGIN FAIL
     */
    public static final int LOGIN_FAIL = CALL_FAIL;

    /**
     * 云台停止动作或开始动作：0－开始
     */
    public static final int PTZ_START = 0;
    /**
     * 云台停止动作或开始动作：1－停止
     */
    public static final int PTZ_STOP = 1;

    /**
     * ability out put xml length
     */
    public static final int XML_ABILITY_OUT_LENGTH = 10240;

    public static final String JPEG_SUFFIX = ".jpeg";
    public static final String MP4_SUFFIX = ".mp4";
    public static final int DEFAULT_CHANNEL = 1;
    public static final String DATE_TIME_PATTERN = "yyyyMMdd_HHmmss";

    private MasUtil() {
    }

    /**
     * default max length is 256
     *
     * @param src src
     * @return byte array
     */
    public static HCNetSDK.BYTE_ARRAY convert2ByteArray(String src) {
        HCNetSDK.BYTE_ARRAY byteArr = new HCNetSDK.BYTE_ARRAY(HCNetSDK.MAX_LEN_OSD_CONTENT);
        System.arraycopy(src.getBytes(), 0, byteArr.byValue, 0, src.length());
        byteArr.write();
        return byteArr;
    }

    /**
     * check res is success or fail
     *
     * @param res res
     * @return true or false
     */
    public static boolean checkCallResult(int res) {
        return res != MasUtil.CALL_FAIL;
    }


    /**
     * String Pointer
     */
    public static class NET_DVR_STRING_POINTER extends Structure {
        public byte[] byString;
    }
}
