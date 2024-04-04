package vehicles;

public abstract class Helicopter extends Aircraft{

    @Override
    public String toString() {
        return "Helicopter";
    }

    public Helicopter(int coordinateX, int coordinateY, String direction) {
        super(coordinateX, coordinateY, direction);
    }
}
