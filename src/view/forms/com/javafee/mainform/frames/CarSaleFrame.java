package com.javafee.mainform.frames;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.startform.RegistrationPanel;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitConfirmationPanel;

import lombok.Getter;

public class CarSaleFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private RegistrationPanel clientDataPanel;
	@Getter
	private CockpitConfirmationPanel cockpitConfirmationPanel;
	private JLabel lblRemark;
	@Getter
	private JTextArea textAreaRemark;

	public CarSaleFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 509, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 248, 0};
		gbl_contentPane.rowHeights = new int[]{238, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		clientDataPanel = new RegistrationPanel();
		clientDataPanel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle()
						.getString("registrationPanel.clientRegistrationPanelBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clientDataPanel.setBackground(Utils.getApplicationUserDefineColor());
		clientDataPanel.getLblLogin().setVisible(false);
		clientDataPanel.getTextFieldLogin().setVisible(false);
		clientDataPanel.getLblPassword().setVisible(false);
		clientDataPanel.getPasswordField().setVisible(false);
		clientDataPanel.getBtnRegisterNow().setVisible(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(clientDataPanel, gbc_panel);

		lblRemark = new JLabel(SystemProperties.getInstance().getResourceBundle().getString("carSellFrame.lblRemark"));
		GridBagConstraints gbc_lblRemark = new GridBagConstraints();
		gbc_lblRemark.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemark.gridx = 0;
		gbc_lblRemark.gridy = 1;
		contentPane.add(lblRemark, gbc_lblRemark);

		textAreaRemark = new JTextArea();
		textAreaRemark.setRows(5);
		GridBagConstraints gbc_textAreaRemark = new GridBagConstraints();
		gbc_textAreaRemark.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaRemark.fill = GridBagConstraints.BOTH;
		gbc_textAreaRemark.gridx = 1;
		gbc_textAreaRemark.gridy = 1;
		contentPane.add(textAreaRemark, gbc_textAreaRemark);

		cockpitConfirmationPanel = new CockpitConfirmationPanel();
		cockpitConfirmationPanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(cockpitConfirmationPanel, gbc_panel_1);
	}

}
