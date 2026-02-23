package org.example.Entity;

public class VoteTypeCount {

    private String voteType;
    private int count;

    public VoteTypeCount(String voteType, int count) {
        this.voteType = voteType;
        this.count = count;
    }

    public String getVoteType() {
        return voteType;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return voteType + " | " + count;
    }
}