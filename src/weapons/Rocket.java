package weapons;

import java.util.Random;

import static simulator.Simulator.map;

public abstract class Rocket extends Thread {
    private int range;
    private int altitude;
    private int speed;
    private String direction;
    private int coordinateX, coordinateY;

    Rocket(int range, int altitude, String direction, int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.range = range;
        this.altitude = altitude;
        this.direction = direction;
        Random rand = new Random();
        speed = (rand.nextInt(3) + 1) * 1000;
    }

    @Override
    public void run() {
        switch (direction) {
            case "South":
                for (int i = 0; i < range && coordinateX < map.getRows(); i++) {
                    flySouth();
                }
                break;
            case "East":
                for (int i = 0; i < range && coordinateY < map.getColumns(); i++) {
                    flyEast();
                }
                break;
            case "North":
                for (int i = 0; i < range && coordinateX < 0; i++) {
                    flyNorth();
                }
                break;
            case "West":
                for (int i = 0; i < range && coordinateY < 0; i++) {
                    flyWest();
                }
                break;
        }
    }

    private void flyWest() {

    }

    private void flyEast() {

    }

    private void flySouth() {

    }

    private void flyNorth() {

    }
}

