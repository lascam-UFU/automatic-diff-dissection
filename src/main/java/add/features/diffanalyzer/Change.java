package add.features.diffanalyzer;

/**
 * Created by tdurieux
 */
public class Change {

    private String type;
    private String file;
    private int line;
    private int endLine;
    private int length;
    private Change associateChange;

    public Change(String type, String file, int line, int endLine, int length) {
        this.type = type;
        this.file = file;
        this.line = line;
        this.endLine = endLine;
        this.length = length;
    }

    public int getLine() {
        return line;
    }

    public String getFile() {
        return file;
    }

    public String getClassName() {
        String file = this.getFile();
        int indexStartPackage = file.lastIndexOf("/org");
        if (indexStartPackage == -1) {
            indexStartPackage = file.lastIndexOf("/com");
        }
        file = file.substring(indexStartPackage + 1);
        file = file.substring(0, file.lastIndexOf(".")).replace("/", ".");
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

    public String getType() {
        return type;
    }

    public void setAssociateChange(Change associateChange) {
        this.associateChange = associateChange;
    }

    public Change getAssociateChange() {
        return associateChange;
    }

    @Override
    public String toString() {
        return "Change{" +
                "type='" + type + '\'' +
                ", line=" + line +
                ", endLine=" + endLine +
                ", length=" + length +
                '}';
    }

}
