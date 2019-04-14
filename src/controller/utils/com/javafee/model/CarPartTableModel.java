package com.javafee.model;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.javafee.common.Constants.CarPartTypeTableColumn;
import com.javafee.common.SystemProperties;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarPartType;
import com.javafee.hibernate.dto.association.scan.CarType;

public class CarPartTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	protected List<CarPartType> carPartType;
	private String[] columns;

	protected HibernateDao<CarPartType, Integer> carPartTypeDao;

	public CarPartTableModel() {
		super();
		this.prepareHibernateDao();
		this.columns = new String[]{
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblName"),
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblDescription"),
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblOrigin"),
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblProducer"),
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.carType")};
	}

	public CarPartType getCarPartType(int index) {
		return carPartType.get(index);
	}

	public void setCarPartType(int index, CarPartType carPartType) {
		this.carPartType.set(index, carPartType);
	}

	public void add(CarPartType carPartType) {
		this.carPartType.add(carPartType);
		this.fireTableDataChanged();
	}

	public void remove(CarPartType carPartType) {
		this.carPartType.remove(carPartType);
		this.fireTableDataChanged();
	}

	protected void prepareHibernateDao() {
		this.carPartTypeDao = new HibernateDao<CarPartType, Integer>(CarPartType.class);
		this.carPartType = carPartTypeDao.findAll();
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
		return carPartType.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		CarPartType carPartType = this.carPartType.get(row);
		switch (CarPartTypeTableColumn.getByNumber(col)) {
			case COL_NAME:
				return carPartType.getName();
			case COL_DESCRIPTION:
				return carPartType.getDescription();
			case COL_ORIGIN:
				return carPartType.getOrigin();
			case COL_PRODUCER:
				return carPartType.getProducer();
			case COL_CAR_TYPE:
				return carPartType.getCarType() != null ? carPartType.getCarType().toString() : "";
			default:
				return null;
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void setValueAt(Object value, int row, int col) {
		CarPartType carPartType = this.carPartType.get(row);
		CarPartType carPartTypeShallowClone = (CarPartType) carPartType.clone();
		switch (CarPartTypeTableColumn.getByNumber(col)) {
			case COL_NAME:
				carPartTypeShallowClone.setName(value.toString());
				break;
			case COL_DESCRIPTION:
				carPartTypeShallowClone.setDescription(value.toString());
				break;
			case COL_ORIGIN:
				carPartTypeShallowClone.setOrigin(value.toString());
				break;
			case COL_PRODUCER:
				carPartTypeShallowClone.setProducer(value.toString());
				break;
			case COL_CAR_TYPE:
				carPartTypeShallowClone.setCarType((CarType) value);
		}

		executeUpdate(CarPartType.class.getName(), carPartType);
		this.carPartType.set(row, carPartTypeShallowClone);

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
