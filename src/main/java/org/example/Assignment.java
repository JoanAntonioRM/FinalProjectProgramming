package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@EqualsAndHashCode
@Getter
@Setter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;
    private static int nextId = 1;

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.format("A%02d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new ArrayList<>();
    }

    public double calcAssignmentAvg() {
        if (scores.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }

        return (double) sum / scores.size();
    }

    public void generateRandomScore() {
        Random rand = new Random();

        for (int i = 0; i < scores.size(); i++) {
            int randomNum = rand.nextInt(11);
            int score;

            if (randomNum == 0) {
                score = rand.nextInt(60);
            } else if (randomNum <= 2) {
                score = rand.nextInt(60, 70);
            } else if (randomNum <= 4) {
                score = rand.nextInt(70, 80);
            } else if (randomNum <= 8) {
                score = rand.nextInt(80, 90);
            } else {
                score = rand.nextInt(90, 101);
            }

            scores.set(i, score);
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f%%)", assignmentName, weight);
    }
}
