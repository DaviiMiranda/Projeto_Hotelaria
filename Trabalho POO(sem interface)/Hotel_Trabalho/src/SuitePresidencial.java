public class SuitePresidencial extends Quarto {

    public SuitePresidencial(String idQuarto, String vago){
        this.idQuarto = idQuarto;
        this.vago = vago;
    }
    public String getTipo(){
        return "SuitePresidencial";
    }

    public String toString(){
        return "ID:"+idQuarto+" Vago: "+vago;
    }
}
