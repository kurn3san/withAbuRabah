package Home.model;

public class Equipment {
    private String equipmentName;
    private int poleDistance;
    private String mpCarrierMedium;
    private String uVLightDensity;
    private int distanceOfLight;
    private String magTech;

    public Equipment(String equipmentName, int poleDistance, String mpCarrierMedium, String uVLightDensity, int distanceOfLight, String magTech) {
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
}
