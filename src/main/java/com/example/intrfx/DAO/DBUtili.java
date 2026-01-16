package com.example.intrfx.DAO;

import com.example.intrfx.Model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtili {
    public String url = "jdbc:mysql://localhost:8889/db_users";
    public String user = "root";
    public String pass = "root";
    public int addUser(Utilisateur u){
            try (
                    Connection connection = DriverManager.getConnection(url,user,pass);
                    PreparedStatement stmt = connection.prepareStatement("INSERT INTO utilisateur(name,username,email,password,role) VALUES (?,?,?,?,?)")
            ){
                stmt.setString(1,u.getName());
                stmt.setString(2,u.getUsername());
                stmt.setString(3,u.getEmail());
                stmt.setString(4,u.getPassword());
                stmt.setString(5,u.getRole());
                stmt.executeUpdate();
                return 1;
            }catch (SQLException e){
                e.printStackTrace();
                return 0;
            }
    }
    public List<Utilisateur> printAllUsers(){
        List<Utilisateur> listeU = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url,user,pass);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur")
        ){
           while (resultSet.next()){
               Utilisateur u = new Utilisateur();
               u.setPassword(resultSet.getString("password"));
               u.setName(resultSet.getString("name"));
               u.setUsername(resultSet.getString("username"));
               u.setRole(resultSet.getString("role"));
               u.setEmail(resultSet.getString("email"));
               u.setId(resultSet.getInt("id"));
                listeU.add(u);
           }
           return listeU;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
