package com.javafee.partform;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import com.javafee.common.Context;
import com.javafee.common.IActionForm;
import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.association.scan.Part;
import com.javafee.mainform.CarPartTypeAddEvent;
import com.javafee.mainform.PowerUpDataEvent;
import com.javafee.model.PartTableModel;
import com.javafee.startform.LogInEvent;

public class Actions implements IActionForm {

	private PartForm partForm;
	private PartAddEvent partAddEvent = null;
	private CarPartTypeAddEvent carPartTypeAddEvent = null;
	private PowerUpDataEvent powerUpDataEvent = null;

	public void control() {
		if (partForm == null || (partForm != null && !partForm.isDisplayable())) {
			partForm = new PartForm();
			this.initializeForm();
			partForm.setVisible(true);

			partForm.getCockpitEditionPanel().getBtnAdd().addActionListener(e -> onClickBtnAdd());
			partForm.getCockpitEditionPanel().getBtnRemove().addActionListener(e -> onClickBtnRemove());
			partForm.getCockpitEditionPanel().getBtnCarPartTypeAdd().addActionListener(e -> onClickBtnCarPartTypeAdd());
			partForm.getCockpitEditionPanel().getBtnPowerUpData().addActionListener(e -> onClickBtnPowerUpData());

			partForm.getPartTablePanel().getTable().getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting())
					onCarPartTypeTableListSelectionChange();
			});

			partForm.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Params.getInstance().remove("selectedCarType");
				}
			});
		} else {
			partForm.toFront();
		}
	}

	@Override
	public void initializeForm() {
		this.setControlsVisibility();
		this.configureContext();
	}

	private void configureContext() {
		if (Context.getInstance().contains("ALL_PARTS")) {
			((PartTableModel) partForm.getPartTablePanel().getTable().getModel())
					.setAllParts((Boolean) Context.getInstance().get("ALL_PARTS"));
			((PartTableModel) partForm.getPartTablePanel().getTable().getModel()).reloadData();
			Context.getInstance().remove("ALL_PARTS");
		}
	}

	private void realodImageViewPanel(Part selectedCarType) {
		File dir = new File("tmp/test");
		dir.mkdirs();
		File tmp = new File(dir, "tmp.txt");
		try {
			tmp.createNewFile();
			if (selectedCarType != null && selectedCarType.getImage() != null
					&& selectedCarType.getImage().length > 0) {
				FileUtils.writeByteArrayToFile(tmp, selectedCarType.getImage());
				partForm.getPartTablePanel().getImagePreviewPanel().loadImage(tmp.getAbsolutePath());
				partForm.getPartTablePanel().getImagePreviewPanel()
						.paint(partForm.getPartTablePanel().getImagePreviewPanel().getGraphics());
				tmp.delete();
			} else {
				partForm.getPartTablePanel().getImagePreviewPanel().setImage(null);
				partForm.getPartTablePanel().getImagePreviewPanel().setScaledImage(null);
				partForm.getPartTablePanel().getImagePreviewPanel()
						.paint(partForm.getPartTablePanel().getImagePreviewPanel().getGraphics());
			}
		} catch (IOException e) {
			LogGuiException.logError(
					SystemProperties.getInstance().getResourceBundle().getString("partAddEvent.errorTitle"), e);
			e.printStackTrace();
		}
	}

	private void setControlsVisibility() {
		partForm.getCockpitEditionPanel().setVisible(LogInEvent.getIsAdmin());
		partForm.getCockpitEditionPanel().getBtnAdd().setEnabled(
				!Context.getInstance().contains("ALL_PARTS") || !(Boolean) Context.getInstance().get("ALL_PARTS"));
	}

	private void onCarPartTypeTableListSelectionChange() {
		if (partForm.getPartTablePanel().getTable().getSelectedRow() != -1 && partForm.getPartTablePanel().getTable()
				.convertRowIndexToModel(partForm.getPartTablePanel().getTable().getSelectedRow()) != -1) {

			int selectedRowIndex = partForm.getPartTablePanel().getTable()
					.convertRowIndexToModel(partForm.getPartTablePanel().getTable().getSelectedRow());

			if (selectedRowIndex != -1) {
				Part selectedCarType = ((PartTableModel) partForm.getPartTablePanel().getTable().getModel())
						.getCarType(selectedRowIndex);
				realodImageViewPanel(selectedCarType);
			}
		}
	}

	private void onClickBtnAdd() {
		if (partAddEvent == null)
			partAddEvent = new PartAddEvent();
		partAddEvent.control((PartTableModel) partForm.getPartTablePanel().getTable().getModel());
	}

	private void onClickBtnCarPartTypeAdd() {
		if (carPartTypeAddEvent == null) {
			carPartTypeAddEvent = new CarPartTypeAddEvent();
		}
		carPartTypeAddEvent.control();
	}

	private void onClickBtnRemove() {
		if (partForm.getPartTablePanel().getTable().getSelectedRow() != -1) {
			int selectedRowIndex = partForm.getPartTablePanel().getTable()
					.convertRowIndexToModel(partForm.getPartTablePanel().getTable().getSelectedRow());

			if (Utils.displayConfirmDialog(
					SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.deleteMessage"),
					"") == JOptionPane.YES_OPTION) {
				if (selectedRowIndex != -1) {
					Part selectedCarType = ((PartTableModel) partForm.getPartTablePanel().getTable().getModel())
							.getCarType(selectedRowIndex);

					HibernateUtil.beginTransaction();
					HibernateUtil.getSession().delete(selectedCarType);
					HibernateUtil.commitTransaction();

					((PartTableModel) partForm.getPartTablePanel().getTable().getModel()).reloadData();
				}
			}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("actions.notSelectedCarTypeWarningTitle"),
					SystemProperties.getInstance().getResourceBundle().getString("actions.notSelectedCarTypeWarning"));
		}
	}

	private void onClickBtnPowerUpData() {
		if (powerUpDataEvent == null)
			powerUpDataEvent = new PowerUpDataEvent();
		powerUpDataEvent.control(null, (PartTableModel) partForm.getPartTablePanel().getTable().getModel());
	}

}
