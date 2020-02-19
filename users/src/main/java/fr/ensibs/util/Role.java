package fr.ensibs.util;

public enum Role {
    admin(1),
    customer(0);

    public int getId() {
        return id;
    }
    public static Role of(int id) {
        for (Role e : values()) {
            if (id == e.id) {
                return e;
            }
        }
        return null;
    }
    Role(int id) {
        this.id = id;
    }
    private final int id;
}
