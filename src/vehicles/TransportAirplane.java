package vehicles;

import interfaces.Transporter;

public class TransportAirplane extends Airplane implements Transporter {
    @Override
    public void transport() {
        System.out.println("Transporting cargo.");
    }

    int maxWeightOfCargo;
    public TransportAirplane(int coordinateX, int coordinateY, String direction)
    {
        super(coordinateX,coordinateY,direction);
    }
}
