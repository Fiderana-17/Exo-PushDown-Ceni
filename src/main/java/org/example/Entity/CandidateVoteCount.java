package org.example.Entity;

public class CandidateVoteCount {
    private String candidateName;
    private int validVoteCount;

    public CandidateVoteCount(String candidateName, int validVoteCount) {
        this.candidateName = candidateName;
        this.validVoteCount = validVoteCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public int getValidVoteCount() {
        return validVoteCount;
    }

    @Override
    public String toString() {
        return candidateName + "=" + validVoteCount;
    }
}