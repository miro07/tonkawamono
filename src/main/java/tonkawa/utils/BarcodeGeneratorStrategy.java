package utils;

import java.awt.image.BufferedImage;

public interface BarcodeGeneratorStrategy {

    BufferedImage generate(String jsonData, int width, int height);
}
