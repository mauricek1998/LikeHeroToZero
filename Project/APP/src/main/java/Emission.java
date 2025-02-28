import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double co2Wert;

    @Column(name = "erstellungsdatum", nullable = false)
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "land_id")
    private Land land;

    public Emission() {
     
    }

    public Long getId() {
        return id;
    }

    public double getCo2Wert() {
        return co2Wert;
    }

    public void setCo2Wert(double co2Wert) {
        this.co2Wert = co2Wert;
    }

    public LocalDateTime getDatum() {
        return datum.toLocalDate().atStartOfDay();
    }

    public String getDatumFormatted() {
        return datum.toLocalDate().toString();
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }
}