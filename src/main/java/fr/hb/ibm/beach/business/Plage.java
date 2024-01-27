package fr.hb.ibm.beach.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plage implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlage;

	//@NotBlank(message = "La plage doit avoir un nom")
	//pose probleme pour le faker
	protected String nomPlage;
	@Size(min=5,max=1600, message="La description doit inclure au minimum {min} charactères et {max} charactères")
	private String descriptionPlage;

	@ManyToOne
	//pose probleme pour le faker
	//@NotNull(message="merci de renseigner un concessionnaire")
	private Concessionnaire concessionnaire;

	/*@OneToMany(mappedBy="plage")
	@JsonIgnore
	private List<File> files;
	@ManyToMany
	@Size(min=0)
	@JoinTable(
			name = "plage_optionplages",
			joinColumns = @JoinColumn(name = "id_plage"),
			inverseJoinColumns = @JoinColumn(name = "id_option_plage"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"id_plage","id_option_plage"})
	)
	private List<OptionPlage> optionplages;*/

	public Plage(Long idPlage) {
		this();
		this.idPlage=idPlage;
	}
}