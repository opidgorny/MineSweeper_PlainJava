package sweeper;

class Bomb {

    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void init() {
        bombMap = new Matrix(Cell.ZERO);
        for(int i = 0; i < totalBombs; i++){
            placeBomb();
        }
    }

    // Checker for legal amount of bombs
    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().getX() * Ranges.getSize().getY() / 2;
        if(totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }

    }

    private void placeBomb () {
        while(true) {
            Coord coord = Ranges.getRandomCoord();
            if(Cell.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Cell.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for(Coord around : Ranges.getCoordsAround(coord)) {
            if(Cell.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberCell());

            }
        }
    }


    Cell get(Coord coord) {
        return bombMap.get(coord);
    }


    int getTotalBombs() {
        return totalBombs;
    }
}
