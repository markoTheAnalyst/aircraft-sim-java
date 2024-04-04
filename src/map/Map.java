package map;

public class Map {

    private int rows;
    private int columns;
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    public Field[][] fields;
    public Map(int rows,int columns){
        this.rows = rows;
        this.columns = columns;
        fields = new Field[rows][columns];
        for(int i =0;i< rows; i++)
            for(int j=0; j<columns;j++)
                fields[i][j]= new Field();
    }
}
