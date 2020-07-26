package Home.model;

import java.util.Objects;

public class EquipInfoSectionOfReport extends Equipment {
    private int selectedEquipSecid;
    private int luxumeter = 1200;
    private int temp = 0;

    private String examinArea = "";
    private String currentType = "AC";

    public EquipInfoSectionOfReport(String equipmentName, int poleDistance, String mpCarrierMedium,
                                    double uVLightIntensity, int distanceOfLight, String magTech,
                                    String examinArea, String currentType,
                                    int luxumeter, String testMedium, String demag,
                                    String heatTreat, int temp, double gaussFieldStrnth,
                                    String surfaceCon, String idOfLightEquip, String liftTestDateAndNumber) {
        super(equipmentName, poleDistance, mpCarrierMedium, uVLightIntensity, distanceOfLight, magTech);
        this.examinArea = examinArea;
        this.currentType = currentType;
        this.luxumeter = luxumeter;
        this.testMedium = testMedium;
        this.demag = demag;
        this.heatTreat = heatTreat;
        this.temp = temp;
        this.gaussFieldStrnth = gaussFieldStrnth;
        this.surfaceCon = surfaceCon;
        this.idOfLightEquip = idOfLightEquip;
        this.liftTestDateAndNumber = liftTestDateAndNumber;
    }

    private String testMedium = "";
    private String demag = "";
    private String heatTreat = "";

    public EquipInfoSectionOfReport(String examinArea, String currentType, int luxumeter,
                                    String testMedium, String demag, String heatTreat,
                                    int temp, double gaussFieldStrnth, String surfaceCon,
                                    String idOfLightEquip, String liftTestDateAndNumber) {
        this.examinArea = examinArea;
        this.currentType = currentType;
        this.luxumeter = luxumeter;
        this.testMedium = testMedium;
        this.demag = demag;
        this.heatTreat = heatTreat;
        this.temp = temp;
        this.gaussFieldStrnth = gaussFieldStrnth;
        this.surfaceCon = surfaceCon;
        this.idOfLightEquip = idOfLightEquip;
        this.liftTestDateAndNumber = liftTestDateAndNumber;
    }

    private double gaussFieldStrnth = 0;//     kA/m
    private String surfaceCon = "";
    private String idOfLightEquip = "";
    private String liftTestDateAndNumber = "";

    public EquipInfoSectionOfReport() {
    }

    public int getSelectedEquipSecid() {
        return selectedEquipSecid;
    }

    public void setSelectedEquipSecid(int selectedEquipSecid) {
        this.selectedEquipSecid = selectedEquipSecid;
    }

    public EquipInfoSectionOfReport(Equipment equipment) {
        super(equipment.getEquipmentName(), equipment.getPoleDistance(),
                equipment.getMpCarrierMedium(), equipment.getDoubleuVLightIntensity(),
                equipment.getDistanceOfLight(), equipment.getMagTech());
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
        return luxumeter;
    }

    public void setLuxumeter(int luxumeter) {
        this.luxumeter = luxumeter;
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

    public double getGaussFieldStrnth() {
        return gaussFieldStrnth;
    }

    public void setGaussFieldStrnth(double gaussFieldStrnth) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipInfoSectionOfReport)) return false;
        if (!super.equals(o)) return false;
        EquipInfoSectionOfReport that = (EquipInfoSectionOfReport) o;
        return getLuxumeter() == that.getLuxumeter() &&
                Double.compare(that.getTemp(), getTemp()) == 0 &&
                Double.compare(that.getGaussFieldStrnth(), getGaussFieldStrnth()) == 0 &&
                getExaminArea().equals(that.getExaminArea()) &&
                getCurrentType().equals(that.getCurrentType()) &&
                Objects.equals(getTestMedium(), that.getTestMedium()) &&
                Objects.equals(getDemag(), that.getDemag()) &&
                Objects.equals(getHeatTreat(), that.getHeatTreat()) &&
                getSurfaceCon().equals(that.getSurfaceCon()) &&
                getIdOfLightEquip().equals(that.getIdOfLightEquip()) &&
                getLiftTestDateAndNumber().equals(that.getLiftTestDateAndNumber());
    }

    @Override
    public String toString() {
        return "EquipInfoSectionOfReport{" +
                "examinArea='" + examinArea + '\'' +
                ", currentType='" + currentType + '\'' +
                ", Luxumeter=" + luxumeter +
                ", gaussFieldStrnth=" + gaussFieldStrnth +
                ", surfaceCon='" + surfaceCon + '\'' +
                ", idOfLightEquip='" + idOfLightEquip + '\'' +
                ", liftTestDateAndNumber='" + liftTestDateAndNumber + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getExaminArea(),
                getCurrentType(), getLuxumeter(), getTestMedium(),
                getDemag(), getHeatTreat(), getTemp(), getGaussFieldStrnth(),
                getSurfaceCon(), getIdOfLightEquip(), getLiftTestDateAndNumber());
    }
}
