package com.javafee.mainform.frames;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitConfirmationPanel;

import lombok.Getter;

public class CarTypeAddFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private JTextField textFieldManufacturer;
	@Getter
	private JTextField textFieldModel;
	@Getter
	private JTextField textFieldFuelKind;
	@Getter
	private JTextField textFieldPower;
	@Getter
	private JTextField textFieldEngine;
	@Getter
	private CockpitConfirmationPanel cockpitConfirmationPanel;

	public CarTypeAddFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 460, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{190, -148, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("carAddFrame.borderTitle" + ""),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
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

		JLabel lblManufacturer = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.manufacturerCol") + ":");
		GridBagConstraints gbc_lblManufacturer = new GridBagConstraints();
		gbc_lblManufacturer.anchor = GridBagConstraints.EAST;
		gbc_lblManufacturer.insets = new Insets(5, 0, 5, 5);
		gbc_lblManufacturer.gridx = 0;
		gbc_lblManufacturer.gridy = 0;
		panel.add(lblManufacturer, gbc_lblManufacturer);

		textFieldManufacturer = new JTextField();
		GridBagConstraints gbc_textFieldManufacturer = new GridBagConstraints();
		gbc_textFieldManufacturer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldManufacturer.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldManufacturer.gridx = 1;
		gbc_textFieldManufacturer.gridy = 0;
		panel.add(textFieldManufacturer, gbc_textFieldManufacturer);
		textFieldManufacturer.setColumns(10);

		JLabel lblModel = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.modelCol") + ":");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.EAST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 1;
		panel.add(lblModel, gbc_lblModel);

		textFieldModel = new JTextField();
		GridBagConstraints gbc_textFieldModel = new GridBagConstraints();
		gbc_textFieldModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldModel.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldModel.gridx = 1;
		gbc_textFieldModel.gridy = 1;
		panel.add(textFieldModel, gbc_textFieldModel);
		textFieldModel.setColumns(10);

		JLabel lblFuelKind = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.fuelKindCol") + ":");
		GridBagConstraints gbc_lblFuelKind = new GridBagConstraints();
		gbc_lblFuelKind.anchor = GridBagConstraints.EAST;
		gbc_lblFuelKind.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuelKind.gridx = 0;
		gbc_lblFuelKind.gridy = 2;
		panel.add(lblFuelKind, gbc_lblFuelKind);

		textFieldFuelKind = new JTextField();
		GridBagConstraints gbc_textFieldFuelKind = new GridBagConstraints();
		gbc_textFieldFuelKind.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFuelKind.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFuelKind.gridx = 1;
		gbc_textFieldFuelKind.gridy = 2;
		panel.add(textFieldFuelKind, gbc_textFieldFuelKind);
		textFieldFuelKind.setColumns(10);

		JLabel lblPower = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.powerCol") + ":");
		GridBagConstraints gbc_lblPower = new GridBagConstraints();
		gbc_lblPower.anchor = GridBagConstraints.EAST;
		gbc_lblPower.insets = new Insets(0, 0, 5, 5);
		gbc_lblPower.gridx = 0;
		gbc_lblPower.gridy = 3;
		panel.add(lblPower, gbc_lblPower);

		textFieldPower = new JTextField();
		GridBagConstraints gbc_textFieldPower = new GridBagConstraints();
		gbc_textFieldPower.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPower.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPower.gridx = 1;
		gbc_textFieldPower.gridy = 3;
		panel.add(textFieldPower, gbc_textFieldPower);
		textFieldPower.setColumns(10);

		JLabel lblEngine = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.engineCol") + ":");
		GridBagConstraints gbc_lblEngine = new GridBagConstraints();
		gbc_lblEngine.anchor = GridBagConstraints.EAST;
		gbc_lblEngine.insets = new Insets(0, 0, 0, 5);
		gbc_lblEngine.gridx = 0;
		gbc_lblEngine.gridy = 4;
		panel.add(lblEngine, gbc_lblEngine);

		textFieldEngine = new JTextField();
		GridBagConstraints gbc_textFieldEngine = new GridBagConstraints();
		gbc_textFieldEngine.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEngine.gridx = 1;
		gbc_textFieldEngine.gridy = 4;
		panel.add(textFieldEngine, gbc_textFieldEngine);
		textFieldEngine.setColumns(10);

		cockpitConfirmationPanel = new CockpitConfirmationPanel();
		cockpitConfirmationPanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(cockpitConfirmationPanel, gbc_panel_1);
	}

}
