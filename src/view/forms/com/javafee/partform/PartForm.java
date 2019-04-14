package com.javafee.partform;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javafee.common.Constants;
import com.javafee.common.Utils;
import com.javafee.mainform.PartTypeTablePanel;
import com.javafee.startform.StartForm;
import com.javafee.uniform.CockpitEditionPanel;

import lombok.Getter;

public class PartForm extends JFrame {

	private static final long serialVersionUID = -3608994423760373741L;

	private JPanel contentPane;
	@Getter
	private PartTypeTablePanel partTablePanel;
	@Getter
	private CockpitEditionPanel cockpitEditionPanel;

	public PartForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Constants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartForm.class.getResource("/images/eseller-ico.png")));
		setBounds(100, 100, 1071, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Utils.getApplicationUserDefineColor());
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 36, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		partTablePanel = new PartTypeTablePanel();
		partTablePanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_carTypeTablePanel = new GridBagConstraints();
		gbc_carTypeTablePanel.insets = new Insets(0, 0, 5, 0);
		gbc_carTypeTablePanel.fill = GridBagConstraints.BOTH;
		gbc_carTypeTablePanel.gridx = 0;
		gbc_carTypeTablePanel.gridy = 1;
		contentPane.add(partTablePanel, gbc_carTypeTablePanel);

		cockpitEditionPanel = new CockpitEditionPanel();
		cockpitEditionPanel.setBackground(Utils.getApplicationUserDefineColor());
		GridBagConstraints gbc_cockpitEditionPanel = new GridBagConstraints();
		gbc_cockpitEditionPanel.fill = GridBagConstraints.BOTH;
		gbc_cockpitEditionPanel.gridx = 0;
		gbc_cockpitEditionPanel.gridy = 2;
		contentPane.add(cockpitEditionPanel, gbc_cockpitEditionPanel);

	}

}
