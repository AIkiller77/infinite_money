package net.lingala.zip4j.model;

public class UnzipParameters {
    private boolean ignoreAllFileAttributes;
    private boolean ignoreArchiveFileAttribute;
    private boolean ignoreDateTimeAttributes;
    private boolean ignoreHiddenFileAttribute;
    private boolean ignoreReadOnlyFileAttribute;
    private boolean ignoreSystemFileAttribute;

    public UnzipParameters() {
    }

    public boolean isIgnoreReadOnlyFileAttribute() {
        return this.ignoreReadOnlyFileAttribute;
    }

    public void setIgnoreReadOnlyFileAttribute(boolean z) {
        boolean z2 = z;
        this.ignoreReadOnlyFileAttribute = z2;
    }

    public boolean isIgnoreHiddenFileAttribute() {
        return this.ignoreHiddenFileAttribute;
    }

    public void setIgnoreHiddenFileAttribute(boolean z) {
        boolean z2 = z;
        this.ignoreHiddenFileAttribute = z2;
    }

    public boolean isIgnoreArchiveFileAttribute() {
        return this.ignoreArchiveFileAttribute;
    }

    public void setIgnoreArchiveFileAttribute(boolean z) {
        boolean z2 = z;
        this.ignoreArchiveFileAttribute = z2;
    }

    public boolean isIgnoreSystemFileAttribute() {
        return this.ignoreSystemFileAttribute;
    }

    public void setIgnoreSystemFileAttribute(boolean z) {
        boolean z2 = z;
        this.ignoreSystemFileAttribute = z2;
    }

    public boolean isIgnoreAllFileAttributes() {
        return this.ignoreAllFileAttributes;
    }

    public void setIgnoreAllFileAttributes(boolean z) {
        boolean z2 = z;
        this.ignoreAllFileAttributes = z2;
    }

    public boolean isIgnoreDateTimeAttributes() {
        return this.ignoreDateTimeAttributes;
    }

    public void setIgnoreDateTimeAttributes(boolean z) {
        boolean z2 = z;
        this.ignoreDateTimeAttributes = z2;
    }
}
