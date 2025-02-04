public abstract class Pessoa {
    protected String nome;
    protected String idade;
    protected String cpf;
    protected String fone;

    public Pessoa(String nome, String idade, String cpf,String fone) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.fone = fone;
    }


    //toString
    @Override
    public String toString() {
        return nome + " - Preço: " + idade + " - Quantidade: " + cpf + "Telefone: "+ fone;
    }



}
