package com.javafee.mainform;

import javax.swing.JOptionPane;

import com.javafee.common.Constants;
import com.javafee.common.Constants.Role;
import com.javafee.common.Context;
import com.javafee.common.IActionForm;
import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarType;
import com.javafee.model.CarTypeTableModel;
import com.javafee.startform.LogInEvent;

public class Actions implements IActionForm {

	private MainForm mainForm = new MainForm();

	private com.javafee.partform.Actions actions = null;

	private CarTypeAddEvent carTypeAddEvent = null;
	private CarPartTypeAddEvent carPartTypeAddEvent = null;
	private PowerUpDataEvent powerUpDataEvent = null;
	private WorkerManagementEvent workerManagementEvent = null;
	private ApplicationStyleEvent applicationStyleEvent = null;

	public void control() {
		this.initializeForm();
		mainForm.setVisible(true);

		mainForm.getBtnManageWorkers().addActionListener(e -> onClickBtnManagerWorkers());
		mainForm.getCockpitEditionPanel().getBtnAdd().addActionListener(e -> onClickBtnAdd());
		mainForm.getCockpitEditionPanel().getBtnRemove().addActionListener(e -> onClickBtnRemove());
		mainForm.getCockpitEditionPanel().getBtnCarPartTypeAdd().addActionListener(e -> onClickBtnCarPartTypeAdd());
		mainForm.getCockpitEditionPanel().getBtnPowerUpData().addActionListener(e -> onClickBtnPowerUpData());
		mainForm.getCockpitConfirmationPanel().getBtnAccept().addActionListener(e -> onClickBtnAccept());
		mainForm.getBtnDisplayAllParts().addActionListener(e -> onClickBtnDisplayAll());
		mainForm.getBtnLogOut().addActionListener(e -> onClickBtnLogOut());
		mainForm.getComboBoxLanguage().addActionListener(e -> onChangeComboBoxLanguage());
		mainForm.getBtnChangeAppStyle().addActionListener(e -> onClickBtnChangeAppStyle());
	}

	@Override
	public void initializeForm() {
		mainForm.getComboBoxLanguage().addItem(Constants.APPLICATION_LANGUAGE_PL);
		mainForm.getComboBoxLanguage().addItem(Constants.APPLICATION_LANGUAGE_EN);
		this.setControlsVisibility();
		this.reloadLblLogInInformationDynamic();
	}

	private void setControlsVisibility() {
		mainForm.getCockpitEditionPanel().setVisible(LogInEvent.getIsAdmin());
		mainForm.getBtnManageWorkers().setVisible(LogInEvent.getIsAdmin());
		mainForm.getBtnChangeAppStyle().setEnabled(!LogInEvent.getIsAdmin());
		mainForm.getComboBoxLanguage().setEnabled(!LogInEvent.getIsAdmin());
	}

	private void reloadLblLogInInformationDynamic() {
		StringBuilder logInInformation = new StringBuilder(mainForm.getLblLogInInformation().getText());

		Role role = LogInEvent.getRole();
		String stringRole = null;
		if (role == Role.CLIENT)
			stringRole = Constants.CLIENT;
		else if (role == Role.ADMIN)
			stringRole = Constants.ROLE_ADMIN;
		else if (role == Role.WORKER_ACCOUNTANT)
			stringRole = Constants.WORKER_ACCOUNTANT;

		if (LogInEvent.getWorker() != null)
			logInInformation.append(
					LogInEvent.getWorker().getName() + " " + LogInEvent.getWorker().getSurname() + ", " + stringRole
							+ " [" + Constants.APPLICATION_DATE_TIME_FORMAT.format(LogInEvent.getLogInDate()) + "]");
		else if (LogInEvent.getClient() != null)
			logInInformation.append(
					LogInEvent.getClient().getName() + " " + LogInEvent.getClient().getSurname() + ", " + stringRole
							+ " [" + Constants.APPLICATION_DATE_TIME_FORMAT.format(LogInEvent.getLogInDate()) + "]");

		if (LogInEvent.getIsAdmin())
			logInInformation.append(stringRole);
		mainForm.getLblLogInInformation().setText(logInInformation.toString());
	}

	private void onChangeComboBoxLanguage() {
		if (Utils.displayConfirmDialog(
				SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.languageChange"),
				"") == JOptionPane.YES_OPTION) {
			Constants.APPLICATION_LANGUAGE = (String) mainForm.getComboBoxLanguage().getSelectedItem();
			SystemProperties.getInstance()
					.setResourceBundleLanguage((String) mainForm.getComboBoxLanguage().getSelectedItem());

			com.javafee.hibernate.dto.common.SystemProperties systemProperties = LogInEvent.getSystemProperties();
			systemProperties.setLanguage((String) mainForm.getComboBoxLanguage().getSelectedItem());

			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(systemProperties);
			HibernateUtil.commitTransaction();

			onClickBtnLogOut();
		}
	}

	private void onClickBtnChangeAppStyle() {
		Params.getInstance().add("MAIN_FORM", mainForm);
		if (applicationStyleEvent == null)
			applicationStyleEvent = new ApplicationStyleEvent();
		applicationStyleEvent.control();
	}

	private void onClickBtnLogOut() {
		LogInEvent.clearLogInData();
		mainForm.dispose();
		mainForm = null;
		openStartForm();
	}

	private void onClickBtnManagerWorkers() {
		if (workerManagementEvent == null)
			workerManagementEvent = new WorkerManagementEvent();
		workerManagementEvent.control();
	}

	private void onClickBtnAdd() {
		if (carTypeAddEvent == null)
			carTypeAddEvent = new CarTypeAddEvent();
		carTypeAddEvent.control((CarTypeTableModel) mainForm.getCarTypeTablePanel().getTable().getModel());
	}

	private void onClickBtnCarPartTypeAdd() {
		if (carPartTypeAddEvent == null) {
			if (mainForm.getCarTypeTablePanel().getTable().getSelectedRow() != -1) {
				int selectedRowIndex = mainForm.getCarTypeTablePanel().getTable()
						.convertRowIndexToModel(mainForm.getCarTypeTablePanel().getTable().getSelectedRow());
				CarType selectedCarType = ((CarTypeTableModel) mainForm.getCarTypeTablePanel().getTable().getModel())
						.getCarType(selectedRowIndex);
				Params.getInstance().add("selectedCarType", selectedCarType);
			}
			carPartTypeAddEvent = new CarPartTypeAddEvent();
		}
		carPartTypeAddEvent.control();
	}

	private void onClickBtnPowerUpData() {
		if (powerUpDataEvent == null)
			powerUpDataEvent = new PowerUpDataEvent();
		powerUpDataEvent.control((CarTypeTableModel) mainForm.getCarTypeTablePanel().getTable().getModel(), null);
	}

	private void onClickBtnRemove() {
		if (mainForm.getCarTypeTablePanel().getTable().getSelectedRow() != -1) {
			int selectedRowIndex = mainForm.getCarTypeTablePanel().getTable()
					.convertRowIndexToModel(mainForm.getCarTypeTablePanel().getTable().getSelectedRow());

			if (Utils.displayConfirmDialog(
					SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.deleteMessage"),
					"") == JOptionPane.YES_OPTION) {
				if (selectedRowIndex != -1) {
					CarType selectedCarType = ((CarTypeTableModel) mainForm.getCarTypeTablePanel().getTable()
							.getModel()).getCarType(selectedRowIndex);

					HibernateUtil.beginTransaction();
					HibernateUtil.getSession().delete(selectedCarType);
					HibernateUtil.commitTransaction();

					((CarTypeTableModel) mainForm.getCarTypeTablePanel().getTable().getModel()).reloadData();
				}
			}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("actions.notSelectedCarTypeWarningTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("actions.notSelectedCarTypeWarning"));
		}
	}

	private void onClickBtnAccept() {
		if (mainForm.getCarTypeTablePanel().getTable().getSelectedRow() != -1) {
			int selectedRowIndex = mainForm.getCarTypeTablePanel().getTable()
					.convertRowIndexToModel(mainForm.getCarTypeTablePanel().getTable().getSelectedRow());
			CarType selectedCarType = ((CarTypeTableModel) mainForm.getCarTypeTablePanel().getTable().getModel())
					.getCarType(selectedRowIndex);
			int carTypePartsQuantity = HibernateUtil.getSession().getNamedQuery("CarPartType.checkIfCarPartsExists")
					.setParameter("idCarType", selectedCarType.getIdCarType()).list().size();
			if (carTypePartsQuantity > 0) {
				Context.getInstance().add("ALL_PARTS", false);
				Params.getInstance().add("selectedCarType", selectedCarType);

				if (actions == null)
					actions = new com.javafee.partform.Actions();
				this.actions.control();
			} else {
				LogGuiException.logWarning(
						SystemProperties.getInstance().getResourceBundle()
								.getString("actions.carTypePartsNotDefinedTitle"),
						SystemProperties.getInstance().getResourceBundle().getString("actions.carTypePartsNotDefined"));
			}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("actions.notSelectedCarTypeWarningTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("actions.notSelectedCarTypeWarning"));
		}
	}

	private void onClickBtnDisplayAll() {
		if (actions == null)
			actions = new com.javafee.partform.Actions();
		Context.getInstance().add("ALL_PARTS", true);
		this.actions.control();
	}

	private void openStartForm() {
		com.javafee.startform.Actions actions = new com.javafee.startform.Actions();
		actions.control();
	}

}
