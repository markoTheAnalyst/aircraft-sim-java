package gui;

import main.Main;
import radar.Collision;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.logging.Level;

import static main.Main.ALERT;
import static main.Main.EVENTS;

public class FileWatcher extends Thread {
    private Path dir;
    private JLabel label;
    FileWatcher(Path dir){
        this.dir = dir;
    }
    FileWatcher(Path dir, JLabel label){
        this.dir = dir;
        this.label = label;
    }
    @Override
    public void run() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        if(dir.endsWith(ALERT)) {
                            sleep(500);
                            JFrame frame = new JFrame();
                            JLabel label = new JLabel();
                            frame.add(label);
                            frame.setBounds(200, 200, 370, 100);
                            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dir +File.separator+ fileName));
                            Collision object = (Collision)objectInputStream.readObject();
                            label.setText(object.getDescription()+" "+object.getPosition()+" at time "+object.getTime());
                            frame.setVisible(true);
                        }
                        else if(dir.endsWith(EVENTS))
                        {
                            sleep(500);
                            BufferedReader input = new BufferedReader(new InputStreamReader( new FileInputStream(dir +File.separator+ fileName)));
                            String line = input.readLine();
                            String[] data = line.split(" ");
                            label.setText("Foreign aircraft detected at coordinates x: "+data[0]+" y: "+data[1]+" at altitude "+data[2]+" heading "+data[3]);
                        }
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            Main.log(Level.SEVERE, FileWatcher.class.getName(), e);
        }

    }

}
