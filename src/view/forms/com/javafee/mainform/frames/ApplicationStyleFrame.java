package com.javafee.mainform.frames;

import java.awt.*;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitConfirmationPanel;

import lombok.Getter;

public class ApplicationStyleFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private JColorChooser colorChooser;
	@Getter
	private CockpitConfirmationPanel confirmationPanel;

	public ApplicationStyleFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 707, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{248, 0};
		gbl_contentPane.rowHeights = new int[]{0, 238, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblChooseAppColor = new JLabel(SystemProperties.getInstance().getResourceBundle()
				.getString("applicationStyleFrame.lblChooseAppColor"));
		GridBagConstraints gbc_lblChooseAppColor = new GridBagConstraints();
		gbc_lblChooseAppColor.insets = new Insets(0, 0, 5, 0);
		gbc_lblChooseAppColor.gridx = 0;
		gbc_lblChooseAppColor.gridy = 0;
		contentPane.add(lblChooseAppColor, gbc_lblChooseAppColor);

		colorChooser = new JColorChooser();
		GridBagConstraints gbc_colorChooser = new GridBagConstraints();
		gbc_colorChooser.insets = new Insets(0, 0, 5, 0);
		gbc_colorChooser.gridx = 0;
		gbc_colorChooser.gridy = 1;
		contentPane.add(colorChooser, gbc_colorChooser);

		confirmationPanel = new CockpitConfirmationPanel();
		GridBagConstraints gbc_confirmationPanel = new GridBagConstraints();
		gbc_confirmationPanel.fill = GridBagConstraints.BOTH;
		gbc_confirmationPanel.gridx = 0;
		gbc_confirmationPanel.gridy = 2;
		contentPane.add(confirmationPanel, gbc_confirmationPanel);
	}

}
