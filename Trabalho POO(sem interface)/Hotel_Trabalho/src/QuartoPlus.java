public class QuartoPlus extends Quarto  {

    public QuartoPlus(String idQuarto, String vago){
        this.idQuarto = idQuarto;
        this.vago = vago;

    }
    public String getTipo(){
        return "QuartoPlus";
    }

    public String toString(){
        return "ID"+idQuarto+"Vago:"+vago;
    }

}
