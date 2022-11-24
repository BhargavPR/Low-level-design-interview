package BattleShip.database;

import BattleShip.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {

    private final List<Player> players = new ArrayList<>();

    private static PlayerDatabase INSTANCE = null;

    public static PlayerDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerDatabase();
        }
        return INSTANCE;
    }

    public void insertPlayer(Player player) {
        players.add(player);
    }

}
