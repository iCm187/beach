package fr.hb.ibm.beach.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.hb.ibm.beach.annotation.DateDebutEgaleOuAvantDateFin;
import fr.hb.ibm.beach.business.Reservation;

public class DateDebutEgaleOuAvantDateFinValidator
		implements ConstraintValidator<DateDebutEgaleOuAvantDateFin, Reservation> {

	@Override
	public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
		return (reservation.getDateDebut().equals(reservation.getDateFin())
				|| reservation.getDateDebut().isBefore(reservation.getDateFin()));
	}

}