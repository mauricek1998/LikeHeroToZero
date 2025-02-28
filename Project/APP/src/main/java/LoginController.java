import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@RequestScoped  
public class LoginController {
   
   private String username;
   private String password;
   private Benutzer loggedInUser;
   private String errorMessage = "";
   
   
   @Inject
   private BenutzerDAO benutzerDAO;

   public LoginController() {}
   
   public String login() {
       Benutzer gefunden = benutzerDAO.findeBenutzer(username, password);
       if (gefunden != null) {
           this.loggedInUser = gefunden;
           return "wissenschaftler.xhtml"; 
       } else {
           this.errorMessage = "Ungültige Zugangsdaten!";
           return null;
       }
   }
   
   public String logout() {
       this.loggedInUser = null;
       return "index.xhtml";
   }

   public String getUsername() {
       return username; 
   }

   public void setUsername(String username) {
       this.username = username;
   }

   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public Benutzer getLoggedInUser() {
       return loggedInUser;
   }

   public void setLoggedInUser(Benutzer loggedInUser) {
       this.loggedInUser = loggedInUser;
   }
   
   public String getErrorMessage() {
       return errorMessage;
   }
   
   public void setErrorMessage(String errorMessage) {
       this.errorMessage = errorMessage;
   }
}