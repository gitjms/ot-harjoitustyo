package jms.tictactoe.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;

import jms.tictactoe.domain.Score;

/**
 * Data Access Object for file storaging.
 * @author jaris
 */
public final class FileScoreDao implements ScoreDao {
    
    private int points;
    private int games;
    private Score scoreItem;
    private String id;
    private String file;
    private Map<String, Pair<Integer, Integer>> fileScores;
    
    public FileScoreDao(String file) throws Exception {
        this.fileScores = new HashMap<>();
        this.file = file;
        try (Scanner reader = new Scanner(new File(file))) {
            if (reader.hasNext()) {
                while (reader.hasNextLine()) {
                    String[] parts = reader.nextLine().split(";");
                    this.id = parts[0];
                    this.points = Integer.parseInt(parts[1]);
                    this.games = Integer.parseInt(parts[2]);
                    this.scoreItem = new Score(this.id, this.points, this.games);
                    Pair<Integer, Integer> newPair = new Pair(this.points, this.games);
                    this.fileScores.put(this.id, newPair);
                }
            } else {
                FileWriter writer = new FileWriter(new File(file));
                writer.write("X;0;0\n");
                writer.write("O;0;0\n");
                this.setScore(new Score("X", 0, 0));
                Pair<Integer, Integer> newPairX = new Pair(0, 0);
                this.fileScores.put("X", newPairX);
                this.setScore(new Score("O", 0, 0));
                Pair<Integer, Integer> newPairO = new Pair(0, 0);
                this.fileScores.put("O", newPairO);
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(new File("scores.txt"));
            writer.write("X;0;0\n");
            writer.write("O;0;0\n");
            this.setScore(new Score("X", 0, 0));
            Pair<Integer, Integer> newPairX = new Pair(0, 0);
            this.fileScores.put("X", newPairX);
            this.setScore(new Score("O", 0, 0));
            Pair<Integer, Integer> newPairO = new Pair(0, 0);
            this.fileScores.put("O", newPairO);
        }
    }
    
    private void save() throws Exception {
        int totGames = this.getGames();
        try (FileWriter writer = new FileWriter(new File(this.file))) {
            for (Map.Entry<String, Pair<Integer, Integer>> mapScores : this.fileScores.entrySet()) {
                writer.write(mapScores.getKey() + ";" + mapScores.getValue().getKey() + ";" + totGames + "\n");
            }
        }
    }
    
    @Override
    public Score create(Score score) throws Exception {
        save();
        return score;
    }

    @Override
    public Score getScore() {
        return scoreItem;
    }

    @Override
    public void setScore(Score score) {
        this.scoreItem = score;
    }

    public boolean isScore() {
        return this.scoreItem != null;
    }

    @Override
    public Map<String, Pair<Integer, Integer>> getAllMap() {
        return fileScores;
    }

    @Override
    public void setAllMap(Map<String, Pair<Integer, Integer>> map) {
        this.fileScores = map;
    }

    @Override
    public void resetPoints() {
        this.fileScores.clear();
        this.setScore(new Score("X", 0, 0));
        this.fileScores.put("X", new Pair(0, 0));
        this.setScore(new Score("O", 0, 0));
        this.fileScores.put("O", new Pair(0, 0));
        this.setPoints("X", 0);
        this.setPoints("O", 0);
    }

    @Override
    public void setPoints(String id, int points) {
        this.fileScores.put(id, new Pair(points, this.getGames()));
    }

    @Override
    public int getPoints(String id) {
        return this.fileScores.get(id).getKey();
    }

    @Override
    public int getGames() {
        return this.isScore() == false ? 0 : this.scoreItem.getGames();
    }

    @Override
    public void setGames(int games) {
        if (this.isScore() == false) {
            resetPoints();
        } else {
            this.scoreItem.setGames(games);
        }
    }
}
