package vehicles;

import interfaces.FireExtinguish;

public class FirefighterHelicopter extends Helicopter implements FireExtinguish {

    int waterAmount;

    public FirefighterHelicopter(int coordinateX,int coordinateY, String direction)
    {
        super(coordinateX,coordinateY,direction);
    }

    @Override
    public void extinguishFire() {
        System.out.println("Extinguishing fire with water.");
    }
}
