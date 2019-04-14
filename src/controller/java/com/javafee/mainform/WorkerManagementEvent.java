package com.javafee.mainform;

import javax.swing.JOptionPane;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.exception.LogGuiException;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.hibernate.dto.cardealer.Worker;
import com.javafee.mainform.frames.WorkerFrame;
import com.javafee.model.WorkerTableModel;

public class WorkerManagementEvent {

	private WorkerFrame workerFrame;
	private WorkerAddEvent workerAddEvent;

	public void control() {
		openClientAddModFrame();

		workerFrame.getBtnRegisterWorker().addActionListener(e -> onClickBtnRegisterNow());
		workerFrame.getBtnRemoveWorker().addActionListener(e -> onClickBtnRemoveWorker());
		workerFrame.getBtnResetPassword().addActionListener(e -> onClickBtnResetPassword());
		workerFrame.getChckbxIsActive().addActionListener(e -> onChangeChckbxIsActive());

		workerFrame.getWorkerTablePanel().getWorkerTable().getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting())
				onWorkerTableListSelectionChange();
		});
	}

	private void openClientAddModFrame() {
		if (workerFrame == null || (workerFrame != null && !workerFrame.isDisplayable())) {
			workerFrame = new WorkerFrame();
			workerFrame.setVisible(true);
		} else {
			workerFrame.toFront();
		}
	}

	private void reloadChckbxIsRegistered(boolean isRegistered) {
		workerFrame.getChckbxIsActive().setSelected(isRegistered);
	}

	private void reloadWorkerTable() {
		workerFrame.getWorkerTablePanel().getWorkerTable().repaint();
	}

	private void onWorkerTableListSelectionChange() {
		if (workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow() != -1
				&& workerFrame.getWorkerTablePanel().getWorkerTable().convertRowIndexToModel(
				workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow()) != -1) {
			reloadChckbxIsRegistered(
					(SystemProperties.getInstance().getResourceBundle().getString("clientTableModel.registeredTrueVal"))
							.equals(workerFrame.getWorkerTablePanel().getWorkerTable().getModel().getValueAt(
									workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow(),
									Constants.WorkerTableColumn.COL_REGISTERED.getValue())));
		}
	}

	private void onChangeChckbxIsActive() {
		if (validateWorkerTableSelection(workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow())) {
			int selectedRowIndex = workerFrame.getWorkerTablePanel().getWorkerTable()
					.convertRowIndexToModel(workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow());
			Worker selectedWorker = ((WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable().getModel())
					.getWorker(selectedRowIndex);
			Worker workerShallowClone = (Worker) selectedWorker.clone();

			workerShallowClone.setRegistered(workerFrame.getChckbxIsActive().isSelected());

			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().evict(selectedWorker);
			HibernateUtil.getSession().update(Worker.class.getName(), workerShallowClone);
			HibernateUtil.commitTransaction();

			((WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable().getModel())
					.setWorker(selectedRowIndex, workerShallowClone);
			reloadWorkerTable();
		} else {
			Utils.displayOptionPane(
					SystemProperties.getInstance().getResourceBundle()
							.getString("tabClientEvent.validateClientTableSelectionWarning1"),
					SystemProperties.getInstance().getResourceBundle().getString(
							"tabClientEvent.validateClientTableSelectionWarning1Title"),
					JOptionPane.WARNING_MESSAGE);
			workerFrame.getChckbxIsActive().setSelected(false);
		}
	}

	private void onClickBtnRegisterNow() {
		if (workerAddEvent == null)
			workerAddEvent = new WorkerAddEvent();
		workerAddEvent.control(Constants.Context.ADDITION,
				(WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable().getModel());
	}

	private void onClickBtnResetPassword() {
		if (workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow() != -1) {
			int selectedRowIndex = workerFrame.getWorkerTablePanel().getWorkerTable()
					.convertRowIndexToModel(workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow());

			if (Utils.displayConfirmDialog(
					SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.resetPasswordMessage"),
					"") == JOptionPane.YES_OPTION) {
				if (selectedRowIndex != -1) {
					Worker selectedWorker = ((WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable()
							.getModel()).getWorker(selectedRowIndex);
					selectedWorker.setPassword(Constants.DATA_BASE_RESET_PASSWORD);

					HibernateUtil.beginTransaction();
					HibernateUtil.getSession().update(selectedWorker);
					HibernateUtil.commitTransaction();

					((WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable().getModel()).reloadData();
				}
			}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("workerManagementEvent.notSelectedWorkerWarningTitle"),
					SystemProperties.getInstance().getResourceBundle()
							.getString("workerManagementEvent.notSelectedWorkerWarning"));
		}
	}

	private void onClickBtnRemoveWorker() {
		if (workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow() != -1) {
			int selectedRowIndex = workerFrame.getWorkerTablePanel().getWorkerTable()
					.convertRowIndexToModel(workerFrame.getWorkerTablePanel().getWorkerTable().getSelectedRow());

			if (Utils.displayConfirmDialog(
					SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.deleteMessage"),
					"") == JOptionPane.YES_OPTION) {
				if (selectedRowIndex != -1) {
					Worker selectedWorker = ((WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable()
							.getModel()).getWorker(selectedRowIndex);

					HibernateUtil.beginTransaction();
					HibernateUtil.getSession().delete(selectedWorker);
					HibernateUtil.commitTransaction();

					((WorkerTableModel) workerFrame.getWorkerTablePanel().getWorkerTable().getModel()).reloadData();
				}
			}
		} else {
			LogGuiException.logWarning(
					SystemProperties.getInstance().getResourceBundle()
							.getString("workerManagementEvent.notSelectedWorkerWarningTitle"),
					SystemProperties.getInstance().getResourceBundle()
							.getString("workerManagementEvent.notSelectedWorkerWarning"));
		}
	}

	public boolean validateWorkerTableSelection(int index) {
		return index > -1;
	}

}
