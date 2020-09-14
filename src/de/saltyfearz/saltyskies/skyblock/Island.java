package de.saltyfearz.saltyskies.skyblock;

import de.saltyfearz.saltyskies.regions.Cuboid;

public class Island {

    private Cuboid region;

    private String ownerUUID;
    private String islandName;

    private double positionX1;
    private double positionX2;

    private double positionZ1;
    private double positionZ2;

    public Island ( Cuboid region, String ownerUUID, String islandName ) {

        this.region = region;
        this.ownerUUID = ownerUUID;
        this.islandName = islandName;

        this.positionX1 = region.getLowerX();
        this.positionX2 = region.getUpperX();

        this.positionZ1 = region.getLowerZ();
        this.positionX2 = region.getUpperZ();

    }

    public Cuboid getRegion ( ) {
        return region;
    }

    public void setRegion ( Cuboid region ) {
        this.region = region;
    }

    public String getOwnerUUID ( ) {
        return ownerUUID;
    }

    public void setOwnerUUID ( String ownerUUID ) {
        this.ownerUUID = ownerUUID;
    }

    public String getIslandName ( ) {
        return islandName;
    }

    public void setIslandName ( String islandName ) {
        this.islandName = islandName;
    }

    public double getPositionX1 ( ) {
        return positionX1;
    }

    public void setPositionX1 ( double positionX1 ) {
        this.positionX1 = positionX1;
    }

    public double getPositionX2 ( ) {
        return positionX2;
    }

    public void setPositionX2 ( double positionX2 ) {
        this.positionX2 = positionX2;
    }

    public double getPositionZ1 ( ) {
        return positionZ1;
    }

    public void setPositionZ1 ( double positionZ1 ) {
        this.positionZ1 = positionZ1;
    }

    public double getPositionZ2 ( ) {
        return positionZ2;
    }

    public void setPositionZ2 ( double positionZ2 ) {
        this.positionZ2 = positionZ2;
    }
}
