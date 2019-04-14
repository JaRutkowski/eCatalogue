package com.javafee.uniform;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.javafee.common.SystemProperties;
import com.javafee.common.Utils;
import com.javafee.startform.RegistrationPanel;

import lombok.Getter;

public class CockpitEditionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@Getter
	private JButton btnAdd;
	@Getter
	private JButton btnRemove;
	@Getter
	private JButton btnCarPartTypeAdd;
	@Getter
	private JButton btnPowerUpData;

	public CockpitEditionPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setBackground(Utils.getApplicationUserDefineColor());

		btnAdd = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("cockpitEditionPanel.btnAdd"));
		btnAdd.setIcon(new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnAddToList-ico.png"))
				.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		add(btnAdd, gbc_btnAdd);

		btnRemove = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("cockpitEditionPanel.btnRemove"));
		btnRemove.setIcon(new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnRemove-ico.png"))
				.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemove.fill = GridBagConstraints.BOTH;
		gbc_btnRemove.gridx = 1;
		gbc_btnRemove.gridy = 0;
		add(btnRemove, gbc_btnRemove);

		btnCarPartTypeAdd = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("cockpitEditionPanel.btnCarPartTypeAdd"));
		btnCarPartTypeAdd
				.setIcon(new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnModify-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnCarPartTypeAdd = new GridBagConstraints();
		gbc_btnCarPartTypeAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnCarPartTypeAdd.gridx = 2;
		gbc_btnCarPartTypeAdd.gridy = 0;
		add(btnCarPartTypeAdd, gbc_btnCarPartTypeAdd);

		btnPowerUpData = new JButton(
				SystemProperties.getInstance().getResourceBundle().getString("cockpitEditionPanel.btnPowerUpData"));
		btnPowerUpData.setIcon(
				new ImageIcon(new ImageIcon(RegistrationPanel.class.getResource("/images/btnPowerUpData-ico.png"))
						.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnPowerUpData = new GridBagConstraints();
		gbc_btnPowerUpData.gridx = 3;
		gbc_btnPowerUpData.gridy = 0;
		add(btnPowerUpData, gbc_btnPowerUpData);
	}
}
