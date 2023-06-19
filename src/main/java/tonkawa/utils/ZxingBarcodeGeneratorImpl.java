package utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import exceptions.BarcodeGenerationException;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ZxingBarcodeGeneratorImpl implements BarcodeGeneratorStrategy {

    @Override
    public BufferedImage generate(String jsonData, int width, int height) {
       try{
            Code128Writer writer = new Code128Writer();
            BitMatrix bitMatrix = writer.encode(jsonData, BarcodeFormat.CODE_128, width, height);

            int onColor = 0xFF000000;
            int offColor = 0xFFFFFFFF;
            MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);

            return MatrixToImageWriter.toBufferedImage(bitMatrix, config);

        } catch(Exception e) {
           throw new BarcodeGenerationException("Barcode generation failed. Error: " + e.getMessage(), e);
        }

}
}
