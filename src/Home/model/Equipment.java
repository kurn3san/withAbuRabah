package Home.model;

public class Equipment {

    private int equipmentid = 0;
    private String equipmentName = "";
    private int poleDistance = 0;
    private String mpCarrierMedium = "";
    private String uVLightDensity = "";
    private int distanceOfLight = 0;
    private String magTech = "";


    public Equipment(int equipmentid, String equipmentName,
                     int poleDistance, String mpCarrierMedium,
                     String uVLightDensity, int distanceOfLight,
                     String magTech, boolean testType) {
        this.equipmentName = equipmentName;
        this.poleDistance = poleDistance;
        this.mpCarrierMedium = mpCarrierMedium;
        this.uVLightDensity = uVLightDensity;
        this.distanceOfLight = distanceOfLight;
        this.magTech = magTech;
        this.equipmentid = equipmentid;

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

    public int getId() {
        return equipmentid;
    }

    public void setId(int equipmentid) {
        this.equipmentid = equipmentid;
    }

    public int getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(int equipmentid) {
        this.equipmentid = equipmentid;
    }

    @Override
    public String toString() {
        return "Equipment: " +
                " equipmentid= " + equipmentid +
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
        return getId() == equipment.getId();
    }

}
