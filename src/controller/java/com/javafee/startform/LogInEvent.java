package com.javafee.startform;

import java.util.Date;

import com.javafee.common.Common;
import com.javafee.common.Constants.Role;
import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.exception.RefusedLogInException;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.cardealer.Client;
import com.javafee.hibernate.dto.cardealer.Worker;
import com.javafee.hibernate.dto.common.UserData;

import lombok.Getter;

public final class LogInEvent {
	@Getter
	private static LogInEvent logInEvent = null;
	@Getter
	private static Role role;
	@Getter
	private static com.javafee.hibernate.dto.common.SystemProperties systemProperties = null;
	@Getter
	private static Client client;
	@Getter
	private static Worker worker;
	@Getter
	private static Boolean isAdmin;
	@Getter
	private static Date logInDate;

	public enum LogInFailureCause {
		NOT_REGISTERED, BAD_PASSWORD, NO_USER
	}

	private LogInEvent() {
	}

	public static LogInEvent getInstance(String login, String password) throws RefusedLogInException {
		if (checkLogAndRole(login, password)) {
			logInEvent = new LogInEvent();
			logInDate = new Date();
		} else
			throw new RefusedLogInException("Cannot log in to the system");
		return logInEvent;
	}

	public Object getUser() {
		Object user = null;
		if (role == Role.CLIENT)
			user = client;
		else if (role == Role.WORKER_ACCOUNTANT || role == Role.WORKER)
			user = worker;

		return user;
	}

	private static boolean checkLogAndRole(String login, String password) {
		boolean result = false;

		worker = (Worker) HibernateUtil.getSession().getNamedQuery("Worker.checkIfWorkerLoginExist")
				.setParameter("login", login).uniqueResult();
		client = (Client) HibernateUtil.getSession().getNamedQuery("Client.checkIfClientLoginExist")
				.setParameter("login", login).uniqueResult();
		isAdmin = Common.isAdmin(login, password);

		if (worker != null) {
			if (checkLoginAndPassword(password)) {
				if (worker.getRegistered()) {
					initializeSystemProperties(worker.getIdUserData(), false);
					result = true;
				} else
					Params.getInstance().add("NOT_REGISTERED", LogInFailureCause.NOT_REGISTERED);
			}
		} else if (worker == null && isAdmin) {
			role = Role.ADMIN;
			result = true;
		} else if (client != null && worker == null) {
			if (checkLoginAndPassword(password)) {
				if (client.getRegistered()) {
					initializeSystemProperties(client.getIdUserData(), false);
					result = true;
				} else
					Params.getInstance().add("NOT_REGISTERED", LogInFailureCause.NOT_REGISTERED);
			}
		} else {
			Params.getInstance().add("NO_USER", LogInFailureCause.NO_USER);
		}

		return result;
	}

	private static void initializeSystemProperties(Integer idUserData, boolean isAdmin) {
		if (!isAdmin) {
			systemProperties = (com.javafee.hibernate.dto.common.SystemProperties) HibernateUtil.getSession()
					.getNamedQuery("SystemProperties.checkIfExistsForUser").setParameter("idUserData", idUserData)
					.uniqueResult();

			if (systemProperties != null) {
				SystemProperties.getInstance().setResourceBundleLanguage(LogInEvent.systemProperties.getLanguage());
			} else {
				com.javafee.hibernate.dto.common.SystemProperties systemProperties = new com.javafee.hibernate.dto.common.SystemProperties();
				HibernateDao<UserData, Integer> dao = new HibernateDao<>(UserData.class);
				UserData userData = dao.findByPrimaryKey(idUserData);
				systemProperties.setUserData(userData);
				systemProperties.setLanguage("en");

				HibernateUtil.beginTransaction();
				HibernateUtil.getSession().save(systemProperties);
				HibernateUtil.commitTransaction();

				LogInEvent.systemProperties = systemProperties;

				SystemProperties.getInstance().setResourceBundleLanguage(LogInEvent.systemProperties.getLanguage());
			}
		}
	}

	private static boolean checkLoginAndPassword(String password) {
		boolean result = false;
		String md5 = Common.createMd5(password);

		if (client != null && md5.equals(client.getPassword()))
			result = true;
		else if (worker != null && md5.equals(worker.getPassword()))
			result = true;

		if (!result && worker != null)
			Params.getInstance().add("BAD_PASSWORD", LogInFailureCause.BAD_PASSWORD);
		if (!result && client != null)
			Params.getInstance().add("BAD_PASSWORD", LogInFailureCause.BAD_PASSWORD);

		return result;
	}

	public static void clearLogInData() {
		LogInEvent.role = null;
		LogInEvent.logInDate = null;
	}
}
