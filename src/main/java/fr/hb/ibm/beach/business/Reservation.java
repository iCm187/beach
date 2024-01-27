package fr.hb.ibm.beach.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.hb.ibm.beach.annotation.DateDebutEgaleOuAvantDateFin;
import fr.hb.ibm.beach.annotation.SaisonEstivale;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@DateDebutEgaleOuAvantDateFin(message = "La date de début doit être égale ou avant la date de fin")
@SaisonEstivale(message="La réservation doit être en période estivale")
public class Reservation {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long id;

	    @NotNull
	    @DateTimeFormat(iso = ISO.DATE)
	    LocalDate dateDebut;

	    @NotNull
	    @DateTimeFormat(iso = ISO.DATE)
	    LocalDate dateFin;

	    @DecimalMin(value = "0.0")
	    double montantAReglerEnEuros;

	    @Lob  // Large Object
	    String remarques;

	    @CreditCardNumber
	    @Size(min = 16, max = 16)
	    String numeroCarte;

	    @Positive
	    @Max(12)
	    Byte moisExpiration;

	    @Positive
	    Short anneeExpiration;

	    @Size(min = 3, max = 3)
	    String cryptogramme;

	    //@NotNull
	    //@Positive
	    int version;

	    
	    @ManyToOne
	    Client client;

	    
	    @ManyToOne
	    Statut statut;

	    
	    @ManyToMany
	    @JsonIgnore
	    List<Parasol> parasols;
	    
	    private String nomFile;

	    public String getNomFile() {
	        return nomFile;
	    }

	    public void setNomFile(String nomFile) {
	        this.nomFile = nomFile;
	    }
}
