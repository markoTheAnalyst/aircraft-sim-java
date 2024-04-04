package vehicles;

import interfaces.FireExtinguish;

public class FirefighterAirplane extends Airplane implements FireExtinguish {

    int waterAmount;
    public FirefighterAirplane(int coordinateX, int coordinateY, String direction)
    {
        super(coordinateX,coordinateY,direction);
    }

    @Override
    public void extinguishFire() {
        System.out.println("Extinguishing fire with water.");
    }
}
