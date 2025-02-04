import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean loginValido = false;
        int cont = 0;

        //ex
        GerenciarHospedes ger = new GerenciarHospedes();
        GerenciarQuartos gerQ = new GerenciarQuartos();


        gerQ.carregarQuartos();
        ger.carregarHospedes();






        while(true) {
            System.out.println("Bem-Vindo ao Hotel Overlook");
            System.out.println("É funcionário? [S] [N]");
            String res3 = scan.nextLine();
            if(res3.equalsIgnoreCase("S")) {
                do {
                    // Dados de exemplo para login válido
                    String nomeUsuarioCorreto = "admin";
                    String senhaCorreta = "1234";


                    try {
                        System.out.println("===========Login de Funcionario===========");

                        // Entrada de Nome
                        System.out.print("Digite seu nome de usuário: ");
                        String nomeUsuario = scan.nextLine();
                        if (nomeUsuario.isBlank()) {
                            throw new IllegalArgumentException("O campo nome de usuário não pode estar vazio.");
                        }

                        // Entrada de Senha
                        System.out.print("Digite sua senha: ");
                        String senha = scan.nextLine();
                        if (senha.isBlank()) {
                            throw new IllegalArgumentException("O campo senha não pode estar vazio.");
                        }

                        // Validação de Login
                        if (!nomeUsuario.equals(nomeUsuarioCorreto) || !senha.equals(senhaCorreta)) {
                            throw new SecurityException("Nome de usuário ou senha inválidos.");
                        }
                        if(nomeUsuario.equals(nomeUsuarioCorreto) && senha.equals(senhaCorreta)){
                            loginValido = true;
                        }

                        System.out.println("Login realizado com sucesso!");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro de entrada: " + e.getMessage());
                    } catch (SecurityException e) {
                        System.out.println("Erro de segurança: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }

                } while(!loginValido);

                gerQ.carregarQuartos();

                System.out.println("-------------Tela de gerenciamento de hóspedes---------------");
                System.out.println("NOME = IDADE = CPF = TELEFONE = CHECKIN = CHECKOUT = IDQUARTO");
                ger.listarHospedes();

                System.out.println("----------Quartos--------------");
                System.out.println("ID = TIPO = DISPONIBILIDADE");
                gerQ.listarQuartos();




                System.out.println("Sair? [x]");
                String res6 = scan.nextLine();
                if(res6.equalsIgnoreCase("x")){
                    break;
                }
            }


            System.out.println("Quantos vão se hospedar?");
            int hos = scan.nextInt();
            scan.nextLine();



            String tipoQuarto = "";
            GerenciarQuartos quartos = new GerenciarQuartos();
            boolean dis = false;
            String id = "";

            do {
                System.out.println("Escolha o tipo de quarto:");
                System.out.println("1 - Quarto Pequeno");
                System.out.println("2 - Quarto Plus");
                System.out.println("3 - Suite Presidencial");
                System.out.println("0 - Cancelar");

                int res6 = scan.nextInt(); // Atualiza res1 para cada iteração

                switch (res6) {
                    case 1:
                        String var1 = quartos.verificarDisponibilidade("QuartoPequeno");
                        String[] dados = var1.split(",");
                        String disponivel = dados[0];
                        id = dados[1];
                        if(disponivel.equalsIgnoreCase("true")){

                            System.out.println("QuartoPequeno Disponível!");
                            tipoQuarto = "quartoPequeno";
                            dis = true;
                        } else {
                            System.out.println("Quarto indisponível para esse tipo.");
                        }
                        break;

                    case 2:
                        String var2 = quartos.verificarDisponibilidade("QuartoPlus");
                        String[] dados2 = var2.split(","); // Divide a string em partes separadas por vírgula
                        String disponivel2 = dados2[0];       // "101"
                        id = dados2[1];   // "true"
                        if(disponivel2.equalsIgnoreCase("true")){

                            System.out.println("QuartoPlus Disponível!");
                            tipoQuarto = "QuartoPlus";
                            dis = true;
                        } else {
                            System.out.println("Quarto indisponível para esse tipo.");
                        }
                        break;

                    case 3:
                        String var3 = quartos.verificarDisponibilidade("SuitePresidencial");
                        String[] dados3 = var3.split(",");
                        String disponivel3 = dados3[0];
                        id = dados3[1];
                        if(disponivel3.equalsIgnoreCase("true")){

                            System.out.println("SuitePresidencial Disponível!");
                            tipoQuarto = "SuitePresidencial";
                            dis = true;
                        } else {
                            System.out.println("Quarto indisponível para esse tipo.");
                        }
                        break;

                    case 0:
                        System.out.println("Operação cancelada.");
                        dis = true; // Encerra o loop
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } while (!dis);

            scan.nextLine();

            //datas
            System.out.println("Data de CheckIn: ");
            String checkIn = scan.nextLine();

            scan.nextLine();

            System.out.println("Data de CheckOut: ");
            String CheckOut = scan.nextLine();

            // Converte as strings em objetos LocalDate
            LocalDate dataEntrada = LocalDate.parse(checkIn , formato);
            LocalDate dataSaida = LocalDate.parse(CheckOut, formato);


            if (dataSaida.isBefore(dataEntrada)) {
                System.out.println("A data de saída deve ser posterior à data de entrada.");
                return;
            }


            long numeroDiarias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);


            do {

                System.out.println("Cadastro de Hóspede");

                System.out.println("Nome: ");
                String nome = scan.nextLine();

                System.out.println("Idade: ");
                String idade = scan.nextLine();

                scan.nextLine();

                System.out.println("Cpf: ");
                String cpf = scan.nextLine();

                System.out.println("Telefone: ");
                String fone = scan.nextLine();



                Hospede hospede = new Hospede(nome,idade,cpf,fone,checkIn,CheckOut,id);
                ger.adicionarHospede(hospede);
                cont++;

            }while(hos > cont);

            Fatura fatura = new Fatura();
            float custo = fatura.calcularCusto(tipoQuarto,numeroDiarias);

            System.out.println("===============/Tela de pagamento/==============");
            System.out.println("Valor total: "+custo);
            System.out.println("Opções:" +
                    "Cartão de Crédito [1]" +
                    "Cartão de Débito (desconto de 5%) [2]" +
                    "Pix (desconto de 10%) [3] ");
            int res = scan.nextInt();
            scan.nextLine();

            if (res == 1) {

                System.out.println("PAGO: "+ fatura.des("credito",custo));
            }
            else if(res == 2){
                System.out.println("PAGO: "+ fatura.des("debito",custo));
            }
            else if (res == 3){
                System.out.println("PAGO: "+fatura.des("pix",custo));
            }
            ger.salvarHospedes();

            GerenciarQuartos quartoOcupado = new GerenciarQuartos();
            quartoOcupado.ocupar(tipoQuarto);

            //gerQ.salvarQuartos();





        }

    }
}