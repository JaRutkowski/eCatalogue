package com.javafee.mainform;

import java.awt.*;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;

import lombok.Getter;

public class InvoiceDataPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@Getter
	private ButtonGroup groupRadioButtonSex;
	@Getter
	private JLabel lblManufactureDat;
	@Getter
	private JLabel lblModelDat;
	@Getter
	private JLabel lblPriceDat;
	@Getter
	private JLabel lblProductionYearDat;
	@Getter
	private JLabel lblFuelKindDat;
	@Getter
	private JLabel lblPowerDat;
	@Getter
	private JLabel lblEngineDat;
	@Getter
	private JLabel lblCourseDat;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblPesel;
	@Getter
	private JLabel lblPeselDat;
	private JLabel lblDocumentNumber;
	@Getter
	private JLabel lblDocumentNumberDat;
	private JLabel lblName;
	@Getter
	private JLabel lblNameDat;
	private JLabel lblSurname;
	@Getter
	private JLabel lblSurnameDat;
	private JLabel lblAddress;
	@Getter
	private JLabel lblAddressDat;
	private JLabel lblCity;
	@Getter
	private JLabel lblCityDat;
	private JLabel lblBirthDate;
	@Getter
	private JLabel lblBirthDateDat;
	private JLabel lblEMail;
	@Getter
	private JLabel lblEMailDat;
	private JPanel panel_2;
	private JLabel lblSellerName;
	@Getter
	private JLabel lblSellerNameDat;
	private JLabel lblSellerSurname;
	@Getter
	private JLabel lblSellerSurnameDat;
	private JLabel lblSellerLogin;
	@Getter
	private JLabel lblSellerLoginDat;
	private JLabel lblSellerEmail;
	@Getter
	private JLabel lblSellerEmailDat;

	public InvoiceDataPanel() {
		setBackground(Utils.getApplicationUserDefineColor());
		setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle()
						.getString("invoiceDataPanel.invoiceDataPanelBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 209, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle()
						.getString("invoiceDataPanel.invoiceDataPanelCarDataBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 130, 198, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblManufacture = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.manufacturerCol"));
		GridBagConstraints gbc_lblManufacture = new GridBagConstraints();
		gbc_lblManufacture.anchor = GridBagConstraints.WEST;
		gbc_lblManufacture.insets = new Insets(5, 0, 5, 5);
		gbc_lblManufacture.gridx = 0;
		gbc_lblManufacture.gridy = 0;
		panel.add(lblManufacture, gbc_lblManufacture);
		lblManufacture.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblManufactureDat = new JLabel();
		GridBagConstraints gbc_lblManufactureDat = new GridBagConstraints();
		gbc_lblManufactureDat.gridwidth = 2;
		gbc_lblManufactureDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblManufactureDat.gridx = 1;
		gbc_lblManufactureDat.gridy = 0;
		panel.add(lblManufactureDat, gbc_lblManufactureDat);

		JLabel lblModel = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.modelCol"));
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.WEST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 1;
		panel.add(lblModel, gbc_lblModel);
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblModelDat = new JLabel();
		GridBagConstraints gbc_lblModelDat = new GridBagConstraints();
		gbc_lblModelDat.gridwidth = 2;
		gbc_lblModelDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblModelDat.gridx = 1;
		gbc_lblModelDat.gridy = 1;
		panel.add(lblModelDat, gbc_lblModelDat);

		JLabel lblPrice = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.priceCol"));
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 2;
		panel.add(lblPrice, gbc_lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblPriceDat = new JLabel();
		GridBagConstraints gbc_lblPriceDat = new GridBagConstraints();
		gbc_lblPriceDat.gridwidth = 2;
		gbc_lblPriceDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblPriceDat.gridx = 1;
		gbc_lblPriceDat.gridy = 2;
		panel.add(lblPriceDat, gbc_lblPriceDat);

		JLabel lblProductionYear = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.productionYearCol"));
		GridBagConstraints gbc_lblProductionYear = new GridBagConstraints();
		gbc_lblProductionYear.anchor = GridBagConstraints.WEST;
		gbc_lblProductionYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductionYear.gridx = 0;
		gbc_lblProductionYear.gridy = 3;
		panel.add(lblProductionYear, gbc_lblProductionYear);
		lblProductionYear.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblProductionYearDat = new JLabel();
		GridBagConstraints gbc_lblProductionYearDat = new GridBagConstraints();
		gbc_lblProductionYearDat.gridwidth = 2;
		gbc_lblProductionYearDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductionYearDat.gridx = 1;
		gbc_lblProductionYearDat.gridy = 3;
		panel.add(lblProductionYearDat, gbc_lblProductionYearDat);

		JLabel lblFuelKind = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.fuelKindCol"));
		GridBagConstraints gbc_lblFuelKind = new GridBagConstraints();
		gbc_lblFuelKind.anchor = GridBagConstraints.WEST;
		gbc_lblFuelKind.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuelKind.gridx = 0;
		gbc_lblFuelKind.gridy = 4;
		panel.add(lblFuelKind, gbc_lblFuelKind);
		lblFuelKind.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblFuelKindDat = new JLabel();
		GridBagConstraints gbc_lblFuelKindDat = new GridBagConstraints();
		gbc_lblFuelKindDat.gridwidth = 2;
		gbc_lblFuelKindDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblFuelKindDat.gridx = 1;
		gbc_lblFuelKindDat.gridy = 4;
		panel.add(lblFuelKindDat, gbc_lblFuelKindDat);

		JLabel lblPower = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.powerCol"));
		GridBagConstraints gbc_lblPower = new GridBagConstraints();
		gbc_lblPower.anchor = GridBagConstraints.WEST;
		gbc_lblPower.insets = new Insets(0, 0, 5, 5);
		gbc_lblPower.gridx = 0;
		gbc_lblPower.gridy = 5;
		panel.add(lblPower, gbc_lblPower);
		lblPower.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblPowerDat = new JLabel();
		GridBagConstraints gbc_lblPowerDat = new GridBagConstraints();
		gbc_lblPowerDat.gridwidth = 2;
		gbc_lblPowerDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblPowerDat.gridx = 1;
		gbc_lblPowerDat.gridy = 5;
		panel.add(lblPowerDat, gbc_lblPowerDat);

		JLabel lblEngine = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.engineCol"));
		GridBagConstraints gbc_lblEngine = new GridBagConstraints();
		gbc_lblEngine.anchor = GridBagConstraints.WEST;
		gbc_lblEngine.insets = new Insets(0, 0, 5, 5);
		gbc_lblEngine.gridx = 0;
		gbc_lblEngine.gridy = 6;
		panel.add(lblEngine, gbc_lblEngine);
		lblEngine.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblEngineDat = new JLabel();
		GridBagConstraints gbc_lblEngineDat = new GridBagConstraints();
		gbc_lblEngineDat.gridwidth = 2;
		gbc_lblEngineDat.insets = new Insets(0, 0, 5, 0);
		gbc_lblEngineDat.gridx = 1;
		gbc_lblEngineDat.gridy = 6;
		panel.add(lblEngineDat, gbc_lblEngineDat);

		JLabel lblCourse = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("carTableModel.courseCol"));
		GridBagConstraints gbc_lblCourse = new GridBagConstraints();
		gbc_lblCourse.anchor = GridBagConstraints.WEST;
		gbc_lblCourse.insets = new Insets(0, 0, 0, 5);
		gbc_lblCourse.gridx = 0;
		gbc_lblCourse.gridy = 7;
		panel.add(lblCourse, gbc_lblCourse);
		lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblCourseDat = new JLabel();
		GridBagConstraints gbc_lblCourseDat = new GridBagConstraints();
		gbc_lblCourseDat.gridwidth = 2;
		gbc_lblCourseDat.insets = new Insets(0, 0, 0, 5);
		gbc_lblCourseDat.gridx = 1;
		gbc_lblCourseDat.gridy = 7;
		panel.add(lblCourseDat, gbc_lblCourseDat);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle()
						.getString("invoiceDataPanel.invoiceDataPanelClientDataBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{100, 130, 198, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		lblPesel = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblPesel"));
		lblPesel.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(5, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblPesel, gbc_lblNewLabel_1);

		lblPeselDat = new JLabel();
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 2;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 0;
		panel_1.add(lblPeselDat, gbc_label_1);

		lblDocumentNumber = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblDocumentNumber"));
		lblDocumentNumber.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel_1.add(lblDocumentNumber, gbc_label_2);

		lblDocumentNumberDat = new JLabel();
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridwidth = 2;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 1;
		panel_1.add(lblDocumentNumberDat, gbc_label_3);

		lblName = new JLabel(SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblName"));
		lblName.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 2;
		panel_1.add(lblName, gbc_label_4);

		lblNameDat = new JLabel();
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.gridwidth = 2;
		gbc_label_5.insets = new Insets(0, 0, 5, 0);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 2;
		panel_1.add(lblNameDat, gbc_label_5);

		lblSurname = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblSurname"));
		lblSurname.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 3;
		panel_1.add(lblSurname, gbc_label_6);

		lblSurnameDat = new JLabel();
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.gridwidth = 2;
		gbc_label_7.insets = new Insets(0, 0, 5, 0);
		gbc_label_7.gridx = 1;
		gbc_label_7.gridy = 3;
		panel_1.add(lblSurnameDat, gbc_label_7);

		lblAddress = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblAddress"));
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.WEST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 4;
		panel_1.add(lblAddress, gbc_label_8);

		lblAddressDat = new JLabel();
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.gridwidth = 2;
		gbc_label_9.insets = new Insets(0, 0, 5, 0);
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 4;
		panel_1.add(lblAddressDat, gbc_label_9);

		lblCity = new JLabel(SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblCity"));
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.WEST;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 0;
		gbc_label_10.gridy = 5;
		panel_1.add(lblCity, gbc_label_10);

		lblCityDat = new JLabel();
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.gridwidth = 2;
		gbc_label_11.insets = new Insets(0, 0, 5, 0);
		gbc_label_11.gridx = 1;
		gbc_label_11.gridy = 5;
		panel_1.add(lblCityDat, gbc_label_11);

		lblBirthDate = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblBirthDate"));
		lblBirthDate.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.WEST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 0;
		gbc_label_12.gridy = 6;
		panel_1.add(lblBirthDate, gbc_label_12);

		lblBirthDateDat = new JLabel();
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.gridwidth = 2;
		gbc_label_13.insets = new Insets(0, 0, 5, 0);
		gbc_label_13.gridx = 1;
		gbc_label_13.gridy = 6;
		panel_1.add(lblBirthDateDat, gbc_label_13);

		lblEMail = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblEMail"));
		lblEMail.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.anchor = GridBagConstraints.WEST;
		gbc_label_14.insets = new Insets(0, 0, 0, 5);
		gbc_label_14.gridx = 0;
		gbc_label_14.gridy = 7;
		panel_1.add(lblEMail, gbc_label_14);

		lblEMailDat = new JLabel();
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.insets = new Insets(0, 0, 0, 5);
		gbc_label_15.gridwidth = 2;
		gbc_label_15.gridx = 1;
		gbc_label_15.gridy = 7;
		panel_1.add(lblEMailDat, gbc_label_15);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle()
						.getString("invoiceDataPanel.invoiceDataPanelSellerDataBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{100, 130, 198, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		lblSellerName = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblName"));
		lblSellerName.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.WEST;
		gbc_label1.insets = new Insets(15, 0, 5, 5);
		gbc_label1.gridx = 0;
		gbc_label1.gridy = 0;
		panel_2.add(lblSellerName, gbc_label1);

		lblSellerNameDat = new JLabel();
		GridBagConstraints gbc_label_111 = new GridBagConstraints();
		gbc_label_111.gridwidth = 2;
		gbc_label_111.insets = new Insets(0, 0, 5, 0);
		gbc_label_111.gridx = 1;
		gbc_label_111.gridy = 0;
		panel_2.add(lblSellerNameDat, gbc_label_111);

		lblSellerSurname = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblSurname"));
		lblSellerSurname.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_21 = new GridBagConstraints();
		gbc_label_21.anchor = GridBagConstraints.WEST;
		gbc_label_21.insets = new Insets(0, 0, 5, 5);
		gbc_label_21.gridx = 0;
		gbc_label_21.gridy = 1;
		panel_2.add(lblSellerSurname, gbc_label_21);

		lblSellerSurnameDat = new JLabel();
		GridBagConstraints gbc_label_31 = new GridBagConstraints();
		gbc_label_31.gridwidth = 2;
		gbc_label_31.insets = new Insets(0, 0, 5, 0);
		gbc_label_31.gridx = 1;
		gbc_label_31.gridy = 1;
		panel_2.add(lblSellerSurnameDat, gbc_label_31);

		lblSellerEmail = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblEMail"));
		lblSellerEmail.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 2;
		panel_2.add(lblSellerEmail, gbc_lblNewLabel_11);

		lblSellerEmailDat = new JLabel();
		GridBagConstraints gbc_lblNewLabel_111 = new GridBagConstraints();
		gbc_lblNewLabel_111.gridwidth = 2;
		gbc_lblNewLabel_111.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_111.gridx = 1;
		gbc_lblNewLabel_111.gridy = 2;
		panel_2.add(lblSellerEmailDat, gbc_lblNewLabel_111);

		lblSellerLogin = new JLabel(
				SystemProperties.getInstance().getResourceBundle().getString("registrationPanel.lblLogin"));
		lblSellerLogin.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_label_41 = new GridBagConstraints();
		gbc_label_41.anchor = GridBagConstraints.WEST;
		gbc_label_41.insets = new Insets(0, 0, 5, 5);
		gbc_label_41.gridx = 0;
		gbc_label_41.gridy = 3;
		panel_2.add(lblSellerLogin, gbc_label_41);

		lblSellerLoginDat = new JLabel();
		GridBagConstraints gbc_label_51 = new GridBagConstraints();
		gbc_label_51.gridwidth = 2;
		gbc_label_51.insets = new Insets(0, 0, 5, 0);
		gbc_label_51.gridx = 1;
		gbc_label_51.gridy = 3;
		panel_2.add(lblSellerLoginDat, gbc_label_51);
	}
}
