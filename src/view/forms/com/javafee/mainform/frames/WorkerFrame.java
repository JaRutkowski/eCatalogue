package com.javafee.mainform.frames;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.mainform.WorkerTablePanel;
import com.javafee.startform.RegistrationPanel;
import com.javafee.startform.StartForm;

import lombok.Getter;

public class WorkerFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private WorkerTablePanel workerTablePanel;
	@Getter
	private JButton btnRegisterWorker;
	@Getter
	private JButton btnRemoveWorker;
	@Getter
	private JCheckBox chckbxIsActive;
	@Getter
	private JButton btnResetPassword;

	public WorkerFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 1288, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{233, 248, 0};
		gbl_contentPane.rowHeights = new int[]{238, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		workerTablePanel = new WorkerTablePanel();
		workerTablePanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(workerTablePanel, gbc_panel);

		btnRegisterWorker = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("workerFrame.btnRegisterWorker"));
		btnRegisterWorker.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnRegisterNow-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnRegisterWorker = new GridBagConstraints();
		gbc_btnRegisterWorker.anchor = GridBagConstraints.SOUTH;
		gbc_btnRegisterWorker.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegisterWorker.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegisterWorker.gridx = 0;
		gbc_btnRegisterWorker.gridy = 1;
		contentPane.add(btnRegisterWorker, gbc_btnRegisterWorker);

		btnRemoveWorker = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("workerFrame.btnRemoveWorker"));
		btnRemoveWorker
				.setIcon(new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnRemove-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnRemoveWorker = new GridBagConstraints();
		gbc_btnRemoveWorker.anchor = GridBagConstraints.SOUTH;
		gbc_btnRemoveWorker.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveWorker.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoveWorker.gridx = 1;
		gbc_btnRemoveWorker.gridy = 1;
		contentPane.add(btnRemoveWorker, gbc_btnRemoveWorker);

		chckbxIsActive = new JCheckBox(
				SystemProperties.getInstance().getResourceBundle().getString("workerFrame.chckbxIsActive"));
		GridBagConstraints gbc_chckbxIsActive = new GridBagConstraints();
		gbc_chckbxIsActive.anchor = GridBagConstraints.NORTH;
		gbc_chckbxIsActive.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxIsActive.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxIsActive.gridx = 0;
		gbc_chckbxIsActive.gridy = 2;
		contentPane.add(chckbxIsActive, gbc_chckbxIsActive);

		btnResetPassword = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("workerFrame.btnResetPassword"));
		btnResetPassword.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnSendAgain-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnResetPassword = new GridBagConstraints();
		gbc_btnResetPassword.anchor = GridBagConstraints.NORTH;
		gbc_btnResetPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnResetPassword.gridx = 1;
		gbc_btnResetPassword.gridy = 2;
		contentPane.add(btnResetPassword, gbc_btnResetPassword);
	}

}
