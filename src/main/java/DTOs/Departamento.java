package DTOs;

public class Departamento {

    public int idDepto;
    public String nomDepto;

    public Departamento(int idDepto, String nomDepto) {
        this.idDepto = idDepto;
        this.nomDepto = nomDepto;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }
}
