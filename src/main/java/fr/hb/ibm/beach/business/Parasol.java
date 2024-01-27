package fr.hb.ibm.beach.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parasol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotNull(message= "Merci de renseigner le numero d'emplacement")
	@Range(min=1, max=36, message="Merci de préciser un numero d'emplacement compris entre {min} et {max}")
	Byte numEmplacement;
	
	@NotNull(message = "Merci de choisir une file")
	@ManyToOne
	File file;
}
