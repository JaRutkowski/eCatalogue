package com.javafee.mainform;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.javafee.model.WorkerTableModel;

import lombok.Getter;
import net.coderazzi.filters.gui.TableFilterHeader;

public class WorkerTablePanel extends JPanel {

	private static final long serialVersionUID = -6644685896316330046L;

	@Getter
	private JTable workerTable;

	public WorkerTablePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		workerTable = new JTable();
		@SuppressWarnings("unused")
		TableFilterHeader tableFilterHeader = new TableFilterHeader(workerTable);
		workerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		workerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		workerTable.setModel(new WorkerTableModel());
		workerTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(workerTable);

	}

}
