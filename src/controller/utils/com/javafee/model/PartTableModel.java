package com.javafee.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.javafee.common.Constants.PartTypeTableColumn;
import com.javafee.common.Context;
import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarPartType;
import com.javafee.hibernate.dto.association.scan.Part;

import lombok.Getter;
import lombok.Setter;

public class PartTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	protected List<Part> parts;
	private String[] columns;
	@Getter
	@Setter
	private Boolean allParts = (Boolean) Context.getInstance().get("ALL_PARTS");

	protected HibernateDao<Part, Integer> partDao;

	public PartTableModel() {
		super();
		this.prepareHibernateDao();
		this.columns = new String[]{
				SystemProperties.getInstance().getResourceBundle().getString("partTableModel.nameCol"),
				SystemProperties.getInstance().getResourceBundle().getString("partTableModel.partTypeNameCol")};
		if (this.allParts) {
			String[] allPartsColumn = Arrays.copyOf(columns, columns.length + 1);
			allPartsColumn[allPartsColumn.length - 1] = SystemProperties.getInstance().getResourceBundle()
					.getString("partTableModel.carTypeCol");
			this.columns = allPartsColumn;
		}
	}

	public Part getCarType(int index) {
		return parts.get(index);
	}

	public void setPartType(int index, Part partType) {
		parts.set(index, partType);
	}

	public void add(Part part) {
		parts.add(part);
		this.fireTableDataChanged();
	}

	public void remove(Part part) {
		parts.remove(part);
		this.fireTableDataChanged();
	}

	protected void prepareHibernateDao() {
		this.partDao = new HibernateDao<Part, Integer>(Part.class);
		this.parts = partDao.findAll();
		if (!this.allParts)
			this.parts = parts.stream()
					.filter(e -> (e.getCarPartType().getCarType() == Params.getInstance().get("selectedCarType")))
					.collect(Collectors.toList());
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
		return parts.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Part part = parts.get(row);
		switch (PartTypeTableColumn.getByNumber(col)) {
			case COL_NAME:
				return part.getName();
			case COL_CAR_PART_TYPE_NAME:
				return part.getCarPartType() != null ? part.getCarPartType().getName() : "";
			case COL_CAR_TYPE:
				return part.getCarPartType() != null ? part.getCarPartType().getCarType() : "";
			default:
				return null;
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void setValueAt(Object value, int row, int col) {
		Part part = parts.get(row);
		Part partShallowClone = (Part) part.clone();
		switch (PartTypeTableColumn.getByNumber(col)) {
			case COL_NAME:
				partShallowClone.setName(value.toString());
				break;
			case COL_CAR_PART_TYPE_NAME:
				partShallowClone.setCarPartType((CarPartType) value);
				break;
			case COL_CAR_TYPE:
				partShallowClone.setCarPartType((CarPartType) value);
				break;
		}

		executeUpdate(Part.class.getName(), part);
		this.parts.set(row, partShallowClone);

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
