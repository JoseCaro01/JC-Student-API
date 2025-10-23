package com.jcaro.jcstudentapi.domain.model;


public enum ScoreEnum {

    INSUFFICIENT(0, "Insufficient"), SUFFICIENT(1, "Sufficient"), GOOD(2, "Good"), EXCELLENT(3, "Excellent");

    private final int value;
    private final String name;

    ScoreEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    public static ScoreEnum fromValue(int value) {
        for (ScoreEnum score : ScoreEnum.values()) {
            if (score.value == value) {
                return score;
            }
        }
        throw new IllegalArgumentException("Invalid score value: " + value);
    }

    public static ScoreEnum fromValue(String value) {
        try {
            int scoreValue = Integer.parseInt(value);
            return ScoreEnum.fromValue(scoreValue);

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid score value provided: " + value, e);
        }
    }


}

