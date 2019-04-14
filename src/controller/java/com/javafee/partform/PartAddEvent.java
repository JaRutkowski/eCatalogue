package com.javafee.partform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarPartType;
import com.javafee.hibernate.dto.association.scan.Part;
import com.javafee.model.PartTableModel;
import com.javafee.partform.frames.PartAddFrame;

public class PartAddEvent {

	private PartAddFrame partAddFrame;
	private PartTableModel partTableModel;

	private File cachedImageFile;

	public void control(PartTableModel partTypeTableModel) {
		this.partTableModel = partTypeTableModel;
		openClientAddModFrame();

		partAddFrame.getCockpitConfirmationPanel().getBtnAccept().addActionListener(e -> onClickBtnAccept());
		partAddFrame.getBtnChooseImageFile().addActionListener(e -> onClickBtnChooseImageFile());
	}

	private void openClientAddModFrame() {
		if (partAddFrame == null || (partAddFrame != null && !partAddFrame.isDisplayable())) {
			partAddFrame = new PartAddFrame();
			reloadPartTypeComboBox();
			partAddFrame.setVisible(true);
		} else {
			partAddFrame.toFront();
		}
	}

	private void reloadPartTypeComboBox() {
		DefaultComboBoxModel<CarPartType> comboBoxCarPartModel = new DefaultComboBoxModel<CarPartType>();
		HibernateDao<CarPartType, Integer> carPartType = new HibernateDao<CarPartType, Integer>(CarPartType.class);
		List<CarPartType> carPartTypeListToSort = carPartType.findAll();
		carPartTypeListToSort = carPartTypeListToSort.stream()
				.filter(e -> (e.getCarType() == Params.getInstance().get("selectedCarType")))
				.collect(Collectors.toList());
		carPartTypeListToSort
				.sort(Comparator.comparing(CarPartType::getName, Comparator.nullsFirst(Comparator.naturalOrder())));
		carPartTypeListToSort.forEach(c -> comboBoxCarPartModel.addElement(c));

		partAddFrame.getComboBoxCarPartType().setModel(comboBoxCarPartModel);
	}

	private void realodImageViewPanel() {
		if (cachedImageFile != null) {
			try {
				partAddFrame.getImageViewPanel().loadImage(cachedImageFile.getAbsolutePath());
				partAddFrame.getImageViewPanel().paint(partAddFrame.getImageViewPanel().getGraphics());
			} catch (IOException e) {
				LogGuiException.logError(
						SystemProperties.getInstance().getResourceBundle().getString("partAddEvent.errorTitle"), e);
				e.printStackTrace();
			}
		} else {
			LogGuiException.logError(
					SystemProperties.getInstance().getResourceBundle().getString("partAddEvent.emptyImageErrorTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("partAddEvent.emptyImageErrorTitle"));
		}
	}

	private void onClickBtnChooseImageFile() {
		cachedImageFile = Utils.displayJFileChooserAndGetFile(null);
		partAddFrame.getTextFieldImagePath().setText(cachedImageFile.getAbsolutePath());

		realodImageViewPanel();
	}

	private void onClickBtnAccept() {
		try {
			Part part = new Part();
			part.setName(partAddFrame.getTextFieldName().getText());
			if (cachedImageFile != null)
				part.setImage(Files.readAllBytes(cachedImageFile.toPath()));
			part.setCarPartType(((CarPartType) partAddFrame.getComboBoxCarPartType().getSelectedItem()));

			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(part);
			HibernateUtil.commitTransaction();

			partTableModel.add(part);

			Utils.displayOptionPane(
					SystemProperties.getInstance().getResourceBundle().getString("partAddEvent.partAdditionSuccess"),
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.carAdditionSuccessTitle"),
					JOptionPane.INFORMATION_MESSAGE);

			partTableModel.reloadData();
			partAddFrame.dispose();
		} catch (NumberFormatException | IOException e) {
			LogGuiException.logError(
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.dataParseErrorTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.dataParseError"), e);
		}
	}
}
