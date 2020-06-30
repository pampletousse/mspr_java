package com.epsi.msprjava.controler;

import com.epsi.msprjava.bdd.OracleConnexion;
import com.epsi.msprjava.model.*;
import com.epsi.msprjava.util.SiteStringConverter;
import com.epsi.msprjava.util.TypeDechetStringConverter;
import com.epsi.msprjava.viewmodel.DechetsEnleves;
import com.epsi.msprjava.viewmodel.EntrepriseTourneeQteByDemande;
import com.sun.rowset.internal.Row;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.Proxy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardController {

    @FXML
    private Label labelWelcome = new Label();

    @FXML
    private Label labelRaison = new Label();

    @FXML
    private Label labelNumTournee = new Label();

    @FXML
    private Label labelqteTotale = new Label();

    @FXML
    TextField nbTournees;

    @FXML
    ListView<String> listeEmployes = new ListView<String>();

    @FXML
    ChoiceBox<TypeDechet> cb1 = new ChoiceBox();

    @FXML
    ChoiceBox<Site> cb2 = new ChoiceBox();

    @FXML
    ListView<Demande> listDemandes = new ListView<Demande>();

    @FXML
    ListView<Demande> listeDemandesNA = new ListView<Demande>();

    @FXML
    ListView<Map.Entry> listDechets = new ListView<Map.Entry>();

    @FXML
    Button btnRechercherApres = new Button();

    @FXML
    DatePicker datePicker1 = new DatePicker();

    @FXML
    DatePicker datePicker2 = new DatePicker();

    @FXML
    DatePicker datePicker3 = new DatePicker();

    ObservableList<String> items = FXCollections.observableArrayList();

    ObservableList<TypeDechet> itemsDechet = FXCollections.observableArrayList();

    ObservableList<String> itemsEmployes = FXCollections.observableArrayList();

    ObservableList<Demande> itemsDemandesNA = FXCollections.observableArrayList();

    private OracleConnexion oracleConnexion = new OracleConnexion();

    private ErrorController errorController;

    private SimpleStringProperty value = new SimpleStringProperty();

    private SimpleStringProperty valueRaison = new SimpleStringProperty();

    private SimpleStringProperty valueNumTournee = new SimpleStringProperty();

    private SimpleStringProperty valueQteTotale = new SimpleStringProperty();


    public void initialize() {

        value.set("Bienvenue");
        labelWelcome.textProperty().bind(Bindings.convert(value));

        listDemandes.setCellFactory(param -> new ListCell<Demande>() {
            @Override
            protected void updateItem(Demande item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getIddemande() + " - " + item.getDateenlevement());
                }
            }
        });

        listeDemandesNA.setCellFactory(param -> new ListCell<Demande>() {
            @Override
            protected void updateItem(Demande item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getIddemande() + " - " + item.getDateenlevement());
                }
            }
        });

        listDechets.setCellFactory(param -> new ListCell<Map.Entry>() {
            @Override
            protected void updateItem(Map.Entry item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    TypeDechet td = (TypeDechet) item.getKey();
                    setText(td.getNom() + " -> " + item.getValue().toString());
                }
            }
        });

        List<Demande> ld = getDemandes();
        for (Demande d : ld) {
            listDemandes.getItems().add(d);
        }

        List<Demande> ldd = getDemandesNonAffectees();
        for (Demande d2 : ldd) {
            listeDemandesNA.getItems().add(d2);
        }

        List<TypeDechet> lt = getTypeDechets();
        for (TypeDechet t : lt) {
            cb1.setConverter(new TypeDechetStringConverter());
            cb1.getItems().add(t);
        }

        List<Site> ls = getSites();
        for (Site s : ls) {
            cb2.setConverter(new SiteStringConverter());
            cb2.getItems().add(s);
        }

        cb1.getSelectionModel().selectFirst();
        cb2.getSelectionModel().selectFirst();


        listDemandes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Demande>() {
            @Override
            public void changed(ObservableValue<? extends Demande> observable, Demande oldValue, Demande newValue) {
                try {
                    System.out.println("Selected item: " + newValue.getIddemande());
                    EntrepriseTourneeQteByDemande etq = getInformationsByDemande(Integer.parseInt(newValue.getIddemande().toString()));
                    valueRaison.set("Raison Sociale : " + etq.getRaisonSocialeEntreprise());
                    labelRaison.textProperty().bind(Bindings.convert(valueRaison));

                    valueNumTournee.set("Numéro de tournée : " + String.valueOf(etq.getTournee()));
                    labelNumTournee.textProperty().bind(Bindings.convert(valueNumTournee));

                    listDechets.getItems().clear();
                    for (Map.Entry e : etq.getDechetsEnleves().getMapDechetsEnleves().entrySet()) {
                        listDechets.getItems().add(e);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(DashboardController.class.getResource("../views/dashboard.fxml"));

        Scene scene = new Scene(root, 500, 500);
        window.setScene(scene);
        window.show();

        callErrorModale("test");

        // Tab Demandes

        // Tap Employés

        // Tap Tournées

        // Tab Dechets


        // Fonction 1
        String str = "2018-10-10";
        java.sql.Date date = java.sql.Date.valueOf(str);
        List<Demande> ld = getListDemandesApresDate(date);

        // Fonction 2
        String str1 = "2018-09-10";
        java.sql.Date date1 = java.sql.Date.valueOf(str1);
        String str2 = "2018-11-10";
        java.sql.Date date2 = java.sql.Date.valueOf(str2);
        DechetsEnleves de = getQteTotaleDechetByDate(date1, date2);

        // Fonction 2
        EntrepriseTourneeQteByDemande etq = getInformationsByDemande(1);

        // Fonction 3
        List<Demande> ldd = getDemandesNonAffectees();

        // Fonction 4
        List<Employe> le = getEmployesMoinsTournees(4);

        // Fonction 5
        DechetsEnleves dee = getQteTotaleByTypeDateSite(1, date1, date2, 1);
    }

    Callback<ListView<TypeDechet>, ListCell<TypeDechet>> cellFactory = new Callback<ListView<TypeDechet>, ListCell<TypeDechet>>() {

        @Override
        public ListCell<TypeDechet> call(ListView<TypeDechet> l) {
            return new ListCell<TypeDechet>() {

                @Override
                protected void updateItem(TypeDechet item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setText(item.getNom() + "    " + item.getNivDanger());
                    }
                }
            };
        }
    };

    public void demandeApres(ActionEvent actionEvent) throws Exception {

        LocalDate localedate = datePicker1.getValue();
        java.sql.Date date = Date.valueOf(localedate);

        listDemandes.getItems().clear();
        for (Demande d : getListDemandesApresDate(date)) {
            listDemandes.getItems().add(d);
        }
    }

    public void searchEmployes(ActionEvent actionEvent) throws Exception {

        int nbTournee = Integer.parseInt(nbTournees.getText());
        listeEmployes.getItems().clear();
        for (Employe e : getEmployesMoinsTournees(nbTournee)) {
            listeEmployes.getItems().add(e.getIdEmploye() + " - " + e.getNom() + " " + e.getPrenom());
        }
    }

    public void onClickSearchDechets(ActionEvent actionEvent) throws Exception {

        System.out.println(cb1.getValue().getIdTypeDechet());
        System.out.println(cb2.getValue().getIdSite());

        LocalDate localedate1 = datePicker2.getValue();
        java.sql.Date date1 = Date.valueOf(localedate1);

        LocalDate localedate2 = datePicker3.getValue();
        java.sql.Date date2 = Date.valueOf(localedate2);


        listDechets.getItems().clear();
        DechetsEnleves de = getQteTotaleByTypeDateSite(cb1.getValue().getIdTypeDechet(), date1, date2, cb2.getValue().getIdSite());
        //de.setMapDechetsEnleves(de.getMapDechetsEnleves());
        Map map = de.getMapDechetsEnleves();
        Map.Entry<TypeDechet, Long> entry = (Map.Entry<TypeDechet, Long>) map.entrySet().iterator().next();
        //valueQteTotale.set(entry.getValue().toString());
        String s = entry.getValue().toString();
        valueQteTotale.set("Quantité totale récupérée : " + s);
        labelqteTotale.textProperty().bind(Bindings.convert(valueQteTotale));
        //listDemandes.getItems().add(d.getIddemande() + " - " + d.getDatedemande());
    }

    // Récupération des demandes après une date donnée
    public List<Demande> getListDemandesApresDate(java.sql.Date date) {
        List listedemandes = new ArrayList<Demande>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where datedemande > ?");
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Demande d = new Demande();
                d.setIddemande(rs.getLong(1));
                d.setDatedemande(rs.getDate(2));
                d.setDateenlevement(rs.getDate(3));
                d.setIdEntreprise(rs.getInt(4));
                d.setIdTournee(rs.getLong(5));
                d.setWebON(rs.getString(6));
                d.setIdSite(rs.getInt(7));
                listedemandes.add(d);
                items.add(d.getWebON());
            }
            connexion.close();
            return listedemandes;
        } catch (Exception e) {
            callErrorModale("Une erreur est survenue");
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de déchet par type pour une période donnée
    public DechetsEnleves getQteTotaleDechetByDate(Date date1, Date date2) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement(
                    "select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                    "inner join demande d on dd.id_demande = d.id_demande " +
                    "inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                    "where d.datedemande between ? and ? " +
                    "group by dd.id_type_dechet");
            stmt.setDate(1, date1);
            stmt.setDate(2, date2);
            ResultSet rs = stmt.executeQuery();

            DechetsEnleves dechetsEnleves = new DechetsEnleves();

            while (rs.next()) {
                TypeDechet td = getTypeDechetById(rs.getInt(1));
                dechetsEnleves.getMapDechetsEnleves().put(td, rs.getLong(2));
            }
            connexion.close();
            return dechetsEnleves;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EntrepriseTourneeQteByDemande getInformationsByDemande(int demandeid) {
        EntrepriseTourneeQteByDemande etq = new EntrepriseTourneeQteByDemande();
        DechetsEnleves de = new DechetsEnleves();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement(
                    "select id_tournee, raisonsociale from demande d " +
                            "inner join entreprise e on d.id_entreprise = e.id_entreprise " +
                            "where id_demande = ? ");
            stmt.setInt(1, demandeid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                etq.setTournee(rs.getInt(1));
                etq.setRaisonSocialeEntreprise(rs.getString(2));
            }

            PreparedStatement stmt2 = connexion.prepareStatement(
                    "select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                            "inner join demande d on dd.id_demande = d.id_demande " +
                            "inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                            "where dd.id_demande = ? " +
                            "group by dd.id_type_dechet "
            );
            stmt2.setInt(1, demandeid);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                TypeDechet td = getTypeDechetById(rs2.getInt(1));
                de.getMapDechetsEnleves().put(td, rs2.getLong(2));
            }
            etq.setDechetsEnleves(de);
            connexion.close();
            return etq;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupération des demandes non affectées à une tournée
    public List<Demande> getDemandesNonAffectees() {
        List listedemandes = new ArrayList<Demande>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande where id_tournee IS NULL");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Demande d = new Demande();
                d.setIddemande(rs.getLong(1));
                d.setDatedemande(rs.getDate(2));
                d.setDateenlevement(rs.getDate(3));
                d.setIdEntreprise(rs.getInt(4));
                d.setIdTournee(rs.getLong(5));
                d.setWebON(rs.getString(6));
                d.setIdSite(rs.getInt(7));
                //listDemandesNA.getItems().add(d);
                listedemandes.add(d);
            }
            connexion.close();
            return listedemandes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération des employés ayant effectués moins de n tournées
    public List<Employe> getEmployesMoinsTournees(int nbTournees) {
        List listeEmployes = new ArrayList<Employe>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement(
                    "select *" +
                            "FROM employe " +
                            "WHERE id_employe IN (" +
                            "select id_employe " +
                            "from tournee " +
                            "group by id_employe " +
                            "HAVING COUNT(id_employe) < ? " +
                            ")");
            stmt.setInt(1, nbTournees);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employe e = new Employe();
                e.setIdEmploye(rs.getShort(1));
                e.setNom(rs.getString(2));
                e.setPrenom(rs.getString(3));
                e.setDatenaiss(rs.getTime(4));
                e.setDateembauche(rs.getTime(5));
                e.setSalaire(rs.getLong(6));
                e.setIdProfil(rs.getInt(7));
                listeEmployes.add(e);
            }
            connexion.close();
            return listeEmployes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupération de la quantité totale de dechet par type periode et/ou site
    public DechetsEnleves getQteTotaleByTypeDateSite(int idTypeDechet, Date dateDebut, Date dateFin, int idSite) {
        try {
            Connection connexion = oracleConnexion.connect();
            String sql =
                    "select dd.id_type_dechet, SUM(quantite_enlevee) from detail_demande dd " +
                            " inner join demande d on dd.id_demande = d.id_demande " +
                            " inner join type_dechet td on dd.id_type_dechet  = td.id_type_dechet " +
                            " where dd.id_type_dechet = ? and d.datedemande between ? and ? ";

            if (idSite != -1) {
                // Recherche sur un site précis
                sql += " and d.id_site = ? ";
            }
            sql += " group by dd.id_type_dechet ";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, idTypeDechet);
            stmt.setDate(2, dateDebut);
            stmt.setDate(3, dateFin);
            stmt.setInt(4, idSite);

            ResultSet rs = stmt.executeQuery();
            DechetsEnleves dechetsEnleves = new DechetsEnleves();
            itemsDechet.clear();
            while (rs.next()) {
                TypeDechet td = getTypeDechetById(rs.getInt(1));
                dechetsEnleves.getMapDechetsEnleves().put(td, rs.getLong(2));
                itemsDechet.add(td);
            }
            connexion.close();
            return dechetsEnleves;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Affectation des tournées
    /*
    public void affectTournees(Date datedemandee) {
        // Inscription dans une tournée déjà créée pour la date demandée
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from tournee where detatournee = ?");
            stmt.setDate(1, datedemandee);
            ResultSet rs = stmt.executeQuery();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public TypeDechet getTypeDechetById(int idTypeDechet) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from type_dechet where id_type_dechet = ?");
            stmt.setInt(1, idTypeDechet);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TypeDechet td = new TypeDechet();
                td.setIdTypeDechet(rs.getInt(1));
                td.setNom(rs.getString(2));
                td.setNivDanger(rs.getBoolean(3));
                return td;
            }
            connexion.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Tournee getTourneeById(int idTournee) {
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from tournee where id_type_dechet = ?");
            stmt.setInt(1, idTournee);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tournee t = new Tournee();
                t.setIdTournee(rs.getLong(1));
                t.setDatetournee(rs.getTime(2));
                t.setIdimmatric(rs.getString(3));
                t.setIdEmploye(rs.getShort(4));
                return t;
            }
            connexion.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Demande> getDemandes() {
        List listedemandes = new ArrayList<Demande>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from demande");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Demande d = new Demande();
                d.setIddemande(rs.getLong(1));
                d.setDatedemande(rs.getDate(2));
                d.setDateenlevement(rs.getDate(3));
                d.setIdEntreprise(rs.getInt(4));
                d.setIdTournee(rs.getLong(5));
                d.setWebON(rs.getString(6));
                d.setIdSite(rs.getInt(7));
                listedemandes.add(d);
            }
            connexion.close();
            return listedemandes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Site> getSites() {
        List listesites = new ArrayList<Site>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from sites");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Site s = new Site();
                s.setIdSite(rs.getInt(1));
                s.setNomsite(rs.getString(2));
                s.setNoruesite(rs.getInt(3));
                s.setRuesite(rs.getString(4));
                s.setCpostalsite(rs.getShort(5));
                s.setVillesite(rs.getString(6));
                listesites.add(s);
            }
            connexion.close();
            return listesites;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TypeDechet> getTypeDechets() {
        List listeTypes = new ArrayList<TypeNotPresentException>();
        try {
            Connection connexion = oracleConnexion.connect();
            PreparedStatement stmt = connexion.prepareStatement("select * from type_dechet");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Site s = new Site();
                TypeDechet t = new TypeDechet();
                t.setIdTypeDechet(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setNivDanger(rs.getBoolean(3));
                listeTypes.add(t);
            }
            connexion.close();
            return listeTypes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void callErrorModale(String message) {
        try {
            new ErrorController(message);
            errorController.start(new Stage(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
