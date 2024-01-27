package fr.hb.ibm.beach.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class LienDeParente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotBlank(message = "Merci de préciser votre nom")
	@Size(max = 20, message = "Le nom doit comporter au plus {max} caractères")
	String nom;

	float coefficient;
	
    //Relation
    @OneToMany(mappedBy="lienDeParente", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Client> clients;
    
    public LienDeParente(String nom, float coefficient) {
        this();
        this.nom = nom;
        this.coefficient = coefficient;
    }

}
