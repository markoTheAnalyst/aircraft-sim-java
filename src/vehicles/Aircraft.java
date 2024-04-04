package vehicles;

import main.Main;
import person.Person;
import simulator.Simulator;

import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import static simulator.Simulator.map;

public abstract class Aircraft extends Thread{

    int coordinateX;
    int coordinateY;
    private int speed;
    boolean canFly = true;
    boolean isMilitary = false;
    String model;
    Map<String,String> characteristics;
    String identification;
    int altitude;
    Person[] people;
    String direction;

    public String getDirection() {
        return direction;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }
    public boolean isMilitary() {
        return isMilitary;
    }
    public int getAltitude() {
        return altitude;
    }
    Aircraft(int coordinateX, int coordinateY, String direction)
    {
        Random rand = new Random();
        speed = (rand.nextInt(3) + 1)*1000;
        altitude = rand.nextInt(200);
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.direction = direction;
        start();
    }

    @Override
    public String toString() {
        return "Aircraft";
    }

    @Override
    public void run() {
        switch (direction) {
            case "South":
                for (int i = 0; i < map.getRows() && canFly; i++) {
                    flySouth();
                    if(!Simulator.permission) {
                        retreat();
                        return;
                    }
                }
                break;
            case "East":
                for (int i = 0; i < map.getColumns() && canFly; i++) {
                    flyEast();
                    if(!Simulator.permission) {
                        retreat();
                        return;
                    }
                }
                break;
            case "North":
                for (int i = 0; i < map.getRows() && canFly; i++) {
                    flyNorth();
                    if (!Simulator.permission) {
                        retreat();
                        return;
                    }
                }
                break;
            case "West":
                for (int i = 0; i < map.getColumns() && canFly; i++) {
                    flyWest();
                    if (!Simulator.permission) {
                        retreat();
                        return;
                    }
                }
                break;
        }
    }

    void flyWest()
    {
        map.fields[coordinateX][coordinateY].putAircraft(this);
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            Main.log(Level.SEVERE, Aircraft.class.getName(), e);
        }
        map.fields[coordinateX][coordinateY].removeAircraft(this);
        coordinateY--;
    }
    void flyEast()
    {
        map.fields[coordinateX][coordinateY].putAircraft(this);
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            Main.log(Level.SEVERE, Aircraft.class.getName(), e);
        }
        map.fields[coordinateX][coordinateY].removeAircraft(this);
        coordinateY++;
    }
    void flySouth()
    {
        map.fields[coordinateX][coordinateY].putAircraft(this);
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            Main.log(Level.SEVERE, Aircraft.class.getName(), e);
        }
        map.fields[coordinateX][coordinateY].removeAircraft(this);
        coordinateX++;
    }
    void flyNorth()
    {
        map.fields[coordinateX][coordinateY].putAircraft(this);
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            Main.log(Level.SEVERE, Aircraft.class.getName(), e);
        }
        map.fields[coordinateX][coordinateY].removeAircraft(this);
        coordinateX--;
    }
    private void retreat()
    {
        if(coordinateX <= map.getRows() - coordinateX-1 && coordinateX <= coordinateY && coordinateX <= map.getColumns()- coordinateY-1)
        {
            if("South".equals(direction) && canFly) {
                flyEast();
            }
            while(coordinateX >=0 && canFly){
                flyNorth();
            }
            return;
        }
        if(map.getRows()- coordinateX -1 <= coordinateY && map.getRows()- coordinateX -1<= map.getColumns()- coordinateY -1)
        {
            if("North".equals(direction) && canFly) {
                flyEast();
            }
            while(coordinateX < map.getRows() && canFly) {
                flySouth();
            }
            return;
        }
        if(coordinateY <= map.getColumns() - coordinateY -1) {
            if("East".equals(direction) && canFly) {
                flyNorth();
            }
            while(coordinateY >= 0 && canFly){
                flyWest();
            }
            return;
        }
        if("West".equals(direction) && canFly) {
            flyNorth();
        }
        while(coordinateY < map.getColumns() && canFly){
            flyEast();

        }

    }
}
