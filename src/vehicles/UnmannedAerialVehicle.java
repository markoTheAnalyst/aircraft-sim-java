package vehicles;

import person.Person;

public class UnmannedAerialVehicle extends Aircraft{

    @Override
    public String toString() {
        return "UnmannedAerialVehicle";
    }

    public UnmannedAerialVehicle(int coordinateX, int coordinateY, String direction) {

        super(coordinateX, coordinateY, direction);
        this.people = new Person[0];
    }

    public void recordArea(){
        System.out.println("Area is being recorded");
    }
}
