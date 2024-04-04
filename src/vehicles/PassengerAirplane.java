package vehicles;

import interfaces.Transporter;

public class PassengerAirplane extends Airplane implements Transporter {
    int numberOfSeats;
    int maxWeightOfLuggage;

    public PassengerAirplane(int coordinateX, int coordinateY, String direction)
    {
        super(coordinateX,coordinateY,direction);
    }

    @Override
    public void transport() {
        System.out.println("Transporting passengers.");
    }
}
