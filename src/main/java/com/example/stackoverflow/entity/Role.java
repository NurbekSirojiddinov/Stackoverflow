package com.example.stackoverflow.entity;

public enum Role {
    ADMINISTRATOR(1), MODERATOR(2), USER(3);
    private final int rank;

    Role(final int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
