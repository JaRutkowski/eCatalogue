package com.javafee.mainform.frames;

import java.awt.*;

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
import com.javafee.hibernate.dto.association.scan.CarType;
import com.javafee.mainform.CarPartTypeTablePanel;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitConfirmationPanel;

import lombok.Getter;

public class CarPartTypeAddFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private JTextField textFieldName;
	@Getter
	private JComboBox<CarType> comboBoxCarType;
	@Getter
	private CockpitConfirmationPanel cockpitConfirmationPanel;
	private JLabel lblDescription;
	@Getter
	private JTextField textFieldDescription;
	private JLabel lblOrigin;
	@Getter
	private JTextField textFieldOrigin;
	private JLabel lblProducer;
	@Getter
	private JTextField textFieldProducer;
	@Getter
	private CarPartTypeTablePanel carPartTypeTablePanel;

	public CarPartTypeAddFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 1068, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{438, 239, 0};
		gbl_contentPane.rowHeights = new int[]{165, -148, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.borderTitle" + ""),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblName = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblName") + ":");
		GridBagConstraints gbc_lblManufacturer = new GridBagConstraints();
		gbc_lblManufacturer.anchor = GridBagConstraints.EAST;
		gbc_lblManufacturer.insets = new Insets(5, 0, 5, 5);
		gbc_lblManufacturer.gridx = 0;
		gbc_lblManufacturer.gridy = 0;
		panel.add(lblName, gbc_lblManufacturer);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldManufacturer = new GridBagConstraints();
		gbc_textFieldManufacturer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldManufacturer.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldManufacturer.gridx = 1;
		gbc_textFieldManufacturer.gridy = 0;
		panel.add(textFieldName, gbc_textFieldManufacturer);
		textFieldName.setColumns(10);

		lblDescription = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblDescription") + ":");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		panel.add(lblDescription, gbc_lblDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textFieldDescription, gbc_textField);
		textFieldDescription.setColumns(10);

		lblOrigin = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblOrigin") + ":");
		GridBagConstraints gbc_lblOrigin = new GridBagConstraints();
		gbc_lblOrigin.anchor = GridBagConstraints.EAST;
		gbc_lblOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigin.gridx = 0;
		gbc_lblOrigin.gridy = 2;
		panel.add(lblOrigin, gbc_lblOrigin);

		textFieldOrigin = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(textFieldOrigin, gbc_textField_1);
		textFieldOrigin.setColumns(10);

		lblProducer = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.lblProducer") + ":");
		GridBagConstraints gbc_lblProducer = new GridBagConstraints();
		gbc_lblProducer.anchor = GridBagConstraints.EAST;
		gbc_lblProducer.insets = new Insets(0, 0, 5, 5);
		gbc_lblProducer.gridx = 0;
		gbc_lblProducer.gridy = 3;
		panel.add(lblProducer, gbc_lblProducer);

		textFieldProducer = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panel.add(textFieldProducer, gbc_textField_2);
		textFieldProducer.setColumns(10);

		JLabel lblCarType = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carPartAddFrame.carType") + ":");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.EAST;
		gbc_lblModel.insets = new Insets(0, 0, 0, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 4;
		panel.add(lblCarType, gbc_lblModel);

		comboBoxCarType = new JComboBox<CarType>();
		GridBagConstraints gbc_comboBoxCarType = new GridBagConstraints();
		gbc_comboBoxCarType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCarType.gridx = 1;
		gbc_comboBoxCarType.gridy = 4;
		panel.add(comboBoxCarType, gbc_comboBoxCarType);

		carPartTypeTablePanel = new CarPartTypeTablePanel();
		GridBagConstraints gbc_carPartTypeTablePanel = new GridBagConstraints();
		gbc_carPartTypeTablePanel.insets = new Insets(0, 0, 5, 0);
		gbc_carPartTypeTablePanel.fill = GridBagConstraints.BOTH;
		gbc_carPartTypeTablePanel.gridx = 1;
		gbc_carPartTypeTablePanel.gridy = 0;
		contentPane.add(carPartTypeTablePanel, gbc_carPartTypeTablePanel);

		cockpitConfirmationPanel = new CockpitConfirmationPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(cockpitConfirmationPanel, gbc_panel_1);
	}

}
