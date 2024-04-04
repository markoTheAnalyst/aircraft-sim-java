package map;

import main.Main;
import radar.Collision;
import vehicles.Aircraft;
import vehicles.MilitaryAirplane;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.logging.Level;

import static main.Main.ALERT;


public class Field {

    private LinkedList<Aircraft> field;

    public LinkedList<Aircraft> getField() {
        return field;
    }

    Field() {
        field = new LinkedList<>();
    }
    public synchronized void removeAircraft(Aircraft aircraft){

        field.remove(aircraft);
    }
    public synchronized void containsDomesticMilitary(int altitude){
        for(Aircraft aircraft : field)
        {
            if(aircraft.isMilitary() && aircraft.getAltitude() == altitude && ((MilitaryAirplane)aircraft).isForeign()){
                aircraft.setCanFly(false);
            }
        }
    }
    public synchronized void putAircraft(Aircraft aircraft){
        for(Aircraft a : field)
        {
            if(a.getAltitude() == aircraft.getAltitude())
            {
                a.setCanFly(false);
                aircraft.setCanFly(false);
                removeAircraft(a);
                removeAircraft(aircraft);

                System.out.println("COLISION");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("HH-mm-ss");
                LocalDateTime currentTime = LocalDateTime.now();
                String time = currentTime.format(format);

                try (FileOutputStream file = new FileOutputStream(ALERT + File.separator  + time);
                     ObjectOutputStream out = new ObjectOutputStream(file)) {
                    out.writeObject(new Collision("A collision happened at coordinates", time, "x: " + a.getCoordinateX() + " y: " + a.getCoordinateY()));
                } catch (IOException e) {
                    Main.log(Level.SEVERE, Field.class.getName(), e);
                }
                return;
            }
        }
        field.add(aircraft);
    }
}
