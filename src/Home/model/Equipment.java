package Home.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Equipment {

    private String equipmentName = "";
    private int poleDistance = 0;
    private String mpCarrierMedium = "";
    private final DoubleProperty uVLightIntnsity = new SimpleDoubleProperty();
    private int distanceOfLight = 0;
    private String magTech = "";
    private double uVLightIntensity = 0.0;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return getPoleDistance() == equipment.getPoleDistance() &&
                getDistanceOfLight() == equipment.getDistanceOfLight() &&
                getEquipmentName().equals(equipment.getEquipmentName()) &&
                getMpCarrierMedium().equals(equipment.getMpCarrierMedium()) &&
                getDoubleuVLightIntensity() == equipment.getDoubleuVLightIntensity() &&
                getMagTech().equals(equipment.getMagTech());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquipmentName(), getPoleDistance(), getMpCarrierMedium(), getDoubleuVLightIntensity(), getDistanceOfLight(), getMagTech());
    }

}
