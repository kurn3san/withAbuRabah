package Home.model;

public class Personel {
    private Employee operator;
    private Employee evaluator;
    private Employee confirmationMaker;

    public Personel(Employee operator, Employee evaluator, Employee confirmationMaker) {
        this.operator = operator;
        this.evaluator = evaluator;
        this.confirmationMaker = confirmationMaker;
    }

    public Personel() {
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public Employee getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Employee evaluator) {
        this.evaluator = evaluator;
    }

    public Employee getConfirmationMaker() {
        return confirmationMaker;
    }

    public void setConfirmationMaker(Employee confirmationMaker) {
        this.confirmationMaker = confirmationMaker;
    }
}
