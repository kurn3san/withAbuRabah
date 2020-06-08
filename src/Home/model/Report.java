package Home.model;

import java.time.LocalDate;
import java.util.List;

public class Report {
    ///company informations
    ///first section, first column on the left...
    private Company company;
    private String place = this.company.getAddress();
    private String projectName;
    private String inspectionStandard;
    private String evaluationStandard;
    ///first section, second comlumn
    private int drawingNO = 0;
    private String surfaceCondition;
    private int inspectionScope = 100;
    private String stageOfExamination;
    private String inspectionProcedure;
    ////last column of first section
    private int pageNumber = 1;
    private int reportNo;
    private int jobOrderNo;
    private int jobOfferNo;
    private LocalDate reportDate;
    //////
    private Equipment equipment;
    private int poleDistance = this.equipment.getPoleDistance();
    private String equipmentName = this.equipment.getEquipmentName();
    private String mpCarrierMedium = this.equipment.getMpCarrierMedium();
    private String magTech = this.equipment.getMagTech();
    private int distanceOfLight = this.equipment.getDistanceOfLight();
    private String examinationArea = "KAYNAK+HAZ";
    private String currentType = "AC";
    private int luxmeter = 1200; ///must have the word Lux in the view...
    private String testMedium = "";
    private String demagnatization = "";
    private String heatTreatment = "";
    private int surfaceTemprature = 15;
    private double gaussFieldStrength = 3.2;
    private String equipmentSurfaceCondition = "TAŞLANMIŞ / GRINDING";
    private String identificationOfLightEquipment = "***";
    private String LiftingTestDateAndNumber;
    /////
    private String standardDeviations = "Standarttan sapma yoktur.";
    private LocalDate inspectionDate = this.reportDate;
    private String descriptionAndAttachments = "";
    ////// inspection results...
    private List<WeldedPiece> weldedPieceList;
    private Personel personel;

    public Report(Company company, String projectName, String inspectionStandard, String evaluationStandard, String surfaceCondition, String stageOfExamination, String inspectionProcedure, int reportNo, int jobOrderNo, int jobOfferNo, LocalDate reportDate, Equipment equipment, int distanceOfLight, String liftingTestDateAndNumber, LocalDate inspectionDate, List<WeldedPiece> weldedPieceList) {
        this.company = company;
        this.projectName = projectName;
        this.inspectionStandard = inspectionStandard;
        this.evaluationStandard = evaluationStandard;
        this.surfaceCondition = surfaceCondition;
        this.stageOfExamination = stageOfExamination;
        this.inspectionProcedure = inspectionProcedure;
        this.reportNo = reportNo;
        this.jobOrderNo = jobOrderNo;
        this.jobOfferNo = jobOfferNo;
        this.reportDate = reportDate;
        this.equipment = equipment;
        this.distanceOfLight = distanceOfLight;
        LiftingTestDateAndNumber = liftingTestDateAndNumber;
        this.inspectionDate = inspectionDate;
        this.weldedPieceList = weldedPieceList;
    }

    public Report() {
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInspectionStandard() {
        return inspectionStandard;
    }

    public void setInspectionStandard(String inspectionStandard) {
        this.inspectionStandard = inspectionStandard;
    }

    public String getEvaluationStandard() {
        return evaluationStandard;
    }

    public void setEvaluationStandard(String evaluationStandard) {
        this.evaluationStandard = evaluationStandard;
    }

    public int getDrawingNO() {
        return drawingNO;
    }

    public void setDrawingNO(int drawingNO) {
        this.drawingNO = drawingNO;
    }

    public String getSurfaceCondition() {
        return surfaceCondition;
    }

    public void setSurfaceCondition(String surfaceCondition) {
        this.surfaceCondition = surfaceCondition;
    }

    public int getInspectionScope() {
        return inspectionScope;
    }

    public void setInspectionScope(int inspectionScope) {
        this.inspectionScope = inspectionScope;
    }

    public String getStageOfExamination() {
        return stageOfExamination;
    }

    public void setStageOfExamination(String stageOfExamination) {
        this.stageOfExamination = stageOfExamination;
    }

    public String getInspectionProcedure() {
        return inspectionProcedure;
    }

    public void setInspectionProcedure(String inspectionProcedure) {
        this.inspectionProcedure = inspectionProcedure;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public int getJobOrderNo() {
        return jobOrderNo;
    }

    public void setJobOrderNo(int jobOrderNo) {
        this.jobOrderNo = jobOrderNo;
    }

    public int getJobOfferNo() {
        return jobOfferNo;
    }

    public void setJobOfferNo(int jobOfferNo) {
        this.jobOfferNo = jobOfferNo;
    }

    public LocalDate getReportDate() {
        return this.reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getPoleDistance() {
        return poleDistance;
    }

    public void setPoleDistance(int poleDistance) {
        this.poleDistance = poleDistance;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getMpCarrierMedium() {
        return mpCarrierMedium;
    }

    public void setMpCarrierMedium(String mpCarrierMedium) {
        this.mpCarrierMedium = mpCarrierMedium;
    }

    public String getMagTech() {
        return magTech;
    }

    public void setMagTech(String magTech) {
        this.magTech = magTech;
    }

    public int getDistanceOfLight() {
        return distanceOfLight;
    }

    public void setDistanceOfLight(int distanceOfLight) {
        this.distanceOfLight = distanceOfLight;
    }

    public String getExaminationArea() {
        return examinationArea;
    }

    public void setExaminationArea(String examinationArea) {
        this.examinationArea = examinationArea;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public int getLuxmeter() {
        return luxmeter;
    }

    public void setLuxmeter(int luxmeter) {
        this.luxmeter = luxmeter;
    }

    public String getTestMedium() {
        return testMedium;
    }

    public void setTestMedium(String testMedium) {
        this.testMedium = testMedium;
    }

    public String getDemagnatization() {
        return demagnatization;
    }

    public void setDemagnatization(String demagnatization) {
        this.demagnatization = demagnatization;
    }

    public String getHeatTreatment() {
        return heatTreatment;
    }

    public void setHeatTreatment(String heatTreatment) {
        this.heatTreatment = heatTreatment;
    }

    public int getSurfaceTemprature() {
        return surfaceTemprature;
    }

    public void setSurfaceTemprature(int surfaceTemprature) {
        this.surfaceTemprature = surfaceTemprature;
    }

    public double getGaussFieldStrength() {
        return gaussFieldStrength;
    }

    public void setGaussFieldStrength(double gaussFieldStrength) {
        this.gaussFieldStrength = gaussFieldStrength;
    }

    public String getEquipmentSurfaceCondition() {
        return equipmentSurfaceCondition;
    }

    public void setEquipmentSurfaceCondition(String equipmentSurfaceCondition) {
        this.equipmentSurfaceCondition = equipmentSurfaceCondition;
    }

    public String getIdentificationOfLightEquipment() {
        return identificationOfLightEquipment;
    }

    public void setIdentificationOfLightEquipment(String identificationOfLightEquipment) {
        this.identificationOfLightEquipment = identificationOfLightEquipment;
    }

    public String getLiftingTestDateAndNumber() {
        return LiftingTestDateAndNumber;
    }

    public void setLiftingTestDateAndNumber(String liftingTestDateAndNumber) {
        LiftingTestDateAndNumber = liftingTestDateAndNumber;
    }

    public String getStandardDeviations() {
        return standardDeviations;
    }

    public void setStandardDeviations(String standardDeviations) {
        this.standardDeviations = standardDeviations;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getDescriptionAndAttachments() {
        return descriptionAndAttachments;
    }

    public void setDescriptionAndAttachments(String descriptionAndAttachments) {
        this.descriptionAndAttachments = descriptionAndAttachments;
    }

    public List<WeldedPiece> getWeldedPieceList() {
        return weldedPieceList;
    }

    public void setWeldedPieceList(List<WeldedPiece> weldedPieceList) {
        this.weldedPieceList = weldedPieceList;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
}
