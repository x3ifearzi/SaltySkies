package de.saltyfearz.saltyskies.ranksystem;

public enum RANKS {

    ADMIN("§4[A]", "SaltySkies.*"),

    DEV("§b[DEV]", "SaltySkies.*"),

    SUP("§2[SUP]", "SaltySkies.*"),

    VIP("§d[VIP]", "SaltySkies.*"),

    PLAYER("§7[P]", "SaltySkies.*");

    private final String prefix, permissions;

    RANKS( final String permissions, final String prefix ) {

        this.prefix = prefix;

        this.permissions = permissions;

    }

    public String getPrefix( ) {
        return prefix;
    }

    public String getPermissions ( ) { return permissions; }

}

