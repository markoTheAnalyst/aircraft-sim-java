package radar;

import main.Main;
import map.Map;
import simulator.Simulator;
import vehicles.Aircraft;
import vehicles.MilitaryAirplane;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.logging.Level;

import static main.Main.EVENTS;
import static main.Main.MAP;

public class Radar extends Thread {

    private int interval;
    private final Object lock;
    private Map map;
    private LinkedList<String> events;

    public Radar(Map map, Object lock, LinkedList<String> events, int radInterval) {
        this.lock = lock;
        this.map = map;
        this.events = events;
        this.interval = radInterval;
    }

    @Override
    public void run() {
        try {
            PrintWriter out;
            while (true) {
                synchronized (lock) {
                    out = new PrintWriter(MAP);
                    for (int i = 0; i < map.getRows(); i++) {
                        for (int j = 0; j < map.getColumns(); j++) {
                            if(!map.fields[i][j].getField().isEmpty()){
                                out.println(i + " " + j+" "+map.fields[i][j].getField().getFirst());
                                out.flush();
                            }
                            for(Aircraft aircraft : map.fields[i][j].getField()) {
                                if (aircraft.isMilitary() && ((MilitaryAirplane)aircraft).isForeign() && !((MilitaryAirplane)aircraft).isDetected()) {
                                    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH-mm-ss");
                                    LocalDateTime currentTime = LocalDateTime.now();
                                    String timeFormat = currentTime.format(format);
                                    try (PrintWriter event = new PrintWriter(EVENTS +File.separator + timeFormat + ".txt")) {
                                        event.println(i+ " "+j+ " "+ aircraft.getAltitude()+" "+aircraft.getDirection());
                                        events.add(i+ " "+j+ " "+ aircraft.getAltitude()+" "+aircraft.getDirection());
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    ((MilitaryAirplane) aircraft).setDetected(true);
                                    Simulator.permission = false;

                                }
                            }
                        }
                    }
                }
                sleep(interval);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            Main.log(Level.SEVERE, Main.class.getName(), e);
        }
    }
}
