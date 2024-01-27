package fr.hb.ibm.beach.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


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
public class Utilisateur {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @NotEmpty(message = "Le nom ne peut pas être vide")
    @Size(max = 100, message = "Le nom doit comporter au plus {max} caractères")
    String nom;
    
    @NotEmpty(message = "Le prénom ne peut pas être vide")
    @Size(max = 100, message = "Le prénom doit comporter au plus {max} caractères")
    String prenom;
    
    @Email(message = "L'email doit être valide")
    @NotEmpty(message = "L'email ne peut pas être vide")
    String email;
    
    @NotEmpty(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 6, message = "Le mot de passe doit comporter au moins {min} caractères")
    String motDePasse;
}
