package com.javafee.mainform;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.startform.RegistrationPanel;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitConfirmationPanel;
import com.javafee.uniform.CockpitEditionPanel;

import lombok.Getter;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -3608994423760373741L;

	private JPanel contentPane;
	@Getter
	private CarTypeTablePanel carTypeTablePanel;
	@Getter
	private CockpitConfirmationPanel cockpitConfirmationPanel;
	@Getter
	private CockpitEditionPanel cockpitEditionPanel;
	@Getter
	private JLabel lblLogInInformation;
	@Getter
	private JComboBox<String> comboBoxLanguage;
	@Getter
	private JButton btnLogOut;
	private JPanel panel;
	@Getter
	private JButton btnDisplayAllParts;
	private JPanel panelCatalogue;
	@Getter
	private JButton btnManageWorkers;
	@Getter
	private JButton btnChangeAppStyle;

	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 1071, 560);
		contentPane = new JPanel();
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		lblLogInInformation = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("tabbedForm.lblLogInInformation"));
		lblLogInInformation.setForeground(SystemColor.textHighlight);
		GridBagConstraints gbc_lblLogInInformation = new GridBagConstraints();
		gbc_lblLogInInformation.anchor = GridBagConstraints.WEST;
		gbc_lblLogInInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogInInformation.gridx = 0;
		gbc_lblLogInInformation.gridy = 0;
		contentPane.add(lblLogInInformation, gbc_lblLogInInformation);

		btnManageWorkers = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("cockpitEditionPanel.btnRegistration"));
		btnManageWorkers
				.setIcon(new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnModify-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnManageWorkers = new GridBagConstraints();
		gbc_btnManageWorkers.insets = new Insets(0, 0, 5, 5);
		gbc_btnManageWorkers.gridx = 1;
		gbc_btnManageWorkers.gridy = 0;
		contentPane.add(btnManageWorkers, gbc_btnManageWorkers);

		btnLogOut = new JButton(SystemProperties.getInstance().getResourceBundle().getString("tabbedForm.btnLogOut"));
		btnLogOut.setIcon(new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnLogOut-ico.png"))
				.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogOut.gridx = 2;
		gbc_btnLogOut.gridy = 0;
		contentPane.add(btnLogOut, gbc_btnLogOut);

		btnChangeAppStyle = new JButton("");
		btnChangeAppStyle.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnChangeAppStyle-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnChangeAppStyle = new GridBagConstraints();
		gbc_btnChangeAppStyle.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeAppStyle.gridx = 3;
		gbc_btnChangeAppStyle.gridy = 0;
		contentPane.add(btnChangeAppStyle, gbc_btnChangeAppStyle);

		comboBoxLanguage = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxLanguage = new GridBagConstraints();
		gbc_comboBoxLanguage.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxLanguage.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxLanguage.gridx = 4;
		gbc_comboBoxLanguage.gridy = 0;
		contentPane.add(comboBoxLanguage, gbc_comboBoxLanguage);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("mainForm.borderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 5;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 36, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		carTypeTablePanel = new CarTypeTablePanel();
		GridBagConstraints gbc_carTypeTablePanel = new GridBagConstraints();
		gbc_carTypeTablePanel.fill = GridBagConstraints.BOTH;
		gbc_carTypeTablePanel.insets = new Insets(0, 0, 5, 0);
		gbc_carTypeTablePanel.gridx = 0;
		gbc_carTypeTablePanel.gridy = 0;
		panel.add(carTypeTablePanel, gbc_carTypeTablePanel);

		cockpitEditionPanel = new CockpitEditionPanel();
		GridBagConstraints gbc_cockpitEditionPanel = new GridBagConstraints();
		gbc_cockpitEditionPanel.fill = GridBagConstraints.BOTH;
		gbc_cockpitEditionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cockpitEditionPanel.gridx = 0;
		gbc_cockpitEditionPanel.gridy = 1;
		panel.add(cockpitEditionPanel, gbc_cockpitEditionPanel);

		panelCatalogue = new JPanel();
		panelCatalogue.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("mainForm.borderCatalogueTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCatalogue.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		panel.add(panelCatalogue, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelCatalogue.setLayout(gbl_panel_1);

		btnDisplayAllParts = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("mainForm.btnDisplayAllParts"));
		btnDisplayAllParts.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnDisplayAllParts-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnDisplayAllParts = new GridBagConstraints();
		gbc_btnDisplayAllParts.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDisplayAllParts.gridwidth = 3;
		gbc_btnDisplayAllParts.insets = new Insets(0, 0, 5, 0);
		gbc_btnDisplayAllParts.gridx = 0;
		gbc_btnDisplayAllParts.gridy = 0;
		panelCatalogue.add(btnDisplayAllParts, gbc_btnDisplayAllParts);

		cockpitConfirmationPanel = new CockpitConfirmationPanel();
		GridBagConstraints gbc_cockpitConfirmationPanel = new GridBagConstraints();
		gbc_cockpitConfirmationPanel.fill = GridBagConstraints.BOTH;
		gbc_cockpitConfirmationPanel.gridwidth = 3;
		gbc_cockpitConfirmationPanel.insets = new Insets(0, 0, 0, 5);
		gbc_cockpitConfirmationPanel.gridx = 0;
		gbc_cockpitConfirmationPanel.gridy = 1;
		panelCatalogue.add(cockpitConfirmationPanel, gbc_cockpitConfirmationPanel);
	}

}
