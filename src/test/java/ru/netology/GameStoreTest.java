package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldNotContainNewGameNoGames() {

        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    @Test
    public void shouldNotContainNewGameGamesExist() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = new Game("Нетология Баттл Онлайн-2", "Аркады", store);
        assertFalse(store.containsGame(game2));
    }


    @Test
    public void shouldFindBestPlayerIfNotEqual() {
        GameStore store = new GameStore();

        store.addPlayTime("Petya", 3);
        store.addPlayTime("Olga", 5);
        store.addPlayTime("Vaska", 4);

        String expected = "Olga";
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindBestPlayerIfNoPlayers() {
        GameStore store = new GameStore();
        assertEquals(null, store.getMostPlayer());
    }

    @Test
    public void shouldAddPlayTimeIfFirstPlayed() {
        GameStore store = new GameStore();

        store.addPlayTime("Petya", 3);

        int expected = 3;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddPlayTimeIfPlayedBefore() {
        GameStore store = new GameStore();

        store.addPlayTime("Petya", 3);
        store.addPlayTime("Petya", 4);

        int expected = 7;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumPlayedTimeIfSeveral() {
        GameStore store = new GameStore();

        store.addPlayTime("Petya", 3);
        store.addPlayTime("Olga", 5);

        int expected = 8;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }


}
