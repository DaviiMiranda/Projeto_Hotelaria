import java.util.ArrayList;

public class Hospede extends Pessoa{

    private String checkIn;
    private String checkOut;
    private String idQuarto;




    public Hospede(String nome,String idade,String cpf,String fone,String checkIn,String checkOut,String idQuarto) {

        super(nome, idade, cpf, fone);
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.idQuarto = idQuarto;


    }
    public String getNome() {

        return nome;
    }


    public String getIdade() {

        return idade;
    }

    public String getCpf(){

        return cpf;
    }
    public String getFone(){

        return fone;
    }
    public String getIdQuarto() {

        return idQuarto;
    }

    public String getCheckIn() {

        return checkIn;
    }

    public String getCheckOut() {

        return checkOut;
    }
}
