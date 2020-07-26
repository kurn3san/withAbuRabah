package Home.model;

import java.awt.*;

public class WeldedPiece {
    private String serialNo;
    private String weldPieceNo = "";
    private String testLength;
    private String weldingProcess = "";
    private String thickness;
    private String diameter;
    private String defectType = "";
    private String defectLoc = "";
    private String result = "RED";
    private Button update;


    public WeldedPiece(String serialNo, String weldPieceNo, String testLength, String weldingProcess, String thickness, String diameter, String result, Button update) {
        this.serialNo = serialNo;
        this.weldPieceNo = weldPieceNo;
        this.testLength = testLength;
        this.weldingProcess = weldingProcess;
        this.thickness = thickness;
        this.diameter = diameter;
        this.result = result;
        this.update = update;
    }

    public WeldedPiece(int serialNo, String weldPieceNo, String testLength,
                       String weldingProcess, String thickness,
                       String diameter, String defectType,
                       String defectLoc, String result, Button update) {
        this.weldPieceNo = weldPieceNo;
        this.testLength = testLength;
        this.weldingProcess = weldingProcess;
        this.thickness = thickness;
        this.result = result;
        this.diameter = diameter;
        this.defectType = defectType;
        this.defectLoc = defectLoc;
        this.update = update;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public WeldedPiece() {
    }

    @Override
    public String toString() {
        return "WeldedPiece: " +
                "Serial no: " + this.serialNo +
                " weldPieceNo= " + weldPieceNo +
                ", testLength= " + testLength +
                ", weldingProcess= " + weldingProcess +
                ", thickness= " + thickness +
                ", diameter= " + diameter +
                ", defectType= " + defectType +
                ", defectLoc=" + defectLoc +
                ", result= " + result + " .";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeldedPiece)) return false;
        WeldedPiece that = (WeldedPiece) o;
        return getSerialNo() == that.getSerialNo() &&
                getTestLength() == that.getTestLength() &&
                getThickness() == that.getThickness() &&
                getWeldPieceNo().equals(that.getWeldPieceNo()) &&
                getWeldingProcess().equals(that.getWeldingProcess()) &&
                getResult().equals(that.getResult());
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getWeldPieceNo() {
        return weldPieceNo;
    }

    public void setWeldPieceNo(String weldPieceNo) {
        this.weldPieceNo = weldPieceNo;
    }

    public String getTestLength() {
        return testLength;
    }

    public void setTestLength(String testLength) {
        this.testLength = testLength;
    }

    public String getWeldingProcess() {
        return weldingProcess;
    }

    public void setWeldingProcess(String weldingProcess) {
        this.weldingProcess = weldingProcess;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getDefectLoc() {
        return defectLoc;
    }

    public void setDefectLoc(String defectLoc) {
        this.defectLoc = defectLoc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
