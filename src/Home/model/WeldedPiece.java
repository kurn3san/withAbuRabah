package Home.model;

public class WeldedPiece {
    private int serialNo = 0;
    private String weldPieceNo = "";
    private int testLength = 0;
    private String weldingProcess = "";
    private int thickness = 0;
    private int diameter = 0;
    private String defectType = "";
    private String defectLoc = "";
    private String result = "RED";

    public WeldedPiece(String weldPieceNo, int testLength, String weldingProcess, int thickness, String result, int serialNo) {
        this.weldPieceNo = weldPieceNo;
        this.testLength = testLength;
        this.weldingProcess = weldingProcess;
        this.thickness = thickness;
        this.result = result;
    }

    public WeldedPiece(int serialNo, String weldPieceNo, int testLength,
                       String weldingProcess, int thickness,
                       int diameter, String defectType,
                       String defectLoc, String result) {
        this.weldPieceNo = weldPieceNo;
        this.testLength = testLength;
        this.weldingProcess = weldingProcess;
        this.thickness = thickness;
        this.result = result;
        this.diameter = diameter;
        this.defectType = defectType;
        this.defectLoc = defectLoc;
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
                getDiameter() == that.getDiameter() &&
                getWeldPieceNo().equals(that.getWeldPieceNo()) &&
                getWeldingProcess().equals(that.getWeldingProcess()) &&
                getDefectType().equals(that.getDefectType()) &&
                getDefectLoc().equals(that.getDefectLoc()) &&
                getResult().equals(that.getResult());
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getWeldPieceNo() {
        return weldPieceNo;
    }

    public void setWeldPieceNo(String weldPieceNo) {
        this.weldPieceNo = weldPieceNo;
    }

    public int getTestLength() {
        return testLength;
    }

    public void setTestLength(int testLength) {
        this.testLength = testLength;
    }

    public String getWeldingProcess() {
        return weldingProcess;
    }

    public void setWeldingProcess(String weldingProcess) {
        this.weldingProcess = weldingProcess;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
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
