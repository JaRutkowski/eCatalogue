package com.javafee.mainform;

import javax.swing.JOptionPane;

import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.CarType;
import com.javafee.mainform.frames.CarTypeAddFrame;
import com.javafee.model.CarTypeTableModel;

public class CarTypeAddEvent {

	private CarTypeAddFrame carTypeAddFrame;
	private CarTypeTableModel carTypeTableModel;

	public void control(CarTypeTableModel carTableModel) {
		this.carTypeTableModel = carTableModel;
		openCarTypeAddFrame();

		carTypeAddFrame.getCockpitConfirmationPanel().getBtnAccept().addActionListener(e -> onClickBtnAccept());
	}

	private void openCarTypeAddFrame() {
		if (carTypeAddFrame == null || (carTypeAddFrame != null && !carTypeAddFrame.isDisplayable())) {
			carTypeAddFrame = new CarTypeAddFrame();
			carTypeAddFrame.setVisible(true);
		} else {
			carTypeAddFrame.toFront();
		}
	}

	private void onClickBtnAccept() {
		try {
			CarType car = new CarType();
			car.setManufacturer(carTypeAddFrame.getTextFieldManufacturer().getText());
			car.setModel(carTypeAddFrame.getTextFieldModel().getText());
			car.setFuelKind(carTypeAddFrame.getTextFieldFuelKind().getText());
			car.setPower(!"".equals(carTypeAddFrame.getTextFieldPower().getText())
					? Integer.parseInt(carTypeAddFrame.getTextFieldPower().getText())
					: null);
			car.setEngine(!"".equals(carTypeAddFrame.getTextFieldEngine().getText())
					? Double.parseDouble(carTypeAddFrame.getTextFieldEngine().getText())
					: null);

			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().save(car);
			HibernateUtil.commitTransaction();

			carTypeTableModel.add(car);

			Utils.displayOptionPane(
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.carAdditionSuccess"),
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.carAdditionSuccessTitle"),
					JOptionPane.INFORMATION_MESSAGE);

			carTypeTableModel.reloadData();
			carTypeAddFrame.dispose();
		} catch (NumberFormatException e) {
			LogGuiException.logError(
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.dataParseErrorTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("carAddEvent.dataParseError"), e);
		}
	}
}
