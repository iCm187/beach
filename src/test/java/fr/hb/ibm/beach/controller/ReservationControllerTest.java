package fr.hb.ibm.beach.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc // On demande à Spring de créer et de configurer un objet
// qui va imiter ce que fait le navigateur Internet
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationControllerTest {

}
