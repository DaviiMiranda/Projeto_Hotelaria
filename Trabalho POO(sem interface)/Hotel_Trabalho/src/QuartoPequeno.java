public class QuartoPequeno extends Quarto {

    public QuartoPequeno(String idQuarto, String vago){
        this.idQuarto = idQuarto;
        this.vago = vago;


    }
    public String getTipo(){
        return "QuartoPequeno";
    }

    public String toString(){
        return idQuarto +"e"+ vago;
    }


}
