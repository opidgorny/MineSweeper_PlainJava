package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;

    private GameState state;

    public GameState getState() {
        return state;
    }

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.init();
        flag.init();
        state = GameState.PLAYING;
    }


    public Cell getCell(Coord coord) {
        if(flag.get(coord) == Cell.OPENED) {
            return bomb.get(coord);
        } else {
            return flag.get(coord);
        }
    }

    public void pressLeftButton(Coord coord) {
        if(gameOver()) return;
        openCell(coord);
        checkWinner();
    }

    private void checkWinner() {
        if(state == GameState.PLAYING) {
            if(flag.getCountOfClosedCells() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }

    private void openCell(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED: setOpenedToClosedCellsAroundNumber(coord); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(coord)){
                    case ZERO: openCellsAround(coord); return;
                    case BOMB: openBombs(coord); return;
                    //Any NUM*
                    default: flag.setOpenedToCell(coord);return;
                }
        }
    }

    private void setOpenedToClosedCellsAroundNumber(Coord coord) {
        if(bomb.get(coord) != Cell.BOMB) {
            if (flag.getCountOfFlagedCellsAround(coord) == bomb.get(coord).getNumber()){
                for(Coord around : Ranges.getCoordsAround(coord)) {
                    if (flag.get(around) == Cell.CLOSED) {
                        openCell(around);
                    }
                }
            }
        }
    }

    private void openBombs(Coord coord) {
        state = GameState.BOMBED;
        flag.setBombedToCell(coord);

        for(Coord bombed : Ranges.getAllCoords()) {
            if(bomb.get(bombed) == Cell.BOMB) {
                flag.setOpenedToClosedBombCell(bombed);
            } else {
                flag.setNoBombToFlagedSafeCell(bombed);
            }
        }
    }

    private void openCellsAround(Coord coord) {
        flag.setOpenedToCell(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openCell(around);
        }
    }

    public void pressRightButton(Coord coord) {
        if(gameOver()) return;
        flag.toggleFlaggedToCell(coord);
    }

    private boolean gameOver() {
        if(state == GameState.PLAYING) {
            return false;
        }
        start();
        return true;
    }
}
