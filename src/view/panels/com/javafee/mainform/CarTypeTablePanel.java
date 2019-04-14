package com.javafee.mainform;

import com.javafee.model.CarTypeTableModel;

public class CarTypeTablePanel extends TablePanel {

	private static final long serialVersionUID = -6644685896316330046L;

	public CarTypeTablePanel() {
		super(new CarTypeTableModel());
	}

}
