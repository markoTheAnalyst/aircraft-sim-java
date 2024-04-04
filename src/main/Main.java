package main;

import map.Map;
import radar.Radar;
import gui.MapGUI;
import simulator.Simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.*;

public class Main {

    private static Handler handler;
    static{
        try {
            handler = new FileHandler("error.log",true);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void log(Level level, String name, Exception ex) {
        Logger logger = Logger.getLogger(name);
        logger.addHandler(handler);
        logger.log(level, null, ex);
    }
    public static final String EVENTS = "events";
    public static final String ALERT = "alert";
    public static final String MAP = "map.txt";
    public static final String CONFIG = "config.properties";
    public static final String RADAR = "radar.properties";

    public static void main(String[] args) {

        int rows, columns;
        Map map;
        String line;
        int simInterval;
        int radInterval;
        try (BufferedReader simReader = new BufferedReader(new FileReader(CONFIG))) {
            line = simReader.readLine();
            rows = Integer.parseInt(line.split("=")[1]);
            line = simReader.readLine();
            columns = Integer.parseInt(line.split("=")[1]);
            map = new Map(rows, columns);
            line = simReader.readLine();
            simInterval = Integer.parseInt(line.split("=")[1]);
            try(BufferedReader radReader = new BufferedReader(new FileReader(RADAR))){
                line = radReader.readLine();
                radInterval = Integer.parseInt(line.split("=")[1]);
            }

        } catch (NegativeArraySizeException | NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException | IOException e) {
            rows = 100;
            columns = 100;
            simInterval = 5000;
            radInterval = 1000;
            map = new Map(rows, columns);
            log(Level.SEVERE, Main.class.getName(), e);
        }
        LinkedList<String> events = new LinkedList<>();
        final Object lock = new Object();
        MapGUI gui = new MapGUI(lock, rows, columns);
        Simulator sim = new Simulator(map, events, simInterval);
        Radar rad = new Radar(map, lock, events, radInterval);
        FileBackup backup = new FileBackup();
        sim.start();
        rad.start();
        gui.start();
        backup.start();
    }
}
