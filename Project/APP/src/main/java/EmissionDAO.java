
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmissionDAO {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("hero2zero");

    public void speichereEmission(Emission emission) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emission);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    public List<Emission> findNewestPerLand() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Emission> alleEmissionen = em.createQuery(
                "SELECT e FROM Emission e JOIN FETCH e.land ORDER BY e.land.name",
                Emission.class).getResultList();

            Map<Long, Emission> neuesteProLand = new HashMap<>();

            for (Emission emission : alleEmissionen) {
                Long landId = emission.getLand().getId();

                if (!neuesteProLand.containsKey(landId)) {
                    neuesteProLand.put(landId, emission);
                }
            }

            return new ArrayList<>(neuesteProLand.values());
        } finally {
            em.close();
        }
    }

    public List<Emission> findByLand(String landName) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Emission> alleEmissionen = em.createQuery(
                "SELECT e FROM Emission e JOIN FETCH e.land",
                Emission.class).getResultList();

            Emission neuesteEmission = null;
            
            for (Emission e : alleEmissionen) {
                if (e.getLand() != null && e.getLand().getName().equals(landName)) {
                    if (neuesteEmission == null || e.getDatum().isAfter(neuesteEmission.getDatum())) {
                        neuesteEmission = e;
                    }
                }
            }
            
            List<Emission> ergebnis = new ArrayList<>();
            if (neuesteEmission != null) {
                ergebnis.add(neuesteEmission);
            }
            
            return ergebnis;
        } finally {
            em.close();
        }
    }
}
