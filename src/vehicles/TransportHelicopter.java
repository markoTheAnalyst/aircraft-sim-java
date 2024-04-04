package vehicles;


import interfaces.Transporter;

public class TransportHelicopter extends Helicopter implements Transporter {

    int maxWeightOfCargo;
    public TransportHelicopter(int coordinateX,int coordinateY, String direction)
    {
        super(coordinateX,coordinateY,direction);
    }

    @Override
    public void transport() {
        System.out.println("Transporting cargo.");
    }
}