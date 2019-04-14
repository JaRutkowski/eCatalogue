package com.javafee.partform.frames;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.hibernate.dto.association.scan.CarPartType;
import com.javafee.startform.RegistrationPanel;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitConfirmationPanel;
import com.javafee.uniform.ImagePanel;

import lombok.Getter;
import lombok.Setter;

public class PartAddFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private JTextField textFieldName;
	@Getter
	private CockpitConfirmationPanel cockpitConfirmationPanel;
	@Getter
	private JComboBox<CarPartType> comboBoxCarPartType;
	@Getter
	private ImagePanel imageViewPanel;
	private JLabel lblImage;
	@Getter
	@Setter
	private JTextField textFieldImagePath;
	@Getter
	private JButton btnChooseImageFile;

	public PartAddFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 1306, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 472, 0};
		gbl_contentPane.rowHeights = new int[]{558, 62, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("partAddFrame.borderTitle" + ""),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblManufacturer = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("partTableModel.nameCol") + ":");
		GridBagConstraints gbc_lblManufacturer = new GridBagConstraints();
		gbc_lblManufacturer.anchor = GridBagConstraints.EAST;
		gbc_lblManufacturer.insets = new Insets(5, 0, 5, 5);
		gbc_lblManufacturer.gridx = 0;
		gbc_lblManufacturer.gridy = 0;
		panel.add(lblManufacturer, gbc_lblManufacturer);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 2;
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		panel.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel lblPartType = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("partTableModel.partTypeNameCol") + ":");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.EAST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 1;
		panel.add(lblPartType, gbc_lblModel);

		comboBoxCarPartType = new JComboBox<CarPartType>();
		GridBagConstraints gbc_comboBoxPartType = new GridBagConstraints();
		gbc_comboBoxPartType.gridwidth = 2;
		gbc_comboBoxPartType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPartType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPartType.gridx = 1;
		gbc_comboBoxPartType.gridy = 1;
		panel.add(comboBoxCarPartType, gbc_comboBoxPartType);

		lblImage = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("partAddFrame.lblImage") + ":");
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.anchor = GridBagConstraints.EAST;
		gbc_lblImage.insets = new Insets(0, 0, 0, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 2;
		panel.add(lblImage, gbc_lblImage);

		textFieldImagePath = new JTextField();
		textFieldImagePath.setEditable(false);
		textFieldImagePath.setEnabled(false);
		GridBagConstraints gbc_textFieldImagePath = new GridBagConstraints();
		gbc_textFieldImagePath.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldImagePath.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldImagePath.gridx = 1;
		gbc_textFieldImagePath.gridy = 2;
		panel.add(textFieldImagePath, gbc_textFieldImagePath);
		textFieldImagePath.setColumns(10);

		btnChooseImageFile = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("partAddFrame.btnChooseImageFile"));
		btnChooseImageFile.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnChooseFile-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_bntChooseImageFile = new GridBagConstraints();
		gbc_bntChooseImageFile.gridx = 2;
		gbc_bntChooseImageFile.gridy = 2;
		panel.add(btnChooseImageFile, gbc_bntChooseImageFile);

		imageViewPanel = new ImagePanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(imageViewPanel, gbc_panel_1);

		cockpitConfirmationPanel = new CockpitConfirmationPanel();
		cockpitConfirmationPanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel_confirmation = new GridBagConstraints();
		gbc_panel_confirmation.insets = new Insets(0, 0, 0, 5);
		gbc_panel_confirmation.fill = GridBagConstraints.BOTH;
		gbc_panel_confirmation.gridx = 0;
		gbc_panel_confirmation.gridy = 1;
		contentPane.add(cockpitConfirmationPanel, gbc_panel_confirmation);
	}

}
