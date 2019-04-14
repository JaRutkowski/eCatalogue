package com.javafee.mainform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.reflections.Reflections;

import com.javafee.common.Constants;
import com.javafee.common.Constants.CarPartTypeTableColumn;
import com.javafee.common.Constants.CarTypeTableColumn;
import com.javafee.common.Constants.PartTypeTableColumn;
import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarPartType;
import com.javafee.hibernate.dto.association.scan.CarType;
import com.javafee.hibernate.dto.association.scan.IScan;
import com.javafee.hibernate.dto.association.scan.Part;
import com.javafee.mainform.frames.PowerUpDataFrame;
import com.javafee.model.CarPartTableModel;
import com.javafee.model.CarTypeTableModel;
import com.javafee.model.PartTableModel;

public class PowerUpDataEvent {

	private PowerUpDataFrame powerUpDataFrame;

	private CarTypeTableModel carTypeTableModel;
	private PartTableModel partTypeTableModel;
	private CarPartTableModel carPartTableModel;

	private List<Class<? extends IScan>> classesList;
	private File cachedXlsxFile;

	private List<Object> cachedEntityObjectList = new ArrayList<>();

	public void control(CarTypeTableModel carTableModel, PartTableModel partTypeTableModel) {
		this.carTypeTableModel = carTableModel;
		this.partTypeTableModel = partTypeTableModel;

		if (Params.getInstance().get("carPartTableModel") != null)
			carPartTableModel = (CarPartTableModel) Params.getInstance().get("carPartTableModel");

		openPowerUpDataFrame();
		clearCachedXlsxFile();

		powerUpDataFrame.getPowerUpDataPanel().getBtnChooseXlsxFile()
				.addActionListener(e -> onClickBtnChooseXlsxFile());
		powerUpDataFrame.getPowerUpDataPanel().getBtnPowerUp().addActionListener(e -> onClickBtnPoweUpData());
		powerUpDataFrame.getPowerUpDataPanel().getBtnUndoLastPowerUp()
				.addActionListener(e -> onClickBtnUndoLastPowerUp());
		powerUpDataFrame.getPowerUpDataPanel().getComboBoxEntity().addActionListener(e -> onPowerUpDataChange());
	}

	private void clearCachedXlsxFile() {
		this.cachedXlsxFile = null;
		powerUpDataFrame.getPowerUpDataPanel().getLblChoosenFile()
				.setText(Constants.APPLICATION_XLS_XLSX_FILE_LABEL_TEXT);
	}

	private void openPowerUpDataFrame() {
		if (powerUpDataFrame == null || (powerUpDataFrame != null && !powerUpDataFrame.isDisplayable())) {
			powerUpDataFrame = new PowerUpDataFrame();
			this.reloadPartTypeComboBox();
			this.onPowerUpDataChange();
			powerUpDataFrame.setVisible(true);
		} else {
			powerUpDataFrame.toFront();
		}
	}

	private void reloadLblDataStructure(String forClass) {
		switch (forClass) {
			case "CarPartType":
				powerUpDataFrame.getPowerUpDataPanel().getLblDataStructure().setText(SystemProperties.getInstance()
						.getResourceBundle().getString("powerUpData.lblDataStructureCarPartType"));
				break;
			case "CarType":
				powerUpDataFrame.getPowerUpDataPanel().getLblDataStructure().setText(SystemProperties.getInstance()
						.getResourceBundle().getString("powerUpData.lblDataStructureCarType"));
				break;
			case "Part":
				powerUpDataFrame.getPowerUpDataPanel().getLblDataStructure().setText(
						SystemProperties.getInstance().getResourceBundle().getString("powerUpData.lblDataStructurePart"));
				break;
		}
	}

	private void reloadPartTypeComboBox() {
		Reflections reflections = new Reflections("com.javafee.hibernate.dto.association.scan");
		Set<Class<? extends IScan>> classes = reflections.getSubTypesOf(IScan.class);
		classesList = new ArrayList<>();
		classesList.addAll(classes);
		DefaultComboBoxModel<String> comboBoxEntityModel = new DefaultComboBoxModel<String>();
		classesList.sort(Comparator.comparing(Class::getName, Comparator.nullsFirst(Comparator.naturalOrder())));
		classesList.forEach(c -> comboBoxEntityModel.addElement(c.getSimpleName()));

		powerUpDataFrame.getPowerUpDataPanel().getComboBoxEntity().setModel(comboBoxEntityModel);
	}

	private void onPowerUpDataChange() {
		reloadLblDataStructure(powerUpDataFrame.getPowerUpDataPanel().getComboBoxEntity().getSelectedItem().toString());
	}

	private void onClickBtnChooseXlsxFile() {
		cachedXlsxFile = Utils.displayJFileChooserAndGetFile(null);
		powerUpDataFrame.getPowerUpDataPanel().getLblChoosenFile().setText(cachedXlsxFile.getAbsolutePath());
	}

	private void onClickBtnPoweUpData() {
		cachedEntityObjectList.clear();

		String entityClass = powerUpDataFrame.getPowerUpDataPanel().getComboBoxEntity().getSelectedItem() != null
				? (String) powerUpDataFrame.getPowerUpDataPanel().getComboBoxEntity().getSelectedItem()
				: null;
		if (entityClass != null && cachedXlsxFile != null) {
			if (Utils.displayConfirmDialog(
					SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.powerUpData"),
					"") == JOptionPane.YES_OPTION)
				try {

					Workbook workbook = WorkbookFactory.create(this.cachedXlsxFile);
					Sheet sheet = workbook.getSheetAt(0);
					DataFormatter dataFormatter = new DataFormatter();
					sheet.forEach(row -> {
						List<Object> rowData = new ArrayList<>();
						row.forEach(cell -> {
							String cellValue = dataFormatter.formatCellValue(cell);
							rowData.add(cellValue);
							System.out.print(cellValue + "\t");
						});
						createEntityObject(entityClass, rowData);
						System.out.println();
					});

					if (carTypeTableModel != null)
						carTypeTableModel.reloadData();
					else if (partTypeTableModel != null)
						partTypeTableModel.reloadData();
					else if (carPartTableModel != null)
						carPartTableModel.reloadData();

					workbook.close();
					Utils.displayOptionPane(
							SystemProperties.getInstance().getResourceBundle().getString("powerUpEvent.success"),
							SystemProperties.getInstance().getResourceBundle().getString("powerUpEvent.successTitle"),
							JOptionPane.INFORMATION_MESSAGE);
				} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
					LogGuiException.logError(
							SystemProperties.getInstance().getResourceBundle().getString("powerUpData.errorTitle"), e);
					e.printStackTrace();
				}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("powerUpDataEvent.notSelectedEntityOrNoFileTitle"),
					SystemProperties.getInstance().getResourceBundle()
							.getString("powerUpDataEvent.notSelectedEntityOrNoFile"));

		}

	}

	private void createEntityObject(String forClass, List<Object> rowData) {
		switch (forClass) {
			case "CarPartType":
				createCarPartType(rowData);
				break;
			case "CarType":
				createCarType(rowData);
				break;
			case "Part":
				createPart(rowData);
				break;
		}
	}

	private void createCarPartType(List<Object> rowData) {
		CarPartType carPartType = new CarPartType();
		int columnIndex = 0;
		for (Object data : rowData) {
			switch (CarPartTypeTableColumn.getByNumber(columnIndex)) {
				case COL_NAME:
					carPartType.setName(data.toString());
					break;
				case COL_DESCRIPTION:
					carPartType.setDescription(data.toString());
					break;
				case COL_ORIGIN:
					carPartType.setOrigin(data.toString());
					break;
				case COL_PRODUCER:
					carPartType.setProducer(data.toString());
					break;
				case COL_CAR_TYPE:
					carPartType.setCarType(
							new HibernateDao<>(CarType.class).findByPrimaryKey(Integer.parseInt(data.toString())));
					break;
			}
			columnIndex++;
		}

		this.cachedEntityObjectList.add(carPartType);
		HibernateUtil.beginTransaction();
		HibernateUtil.getSession().save(carPartType);
		HibernateUtil.commitTransaction();
	}

	private void createCarType(List<Object> rowData) {
		CarType carType = new CarType();
		int columnIndex = 0;
		for (Object data : rowData) {
			switch (CarTypeTableColumn.getByNumber(columnIndex)) {
				case COL_MANUFACTURER:
					carType.setManufacturer(data.toString());
					break;
				case COL_MODEL:
					carType.setModel(data.toString());
					break;
				case COL_FUEL_KIND:
					carType.setFuelKind(data.toString());
					break;
				case COL_POWER:
					carType.setPower(Integer.parseInt(data.toString()));
					break;
				case COL_ENGINE:
					carType.setEngine(Double.parseDouble(data.toString()));
			}
			columnIndex++;
		}

		this.cachedEntityObjectList.add(carType);
		HibernateUtil.beginTransaction();
		HibernateUtil.getSession().save(carType);
		HibernateUtil.commitTransaction();
	}

	@SuppressWarnings("incomplete-switch")
	private void createPart(List<Object> rowData) {
		Part part = new Part();
		int columnIndex = 0;
		for (Object data : rowData) {
			switch (PartTypeTableColumn.getByNumber(columnIndex)) {
				case COL_NAME:
					part.setName(data.toString());
					break;
				case COL_CAR_PART_TYPE:
					part.setCarPartType(
							new HibernateDao<>(CarPartType.class).findByPrimaryKey(Integer.parseInt(data.toString())));
					break;
			}
			columnIndex++;
		}

		this.cachedEntityObjectList.add(part);
		HibernateUtil.beginTransaction();
		HibernateUtil.getSession().save(part);
		HibernateUtil.commitTransaction();
	}

	private void onClickBtnUndoLastPowerUp() {
		if (!cachedEntityObjectList.isEmpty()) {
			if (Utils.displayConfirmDialog(
					SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.powerUpDataUndo"),
					"") == JOptionPane.YES_OPTION) {
				HibernateUtil.beginTransaction();
				cachedEntityObjectList.forEach(e -> HibernateUtil.getSession().delete(e));
				HibernateUtil.commitTransaction();

				this.cachedEntityObjectList.clear();

				if (carTypeTableModel != null)
					carTypeTableModel.reloadData();
				else if (partTypeTableModel != null)
					partTypeTableModel.reloadData();
				else if (carPartTableModel != null)
					carPartTableModel.reloadData();
			}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("powerUpDataEvent.powerUpDataUndoErrorFileTitle"),
					SystemProperties.getInstance().getResourceBundle()
							.getString("powerUpDataEvent.powerUpDataUndoErrorFile"));
		}
	}

}
