/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author arti
 */
public class DatabaseUtil {

    public static Connection getConnection() {
        Properties props = new Properties();
        // PATH do ficheiro com as credenciais da base de dados
        String path = "../database.properties/database.properties";
        try (FileInputStream in = new FileInputStream(path)) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("Erro ao carregar o ficheiro de propriedades: " + e.getMessage());
            return null;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erro na conexão com base de dados: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Diretório do trabalho atual: " + System.getProperty("user.dir"));
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer a conexão.");
            }
        } catch (Exception e) {
            System.out.println("Erro durante a conexão com a base de dados: " + e.getMessage());
        }
    }
}
