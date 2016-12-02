package fr.inria.spirals.features;

public class Change {

	private String type;
	private String file;
	private int endLine;
	private int length;

	public Change(String type, String file, int line, int endLine, int length) {
		this.type = type;
		this.file = file;
		this.line = line;
		this.endLine = endLine;
		this.length = length;
	}

	private int line;

	public int getLine() {
		return line;
	}

	public String getFile() {
		return file;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public int getLength() {
		return length;
	}
}