package fr.hb.ibm.beach.initialisation;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import fr.hb.ibm.beach.business.Client;
import fr.hb.ibm.beach.business.Concessionnaire;
import fr.hb.ibm.beach.business.File;
import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.business.Parasol;
import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.business.Reservation;
import fr.hb.ibm.beach.business.Statut;
import fr.hb.ibm.beach.dao.ClientDao;
import fr.hb.ibm.beach.dao.ConcessionnaireDao;
import fr.hb.ibm.beach.dao.FileDao;
import fr.hb.ibm.beach.dao.LienDeParenteDao;
import fr.hb.ibm.beach.dao.ParasolDao;
import fr.hb.ibm.beach.dao.PaysDao;
import fr.hb.ibm.beach.dao.ReservationDao;
import fr.hb.ibm.beach.dao.StatutDao;
import lombok.AllArgsConstructor;

//Demande a Spring d'instancier cet objet et de placer cette instance dans son conteneur IOC
@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

	// on déclare les dépendances au sens Spring de la classe d'ajout
	// Spring va instantier ces objets à notre place
	private FileDao fileDao;
	private ParasolDao parasolDao;
	private PaysDao paysDao;
	private LienDeParenteDao lienDeParenteDao;
	private ClientDao clientDao;
	private ConcessionnaireDao concessionnaireDao;
	private ReservationDao reservationDao;
	private StatutDao statutDao;
	private PasswordEncoder passwordEncoder;
	// private PlageDao plageDao;

	@Override
	public void run(String... args) throws Exception {

		// ajouterPlages();
		ajouterFiles();
		ajouterParasols();
		ajouterPays();
		ajouterLiensDeParente();
		ajouterClients();
		ajouterStatuts();
		ajouterConcessionnaire();
		int nbReservationsAAjouter = 200;
		ajouterReservations(nbReservationsAAjouter);
	}

	/*
	 * private void ajouterPlages() { if (plageDao.count() == 0) { Concessionnaire
	 * concessionnaire = concessionnaireDao.findByEmail("peppe@humanbooster.fr");
	 * List<File> files = fileDao.findAll().subList(0, 4);
	 * 
	 * List<String> nomsPlages = Arrays.asList( "Plage de la Salis",
	 * "Plage de la Garoupe", "Plage de la Baie des Milliardaires",
	 * "Plage de la Croisette", "Plage du Midi", "Plage de la Bocca",
	 * "Plage de la Napoule", "Plage de Pampelonne", "Plage de Paloma",
	 * "Plage de la Mala" );
	 * 
	 * for (String nomPlage : nomsPlages) { Plage plage = new Plage();
	 * plage.setNom(nomPlage); plage.setConcessionnaire(concessionnaire);
	 * plage.setFiles(files);
	 * 
	 * plageDao.save(plage); } } }
	 */

	private void ajouterFiles() {
		for (int i = 1; i <= 8; i++) {
			File file = new File();
			file.setNumero((byte) i);
			file.setPrixJournalier(10.0 * (9 - i));
			fileDao.save(file);
		}
	}

	public void ajouterParasols() {
		if (parasolDao.count() == 0) {
			for (File file : fileDao.findAll()) {
				for (byte p = 1; p <= 36; p++) {
					Parasol parasol = new Parasol();
					parasol.setNumEmplacement(p);
					parasol.setFile(file);
					parasolDao.save(parasol);
				}
			}
		}
	}

	private void ajouterPays() {
		if (paysDao.count() == 0) {
			paysDao.saveAll(Arrays.asList(new Pays("FR", "France"), new Pays("IT", "Italie"),
					new Pays("GB", "Royaume-Uni"), new Pays("PT", "Portugal"), new Pays("ES", "Espagne"),
					new Pays("DE", "Allemagne"), new Pays("US", "États-Unis"), new Pays("CA", "Canada"),
					new Pays("JP", "Japon"), new Pays("CN", "Chine"), new Pays("BR", "Brésil"), new Pays("IN", "Inde"),
					new Pays("AU", "Australie"), new Pays("ZA", "Afrique du Sud"), new Pays("AR", "Argentine"),
					new Pays("MX", "Mexique")));
		}
	}

	private void ajouterLiensDeParente() {
		if (lienDeParenteDao.count() == 0) {
			lienDeParenteDao.save(new LienDeParente("Frère/Soeur", 0.5f));
			lienDeParenteDao.save(new LienDeParente("Cousin/Cousine", 0.75f));
			lienDeParenteDao.save(new LienDeParente("Aucun", 1f));
		}
	}

	private void ajouterClients() {
		if (clientDao.count() == 0) {
			List<Pays> pays = paysDao.findAll();
			List<LienDeParente> liensDeParente = lienDeParenteDao.findAll();

			List<String> nomsPrenomsNaruto = Arrays.asList("Itachi Uchiwa", "Kabuto Yakushi", "Sasuke Uchiwa",
					"Madara Uchiwa", "Obito Uchiwa", "Kurenaï Yûhi", "Hinata Hyûga", "Kiba Inuzuka", "Shino Aburame",
					"Kakashi Hatake", "Naruto Uzumaki", "Sakura Haruno", "Asuma Sarutobi", "Chôji Akimichi",
					"Ino Yamanaka", "Shikamaru Nara", "Gaï Maito", "Neji Hyûga", "Rock Lee", "Hashirama Senju",
					"Tobirama Senju", "Hiruzen Sarutobi", "Minato Namikaze", "Danzô Shimura");

			Random random = new Random();

			for (String nomPrenom : nomsPrenomsNaruto) {
				String[] parts = nomPrenom.split(" ");
				String nom = parts[1];
				String prenom = parts[0];
				Pays unPays = pays.get(random.nextInt(pays.size()));
				LienDeParente lienDeParente = liensDeParente.get(random.nextInt(liensDeParente.size()));
				String email = nom.toLowerCase() + "." + prenom.toLowerCase() + "@konoha.com";

				Client client = new Client();
				client.setNom(nom);
				client.setPrenom(prenom);
				client.setEmail(email);
				client.setMotDePasse(passwordEncoder.encode("password"));
				client.setPays(unPays);
				client.setLienDeParente(lienDeParente);

				clientDao.save(client);
			}
		}
	}

	public void ajouterClients(int nbClientsAAjouter) {
		if (clientDao.count() == 0) {
			ajouterClientsSansCondition(nbClientsAAjouter);
		}
	}

	public void ajouterClientsSansCondition(int nbClientsAAjouter) {
		List<Pays> pays = paysDao.findAll();
		LienDeParente lienDeParenteAucun = lienDeParenteDao.findByNom("Aucun");
		Map<String, Client> map = new HashMap<>();
		Random random = new Random();
		Faker faker = new Faker(Locale.FRENCH);

		while (map.size() != nbClientsAAjouter) {
			Date dateDebut = Date.from(LocalDate.of(2020, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date dateFin = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Instant instantAleatoire = faker.date().between(dateDebut, dateFin).toInstant();
			LocalDateTime dateAleatoire = LocalDateTime.ofInstant(instantAleatoire, ZoneId.systemDefault());
			Client client = Client.builder().nom(faker.name().lastName()).prenom(faker.name().firstName())
					.pays(pays.get(random.nextInt(pays.size())))
					.email(faker.internet().emailAddress().replaceAll(" ", "")).lienDeParente(lienDeParenteAucun)
					.motDePasse(passwordEncoder.encode("12345678")).build();
			client.setDateHeureInscription(dateAleatoire);
			map.put(client.getEmail(), client);
		}
		clientDao.saveAll(map.values());
	}

	private void ajouterConcessionnaire() {
		if (concessionnaireDao.count() == 0) {
			Faker faker = new Faker(new Locale("fr-FR"));
			Concessionnaire concessionnaire = new Concessionnaire();
			concessionnaire.setNom("ROSSI");
			concessionnaire.setPrenom("Giuseppe");
			concessionnaire.setEmail("peppe@humanbooster.fr");
			concessionnaire.setMotDePasse(passwordEncoder.encode("12345678"));
			concessionnaire.setNumeroDeTelephone(faker.phoneNumber().cellPhone());
			concessionnaireDao.save(concessionnaire);
		}
	}

	private void ajouterReservations(int nbReservationsAAjouter) {
		if (nbReservationsAAjouter <= 0) {
			return;
		}

		List<Client> clients = clientDao.findAll();
		List<Parasol> parasols = parasolDao.findAll();
		Random random = new Random();
		Statut statutEnAttente = statutDao.findByNom("en attente de traitement");

		for (int i = 0; i < nbReservationsAAjouter; i++) {
			LocalDate dateDebut = LocalDate.of(2020, random.nextInt(3) + 6, random.nextInt(30) + 1);
			LocalDate dateFin = dateDebut;
			Reservation reservation = Reservation.builder().client(clients.get(random.nextInt(clients.size())))
					.parasols(Arrays.asList(parasols.get(random.nextInt(parasols.size())))).dateDebut(dateDebut)
					.dateFin(dateFin).statut(statutEnAttente).build();
			reservationDao.save(reservation);
		}
	}

	private void ajouterStatuts() {
		if (statutDao.count() == 0) {
			statutDao.saveAll(Arrays.asList(new Statut("en attente de traitement"), new Statut("acceptée"),
					new Statut("refusée")));
		}
	}

//	@SuppressWarnings("unused")
//	private void afficherStatistiques() {
//
//		clientDao.findSpanishCustomers().forEach(System.out::println);
//
//		clientDao.findCustomersHavingNameStartingWithA().forEach(System.out::println);
//
//		clientDao.findByPays(paysDao.findAll().get(0)).forEach(System.out::println);
//
//		clientDao.findNbInscrits().forEach(System.out::println);
//
//		clientDao.findClientsHavingFirstNameAlexisAndLivingInFrance().forEach(System.out::println);
//
//		clientDao.findClientsWhoRegisteredIn2023().forEach(System.out::println);
//
//		clientDao.findClientsWhoRegisteredBetween(LocalDateTime.of(2023, 2, 1, 0, 0), LocalDateTime.now())
//				.forEach(c -> System.out.println(c.getNom().toUpperCase() + " " + c.getPrenom() + ", inscription le "
//						+ c.getDateHeureInscription()));
//
//		clientDao.findNbInscrits().forEach(System.out::println);
//
//		clientDao.findClientsWhoRegisteredToday().forEach(System.out::println);
//
//		paysDao.findAllShuffled().forEach(System.out::println);
//
//		paysDao.findCountriesWithoutCustomers().forEach(System.out::println);
//
//		paysDao.findCountriesOrderedByNbOfCustomersDesc().forEach(System.out::println);
//
//		File deuxiemeFile = fileDao.findAll().get(1);
//		parasolDao.findByFile(deuxiemeFile).forEach(System.out::println);
//
//	}
}