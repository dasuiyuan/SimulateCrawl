package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Sui Yuan
 * @Description:
 * @Date: 2021/8/20 8:52
 * @Modified by:
 **/
public class FileUtil {
    public static void writeText(List<CMGISExamCrawlerUtil.ExamItem> items, String path) {
        try (FileWriter fileWriter = new FileWriter(path); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (CMGISExamCrawlerUtil.ExamItem item : items) {
                bufferedWriter.write(item.toString());
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
