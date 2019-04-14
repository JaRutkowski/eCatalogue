package com.javafee.mainform;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateDao;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarPartType;
import com.javafee.hibernate.dto.association.scan.CarType;
import com.javafee.mainform.frames.CarPartTypeAddFrame;
import com.javafee.model.CarPartTableModel;

public class CarPartTypeAddEvent {

	private CarPartTypeAddFrame carPartTypeAddFrame;

	public void control() {
		openCarTypeAddFrame();

		carPartTypeAddFrame.getCockpitConfirmationPanel().getBtnAccept().addActionListener(e -> onClickBtnAccept());
		carPartTypeAddFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				Params.getInstance().remove("carPartTableModel");
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
	}

	private void openCarTypeAddFrame() {
		if (carPartTypeAddFrame == null || (carPartTypeAddFrame != null && !carPartTypeAddFrame.isDisplayable())) {
			carPartTypeAddFrame = new CarPartTypeAddFrame();
			reloadCarTypeComboBox();
			registerCarPartTypeTableModelInSession();
			carPartTypeAddFrame.setVisible(true);
		} else {
			carPartTypeAddFrame.toFront();
		}
	}

	private void registerCarPartTypeTableModelInSession() {
		Params.getInstance().add("carPartTableModel",
				carPartTypeAddFrame.getCarPartTypeTablePanel().getTable().getModel());
	}

	private void reloadCarTypeComboBox() {
		DefaultComboBoxModel<CarType> comboBoxCarTypeModel = new DefaultComboBoxModel<CarType>();
		HibernateDao<CarType, Integer> carType = new HibernateDao<CarType, Integer>(CarType.class);
		List<CarType> carTypeListToSort = carType.findAll();
		carTypeListToSort
				.sort(Comparator.comparing(CarType::getManufacturer, Comparator.nullsFirst(Comparator.naturalOrder())));
		carTypeListToSort.forEach(c -> comboBoxCarTypeModel.addElement(c));

		carPartTypeAddFrame.getComboBoxCarType().setModel(comboBoxCarTypeModel);

		if (Params.getInstance().get("selectedCarType") != null)
			carPartTypeAddFrame.getComboBoxCarType()
					.setSelectedItem(Params.getInstance().get("selectedCarType"));
	}

	private void onClickBtnAccept() {
		try {
			CarPartType carPartType = new CarPartType();
			carPartType.setName(carPartTypeAddFrame.getTextFieldName().getText());
			carPartType.setDescription(carPartTypeAddFrame.getTextFieldDescription().getText());
			carPartType.setOrigin(carPartTypeAddFrame.getTextFieldOrigin().getText());
			carPartType.setProducer(carPartTypeAddFrame.getTextFieldProducer().getText());
			carPartType.setCarType((CarType) carPartTypeAddFrame.getComboBoxCarType().getSelectedItem());

			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().save(carPartType);
			HibernateUtil.commitTransaction();

			((CarPartTableModel) carPartTypeAddFrame.getCarPartTypeTablePanel().getTable().getModel()).add(carPartType);

			Utils.displayOptionPane(
					SystemProperties.getInstance().getResourceBundle()
							.getString("carPartTypeAddEvent.carPartTypeAdditionSuccess"),
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.carAdditionSuccessTitle"),
					JOptionPane.INFORMATION_MESSAGE);

			((CarPartTableModel) carPartTypeAddFrame.getCarPartTypeTablePanel().getTable().getModel()).reloadData();
		} catch (NumberFormatException e) {
			LogGuiException.logError(
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.dataParseErrorTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.dataParseError"), e);
		}
	}
}
