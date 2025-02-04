import java.io.*;
import java.util.ArrayList;

public class GerenciarQuartos extends Quarto implements CheckIn {

    private ArrayList<Quarto> quartos;
    String arquivo = "C:\\Users\\davim\\OneDrive\\Área de Trabalho\\AV3 POO\\Quartos.txt";

    // Construtor
    public GerenciarQuartos() {
        this.quartos = new ArrayList<>();
    }


    public void listarQuartos(){
        for(Quarto quarto : quartos){
            System.out.println("ID: " + quarto.getIdQuarto() +   " // Tipo: " + quarto.getClass() + " // Vago: " + quarto.getVago());


        }
    }

    // salvar os quartos no arquivo
    public void salvarQuartos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Quarto quarto : quartos) {
                writer.write(quarto.getIdQuarto() + ","  + quarto.getVago() + "," + quarto.getClass().getSimpleName());
                writer.newLine();
            }
            System.out.println("Quartos salvos com sucesso no arquivo!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os quartos: " + e.getMessage());
        }
    }

    // carregar os quartos do arquivo
    public void carregarQuartos() {
        quartos.clear(); // Limpa a lista atual para evitar duplicação
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String idQuarto = dados[0];
                String vago = dados[1];
                String tipoQuarto = dados[2];


                Quarto quarto;
                if (tipoQuarto.equals("QuartoPequeno")) {
                    quarto = new QuartoPequeno(idQuarto,vago);
                } else if (tipoQuarto.equals("QuartoPlus")) {
                    quarto = new QuartoPlus(idQuarto,vago);
                } else if (tipoQuarto.equals("SuitePresidencial")) {
                    quarto = new SuitePresidencial(idQuarto,vago);
                } else {
                    continue; // Ignora tipos desconhecidos
                }

                quarto.setIdQuarto(idQuarto);
                quarto.setVago(vago);
                quartos.add(quarto);
            }
            System.out.println("Quartos carregados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os quartos: " + e.getMessage());
        }
    }


    @Override
    public void ocupar(String tipo1) {
        ArrayList<String> linhasAtualizadas = new ArrayList<>(); // Para armazenar as linhas atualizadas
        boolean quartoEncontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            // Ler todas as linhas do arquivo
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String numero = dados[0];
                String disponivel = dados[1];
                String tipo2 = dados[2];
                // Verificar se o tipo corresponde e está disponível
                if (tipo2.equalsIgnoreCase(tipo1) && disponivel.equalsIgnoreCase("true") && !quartoEncontrado) {
                    linhasAtualizadas.add(numero + ",false," + tipo2); // Atualiza a disponibilidade para "false"
                    System.out.println("O quarto ID: " + numero + " do tipo " + tipo2 + " foi ocupado com sucesso!");
                    quartoEncontrado = true; // Evita alterar outros quartos do mesmo tipo
                } else {
                    linhasAtualizadas.add(linha); // Mantém a linha original
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Sobrescrever o arquivo com os dados atualizados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linhaAtualizada : linhasAtualizadas) {
                writer.write(linhaAtualizada);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }


    @Override
    public String verificarDisponibilidade(String tipoQuarto) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;


            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String numero = dados[0];
                String disponivel = dados[1];
                String tipo = dados[2];


                if (tipo.equalsIgnoreCase(tipoQuarto) && disponivel.equalsIgnoreCase("true")) {


                    return "true,"+numero;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return "false";

}
}