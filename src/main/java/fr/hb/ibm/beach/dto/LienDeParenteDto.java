package fr.hb.ibm.beach.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LienDeParenteDto {

	Long id;
	
	@NotBlank(message = "Merci de préciser votre nom")
	@Size(max = 20, message = "Le nom doit comporter au plus {max} caractères")
	String nom;

	@Positive(message = "Merci de préciser un nombre strictement positif")
	float coefficient;
}
