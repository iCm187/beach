package fr.hb.ibm.beach.automate;

import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.hb.ibm.beach.initialisation.AjoutDonneesInitiales;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@DependsOn("ajoutDonneesInitiales")
public class UtilisateurAutomate {
	
	private AjoutDonneesInitiales ajoutDonneesInitiales;
	
	@Scheduled(cron = "* * * * * *")
	private void envoyerMails() {
		
		ajoutDonneesInitiales.ajouterClientsSansCondition(1);
	}
}
