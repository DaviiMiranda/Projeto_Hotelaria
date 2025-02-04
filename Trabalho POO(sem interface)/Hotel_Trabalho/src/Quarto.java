import java.util.ArrayList;

public abstract class Quarto {
    protected String idQuarto;
    protected String vago;


    @Override
    public String toString() {
        return super.toString() + " ID: " + idQuarto ;
    }



    public String getIdQuarto() {
        return idQuarto;
    }

    public String getVago() {
        return vago;
    }

    public void setVago(String vago) {
        this.vago = vago;
    }

    public void setIdQuarto(String idQuarto) {
        this.idQuarto = idQuarto;
    }
    public String getTipo(){
        return "";
    };



}
