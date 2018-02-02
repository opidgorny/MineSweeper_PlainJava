package sweeper;

class Flag {

    private Matrix flagMap;
    private int countOfClosedCells;

    void init() {
        flagMap = new Matrix(Cell.CLOSED);
        countOfClosedCells = Ranges.getSize().getX() * Ranges.getSize().getY();
    }

    Cell get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToCell(Coord coord) {
        flagMap.set(coord, Cell.OPENED);
        countOfClosedCells--;
    }

    void toggleFlaggedToCell(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED: setClosedToCell(coord); break;
            case CLOSED: setFlaggedToCell(coord); break;

        }
    }

    void setFlaggedToCell(Coord coord) {
        flagMap.set(coord, Cell.FLAGED);
    }

    void setClosedToCell(Coord coord) {
        flagMap.set(coord, Cell.CLOSED);
    }

    int getCountOfClosedCells() {
        return countOfClosedCells;
    }

    void setBombedToCell(Coord coord) {
        flagMap.set(coord, Cell.BOMBED);
    }

    void setOpenedToClosedBombCell(Coord coord) {
        if(flagMap.get(coord) == Cell.CLOSED) {
            flagMap.set(coord, Cell.OPENED);
        }
    }

    void setNoBombToFlagedSafeCell(Coord coord) {
        if(flagMap.get(coord) == Cell.FLAGED) {
            flagMap.set(coord, Cell.NOBOMB);
        }
    }

    int getCountOfFlagedCellsAround(Coord coord) {
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord)) {
            if(flagMap.get(around) == Cell.FLAGED) {
                count++;
            }
        }
        return count;
    }
}
