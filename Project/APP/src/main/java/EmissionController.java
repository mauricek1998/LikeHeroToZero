import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Named
@RequestScoped
public class EmissionController {

   @Inject
   private EmissionDAO emissionDAO;
   
   @Inject
   private LandDAO landDAO;
   
   private Emission emission = new Emission();
   private String success = "";
   private String landName;
   private String suchLand;
   private String datumString;
   
   private List<Emission> suchErgebnisse;

   public String speichern() {
       try {
           Land landAusDB = findeOderErzeugeLand(landName);
           if(landAusDB != null) {
               emission.setLand(landAusDB);
               
             
               if (datumString != null && !datumString.isEmpty()) {
                   LocalDate datum = LocalDate.parse(datumString);
                   emission.setDatum(datum.atStartOfDay());
               } else {
                   emission.setDatum(LocalDateTime.now());
               }
               
               emissionDAO.speichereEmission(emission);
               emission = new Emission();
               landName = "";
               datumString = "";
               success = "Neuer Co2 Wert für das Land erfolgreich eingetragen!";
           }
       } catch (DateTimeParseException e) {
           success = "Fehler: Bitte geben Sie das Datum im Format yyyy-MM-dd ein!";
       }
       return null;
   }

   private Land findeOderErzeugeLand(String name) {
       if (name == null || name.isEmpty()) {
           return null;
       }
       
      
       List<Land> vorhanden = landDAO.findByName(name);
       if (!vorhanden.isEmpty()) {
           return vorhanden.get(0);
       }
       
       
       Land neu = new Land();
       neu.setName(name);
       landDAO.speichern(neu);
       return neu;
   }

   public String suchen() {
	    if(suchLand == null || suchLand.isEmpty()) {
	        suchErgebnisse = emissionDAO.findNewestPerLand(); 
	    } else {
	        suchErgebnisse = emissionDAO.findByLand(suchLand);
	    }
	    return null;
	}
   

   
   public Emission getEmission() {
       return emission;
   }

   public void setEmission(Emission emission) {
       this.emission = emission;
       this.success = "Land erfolgreich eingetragen!";
   }

   public String getLandName() {
       return landName;
   }

   public void setLandName(String landName) {
	   System.out.println("Landname wird gesettet.");
       this.landName = landName;
   }

   public String getSuchLand() {
       return suchLand;
   }

   public void setSuchLand(String suchLand) {
       this.suchLand = suchLand;
   }

   public List<Emission> getSuchErgebnisse() {
	   System.out.println("Getter für Suchergebnisse getriggert. Ergebnisse:");
	   System.out.println(suchErgebnisse);
       return suchErgebnisse;
   }

   public String getSuccess() {
       return success;
   }

   public void setSuccess(String success) {
       this.success = success;
   }

   public String getDatumString() {
       return datumString;
   }
   
   public void setDatumString(String datumString) {
       this.datumString = datumString;
   }
}