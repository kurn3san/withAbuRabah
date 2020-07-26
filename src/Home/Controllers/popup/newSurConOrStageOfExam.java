package Home.Controllers.popup;

import Home.DatabaseHandling.DatabaseHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class newSurConOrStageOfExam {

    public TextField newSurfConditinoTXT;
    public TextField newStageOfExaminationTXT;
    public CheckBox NewSurfaceConCheck;
    public CheckBox NewStageOfExaminationCheck;
    public Button saveButton;
    public Button cancelButton;

    public void save() {
        if (NewStageOfExaminationCheck.isSelected()) {

            if (newStageOfExaminationTXT.getText().equals("")) {
                System.out.println("NewStageOfExaminationCheck no input");
            } else {
                DatabaseHandler.addStageOfExamination(newStageOfExaminationTXT.getText().trim());
                System.out.println("here");
            }

        }
        if (NewSurfaceConCheck.isSelected()) {
            if (newSurfConditinoTXT.getText().equals("")) {
                System.out.println("newSurfConditinoTXT no input");
            } else {
                DatabaseHandler.addSurfaceCondition(newSurfConditinoTXT.getText().trim());
                System.out.println("hrere reorow[");
            }
        }
        cancel();
    }

    public void setNewSurfCondition() {
        newSurfConditinoTXT.setEditable(!newSurfConditinoTXT.isEditable());
    }

    public void setStageOfExam() {
        newStageOfExaminationTXT.setEditable(!newStageOfExaminationTXT.isEditable());


    }

    public void cancel() {
        NewStageOfExaminationCheck.setSelected(false);
        NewSurfaceConCheck.setSelected(false);
        newSurfConditinoTXT.clear();
        newStageOfExaminationTXT.clear();
        newStageOfExaminationTXT.getScene().getWindow().hide();
    }
}
