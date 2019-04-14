package com.javafee.startform;

import java.util.Date;

import com.javafee.common.Common;
import com.javafee.common.Constants;
import com.javafee.common.Constants.Role;
import com.javafee.common.Params;
import com.javafee.exception.RefusedRegistrationException;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.City;
import com.javafee.hibernate.dto.cardealer.Client;
import com.javafee.hibernate.dto.cardealer.Worker;
import com.javafee.hibernate.dto.common.UserData;

import lombok.Getter;

public class RegistrationEvent {
	private static RegistrationEvent registrationEvent = null;
	@Getter
	private static Date registrationDate;

	public static UserData userData = null;

	public enum RegistrationFailureCause {
		ALREADY_REGISTERED, WEAK_PASSWORD, INCORRECT_BIRTH_DATE
	}

	private RegistrationEvent() {
	}

	public static RegistrationEvent getInstance(String peselNumber, String documentNumber, String name, String surname,
	                                            String address, City city, Character sex, Date birthDate, String login, String eMail, String password,
	                                            Role role) throws RefusedRegistrationException {
		if (checkRegistration(login, password, peselNumber, role)) {
			registrationEvent = new RegistrationEvent();
			registrationDate = new Date();
			RegistrationEvent.userData = createUser(peselNumber, documentNumber, name, surname, address, city, sex,
					birthDate, login, eMail, password, role);
		} else
			throw new RefusedRegistrationException("Cannot register to the system");
		return registrationEvent;
	}

	public static void forceClearRegistrationEvenet() {
		registrationEvent = null;
	}

	private static boolean checkRegistration(String login, String password, String peselNumber, Role role) {
		boolean result = false;
		Worker worker = null;
		switch (role) {
			case WORKER:
				worker = (Worker) HibernateUtil.getSession().getNamedQuery("Worker.checkIfWorkerLoginExist")
						.setParameter("login", login).uniqueResult();

				if (worker != null)
					Params.getInstance().add("ALREADY_REGISTERED", RegistrationFailureCause.ALREADY_REGISTERED);
				else {
					if (Common.checkPasswordStrenght(password))
						result = true;
					else
						Params.getInstance().add("WEAK_PASSWORD", RegistrationFailureCause.WEAK_PASSWORD);
				}
			case ADMIN:
				worker = (Worker) HibernateUtil.getSession().getNamedQuery("Worker.checkIfWorkerLoginExist")
						.setParameter("login", login).uniqueResult();

				if (worker != null)
					Params.getInstance().add("ALREADY_REGISTERED", RegistrationFailureCause.ALREADY_REGISTERED);
				else {
					if (Common.checkPasswordStrenght(password))
						result = true;
					else
						Params.getInstance().add("WEAK_PASSWORD", RegistrationFailureCause.WEAK_PASSWORD);
				}
				break;
			case CLIENT:
				Client client = (Client) HibernateUtil.getSession().getNamedQuery("Client.checkIfClientLoginExist")
						.setParameter("login", login).uniqueResult();
				if (client != null)
					Params.getInstance().add("ALREADY_REGISTERED", RegistrationFailureCause.ALREADY_REGISTERED);
				else {
					if (Common.checkPasswordStrenght(password))
						result = true;
					else
						Params.getInstance().add("WEAK_PASSWORD", RegistrationFailureCause.WEAK_PASSWORD);
				}
				break;
			default:
				break;
		}

		return result;
	}

	private static UserData createUser(String peselNumber, String documentNumber, String name, String surname,
	                                   String address, City city, Character sex, Date birthDate, String login, String eMail, String password,
	                                   Role role) {
		HibernateUtil.beginTransaction();
		UserData resultUserData = null;
		Worker worker = null;
		switch (role) {
			case WORKER:
				worker = new Worker();
				worker.setPeselNumber(peselNumber);
				worker.setDocumentNumber(documentNumber);
				worker.setName(name);
				worker.setSurname(surname);
				worker.setAddress(address);
				worker.setCity(city);
				worker.setSex(sex);
				worker.setBirthDate(birthDate);
				worker.setLogin(login);
				worker.setEMail(eMail);
				worker.setPassword(Common.createMd5(password));
				worker.setRegistered(Constants.DATA_BASE_REGISTER_DEFAULT_FLAG);
				HibernateUtil.getSession().save(worker);
				HibernateUtil.commitTransaction();

				HibernateUtil.beginTransaction();
				resultUserData = worker;
				break;
			case ADMIN:
				worker = new Worker();
				worker.setPeselNumber(peselNumber);
				worker.setDocumentNumber(documentNumber);
				worker.setName(name);
				worker.setSurname(surname);
				worker.setAddress(address);
				worker.setCity(city);
				worker.setSex(sex);
				worker.setBirthDate(birthDate);
				worker.setLogin(login);
				worker.setEMail(eMail);
				worker.setPassword(Common.createMd5(password));
				worker.setRegistered(true);
				HibernateUtil.getSession().save(worker);
				HibernateUtil.commitTransaction();

				HibernateUtil.beginTransaction();
				resultUserData = worker;
				break;
			case CLIENT:
				Client client = new Client();
				client.setPeselNumber(peselNumber);
				client.setDocumentNumber(documentNumber);
				client.setName(name);
				client.setSurname(surname);
				client.setAddress(address);
				client.setCity(city);
				client.setSex(sex);
				client.setBirthDate(birthDate);
				client.setLogin(login);
				client.setEMail(eMail);
				client.setPassword(Common.createMd5(password));
				client.setRegistered(true);
				HibernateUtil.getSession().save(client);
				resultUserData = client;
				break;
			default:
				break;
		}

		HibernateUtil.commitTransaction();
		return resultUserData;
	}

}
