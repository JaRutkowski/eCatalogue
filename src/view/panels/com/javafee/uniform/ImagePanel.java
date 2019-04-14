package com.javafee.uniform;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 8194600901581133206L;

	@Getter
	@Setter
	private Image image;
	@Getter
	@Setter
	private Image scaledImage;

	public void loadImage(String filename) throws IOException {
		image = ImageIO.read(new File(filename));
		setScaledImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (scaledImage != null) {
			g.drawImage(scaledImage, 0, 0, this);
		}
	}

	private void setScaledImage() {
		if (image != null) {
			int imageWidth = image.getWidth(this);
			int imageHeight = image.getHeight(this);
			float iw = imageWidth;
			float ih = imageHeight;
			float pw = this.getWidth(); // panel width
			float ph = this.getHeight(); // panel height
			if (pw < iw || ph < ih) {
				if ((pw / ph) > (iw / ih)) {
					iw = -1;
					ih = ph;
				} else {
					iw = pw;
					ih = -1;
				}
				// prevent errors if panel is 0 wide or high
				if (iw == 0) {
					iw = -1;
				}
				if (ih == 0) {
					ih = -1;
				}
				scaledImage = image.getScaledInstance(new Float(iw).intValue(), new Float(ih).intValue(),
						Image.SCALE_DEFAULT);
			} else {
				scaledImage = image;
			}
		}
	}
}
