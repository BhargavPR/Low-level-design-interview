package BattleShip.model;

import java.util.List;

public class Game {

    private List<Player> players;
    private List<Board> boards;

    public Game(List<Player> players, List<Board> boards) {
        this.players = players;
        this.boards = boards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
