package utils;

import java.io.File;

public class WaitFileReader {
    public static void waitForFileToBeAvailable(File file, int timeoutSeconds){
        int attempts = 0;
        long lastModified = 0;
        long fileSize = 0;

        while (attempts < timeoutSeconds * 2) {
            if (file.exists()) {
                long currentSize = file.length();
                long currentModified = file.lastModified();
                if (currentSize == fileSize && currentModified == lastModified) {
                    return;
                }
                fileSize = currentSize;
                lastModified = currentModified;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attempts++;
        }
        if (!file.exists()) {
            throw new RuntimeException("File not found within the specified timeout: " + file.getAbsolutePath());
        }
    }
}
