/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.estga.bibliotecavirtual;

/**
 *
 * @author arti
 */
public class SessaoUtilizador {
      private static int idUtilizador = -1; // ID do utilizador que fez login
    private static int idCargo = -1; // Cargo do utilizador (exemplo: 1 = admin)
    private static boolean funcionario = false; // Indica se é funcionário

    // Define os dados do utilizador na sessão
    public static void setUtilizador(int id, int cargo, boolean isFuncionario) {
        idUtilizador = id;
        idCargo = cargo;
        funcionario = isFuncionario;
    }

    // Obtém o ID do utilizador que fez login
    public static int getIdUtilizador() {
        return idUtilizador;
    }

    // Obtém o cargo do utilizador
    public static int getIdCargo() {
        return idCargo;
    }

    // Verifica se o utilizador é funcionário
    public static boolean isFuncionario() {
        return funcionario;
    }

    // Limpa a sessão (ex: ao fazer logout)
    public static void limparSessao() {
        idUtilizador = -1;
        idCargo = -1;
        funcionario = false;
    }
    
}
