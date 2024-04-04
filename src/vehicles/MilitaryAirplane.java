package vehicles;

import interfaces.Weaponized;

import static simulator.Simulator.map;

public abstract class MilitaryAirplane extends Airplane implements Weaponized {

    boolean isForeign;
    private boolean isDetected;

    MilitaryAirplane(int coordinateX, int coordinateY, String direction) {
        super(coordinateX, coordinateY, direction);
        this.isMilitary = true;
        this.isDetected = false;
    }

    public MilitaryAirplane(int coordinateX, int coordinateY, String direction,boolean isForeign) {
        super(coordinateX, coordinateY, direction);
        this.isMilitary = true;
        this.isForeign = isForeign;
    }

    public boolean isDetected() {
        return isDetected;
    }

    public void setDetected(boolean detected) {
        isDetected = detected;
    }

    public boolean isForeign() {
        return isForeign;
    }

    @Override
    public String toString() {
        if(isForeign)
            return "Foreign";
        else
            return "Domestic";
    }

    @Override
    public void run() {
        switch (direction) {
            case "South":
                for (int i = 0; i < map.getRows() && canFly; i++) {
                    flySouth();
                }
                break;
            case "East":
                for (int i = 0; i < map.getColumns() && canFly; i++) {
                    flyEast();
                }
                break;
            case "North":
                for (int i = 0; i < map.getRows() && canFly; i++) {
                    flyNorth();
                }
                break;
            case "West":
                for (int i = 0; i < map.getColumns() && canFly; i++) {
                    flyWest();
                }
                break;
        }
    }
}