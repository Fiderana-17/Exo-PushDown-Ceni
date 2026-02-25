package org.example.Entity;

public class ElectionResult {
        private String candidateName;
        private int validVoteCount;

        public ElectionResult(String candidateName, int validVoteCount) {
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
            return candidateName + " | " + validVoteCount;
        }
    }
