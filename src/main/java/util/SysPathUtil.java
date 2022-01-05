package util;

/**
 * @author : suiyuan
 * @description :
 * @date : Created in 2020-02-01 12:06
 * @modified by :
 **/
public class SysPathUtil {
    /**
     * 获取jar包路径
     *
     * @return
     */
    public static String getPath() {
        String path = SysPathUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows")) {
            path = path.substring(1, path.length());
        }
        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            return path.substring(0, path.lastIndexOf("/"));
        }
        return path.replace("target/classes/", "");
    }
}
