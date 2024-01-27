package fr.hb.ibm.beach.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClientDto {

	@Size(min = 2, max = 40, message = "Le nom doit comporter au minimum {min} caractères et {max} caractères")
	protected String nom;

	@Size(min = 2, max = 40, message = "Le prenom doit comporter au minimum {min} caractères et {max} caractères")
	protected String prenom;

	@Email(message = "vous devez renseigner un email")
	protected String email;
	
	@Size(min = 5, message = "Le mot de passe doit contenir au minimum {min}caractères")
	protected String motDePasse;

	@NotNull(message = "Merci de choisir un pays")
	private PaysDto paysDto;

	@NotNull(message = "Merci de choisir un lien de parenté")
	private LienDeParenteDto lienDeParenteDto;
}
