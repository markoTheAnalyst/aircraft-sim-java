package main;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static main.Main.ALERT;
import static main.Main.EVENTS;

public class FileBackup extends Thread{

    private static final int MINUTE = 60000;
    @Override
    public void run() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
        LocalDateTime currentTime;
        String timeFormat;
        while(true){

                currentTime = LocalDateTime.now();
                timeFormat = currentTime.format(format);
                byte[] buffer = new byte[1024];

            try (FileOutputStream fos = new FileOutputStream("backup_" + timeFormat + ".zip");
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                File[] dirs = new File[2];
                dirs[0] = new File(EVENTS);
                dirs[1] = new File(ALERT);

                for (File dir : dirs) {

                    File[] files = dir.listFiles();

                    for (File file : files) {

                        try (FileInputStream fis = new FileInputStream(file)) {

                            zos.putNextEntry(new ZipEntry(file.getName()));

                            int length;

                            while ((length = fis.read(buffer)) > 0) {
                                zos.write(buffer, 0, length);
                            }

                            zos.closeEntry();
                        }
                    }
                }
            } catch (IOException e) {
                Main.log(Level.SEVERE, FileBackup.class.getName(), e);
            }

            try {
                sleep(MINUTE);
            } catch (InterruptedException e) {
                Main.log(Level.SEVERE, FileBackup.class.getName(), e);
            }
        }
    }
}
