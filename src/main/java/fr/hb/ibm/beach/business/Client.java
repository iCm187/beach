package fr.hb.ibm.beach.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Client extends Utilisateur {
	
		LocalDateTime dateHeureInscription;
	    
	    //Relation
	    @ManyToOne
	    @NotNull(message = "Un Utilisateur doit habiter dans un pays.")
	    private Pays pays;
	    
	    @ManyToOne
	    @NotNull(message = "Un Utilisateur doit avoir un lien de parenté.")
	    private LienDeParente lienDeParente;
	    
	    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
	    @JsonIgnore
	    List<Reservation> reservations;

		public Client(LocalDateTime dateHeureInscription, Pays pays, LienDeParente lienDeParente) {
			super();
			this.dateHeureInscription = dateHeureInscription;
			this.pays = pays;
			this.lienDeParente = lienDeParente;
		}

}
