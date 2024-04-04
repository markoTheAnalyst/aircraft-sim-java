package vehicles;

public class Bomber extends MilitaryAirplane {

    public Bomber(int coordinateX,int coordinateY,String direction,boolean isForeign)
    {
        super(coordinateX, coordinateY, direction);
        this.isForeign = isForeign;
    }

    @Override
    public void attack() {
        System.out.println("Dropping bombs on ground targets");
    }
}