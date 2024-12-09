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

    private static int idUtilizador = -1;
    private static int idCargo = -1;
    private static boolean funcionario = false;
    private static String username = "";
    private static String nomeCompleto = "";
    private static String senha = "";
    private static String email = "";
    private static String contato = "";

    public static void setUtilizador(int id, int cargo, boolean isFuncionario, String user, String nome, String password, String mail, String contact) {
        idUtilizador = id;
        idCargo = cargo;
        funcionario = isFuncionario;
        username = user;
        nomeCompleto = nome;
        senha = password;
        email = mail;
        contato = contact;
    }

    public static int getIdUtilizador() {
        return idUtilizador;
    }

    public static int getIdCargo() {
        return idCargo;
    }

    public static boolean isFuncionario() {
        return funcionario;
    }

    public static String getUsername() {
        return username;
    }

    public static String getNomeCompleto() {
        return nomeCompleto;
    }

    public static String getSenha() {
        return senha;
    }

    public static String getEmail() {
        return email;
    }

    public static String getContato() {
        return contato;
    }

    public static void limparSessao() {
        idUtilizador = -1;
        idCargo = -1;
        funcionario = false;
        username = "";
        nomeCompleto = "";
        senha = "";
        email = "";
        contato = "";
    }

    static boolean isLogged() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
