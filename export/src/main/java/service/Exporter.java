package service;

public class Exporter {
    private Exportformat exportformat;

    public Exporter(Exportformat exportformat) {
        this.exportformat = exportformat;
    }

    public void setExportformat(Exportformat exportformat) {
        this.exportformat = exportformat;
    }

    public Exportformat getExportformat() {
        return exportformat;
    }
}
