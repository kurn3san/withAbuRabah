package Home.model;

import java.util.Objects;

public class EquipInfoSectionOfReport {
    Equipment equipment;
    private String examinArea = "";
    private String currentType = "AC";
    private int Luxumeter = 1200;
    private String testMedium = "";
    private String demag = "";
    private String heatTreat = "";
    private int temp = 0;
    private int gaussFieldStrnth = 0;//     kA/m
    private String surfaceCon = "";
    private String idOfLightEquip = "";
    private String liftTestDateAndNumber = "";

    public EquipInfoSectionOfReport(Equipment equipment,
                                    String examinArea,
                                    String currentType,
                                    int luxumeter,
                                    String testMedium,
                                    String demag,
                                    String heatTreat,
                                    int temp, int gaussFieldStrnth,
                                    String surfaceCon, String idOfLightEquip,
                                    String liftTestDateAndNumber) {
        this.equipment = equipment;
        this.examinArea = examinArea;
        this.currentType = currentType;
        Luxumeter = luxumeter;
        this.testMedium = testMedium;
        this.demag = demag;
        this.heatTreat = heatTreat;
        this.temp = temp;
        this.gaussFieldStrnth = gaussFieldStrnth;
        this.surfaceCon = surfaceCon;
        this.idOfLightEquip = idOfLightEquip;
        this.liftTestDateAndNumber = liftTestDateAndNumber;
    }

    @Override
    public String toString() {
        return "EquipInfoSectionOfReport{" +
                "equipment=" + equipment +
                ", examinArea='" + examinArea + '\'' +
                ", currentType='" + currentType + '\'' +
                ", Luxumeter=" + Luxumeter +
                ", testMedium='" + testMedium + '\'' +
                ", demag='" + demag + '\'' +
                ", heatTreat='" + heatTreat + '\'' +
                ", temp=" + temp +
                ", gaussFieldStrnth=" + gaussFieldStrnth +
                ", surfaceCon='" + surfaceCon + '\'' +
                ", idOfLightEquip='" + idOfLightEquip + '\'' +
                ", liftTestDateAndNumber='" + liftTestDateAndNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipInfoSectionOfReport)) return false;
        EquipInfoSectionOfReport that = (EquipInfoSectionOfReport) o;
        return getLuxumeter() == that.getLuxumeter() &&
                getTemp() == that.getTemp() &&
                getGaussFieldStrnth() == that.getGaussFieldStrnth() &&
                getEquipment().equals(that.getEquipment()) &&
                getExaminArea().equals(that.getExaminArea()) &&
                getCurrentType().equals(that.getCurrentType()) &&
                getTestMedium().equals(that.getTestMedium()) &&
                getDemag().equals(that.getDemag()) &&
                getHeatTreat().equals(that.getHeatTreat()) &&
                getSurfaceCon().equals(that.getSurfaceCon()) &&
                getIdOfLightEquip().equals(that.getIdOfLightEquip()) &&
                getLiftTestDateAndNumber().equals(that.getLiftTestDateAndNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquipment(), getExaminArea(), getCurrentType(), getLuxumeter(), getTestMedium(), getDemag(), getHeatTreat(), getTemp(), getGaussFieldStrnth(), getSurfaceCon(), getIdOfLightEquip(), getLiftTestDateAndNumber());
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getExaminArea() {
        return examinArea;
    }

    public void setExaminArea(String examinArea) {
        this.examinArea = examinArea;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public int getLuxumeter() {
        return Luxumeter;
    }

    public void setLuxumeter(int luxumeter) {
        Luxumeter = luxumeter;
    }

    public String getTestMedium() {
        return testMedium;
    }

    public void setTestMedium(String testMedium) {
        this.testMedium = testMedium;
    }

    public String getDemag() {
        return demag;
    }

    public void setDemag(String demag) {
        this.demag = demag;
    }

    public String getHeatTreat() {
        return heatTreat;
    }

    public void setHeatTreat(String heatTreat) {
        this.heatTreat = heatTreat;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getGaussFieldStrnth() {
        return gaussFieldStrnth;
    }

    public void setGaussFieldStrnth(int gaussFieldStrnth) {
        this.gaussFieldStrnth = gaussFieldStrnth;
    }

    public String getSurfaceCon() {
        return surfaceCon;
    }

    public void setSurfaceCon(String surfaceCon) {
        this.surfaceCon = surfaceCon;
    }

    public String getIdOfLightEquip() {
        return idOfLightEquip;
    }

    public void setIdOfLightEquip(String idOfLightEquip) {
        this.idOfLightEquip = idOfLightEquip;
    }

    public String getLiftTestDateAndNumber() {
        return liftTestDateAndNumber;
    }

    public void setLiftTestDateAndNumber(String liftTestDateAndNumber) {
        this.liftTestDateAndNumber = liftTestDateAndNumber;
    }

}
