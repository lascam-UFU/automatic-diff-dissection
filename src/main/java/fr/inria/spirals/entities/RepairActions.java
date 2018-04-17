package fr.inria.spirals.entities;

import fr.inria.spirals.main.Constants;

/**
 * Created by tdurieux
 */
public class RepairActions {
    private int nbVariable;
    private int nbVariableAccess;
    private int nbInvocation;
    private int nbIf;
    private int nbLoop;
    private int nbType;
    private int nbComment;
    private int nbAssignment;
    private int nbBinary;
    private int nbUnary;
    private int nbConstructorCall;
    private int nbTry;
    private int nbExternalCall;
    private int nbReturn;
    private int nbContinue;
    private int nbBreak;
    private int nbLiteral;
    private int nbThrow;
    private int nbChange;

    public int getNbVariable() {
        return nbVariable;
    }

    public void incNbVariable() {
        nbVariable++;
    }

    public int getNbVariableAccess() {
        return nbVariableAccess;
    }

    public void incNbVariableAccess() {
        nbVariableAccess++;
    }

    public int getNbInvocation() {
        return nbInvocation;
    }

    public void incNbInvocation() {
        nbInvocation++;
    }

    public int getNbIf() {
        return nbIf;
    }

    public void incNbIf() {
        nbIf++;
    }

    public int getNbLoop() {
        return nbLoop;
    }

    public void incNbLoop() {
        nbLoop++;
    }

    public int getNbType() {
        return nbType;
    }

    public void incNbType() {
        nbType++;
    }

    public int getNbComment() {
        return nbComment;
    }

    public void incNbComment() {
        nbComment++;
    }

    public int getNbAssignment() {
        return nbAssignment;
    }

    public void incNbAssignment() {
        nbAssignment++;
    }

    public int getNbBinary() {
        return nbBinary;
    }

    public void incNbBinary() {
        nbBinary++;
    }

    public int getNbUnary() {
        return nbUnary;
    }

    public void incNbUnary() {
        nbUnary++;
    }

    public int getNbConstructorCall() {
        return nbConstructorCall;
    }

    public void incNbConstructorCall() {
        nbConstructorCall++;
    }

    public int getNbTry() {
        return nbTry;
    }

    public void incNbTry() {
        nbTry++;
    }

    public int getNbExternalCall() {
        return nbExternalCall;
    }

    public void incNbExternalCall() {
        nbExternalCall++;
    }

    public int getNbReturn() {
        return nbReturn;
    }

    public void incNbReturn() {
        nbReturn++;
    }

    public int getNbContinue() {
        return nbContinue;
    }

    public void incNbContinue() {
        nbContinue++;
    }

    public int getNbBreak() {
        return nbBreak;
    }

    public void incNbBreak() {
        nbBreak++;
    }

    public int getNbLiteral() {
        return nbLiteral;
    }

    public void incNbLiteral() {
        this.nbLiteral++;
    }

    public int getNbThrow() {
        return nbThrow;
    }

    public void incNbThrow() {
        this.nbThrow ++;
    }

    public int getNbChange() {
        return nbChange;
    }

    public void incNbChange(int length) {
        this.nbChange += length;
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNbVariable()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbVariableAccess()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbInvocation()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbExternalCall()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbIf()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbLoop()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbType()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbComment()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbAssignment()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbBinary()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbUnary()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbConstructorCall()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbTry()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbLiteral()).append(Constants.CSV_SEPARATOR);

        sb.append(getNbThrow()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbReturn()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbBreak()).append(Constants.CSV_SEPARATOR);
        sb.append(getNbContinue()).append(Constants.CSV_SEPARATOR);

        return sb.toString();
    }

}
