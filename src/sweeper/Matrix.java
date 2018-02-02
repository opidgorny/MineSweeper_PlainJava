package sweeper;

class Matrix {

    private Cell [] [] matrix;

    public Matrix(Cell defaultCell) {
        matrix = new Cell[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for(Coord coord : Ranges.getAllCoords()) {
            matrix [coord.getX()] [coord.getY()] = defaultCell;
        }
    }

    public Cell get(Coord coord) {
        if (Ranges.inRange(coord)) {
            return matrix[coord.getX()][coord.getY()];
        }
        return null;
    }

    void set(Coord coord, Cell cell) {
        if (Ranges.inRange(coord))
            matrix[coord.getX()][coord.getY()] = cell;
    }
}
