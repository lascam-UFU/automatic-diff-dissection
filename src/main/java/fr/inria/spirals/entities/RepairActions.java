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
	private int nbChange;
	private int nbThrow;

	public int getNbVariable() {
		return nbVariable;
	}

	public void incNbVariable() {
		nbVariable++;
	}

	public void setNbVariable(int nbVariable) {
		this.nbVariable = nbVariable;
	}

	public int getNbInvocation() {
		return nbInvocation;
	}

	public void incNbInvocation() {
		nbInvocation++;
	}

	public void setNbInvocation(int nbInvocation) {
		this.nbInvocation = nbInvocation;
	}

	public int getNbIf() {
		return nbIf;
	}

	public void incNbIf() {
		nbIf++;
	}

	public void setNbIf(int nbIf) {
		this.nbIf = nbIf;
	}

	public int getNbLoop() {
		return nbLoop;
	}

	public void setNbLoop(int nbLoop) {
		this.nbLoop = nbLoop;
	}

	public int getNbType() {
		return nbType;
	}

	public void incNbType() {
		nbType++;
	}

	public void setNbType(int nbType) {
		this.nbType = nbType;
	}

	public int getNbComment() {
		return nbComment;
	}

	public void setNbComment(int nbComment) {
		this.nbComment = nbComment;
	}

	public int getNbAssignment() {
		return nbAssignment;
	}

	public void setNbAssignment(int nbAssignment) {
		this.nbAssignment = nbAssignment;
	}

	public int getNbBinary() {
		return nbBinary;
	}

	public void setNbBinary(int nbBinary) {
		this.nbBinary = nbBinary;
	}

	public int getNbUnary() {
		return nbUnary;
	}

	public void setNbUnary(int nbUnary) {
		this.nbUnary = nbUnary;
	}

	public int getNbConstructorCall() {
		return nbConstructorCall;
	}

	public void setNbConstructorCall(int nbConstructorCall) {
		this.nbConstructorCall = nbConstructorCall;
	}

	public int getNbTry() {
		return nbTry;
	}

	public void setNbTry(int nbTry) {
		this.nbTry = nbTry;
	}

	public int getNbExternalCall() {
		return nbExternalCall;
	}

	public void setNbExternalCall(int nbExternalCall) {
		this.nbExternalCall = nbExternalCall;
	}

	public int getNbReturn() {
		return nbReturn;
	}

	public void setNbReturn(int nbReturn) {
		this.nbReturn = nbReturn;
	}

	public int getNbContinue() {
		return nbContinue;
	}

	public void setNbContinue(int nbContinue) {
		this.nbContinue = nbContinue;
	}

	public int getNbBreak() {
		return nbBreak;
	}

	public void setNbBreak(int nbBreak) {
		this.nbBreak = nbBreak;
	}

	public void incNbLoop() {
		nbLoop++;
	}

	public void incNbBreak() {
		nbBreak++;
	}

	public void incNbReturn() {
		nbReturn++;
	}

	public void incNbContinue() {
		nbContinue++;
	}

	public void incNbComment() {
		nbComment++;
	}

	public void incNbAssignment() {
		nbAssignment++;
	}

	public int getNbLiteral() {
		return nbLiteral;
	}

	public void incNbLiteral() {
		this.nbLiteral++;
	}

	public void setNbLiteral(int nbLiteral) {
		this.nbLiteral = nbLiteral;
	}

	public void incNbConstructorCall() {
		nbConstructorCall++;
	}

	public void incNbTry() {
		nbTry++;
	}

	public void incNbBinary() {
		nbBinary++;
	}

	public void incNbUnary() {
		nbUnary++;
	}

	public void incNbExternalCall() {
		nbExternalCall++;
	}

	public int getNbVariableAccess() {
		return nbVariableAccess;
	}

	public void incNbVariableAccess() {
		nbVariableAccess++;
	}

	public void setNbVariableAccess(int nbVariableAccess) {
		this.nbVariableAccess = nbVariableAccess;
	}

	public void incNbThrow() {
		this.nbThrow ++;
	}

	public int getNbThrow() {
		return nbThrow;
	}

	public void setNbThrow(int nbThrow) {
		this.nbThrow = nbThrow;
	}

	public void incNbChange(int length) {
		this.nbChange += length;
	}

	public int getNbChange() {
		return nbChange;
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
