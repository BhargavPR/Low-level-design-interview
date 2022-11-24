package BattleShip.service;

import BattleShip.model.*;
import BattleShip.utils.GameUtils;

import java.util.List;

public class GameService {

    private Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void startGame() {
        List<Player> players = game.getPlayers();
        List<Board> boards = game.getBoards();

        int turn = 0;
        while (!isGameCompleted()) {
            Player currentPlayer = players.get(turn % players.size());
            Board opponentBoard = boards.get((turn + 1) % boards.size());

            Location location = GameUtils.generateShipLocation(opponentBoard.getSize());
            makeMove(opponentBoard, location);
            System.out.println("Player " + currentPlayer.getName() + " fire missile and board status is " + opponentBoard.toString());
            turn++;
        }

    }

    private void makeMove(Board board, Location targetLocation) {
        if (!GameUtils.isValidLocation(targetLocation, board.getSize())) {
            return;
        }

        List<Ship> ships = board.getShips();
        for (Ship ship : ships) {
            List<Location> locations = ship.getLocations();
            for (Location location : locations) {
                if (GameUtils.isEqualLocation(location, targetLocation)) {
                    ship.getDamagedLocation().add(targetLocation);
                    return;
                }
            }
        }
    }


    private boolean isGameCompleted() {
        List<Board> boards = game.getBoards();
        for (Board board : boards) {
            if (isBoardDestroyed(board)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardDestroyed(Board board) {
        List<Ship> ships = board.getShips();
        for (Ship ship : ships) {
            if (ship.getLocations().size() != ship.getDamagedLocation().size()) {
                return false;
            }
        }
        return true;
    }
}
