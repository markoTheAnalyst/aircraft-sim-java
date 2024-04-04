package vehicles;


import static simulator.Simulator.map;

public class FighterAircraft extends MilitaryAirplane{

    public FighterAircraft(int coordinateX, int coordinateY, String direction, boolean isForeign) {
        super(coordinateX, coordinateY, direction);
        this.isForeign = isForeign;
    }
    public FighterAircraft(int coordinateX, int coordinateY, String direction,int altitude, boolean isForeign) {
        super(coordinateX, coordinateY, direction);
        this.isForeign = isForeign;
        this.altitude = altitude;
    }

    @Override
    public void run() {
        switch (direction) {
            case "South":
                for (int i = 0; i < map.getRows() && canFly; i++) {
                    if (!isForeign) {
                        searchSouth();
                    }
                    flySouth();
                }
                break;
            case "East":
                for (int i = 0; i < map.getColumns() && canFly; i++) {
                    if (!isForeign) {
                        searchEast();
                    }
                    flyEast();
                }
                break;
            case "North":
                for (int i = 0; i < map.getRows() && canFly; i++) {
                    if (!isForeign) {
                        searchNorth();
                    }
                    flyNorth();
                }
                break;
            case "West":
                for (int i = 0; i < map.getColumns() && canFly; i++) {
                    if (!isForeign) {
                        searchWest();
                    }
                    flyWest();
                }
                break;
        }
    }
    private void searchWest(){
        if (coordinateX == 0) {
            map.fields[coordinateX + 1][coordinateY].containsDomesticMilitary(altitude);
            if(coordinateY >0)
                map.fields[coordinateX][coordinateY - 1].containsDomesticMilitary(altitude);
        } else if (coordinateX == map.getRows() - 1) {
            map.fields[coordinateX - 1][coordinateY].containsDomesticMilitary(altitude);
            if(coordinateY >0)
                map.fields[coordinateX][coordinateY - 1].containsDomesticMilitary(altitude);
        } else {
            map.fields[coordinateX + 1][coordinateY].containsDomesticMilitary(altitude);
            map.fields[coordinateX - 1][coordinateY].containsDomesticMilitary(altitude);
        }
    }

    private void searchNorth(){
        if (coordinateY == 0) {
            if(coordinateX >0)
                map.fields[coordinateX - 1][coordinateY].containsDomesticMilitary(altitude);
            map.fields[coordinateX][coordinateY + 1].containsDomesticMilitary(altitude);
        } else if (coordinateY == map.getColumns() - 1) {
            if(coordinateX >0)
                map.fields[coordinateX - 1][coordinateY].containsDomesticMilitary(altitude);
            map.fields[coordinateX][coordinateY - 1].containsDomesticMilitary(altitude);
        } else {
            map.fields[coordinateX][coordinateY + 1].containsDomesticMilitary(altitude);
            map.fields[coordinateX][coordinateY - 1].containsDomesticMilitary(altitude);
        }
    }

    private void searchEast(){
        if (coordinateX == 0) {
            map.fields[coordinateX + 1][coordinateY].containsDomesticMilitary(altitude);
            if(coordinateY < map.getColumns()-1)
                map.fields[coordinateX][coordinateY + 1].containsDomesticMilitary(altitude);
        } else if (coordinateX == map.getRows()-1) {
            map.fields[coordinateX - 1][coordinateY].containsDomesticMilitary(altitude);
            if(coordinateY < map.getColumns()-1)
                map.fields[coordinateX][coordinateY + 1].containsDomesticMilitary(altitude);
        } else {
            map.fields[coordinateX + 1][coordinateY].containsDomesticMilitary(altitude);
            map.fields[coordinateX - 1][coordinateY].containsDomesticMilitary(altitude);
        }
    }
    private void searchSouth(){
        if (coordinateY == 0) {
            if(coordinateX < map.getRows()-1)
                map.fields[coordinateX + 1][coordinateY].containsDomesticMilitary(altitude);
            map.fields[coordinateX][coordinateY + 1].containsDomesticMilitary(altitude);
        } else if (coordinateY == map.getColumns()-1) {
            if(coordinateX < map.getRows()-1)
                map.fields[coordinateX + 1][coordinateY].containsDomesticMilitary(altitude);
            map.fields[coordinateX][coordinateY - 1].containsDomesticMilitary(altitude);
        } else {
            map.fields[coordinateX][coordinateY + 1].containsDomesticMilitary(altitude);
            map.fields[coordinateX][coordinateY - 1].containsDomesticMilitary(altitude);
        }
    }

    @Override
    public void attack() {
        System.out.println("Shooting at airborne and land targets.");
    }
}
