import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.inject.Named;

@Named
public class BenutzerDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hero2zero");

    public Benutzer findeBenutzer(String benutzername, String passwort) {
        List<Benutzer> benutzerListe = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            benutzerListe = em.createQuery(
                "SELECT b FROM Benutzer b",
                Benutzer.class
            ).getResultList();
        }
        catch (Exception e) {
            return null;
        }
        finally {
            em.close();
        }

        for (Benutzer eintragsBenutzer: benutzerListe) {
            String eintragsName = eintragsBenutzer.getBenutzername();
            String eintragsPasswort = eintragsBenutzer.getPasswort();

            if (eintragsName.equals(benutzername)) {
                if (eintragsPasswort.equals(passwort)) {
                    return eintragsBenutzer;
                }
            }
        }
        return null;
    }
}