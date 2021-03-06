package com.javafee.common;

import java.awt.*;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import com.javafee.startform.LogInEvent;

public class Utils {
	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static Color getApplicationDefaultColor() {
		return new Color(225, 228, 234);
	}

	public static Color getApplicationUserDefineColor() {
		String color = LogInEvent.getSystemProperties() != null ? LogInEvent.getSystemProperties().getColor() : null;
		Color userDefineColor = null;

		if ("".equals(color) || color == null)
			userDefineColor = getApplicationDefaultColor();
		else {
			String[] rgb = color.split(",");
			userDefineColor = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
		}
		return userDefineColor;
	}

	public static void displayOptionPane(String message, String title, int messageType) {
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage("<html>" + message + "</html>");
		optionPane.setMessageType(messageType);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setVisible(true);
	}

	public static int displayConfirmDialog(String message, String title) {
		Object[] options = {SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.yes"),
				SystemProperties.getInstance().getResourceBundle().getString("confirmDialog.no")};
		return JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}

	public static File displayJFileChooserAndGetFile(String directory) {
		String dir = directory != null ? directory : FileSystemView.getFileSystemView().getHomeDirectory().toString();
		JFileChooser jfc = new JFileChooser(dir);
		jfc.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(Constants.APPLICATION_XLSX_EXTENSION)
						|| f.getName().toLowerCase().endsWith(Constants.APPLICATION_XLS_EXTENSION) || f.isDirectory();
			}

			public String getDescription() {
				return Constants.APPLICATION_XLS_XLSX_EXTENSION_DESCRIPTION;
			}
		});

		File result = null;

		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			result = jfc.getSelectedFile();
		}

		return result;
	}
}
