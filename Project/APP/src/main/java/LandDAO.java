
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;



public class LandDAO {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("hero2zero");

    public void speichern(Land land) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(land);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Land> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Land l", Land.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Land> findByName(String name) {
    	   EntityManager em = emf.createEntityManager();
    	   try {
    	       List<Land> alleLaender = em.createQuery("SELECT l FROM Land l", Land.class)
    	               .getResultList();
    	       
    	       List<Land> gefiltert = new ArrayList<>();
    	       
    	       for (Land landeintrag : alleLaender) {
    	           if (landeintrag.getName().equals(name)) {
    	               gefiltert.add(landeintrag);
    	           }
    	       }
    	       
    	       return gefiltert;
    	   } finally {
    	       em.close();
    	   }
    	}
}
