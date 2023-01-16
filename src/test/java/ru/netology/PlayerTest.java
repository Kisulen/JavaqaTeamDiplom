package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {


       @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNoGamesPlayed() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Treasure Hunt", "Three in a row");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 0);

        int expected = 0;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Treasure Hunt", "Three in a row");
        Game game2 = store.publishGame("Butterflies", "Three in a row");
        Game game3 = store.publishGame("Royal Gems", "Three in a row");
        Player player = new Player("Petya");

        player.installGame(game1);
        player.play(game1, 3);

        player.installGame(game2);
        player.play(game1, 4);

        player.installGame(game3);
        player.play(game1, 5);

        int expected = 12;
        int actual = player.sumGenre("Three in a row");
        assertEquals(expected, actual);
    }



    @Test
    public void shouldPlayNewGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Holy Grail", "Quest");

        Player player = new Player("Petya");
        player.installGame(game);

        int expected = 5;
        int actual = player.play(game, 5);;

        assertEquals(expected,actual);
    }

    @Test
    public void shouldPlayTheSameGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Holy Grail", "Quest");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 15);

        int expected = 23;
        int actual = player.play(game, 8);

        assertEquals(expected,actual);
    }

    @Test
    public void shouldOnlyInstallGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Eldorado", "Quest");

        Player player = new Player("Petya");
        player.installGame(game);

        int expected = 0;
        int actual = player.play(game, 0);

        assertEquals(expected,actual);
    }

    @Test
    public void throwsRuntimeException() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Neverhood", "Quest");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () ->
                player.play(game,7)
                );

    }

    @Test
    public void shouldReturnNullForMostPlayedByGenre () {
        GameStore store = new GameStore();
        Game game = store.publishGame("Eldorado", "Quest");

        Player player = new Player("Petya");
        player.installGame(game);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Quest");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldFindMostPlayedGameWithinGenre () {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Treasure Hunt", "Three in a row");
        Game game2 = store.publishGame("Butterflies", "Three in a row");
        Game game3 = store.publishGame("Royal Gems", "Three in a row");


        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 13);
        player.play(game2, 18);
        player.play(game3, 2);


        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Three in a row");
        Assertions.assertEquals(expected,actual);
    }

    // другие ваши тесты
}
