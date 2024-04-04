package vehicles;

public abstract class Airplane extends Aircraft{
    @Override
    public String toString() {
        return "Airplane";
    }

    public Airplane(int coordinateX, int coordinateY, String direction) {
        super(coordinateX, coordinateY, direction);
    }
}
