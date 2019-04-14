package com.javafee.common;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

public final class Constants {

	public enum WorkerTableColumn {
		COL_PESEL_NUMBER(0), COL_DOCUMENT_NUMBER(1), COL_LOGIN(2), COL_E_MAIL(3), COL_NAME(4), COL_SURNAME(5),
		COL_ADDRESS(6), COL_CITY(7), COL_SEX(8), COL_BIRTH_DATE(9), COL_REGISTERED(10);

		private final Integer value;

		WorkerTableColumn(final int newValue) {
			value = newValue;
		}

		public Integer getValue() {
			return value;
		}

		public static WorkerTableColumn getByNumber(int clientTableSelectedIndex) {
			return Stream.of(WorkerTableColumn.values())
					.filter(item -> item.getValue().equals(clientTableSelectedIndex)).findFirst().get();
		}
	}

	public enum CarTypeTableColumn {
		COL_MANUFACTURER(0), COL_MODEL(1), COL_FUEL_KIND(2), COL_POWER(3), COL_ENGINE(4);

		private final Integer value;

		CarTypeTableColumn(final int newValue) {
			value = newValue;
		}

		public Integer getValue() {
			return value;
		}

		public static CarTypeTableColumn getByNumber(int carTypeTableSelectedIndex) {
			return Stream.of(CarTypeTableColumn.values())
					.filter(item -> item.getValue().equals(carTypeTableSelectedIndex)).findFirst().get();
		}
	}

	public enum PartTypeTableColumn {
		COL_NAME(0), COL_CAR_PART_TYPE_NAME(1), COL_CAR_TYPE(2), COL_CAR_PART_TYPE(1);

		private final Integer value;

		PartTypeTableColumn(final int newValue) {
			value = newValue;
		}

		public Integer getValue() {
			return value;
		}

		public static PartTypeTableColumn getByNumber(int partTypeTableSelectedIndex) {
			return Stream.of(PartTypeTableColumn.values())
					.filter(item -> item.getValue().equals(partTypeTableSelectedIndex)).findFirst().get();
		}
	}

	public enum CarPartTypeTableColumn {
		COL_NAME(0), COL_DESCRIPTION(1), COL_ORIGIN(2), COL_PRODUCER(3), COL_CAR_TYPE(4);

		private final Integer value;

		CarPartTypeTableColumn(final int newValue) {
			value = newValue;
		}

		public Integer getValue() {
			return value;
		}

		public static CarPartTypeTableColumn getByNumber(int carPartTypeTableSelectedIndex) {
			return Stream.of(CarPartTypeTableColumn.values())
					.filter(item -> item.getValue().equals(carPartTypeTableSelectedIndex)).findFirst().get();
		}
	}

	public enum Role {
		ADMIN, WORKER_ACCOUNTANT, WORKER, CLIENT
	}

	public enum Context {
		ADDITION, MODIFICATION, CANCELED, LOAN, READING_ROOM
	}

	public static final String APPLICATION_NAME = "e-catalogue";
	public static String APPLICATION_LANGUAGE = "pl";
	public static final String APPLICATION_LANGUAGE_PL = "pl";
	public static final String APPLICATION_LANGUAGE_EN = "en";
	public static final Object APPLICATION_COMBO_BOX_BLANK_OBJECT = null;
	public static final SimpleDateFormat APPLICATION_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	public static final SimpleDateFormat APPLICATION_DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	public static final Integer APPLICATION_MIN_PASSWORD_LENGTH = 8;
	public static final Integer APPLICATION_MAX_PASSWORD_LENGTH = 16;
	public static final Integer APPLICATION_GENERATE_PASSWORD_LENGTH = 16;
	public static final String APPLICATION_XLSX_EXTENSION = ".xlsx";
	public static final String APPLICATION_XLS_EXTENSION = ".xlsx";
	public static final String APPLICATION_XLS_XLSX_EXTENSION_DESCRIPTION = "xlsx, xls file";
	public static final String APPLICATION_XLS_XLSX_FILE_LABEL_TEXT = SystemProperties.getInstance().getResourceBundle()
			.getString("constans.APPLICATION_XLS_XLSX_FILE_LABEL_TEXT");

	public static final Dimension START_FORM_MINIMUM_SIZE = new Dimension(300, 200);

	public static final String ROLE_ADMIN = SystemProperties.getInstance().getResourceBundle()
			.getString("constans.ROLE_ADMIN");
	public static final String WORKER_ACCOUNTANT = SystemProperties.getInstance().getResourceBundle()
			.getString("constans.WORKER_ACCOUNTANT");
	public static final String CLIENT = SystemProperties.getInstance().getResourceBundle().getString("constans.CLIENT");

	public static final String LANGUAGE_RESOURCE_BUNDLE = "messages";

	public static final Character DATA_BASE_MALE_SIGN = 'M';
	public static final Character DATA_BASE_FEMALE_SIGN = 'F';
	public static final Boolean DATA_BASE_REGISTER_DEFAULT_FLAG = false;
	public static final String DATA_BASE_ADMIN_LOGIN = "admin";
	public static final String DATA_BASE_ADMIN_PASSWORD = "192023a7bbd73250516f069df18b500";
	public static final String DATA_BASE_RESET_PASSWORD = "9ed62c5de38c30878855eb50eec488ad";
	public static final Integer DATA_BASE_PESEL_NUMBER_LENGHT = 11;

}
