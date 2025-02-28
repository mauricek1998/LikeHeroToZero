import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Benutzer {

   @Id
   private Long benutzer_Id;
   
   private String benutzername;
   private String passwort;
   private String benutzerart;

   public Benutzer() {}

   public Long getBenutzer_Id() {
       return benutzer_Id;
   }

   public void setBenutzer_Id(Long benutzer_Id) {
       this.benutzer_Id = benutzer_Id;  
   }

   public String getBenutzername() {
       return benutzername;
   }

   public void setBenutzername(String benutzername) {
       this.benutzername = benutzername;
   }

   public String getPasswort() {
       return passwort;
   }

   public void setPasswort(String passwort) {
       this.passwort = passwort;
   }

   public String getBenutzerart() {
       return benutzerart; 
   }

   public void setBenutzerart(String benutzerart) {
       this.benutzerart = benutzerart;
   }
}