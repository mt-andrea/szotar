/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */
package szotar;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import panel.Panel;

/**
 *
 * @author Máté Andrea
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Tab tabSzotar;
    
    @FXML
    private TableView<Szo> tblSzavak;
    
    @FXML
    private TableColumn<Szo, String> oLecke;
    
    @FXML
    private TableColumn<Szo, String> oAngol;
    
    @FXML
    private TableColumn<Szo, String> oMagyar;
    
    @FXML
    private ComboBox<String> cbxValaszt;
    
    @FXML
    private TextField txtLecke;
    
    @FXML
    private TextField txtAngol;
    
    @FXML
    private TextField txtMAgyar;
    
    @FXML
    private TextField txtLeckeSzuro;
    
    @FXML
    private TextField txtAngolSzuro;
    
    @FXML
    private TextField txtMAgyarSzuro;
    
    @FXML
    private Label lblFelso;
    
    @FXML
    private Label lblAlso;
    
    @FXML
    private Button btnTudtam;
    
    @FXML
    private Button btnNemTudtam;
    
    @FXML
    private Label lblHanyvan;
    
    @FXML
    private Button btnIndit;
    
    @FXML
    void hozzaad() {
        String lecke = txtLecke.getText();
        if (lecke.length() < 1 || lecke.length() > 10) {
            Panel.hiba("Hiba!", "A lecke hossza 1-10 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }
        String angol = txtAngol.getText();
        if (angol.length() < 1 || angol.length() > 60) {
            Panel.hiba("Hiba!", "A angol mező hossza 1-60 karakter lehet!");
            txtAngol.requestFocus();
            return;
        }
        String magyar = txtMAgyar.getText();
        if (magyar.length() < 1 || magyar.length() > 60) {
            Panel.hiba("Hiba!", "A magyar mező hossza 1-60 karakter lehet!");
            txtMAgyar.requestFocus();
            return;
        }
        int sor = ab.hozzaad(lecke, angol, magyar);
        if (sor > 0) {
            beolvas();
            uj();
        }
    }
    
    @FXML
    void modosit() {
        int ind = tblSzavak.getSelectionModel().getSelectedIndex();
        if (ind == -1) {
            return;
        }
        int id = tblSzavak.getItems().get(ind).getSzoID();
        String lecke = txtLecke.getText();
        if (lecke.length() < 1 || lecke.length() > 10) {
            Panel.hiba("Hiba!", "A lecke hossza 1-10 karakter lehet!");
            txtLecke.requestFocus();
            return;
        }
        String angol = txtAngol.getText();
        if (angol.length() < 1 || angol.length() > 60) {
            Panel.hiba("Hiba!", "A angol mező hossza 1-60 karakter lehet!");
            txtAngol.requestFocus();
            return;
        }
        String magyar = txtMAgyar.getText();
        if (magyar.length() < 1 || magyar.length() > 60) {
            Panel.hiba("Hiba!", "A magyar mező hossza 1-60 karakter lehet!");
            txtMAgyar.requestFocus();
            return;
        }
        int sor = ab.modosit(id, lecke, angol, magyar);
        if (sor > 0) {
            beolvas();
            int i = 0;
            while (tblSzavak.getItems().get(i).getSzoID() != id && i < tblSzavak.getItems().size()) {
                i++;
            }
            if (i < tblSzavak.getItems().size()) {
                tblSzavak.getSelectionModel().select(i);
            }
        }
    }
    
    @FXML
    void mutat() {
        if (lblFelso.getText().equals("?")) {
            return;
        }
        if (cbxValaszt.getSelectionModel().getSelectedIndex()==0) {
            lblAlso.setText(lista.get(ind).getMagyar());
        }else{
            lblAlso.setText(lista.get(ind).getAngol());
        }
        btnNemTudtam.setDisable(false);
        btnTudtam.setDisable(false);
    }
    
    @FXML
    void tudtam() {
lista.remove(ind);
        if (lista.isEmpty()) {
            Panel.hiba("Gratulálok!", "Minden szót megtanultál!");
            indit();
            return;
        }
        nem_tudtam();
    }
    
    @FXML
    void nem_tudtam() {
        lblHanyvan.setText("Még " + lista.size() + " szó.");
        ind = rand.nextInt(lista.size());
        if (cbxValaszt.getSelectionModel().getSelectedIndex() == 0) {
            lblFelso.setText(lista.get(ind).getAngol());
        } else {
            lblFelso.setText(lista.get(ind).getMagyar());
        }
        lblAlso.setText("?");
        btnTudtam.setDisable(true);
        btnNemTudtam.setDisable(true);
    }
    
    @FXML
    void indit() {
        if (btnIndit.getText().equals("Indít")) {
            tabSzotar.setDisable(true);
            cbxValaszt.setDisable(true);
            btnIndit.setText("Megállít");
            lista = new ArrayList<>(tblSzavak.getItems());
            nem_tudtam();
        } else {
            tabSzotar.setDisable(false);
            cbxValaszt.setDisable(false);
            btnIndit.setText("Indít");
            lblAlso.setText("?");
            lblFelso.setText("?");
            lblHanyvan.setText("");
            btnTudtam.setDisable(true);
            btnNemTudtam.setDisable(true);
        }
    }
    
    @FXML
    void szuro_torol() {
        txtLeckeSzuro.clear();
        txtAngolSzuro.clear();
        txtMAgyarSzuro.clear();
        tblSzavak.requestFocus();
    }
    
    @FXML
    void torol() {
        int ind = tblSzavak.getSelectionModel().getSelectedIndex();
        if (ind == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan tötrölni szeretnéd eztz a szót?")) {
            return;
        }
        int id = tblSzavak.getItems().get(ind).getSzoID();
        int sor = ab.torol(id);
        if (sor > 0) {
            beolvas();
        }
    }
    
    @FXML
    void uj() {
        txtAngol.clear();
        txtMAgyar.clear();
        txtLecke.requestFocus();
        tblSzavak.getSelectionModel().select(null);
        
    }
    
    @FXML
    void google() throws Exception {
        String s = "https://translate.google.com/#view=home&op=translate&sl=en&tl=hu&text=" + txtAngol.getText().replace(" ", "%20");
        Desktop.getDesktop().browse(new URI(s));
        txtMAgyar.requestFocus();
    }
    
    DB ab = new DB();
    
    private void beolvas() {
        String szuro1 = "'%" + txtLeckeSzuro.getText() + "%'";
        String szuro2 = "'%" + txtAngolSzuro.getText() + "%'";
        String szuro3 = "'%" + txtMAgyarSzuro.getText() + "%'";
        String s = "select *from szavak where lecke like " + szuro1 + " and angol like " + szuro2 + " and magyar like " + szuro3 + " order by angol;";
        ab.beolvas(tblSzavak.getItems(), s);
    }
    
    private void tablabol(int i) {
        if (i == -1) {
            return;
        }
        Szo sz = tblSzavak.getItems().get(i);
        txtLecke.setText("" + sz.getLecke());
        txtAngol.setText("" + sz.getAngol());
        txtMAgyar.setText("" + sz.getMagyar());
    }
    
    private ArrayList<Szo> lista;
    private Random rand = new Random();
    private int ind;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oLecke.setCellValueFactory(new PropertyValueFactory<>("lecke"));
        oAngol.setCellValueFactory(new PropertyValueFactory<>("angol"));
        oMagyar.setCellValueFactory(new PropertyValueFactory<>("magyar"));
        beolvas();
        txtLeckeSzuro.textProperty().addListener((o, regi, uj) -> beolvas());
        txtAngolSzuro.textProperty().addListener((o, regi, uj) -> beolvas());
        txtMAgyarSzuro.textProperty().addListener((o, regi, uj) -> beolvas());
        tblSzavak.getSelectionModel().selectedIndexProperty().addListener((o, regi, uj) -> tablabol(uj.intValue()));
        
        cbxValaszt.getItems().addAll("Angol -> Magyar", "Magyar -> Angol");
        cbxValaszt.setValue("Angol -> MAgyar");
    }
    
}
