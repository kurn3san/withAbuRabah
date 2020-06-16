package Home.model;

import java.util.Objects;

public class Equipment {

    private String equipmentName = "";
    private int poleDistance = 0;
    private String mpCarrierMedium = "";
    private String uVLightDensity = "";
    private int distanceOfLight = 0;
    private String magTech = "";


    public Equipment(String equipmentName,
                     int poleDistance, String mpCarrierMedium,
                     String uVLightDensity, int distanceOfLight,
                     String magTech, boolean testType) {
        this.equipmentName = equipmentName;
        this.poleDistance = poleDistance;
        this.mpCarrierMedium = mpCarrierMedium;
        this.uVLightDensity = uVLightDensity;
        this.distanceOfLight = distanceOfLight;
        this.magTech = magTech;

    }

    public Equipment() {
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getPoleDistance() {
        return poleDistance;
    }

    public void setPoleDistance(int poleDistance) {
        this.poleDistance = poleDistance;
    }

    public String getMpCarrierMedium() {
        return mpCarrierMedium;
    }

    public void setMpCarrierMedium(String mpCarrierMedium) {
        this.mpCarrierMedium = mpCarrierMedium;
    }

    public String getuVLightDensity() {
        return uVLightDensity;
    }

    public void setuVLightDensity(String uVLightDensity) {
        this.uVLightDensity = uVLightDensity;
    }

    public int getDistanceOfLight() {
        return distanceOfLight;
    }

    public void setDistanceOfLight(int distanceOfLight) {
        this.distanceOfLight = distanceOfLight;
    }

    public String getMagTech() {
        return magTech;
    }

    public void setMagTech(String magTech) {
        this.magTech = magTech;
    }


    public void setId(int equipmentid) {
    }

    @Override
    public String toString() {
        return "Equipment: " +
                ", equipmentName= " + equipmentName +
                ", poleDistance= " + poleDistance +
                ", mpCarrierMedium= " + mpCarrierMedium +
                ", uVLightDensity= " + uVLightDensity +
                ", distanceOfLight= " + distanceOfLight +
                ", magTech= " + magTech + "."
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return getPoleDistance() == equipment.getPoleDistance() &&
                getDistanceOfLight() == equipment.getDistanceOfLight() &&
                getEquipmentName().equals(equipment.getEquipmentName()) &&
                getMpCarrierMedium().equals(equipment.getMpCarrierMedium()) &&
                getuVLightDensity().equals(equipment.getuVLightDensity()) &&
                getMagTech().equals(equipment.getMagTech());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquipmentName(), getPoleDistance(), getMpCarrierMedium(), getuVLightDensity(), getDistanceOfLight(), getMagTech());
    }
}
