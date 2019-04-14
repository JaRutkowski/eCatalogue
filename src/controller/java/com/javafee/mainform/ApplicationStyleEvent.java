package com.javafee.mainform;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import com.javafee.common.Params;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.hibernate.dao.HibernateUtil;
import com.javafee.mainform.frames.ApplicationStyleFrame;
import com.javafee.startform.LogInEvent;

public class ApplicationStyleEvent {

	private ApplicationStyleFrame applicationStyleFrame;

	private Color color = null;

	public void control() {
		openApplicationStyleFrame();

		applicationStyleFrame.addWindowListener(new WindowListener() {

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
				Params.getInstance().remove("MAIN_FORM");
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		applicationStyleFrame.getColorChooser().getSelectionModel().addChangeListener(e -> onChangeColor());
		applicationStyleFrame.getConfirmationPanel().getBtnAccept().addActionListener(e -> onClickBtnAccept());
	}

	private void openApplicationStyleFrame() {
		if (applicationStyleFrame == null
				|| (applicationStyleFrame != null && !applicationStyleFrame.isDisplayable())) {
			applicationStyleFrame = new ApplicationStyleFrame();
			applicationStyleFrame.setVisible(true);
		} else {
			applicationStyleFrame.toFront();
		}
	}

	private void onChangeColor() {
		this.color = applicationStyleFrame.getColorChooser().getColor();
	}

	private void onClickBtnAccept() {
		if (Utils.displayConfirmDialog(
				SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.applicationStyleChange"),
				"") == JOptionPane.YES_OPTION) {
			StringBuilder applicationColor = new StringBuilder().append(color.getRed()).append(",")
					.append(color.getGreen()).append(",").append(color.getBlue());

			LogInEvent.getSystemProperties().setColor(applicationColor.toString());
			com.javafee.hibernate.dto.common.SystemProperties systemProperties = LogInEvent.getSystemProperties();

			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(systemProperties);
			HibernateUtil.commitTransaction();

			onClickBtnLogOut();
		}
	}

	private void onClickBtnLogOut() {
		LogInEvent.clearLogInData();
		applicationStyleFrame.dispose();
		applicationStyleFrame = null;
		((MainForm) Params.getInstance().get("MAIN_FORM")).dispose();
		Params.getInstance().remove("MAIN_FORM");
		openStartForm();
	}

	private void openStartForm() {
		com.javafee.startform.Actions actions = new com.javafee.startform.Actions();
		actions.control();
	}

}
