package com.javafee.mainform.frames;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.javafee.common.Constants;
import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.mainform.PowerUpDataPanel;
import com.javafee.startform.StartForm;

import lombok.Getter;

public class PowerUpDataFrame extends JFrame {

	private static final long serialVersionUID = -31457200324840715L;

	private JPanel contentPane;
	@Getter
	private PowerUpDataPanel powerUpDataPanel;

	public PowerUpDataFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 509, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 248, 0};
		gbl_contentPane.rowHeights = new int[]{238, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		powerUpDataPanel = new PowerUpDataPanel();
		powerUpDataPanel.getLblChoosenFile().setForeground(Color.RED);
		powerUpDataPanel.setBorder(new TitledBorder(null,
				SystemProperties.getInstance().getResourceBundle().getString("powerUpData.powerUpDataBorderTitle"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		powerUpDataPanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(powerUpDataPanel, gbc_panel);
	}

}
