package org.example.Entity;

public class VoteSummary {
    private int validCount;
    private int blankCount;
    private int nullCount;

    public VoteSummary(int validCount, int blankCount, int nullCount) {
        this.validCount = validCount;
        this.blankCount = blankCount;
        this.nullCount = nullCount;
    }

    public int getValidCount() {
        return validCount;
    }

    public int getBlankCount() {
        return blankCount;
    }

    public int getNullCount() {
        return nullCount;
    }

    @Override
    public String toString() {
        return "VoteSummary(validCount=" + validCount +
                ", blankCount=" + blankCount +
                ", nullCount=" + nullCount + ")";
    }
}