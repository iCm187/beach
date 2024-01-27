package fr.hb.ibm.beach.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pays {

	@Id
	String code;

	@Size(min = 2, message = "Le nom du pays doit comporter au moins {min} caractères")
	String nom;
	
	@OneToMany(mappedBy="pays", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	@ToString.Exclude
	@JsonIgnore
	List<Client> clients;

	public Pays(String code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
	}
}
