package victor.krupka.java_kata.domain.model;

import java.util.UUID;

public class Customer {
    private final UUID id;
    private final MembershipLevel membershipLevel;
    private final String email;
    private final int purchaseCount;

    public Customer(UUID id, MembershipLevel membershipLevel, String email, int purchaseCount) {
        this.id = id;
        this.membershipLevel = membershipLevel;
        this.email = email;
        this.purchaseCount = purchaseCount;
    }

    public UUID getId() {
        return id;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public String getEmail() {
        return email;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public boolean isPremium() {
        return membershipLevel.isPremium();
    }

    public int getPriorityScore() {
        return membershipLevel.getBaseScore() + (purchaseCount * 2);
    }
}
