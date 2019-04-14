package com.javafee.model;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.javafee.common.Constants.CarTypeTableColumn;
import com.javafee.common.SystemProperties;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarType;

public class CarTypeTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	protected List<CarType> carTypes;
	private String[] columns;

	protected HibernateDao<CarType, Integer> carTypesDao;

	public CarTypeTableModel() {
		super();
		this.prepareHibernateDao();
		this.columns = new String[]{
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.manufacturerCol"),
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.modelCol"),
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.fuelKindCol"),
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.powerCol"),
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.engineCol")};
	}

	public CarType getCarType(int index) {
		return carTypes.get(index);
	}

	public void setCarType(int index, CarType car) {
		carTypes.set(index, car);
	}

	public void add(CarType car) {
		carTypes.add(car);
		this.fireTableDataChanged();
	}

	public void remove(CarType car) {
		carTypes.remove(car);
		this.fireTableDataChanged();
	}

	protected void prepareHibernateDao() {
		this.carTypesDao = new HibernateDao<CarType, Integer>(CarType.class);
		this.carTypes = carTypesDao.findAll();
		// TODO Tip - filtrowanie w modelu
		// this.cars = cars.stream().filter(vol ->
		// !vol.getIsSold()).collect(Collectors.toList());
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
		return carTypes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		CarType car = carTypes.get(row);
		switch (CarTypeTableColumn.getByNumber(col)) {
			case COL_MANUFACTURER:
				return car.getManufacturer();
			case COL_MODEL:
				return car.getModel();
			case COL_FUEL_KIND:
				return car.getFuelKind();
			case COL_POWER:
				return car.getPower();
			case COL_ENGINE:
				return car.getEngine();
			default:
				return null;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		CarType car = carTypes.get(row);
		CarType carShallowClone = (CarType) car.clone();
		switch (CarTypeTableColumn.getByNumber(col)) {
			case COL_MANUFACTURER:
				carShallowClone.setManufacturer(value.toString());
				break;
			case COL_MODEL:
				carShallowClone.setModel(value.toString());
				break;
			case COL_FUEL_KIND:
				carShallowClone.setFuelKind(value.toString());
				break;
			case COL_POWER:
				carShallowClone.setPower((Integer) value);
				break;
			case COL_ENGINE:
				carShallowClone.setEngine((Double) value);
				break;
		}

		executeUpdate(CarType.class.getName(), car);
		carTypes.set(row, carShallowClone);

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
