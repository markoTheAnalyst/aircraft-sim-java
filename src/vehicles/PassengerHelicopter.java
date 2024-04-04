package vehicles;

import interfaces.Transporter;

public class PassengerHelicopter extends Helicopter implements Transporter {

    int numberOfSeats;
    public PassengerHelicopter(int coordinateX, int coordinateY, String direction)
    {
        super(coordinateX,coordinateY,direction);
    }

    @Override
    public void transport() {
        System.out.println("Transporting passengers.");
    }

}
