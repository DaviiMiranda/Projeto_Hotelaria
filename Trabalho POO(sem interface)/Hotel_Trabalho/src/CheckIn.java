public interface CheckIn {
    void ocupar(String id); // Registra a entrada
    String verificarDisponibilidade(String id); // Verifica se o quarto está disponível
}
