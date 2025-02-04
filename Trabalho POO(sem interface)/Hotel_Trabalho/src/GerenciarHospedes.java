import java.io.*;
import java.util.ArrayList;

public class GerenciarHospedes {
    private ArrayList<Hospede> hospedes; // Lista de hóspedes
    String arquivo = "C:\\Users\\davim\\OneDrive\\Área de Trabalho\\AV3 POO\\Hospedes.txt";

 
    // Construtor
    public GerenciarHospedes() {
        this.hospedes = new ArrayList<>(); // Inicializa a lista de hóspedes
    }

    // adicionar um novo hóspede
    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
        System.out.println("Hóspede " + hospede.getNome() + " adicionado com sucesso!");
    }


    // salvar os hóspedes no arquivo (armazenar)
    public void salvarHospedes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Hospede hospede : hospedes) {
                writer.write(hospede.getNome() + "," + hospede.getIdade() + "," + hospede.getFone() + ","+ hospede.getCpf() +","+ hospede.getCheckIn() + ","+ hospede.getCheckOut()+","+hospede.getIdQuarto());
                writer.newLine();
            }
            System.out.println("Dados dos hóspedes salvos com sucesso no arquivo!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    //  carregar os hóspedes do arquivo para o programa atual
    public void carregarHospedes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            hospedes.clear(); // Limpa a lista atual antes de carregar
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                String idade = dados[1];
                String fone = dados[2];
                String cpf = dados[3];
                String checkIn = dados[4];
                String checkOut = dados[5];
                String idQuarto = dados[6];
                hospedes.add(new Hospede(nome, idade, fone, cpf,checkIn,checkOut,idQuarto));
            }
            System.out.println("Dados dos hóspedes carregados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }

    //  exibir os hóspedes no console
    public void listarHospedes() {
        if (hospedes.isEmpty()) {
            System.out.println("Nenhum hóspede cadastrado.");
        } else {
            System.out.println("Lista de Hóspedes:");
            for (Hospede hospede : hospedes) {
                System.out.println(hospede.nome + " // " + hospede.getIdade() + " // " + hospede.getFone() + " // "+ hospede.getCpf() +" // "+ hospede.getCheckIn() + " // "+ hospede.getCheckOut()+" // "+hospede.getIdQuarto());
            }
        }
    }
}