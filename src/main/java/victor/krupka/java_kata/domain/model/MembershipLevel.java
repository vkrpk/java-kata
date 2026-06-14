package victor.krupka.java_kata.domain.model;

public enum MembershipLevel {

    STANDARD(0),
    SILVER(10),
    GOLD(25);

    private final int baseScore;

    MembershipLevel(int baseScore) {
        this.baseScore = baseScore;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public boolean isPremium() {
        return this != STANDARD;
    }
}