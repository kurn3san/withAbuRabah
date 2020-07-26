package Home.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Equipment {
    private int equipmentid;
    private int poleDistance;
    private int distanceOfLight;
    private String equipmentName = "";
    private double uVLightIntensity;
    private String mpCarrierMedium = "";
    private final DoubleProperty uVLightIntnsity = new SimpleDoubleProperty();

    public int getEquipmentid() {
        return equipmentid;
    }

    private String magTech = "";

    public void setEquipmentid(int equipmentid) {
        this.equipmentid = equipmentid;
    }

    public Equipment(String equipmentName,
                     int poleDistance, String mpCarrierMedium,
                     double uVLightIntensity, int distanceOfLight,
                     String magTech) {
        this.equipmentName = equipmentName;
        this.poleDistance = poleDistance;
        this.mpCarrierMedium = mpCarrierMedium;
        this.uVLightIntensity = uVLightIntensity;
        this.distanceOfLight = distanceOfLight;
        this.magTech = magTech;

    }

    public final DoubleProperty uVLightIntensityProperty() {
        return uVLightIntnsity;
    }

    public final Double getuVLightIntensity() {
        return uVLightIntnsity.get();
    }

    public final void setuVLightIntensity(Double value) {
        uVLightIntnsity.set(value);
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

    public double getDoubleuVLightIntensity() {
        return uVLightIntensity;
    }

    public void setDoubleuVLightIntensity(double uVLightIntensity) {
        this.uVLightIntensity = uVLightIntensity;
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


    @Override
    public String toString() {
        return "Equipment: "
                + equipmentName +
                ". Specifications: " +
                poleDistance +
                ", " + mpCarrierMedium +
                ", " + uVLightIntensity +
                ", " + distanceOfLight +
                ", " + magTech + "."
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquipmentName(), getPoleDistance(), getMpCarrierMedium(), getDoubleuVLightIntensity(), getDistanceOfLight(), getMagTech());
    }

    public boolean equals(Equipment equipment) {
        return getPoleDistance() == equipment.getPoleDistance() &&
                getDistanceOfLight() == equipment.getDistanceOfLight() &&
                Double.compare(equipment.getuVLightIntensity(), getuVLightIntensity()) == 0 &&
                getEquipmentName().equals(equipment.getEquipmentName()) &&
                getMpCarrierMedium().equals(equipment.getMpCarrierMedium());
    }
}
