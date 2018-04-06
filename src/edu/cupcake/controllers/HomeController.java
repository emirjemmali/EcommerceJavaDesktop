/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cupcake.controllers;

import com.jfoenix.controls.JFXButton;
import edu.cupcake.entities.Panier;
import edu.cupcake.utils.Routing;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author berrahal
 */
public class HomeController implements Initializable {

    @FXML
    private VBox menupane;
    @FXML
    private JFXButton connexiobutton;
    @FXML
    private JFXButton profilebutton;
    @FXML
    private AnchorPane content;
    @FXML
    private JFXButton deconnectbutton;
    @FXML
    private AnchorPane PanierPane;
    @FXML
    private Text txtNbProduits;
    @FXML
    private Text txtPrixTotal;
    
        public static int afficherprofile=0;
        public static int afficheradresses=0;
        public static int affichercommandes=0;
        public static int afficherpanier=0;
        public static int macommande=0;
    @FXML
    private JFXButton livreurbutton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (cupcake.Cupcake.user!=null) {
                menupane.getChildren().remove(connexiobutton);
               if (!cupcake.Cupcake.user.getRoles().contains("ROLE_DELIVERYMAN"))
            {
                menupane.getChildren().remove(livreurbutton);
            }  
                
            }
            else
            {
                menupane.getChildren().remove(profilebutton);
                menupane.getChildren().remove(deconnectbutton);
                
                menupane.getChildren().remove(livreurbutton);

            }
           
            
            try {
                initialiserPanier();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            AnchorPane panier = FXMLLoader.load(getClass().getResource(Routing.PANIER));
            AnchorPane profile = FXMLLoader.load(getClass().getResource(Routing.Profile));
            AnchorPane commande = FXMLLoader.load(getClass().getResource(Routing.MaCommande));


             if(macommande!=0)
            {
                
                content.getChildren().setAll(commande);
                macommande=0;
            }
            if(afficherpanier!=0)
            {
                
                content.getChildren().setAll(panier);
                afficherpanier=0;
            }
            if(afficherprofile!=0)
                    {
                     content.getChildren().setAll(profile);
                     afficherprofile=0;
                    }
           /* if(afficheradresses!=0)
                    {
                         AnchorPane pane=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/Profile.fxml"));
                //AnchorPane pane1=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/EditProfile.fxml"));

                     content.getChildren().setAll(pane,adresses);
                     afficheradresses=0;
                    }*/
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    public void Connexion(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cupcake/gui/Login.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
       
        menupane.getScene().setRoot(root);

    }    
    
    @FXML
    public void Afficherprofile(ActionEvent event) throws IOException {

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cupcake/gui/Login.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
       
        menupane.getScene().setRoot(root);*/
        ProfileController.afficherprofile=1;
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/Profile.fxml"));
                //AnchorPane pane1=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/EditProfile.fxml"));

                content.getChildren().setAll(pane);

        
        

    } 

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        cupcake.Cupcake.user=null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cupcake/gui/Home.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
       
        menupane.getScene().setRoot(root);
    }
    
    
    private void initialiserPanier() throws IOException
    {
           if(cupcake.Cupcake.Panier.isEmpty())
        {
            txtNbProduits.setText("Aucun produit");
            txtPrixTotal.setText("0 DT");
        }
        else
        {
                      Iterator<Panier> it = cupcake.Cupcake.Panier.iterator();
                      int nbproduit=0;
                     double prixtotal=0;
                      while (it.hasNext()) {
                Panier next = it.next();
                nbproduit++;
                prixtotal=next.getProduit().getPrice()*next.getQte()+prixtotal;

                
            }
                txtNbProduits.setText(""+nbproduit+" produits");
                txtPrixTotal.setText(""+prixtotal+" DT");
 
        }
    }

    @FXML
    private void AfficherPanier(MouseEvent event) throws IOException {
         AnchorPane pane=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/Panier.fxml"));
                //AnchorPane pane1=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/EditProfile.fxml"));

                content.getChildren().setAll(pane);
    }

    @FXML
    private void livreurhome(ActionEvent event) throws IOException {
      /*  AnchorPane pane=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/Livreur.fxml"));
                //AnchorPane pane1=FXMLLoader.load(getClass().getResource("/edu/cupcake/gui/EditProfile.fxml"));

                content.getChildren().setAll(pane);*/
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cupcake/gui/Livreur.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
       
        menupane.getScene().setRoot(root);
    }
    
}
