package com.javafee.mainform;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.startform.RegistrationPanel;

import lombok.Getter;

public class PowerUpDataPanel extends JPanel {

	private static final long serialVersionUID = 6611305505467123577L;
	@Getter
	private JButton btnChooseXlsxFile;
	@Getter
	private JButton btnPowerUp;
	@Getter
	JComboBox<String> comboBoxEntity;
	@Getter
	private JLabel lblChoosenFile;
	private JPanel panelChooseFile;
	private JPanel panelPowerUpDataProcess;
	@Getter
	private JButton btnUndoLastPowerUp;
	@Getter
	private JLabel lblDataStructure;

	public PowerUpDataPanel() {
		setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.powerUpDataBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(Utils.getApplicationUserDefineColor());
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{282};
		gbl_panel.rowHeights = new int[]{81, 125, 191, 0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_panel);

		lblDataStructure = new JLabel("New label");
		lblDataStructure.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblDataStructure = new GridBagConstraints();
		gbc_lblDataStructure.fill = GridBagConstraints.VERTICAL;
		gbc_lblDataStructure.insets = new Insets(0, 0, 5, 0);
		gbc_lblDataStructure.gridx = 0;
		gbc_lblDataStructure.gridy = 0;
		add(lblDataStructure, gbc_lblDataStructure);

		panelChooseFile = new JPanel();
		panelChooseFile.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.chooseFileBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelChooseFile.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panelChooseFile, gbc_panel);
		GridBagLayout gbl_panelChooseFile = new GridBagLayout();
		gbl_panelChooseFile.columnWidths = new int[]{282, 393, 0};
		gbl_panelChooseFile.rowHeights = new int[]{0, 0, 0};
		gbl_panelChooseFile.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelChooseFile.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelChooseFile.setLayout(gbl_panelChooseFile);

		lblChoosenFile = new JLabel("New label");
		lblChoosenFile.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		GridBagConstraints gbc_lblChoosenFile = new GridBagConstraints();
		gbc_lblChoosenFile.gridwidth = 2;
		gbc_lblChoosenFile.insets = new Insets(0, 0, 5, 0);
		gbc_lblChoosenFile.gridx = 0;
		gbc_lblChoosenFile.gridy = 0;
		panelChooseFile.add(lblChoosenFile, gbc_lblChoosenFile);

		btnChooseXlsxFile = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.btnChooseXlsxFile"));
		GridBagConstraints gbc_btnChooseXlsxFile = new GridBagConstraints();
		gbc_btnChooseXlsxFile.fill = GridBagConstraints.BOTH;
		gbc_btnChooseXlsxFile.gridwidth = 2;
		gbc_btnChooseXlsxFile.insets = new Insets(0, 0, 0, 5);
		gbc_btnChooseXlsxFile.gridx = 0;
		gbc_btnChooseXlsxFile.gridy = 1;
		panelChooseFile.add(btnChooseXlsxFile, gbc_btnChooseXlsxFile);
		btnChooseXlsxFile.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnPowerUpDataProc-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));

		panelPowerUpDataProcess = new JPanel();
		panelPowerUpDataProcess.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.powerUpDataProcess"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPowerUpDataProcess.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		add(panelPowerUpDataProcess, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{282, 393, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPowerUpDataProcess.setLayout(gbl_panel_1);

		JLabel lblChooseEntity = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.lblChooseEntity"));
		GridBagConstraints gbc_lblChooseEntity = new GridBagConstraints();
		gbc_lblChooseEntity.anchor = GridBagConstraints.WEST;
		gbc_lblChooseEntity.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseEntity.gridx = 0;
		gbc_lblChooseEntity.gridy = 0;
		panelPowerUpDataProcess.add(lblChooseEntity, gbc_lblChooseEntity);

		comboBoxEntity = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEntity = new GridBagConstraints();
		gbc_comboBoxEntity.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEntity.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEntity.gridx = 1;
		gbc_comboBoxEntity.gridy = 0;
		panelPowerUpDataProcess.add(comboBoxEntity, gbc_comboBoxEntity);

		btnUndoLastPowerUp = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.btnUndoLastPowerUp"));
		btnUndoLastPowerUp.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnUndoLastPowerUp-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panelPowerUpDataProcess.add(btnUndoLastPowerUp, gbc_btnNewButton);

		btnPowerUp = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.btnPowerUp"));
		btnPowerUp.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnPowerUpDataProc-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnPowerUp = new GridBagConstraints();
		gbc_btnPowerUp.fill = GridBagConstraints.BOTH;
		gbc_btnPowerUp.gridwidth = 2;
		gbc_btnPowerUp.gridx = 0;
		gbc_btnPowerUp.gridy = 2;
		panelPowerUpDataProcess.add(btnPowerUp, gbc_btnPowerUp);
	}

}
