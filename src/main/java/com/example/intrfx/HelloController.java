package com.example.intrfx;

import com.example.intrfx.DAO.DBUtili;
import com.example.intrfx.Model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    DBUtili db = new DBUtili();
    @FXML
    private Label welcomeText;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField role;

    @FXML
    private TableView<Utilisateur> tableUtilisateur; // table
    //etape 1
    @FXML
    private TableColumn<Utilisateur, Integer> colId;//colonne
    @FXML
    private TableColumn<Utilisateur, String> colName;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;
    @FXML
    private TableColumn<Utilisateur, String> colRole;
    @FXML
    private TableColumn<Utilisateur, String> colUsername;
    @FXML
    private TableColumn<Utilisateur, String> colPassword;

    @FXML
    private ObservableList<Utilisateur> users;//donnne

    @FXML
    public void ajouter(){
        //System.out.println(nom.getText());
        Utilisateur u = new Utilisateur();
        u.setEmail(email.getText());
        u.setName(nom.getText());
        u.setRole(role.getText());
        u.setUsername(prenom.getText());
        u.setPassword("passer");
        int ok = db.addUser(u);
        if(ok==1){
            System.out.println("Utilisateur bien ajoute");
        }else {
            System.out.println("Erreur d'ajout de l'utilisateur");
        }

    }

    //etape 2
    @FXML
    private void initialize(){

        db.printAllUsers().forEach(e->System.out.println(e.getEmail()));
        //lier les collonnes
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        // ajout des donnees
        users = FXCollections.observableArrayList(
                db.printAllUsers()
        );



        //ajouter la liste dans la table
        tableUtilisateur.setItems(users);
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
