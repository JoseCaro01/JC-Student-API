package com.jcaro.jcstudentapi.domain.model;


public enum ScoreEnum {

    INSUFFICIENT(0), SUFFICIENT(1), GOOD(2), EXCELLENT(3);

    private final int value;

    ScoreEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ScoreEnum fromValue(int value) {
        for (ScoreEnum score : ScoreEnum.values()) {
            if (score.value == value) {
                return score;
            }
        }
        throw new IllegalArgumentException("Invalid score value: " + value);
    }
}

