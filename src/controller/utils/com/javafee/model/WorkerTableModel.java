package com.javafee.model;

import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.javafee.common.Constants;
import com.javafee.common.Constants.WorkerTableColumn;
import com.javafee.common.SystemProperties;
import com.javafee.common.Validator;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.City;
import com.javafee.hibernate.dto.cardealer.Worker;

public class WorkerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Worker> workers;
	private String[] columns;

	private HibernateDao<Worker, Integer> clientDao;

	public WorkerTableModel() {
		super();
		this.prepareHibernateDao();
		this.columns = new String[]{
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.peselNumberCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.documentNumberCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.loginCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.eMailCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.nameCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.surnameCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.addressCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.cityCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.sexCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.birthDateCol"),
				SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.registeredCol")};
	}

	public Worker getWorker(int index) {
		return workers.get(index);
	}

	public void setWorker(int index, Worker worker) {
		workers.set(index, worker);
	}

	public void add(Worker worker) {
		workers.add(worker);
		this.fireTableDataChanged();
	}

	public void remove(Worker worker) {
		workers.remove(worker);
		this.fireTableDataChanged();
	}

	private void prepareHibernateDao() {
		this.clientDao = new HibernateDao<Worker, Integer>(Worker.class);
		this.workers = clientDao.findAll();
	}

	private void executeUpdate(String entityName, Object object) {
		HibernateUtil.beginTransaction();
		HibernateUtil.getSession().update(entityName, object);
		HibernateUtil.commitTransaction();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return workers.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Worker worker = workers.get(row);
		switch (WorkerTableColumn.getByNumber(col)) {
			case COL_PESEL_NUMBER:
				return worker.getPeselNumber();
			case COL_DOCUMENT_NUMBER:
				return worker.getDocumentNumber();
			case COL_LOGIN:
				return worker.getLogin();
			case COL_E_MAIL:
				return worker.getEMail();
			case COL_NAME:
				return worker.getName();
			case COL_SURNAME:
				return worker.getSurname();
			case COL_ADDRESS:
				return worker.getAddress();
			case COL_CITY:
				return worker.getCity() != null ? worker.getCity().getName() : null;
			case COL_SEX:
				if (worker.getSex() != null) {
					if (Constants.DATA_BASE_MALE_SIGN.toString().equals(worker.getSex().toString()))
						return SystemProperties.getInstance().getResourceBundle()
								.getString("clientTableModel.registeredMaleVal");
					else if (Constants.DATA_BASE_FEMALE_SIGN.toString().equals(worker.getSex().toString()))
						return SystemProperties.getInstance().getResourceBundle()
								.getString("clientTableModel.registeredFemaleVal");
				} else
					return null;
			case COL_BIRTH_DATE:
				return worker.getBirthDate() != null ? Constants.APPLICATION_DATE_FORMAT.format(worker.getBirthDate())
						: null;
			case COL_REGISTERED:
				return worker.getRegistered()
						? SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.registeredTrueVal")
						: SystemProperties.getInstance().getResourceBundle()
						.getString("clientTableModel.registeredFalseVal");
			default:
				return null;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		Worker worker = workers.get(row);
		Worker workerShallowClone = (Worker) worker.clone();

		switch (WorkerTableColumn.getByNumber(col)) {
			case COL_PESEL_NUMBER:
				workerShallowClone.setPeselNumber(value.toString());
				break;
			case COL_DOCUMENT_NUMBER:
				workerShallowClone.setDocumentNumber(value.toString());
				break;
			case COL_LOGIN:
				workerShallowClone.setLogin(value.toString());
				break;
			case COL_E_MAIL:
				workerShallowClone.setEMail(value.toString());
				break;
			case COL_NAME:
				workerShallowClone.setName(value.toString());
				break;
			case COL_SURNAME:
				workerShallowClone.setSurname(value.toString());
				break;
			case COL_ADDRESS:
				workerShallowClone.setAddress(value.toString());
				break;
			case COL_CITY:
				workerShallowClone.setCity((City) value);
				break;
			case COL_SEX:
				workerShallowClone.setSex((Character) value);
				break;
			case COL_BIRTH_DATE:
				workerShallowClone.setBirthDate((Date) value);
				break;
			case COL_REGISTERED:
				workerShallowClone.setRegistered((Boolean) value);
				break;
		}

		if (Validator.validateClientUpdate(workerShallowClone)) {
			executeUpdate(Worker.class.getName(), worker);
			workers.set(row, workerShallowClone);
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("clientTableModel.constraintViolationErrorTitle"),
					SystemProperties.getInstance().getResourceBundle()
							.getString("clientTableModel.constraintViolationError"));
		}

		this.fireTableRowsUpdated(row, row);
	}

	@Override
	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void reloadData() {
		prepareHibernateDao();
		fireTableDataChanged();
	}

	@Override
	public void fireTableChanged(TableModelEvent e) {
		super.fireTableChanged(e);
	}
}
