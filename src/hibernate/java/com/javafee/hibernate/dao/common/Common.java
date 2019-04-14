package com.javafee.hibernate.dao.common;

import java.util.Optional;

import org.hibernate.Session;

import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.cardealer.Client;
import com.javafee.hibernate.dto.common.UserData;

public class Common {
	public static Optional<Client> findClientById(final int id) {
		Optional<Client> client = Optional.empty();

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			client = Optional.ofNullable(session.get(Client.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return client;
	}

	public static Optional<UserData> findUserDataById(final int id) {
		Optional<UserData> userData = Optional.empty();

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			userData = Optional.ofNullable(session.get(UserData.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userData;
	}

}
