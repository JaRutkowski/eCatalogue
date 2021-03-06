package com.javafee.common;

import java.util.List;

import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.cardealer.Client;
import com.javafee.hibernate.dto.cardealer.Worker;
import com.javafee.hibernate.dto.common.UserData;

public final class Validator {
	@SuppressWarnings("unchecked")
	public static boolean validateClientUpdate(Client client) {
		Client existingPeselClient = null;
		boolean result;

		Client existingLoginClient = (Client) HibernateUtil.getSession()
				.getNamedQuery("Client.checkWithComparingIdIfClientLoginExist").setParameter("login", client.getLogin())
				.setParameter("id", client.getIdUserData()).uniqueResult();
		List<UserData> ud = HibernateUtil.getSession().createQuery("from UserData").list();
		for (UserData u : ud) {
			if (u.getLogin().equals(client.getLogin()) && u.getIdUserData() != client.getIdUserData())
				return false;
		}

		if (existingLoginClient == null) {
			if (!"".equals(client.getPeselNumber())) {
				existingPeselClient = (Client) HibernateUtil.getSession()
						.getNamedQuery("UserData.checkWithComparingIdIfUserDataPeselExist")
						.setParameter("peselNumber", client.getPeselNumber()).setParameter("id", client.getIdUserData())
						.uniqueResult();
				result = client.getPeselNumber() == null || (existingLoginClient == null && existingPeselClient == null);
			} else
				result = existingLoginClient == null;
		} else {
			result = false;
		}

		return result;
	}

	public static boolean validateClientPesel(String pesel) {
		boolean result = true;
		if (!"".equals(pesel)) {
			Client existingPeselClient = (Client) HibernateUtil.getSession()
					.getNamedQuery("UserData.checkIfUserDataPeselExist").setParameter("peselNumber", pesel)
					.uniqueResult();
			result = existingPeselClient == null;
		}
		return result;
	}

	public static boolean validateWorkerPesel(String pesel) {
		boolean result = true;
		if (!"".equals(pesel)) {
			Worker existingPeselClient = (Worker) HibernateUtil.getSession()
					.getNamedQuery("UserData.checkIfUserDataPeselExist").setParameter("peselNumber", pesel)
					.uniqueResult();
			result = existingPeselClient == null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static boolean validateClientUpdate(Worker client) {
		Worker existingPeselClient = null;
		boolean result;

		Worker existingLoginClient = (Worker) HibernateUtil.getSession()
				.getNamedQuery("Worker.checkWithComparingIdIfClientLoginExist").setParameter("login", client.getLogin())
				.setParameter("id", client.getIdUserData()).uniqueResult();

		List<UserData> ud = HibernateUtil.getSession().createQuery("from UserData").list();
		for (UserData u : ud) {
			if (u.getLogin().equals(client.getLogin()) && u.getIdUserData() != client.getIdUserData())
				return false;
		}

		if (existingLoginClient == null) {
			if (!"".equals(client.getPeselNumber())) {
				existingPeselClient = (Worker) HibernateUtil.getSession()
						.getNamedQuery("UserData.checkWithComparingIdIfUserDataPeselExist")
						.setParameter("peselNumber", client.getPeselNumber()).setParameter("id", client.getIdUserData())
						.uniqueResult();
				result = client.getPeselNumber() == null || (existingLoginClient == null && existingPeselClient == null);
			} else
				result = existingLoginClient == null;
		} else {
			result = false;
		}

		return result;
	}
}
