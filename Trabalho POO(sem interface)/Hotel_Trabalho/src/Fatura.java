public class Fatura implements Desconto{

    //Quarto pequeno 200$, Quarto Plus 350$, SP 500$
    public float calcularCusto(String quarto,long diarias){
        float valor = 0;
        if(quarto.equals("quartoPequeno")){
            valor = 200 * diarias;
        }
        else if(quarto.equals("quartoPlus")){
            valor = 350 * diarias;
        }
        else if (quarto.equals( "SuitePresidencial")){
            valor = 500 * diarias;
        }

        return valor;

    };

    @Override
    public float des(String pagamento, float valor) {
        try {
            if (pagamento == "crédito") {
                valor += 0;
            } else if (pagamento == "debito") {
                valor *= 0.95f;
            } else if (pagamento == "pix") {
                valor *= 0.90f;
            }
        } catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());

        }

        return valor;

    }

    //Calcula o custo da estadia

}
