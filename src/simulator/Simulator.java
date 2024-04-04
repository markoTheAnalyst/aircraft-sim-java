package simulator;

import main.Main;
import map.Map;
import vehicles.FighterAircraft;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class Simulator extends Thread{
    private int interval;
    public static boolean permission = true;
    static boolean foreignMilitary = false;
    static boolean domesticMilitary = false;
    public static Map map;
    private LinkedList<String> events;


    public Simulator(Map mapp, LinkedList<String> events, int interval) {
        this.interval = interval;
        this.events = events;
        map = mapp;
    }

    @Override
    public void run() {
        List<String> types = List.of(
                "vehicles.TransportHelicopter",
                "vehicles.PassengerAirplane",
                "vehicles.TransportAirplane",
                "vehicles.PassengerHelicopter",
                "vehicles.FirefighterHelicopter",
                "vehicles.FirefighterAirplane",
                "vehicles.UnmannedAerialVehicle"
        );
        List<String> military = List.of(
                "vehicles.FighterAircraft",
                "vehicles.Bomber"
        );
        Class [] paramTypes = {int.class,int.class,String.class };
        Class [] paramTypesMilitary = {int.class,int.class,String.class,boolean.class };
        new PropertiesChecker().start();
        Random rand = new Random();

        while (true){
            try {
                if(permission) {
                    int randType = rand.nextInt(types.size());
                    int randPosition = rand.nextInt(4);
                    if (randPosition == 0) {
                        Class.forName(types.get(randType)).getConstructor(paramTypes).newInstance(0, rand.nextInt(map.getColumns()), "South");
                    } else if (randPosition == 1) {
                        Class.forName(types.get(randType)).getConstructor(paramTypes).newInstance(map.getRows()-1, rand.nextInt(map.getColumns()), "North");
                    } else if (randPosition == 2) {
                        Class.forName(types.get(randType)).getConstructor(paramTypes).newInstance(rand.nextInt(map.getRows()), 0, "East");
                    } else if (randPosition == 3) {
                        Class.forName(types.get(randType)).getConstructor(paramTypes).newInstance(rand.nextInt(map.getRows()), map.getColumns()-1, "West");
                    }
                }
                if(foreignMilitary)
                {
                    int randType = rand.nextInt(military.size());
                    int randPosition = rand.nextInt(4);
                    if (randPosition == 0) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(0, rand.nextInt(map.getColumns()), "South",true);
                    } else if (randPosition == 1) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(map.getRows()-1, rand.nextInt(map.getColumns()), "North",true);
                    } else if (randPosition == 2) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(rand.nextInt(map.getRows()), 0, "East",true);
                    } else if (randPosition == 3) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(rand.nextInt(map.getRows()), map.getColumns()-1, "West",true);
                    }
                }
                if(domesticMilitary)
                {
                    int randType = rand.nextInt(military.size());
                    int randPosition = rand.nextInt(4);
                    if (randPosition == 0) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(0, rand.nextInt(map.getColumns()), "South",false);
                    } else if (randPosition == 1) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(map.getRows()-1, rand.nextInt(map.getColumns()), "North",false);
                    } else if (randPosition == 2) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(rand.nextInt(map.getRows()), 0, "East",false);
                    } else if (randPosition == 3) {
                        Class.forName(military.get(randType)).getConstructor(paramTypesMilitary).newInstance(rand.nextInt(map.getRows()), map.getColumns()-1, "West",false);
                    }
                }

                Thread.sleep(interval);

                if(!events.isEmpty())
                {
                    String[] args = events.remove().split(" ");
                    seekAndDestroy(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),args[3]);
                }

            } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InterruptedException e){
                Main.log(Level.SEVERE, Main.class.getName(), e);
            }
        }
    }
    private void seekAndDestroy(int coordinateX, int coordinateY, int altitude, String direction){
        switch (direction) {
            case "North":
                deployNorth(coordinateY,altitude);
                break;
            case "South":
                deploySouth(coordinateY,altitude);
                break;
            case "East":
                deployEast(coordinateX,altitude);
                break;
            case "West":
                deployWest(coordinateX,altitude);
                break;
        }

    }
    private void deployWest(int coordinateX, int altitude){
        if (coordinateX == 0) {
            new FighterAircraft(coordinateX, map.getColumns() - 1, "West", altitude, false);
            new FighterAircraft(coordinateX + 1, map.getColumns() - 1, "West", altitude, false);
        } else if (coordinateX == map.getRows() - 1) {
            new FighterAircraft(coordinateX - 1, map.getColumns() - 1, "West", altitude, false);
            new FighterAircraft(coordinateX, map.getColumns() - 1, "West", altitude, false);
        } else {
            new FighterAircraft(coordinateX - 1, map.getColumns() - 1, "West", altitude, false);
            new FighterAircraft(coordinateX + 1, map.getColumns() - 1, "West", altitude, false);
        }
    }

    private void deployEast(int coordinateX, int altitude){
        if (coordinateX == 0) {
            new FighterAircraft(coordinateX, 0, "East", altitude, false);
            new FighterAircraft(coordinateX + 1, 0, "East", altitude, false);
        } else if (coordinateX == map.getRows() - 1) {
            new FighterAircraft(coordinateX - 1, 0, "East", altitude, false);
            new FighterAircraft(coordinateX, 0, "East", altitude, false);
        } else {
            new FighterAircraft(coordinateX - 1, 0, "East", altitude, false);
            new FighterAircraft(coordinateX + 1, 0, "East", altitude, false);
        }
    }

    private void deploySouth(int coordinateY,int altitude){
        if (coordinateY == 0) {
            new FighterAircraft(0, coordinateY + 1, "South", altitude, false);
            new FighterAircraft(0, coordinateY, "South", altitude, false);
        } else if (coordinateY == map.getColumns() - 1) {
            new FighterAircraft(0, coordinateY - 1, "South", altitude, false);
            new FighterAircraft(0, coordinateY, "South", altitude, false);
        } else {
            new FighterAircraft(0, coordinateY - 1, "South", altitude, false);
            new FighterAircraft(0, coordinateY + 1, "South", altitude, false);
        }
    }

    private void deployNorth(int coordinateY,int altitude){
        if (coordinateY == 0) {
            new FighterAircraft(map.getRows() - 1, coordinateY + 1, "North", altitude, false);
            new FighterAircraft(map.getRows() - 1, coordinateY, "North", altitude, false);
        } else if (coordinateY == map.getColumns() - 1) {
            new FighterAircraft(map.getRows() - 1, coordinateY - 1, "North", altitude, false);
            new FighterAircraft(map.getRows() - 1, coordinateY, "North", altitude, false);
        } else {
            new FighterAircraft(map.getRows() - 1, coordinateY - 1, "North", altitude, false);
            new FighterAircraft(map.getRows() - 1, coordinateY + 1, "North", altitude, false);
        }
    }
}
