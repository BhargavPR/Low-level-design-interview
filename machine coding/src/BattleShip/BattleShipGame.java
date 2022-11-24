package BattleShip;

import BattleShip.constants.Constants;
import BattleShip.model.Board;
import BattleShip.model.Game;
import BattleShip.model.Player;
import BattleShip.model.Ship;
import BattleShip.service.GameService;
import BattleShip.utils.GameUtils;

import java.util.List;

public class BattleShipGame {

    public static void main(String[] arg) {

        Player bhargav = new Player("Bhargav");
        Player jaimin = new Player("jaimin");

        List<Ship> bhargavShips = GameUtils.generateShips(Constants.DEFAULT_BOARD_SIZE, Constants.DEFAULT_SHIP_COUNT);
        List<Ship> jaiminShips = GameUtils.generateShips(Constants.DEFAULT_BOARD_SIZE, Constants.DEFAULT_SHIP_COUNT);

        Board bhargavBoard = new Board(Constants.DEFAULT_BOARD_SIZE);
        Board jaiminBoard = new Board(Constants.DEFAULT_BOARD_SIZE);

        bhargavBoard.setShips(bhargavShips);
        jaiminBoard.setShips(jaiminShips);

        Game game = new Game(List.of(bhargav, jaimin), List.of(bhargavBoard, jaiminBoard));

        GameService gameService = new GameService(game);

        gameService.startGame();
    }
}
