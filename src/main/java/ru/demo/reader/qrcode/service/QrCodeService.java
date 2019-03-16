package ru.demo.reader.qrcode.service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Service
public class QrCodeService {

    public String decode(MultipartFile qrCodeImageFile) throws IOException {
        validate(qrCodeImageFile);
        return decodeInternal(qrCodeImageFile.getInputStream());
    }

    private String decodeInternal(InputStream qrCodeInputStream) {
        BufferedImage bufferedImage = readImage(qrCodeInputStream);
        BinaryBitmap bitmap = convert(bufferedImage);
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            log.warn("#decode: There is no QR code in the image");
            throw new IllegalArgumentException("There is no QR code in the image.");
        }
    }

    private BufferedImage readImage(InputStream io) {
        try {
            return ImageIO.read(io);
        } catch (IOException ex) {
            log.error("#readImage:", ex);
            throw new IllegalStateException(ex);
        }
    }

    private BinaryBitmap convert(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        return new BinaryBitmap(new HybridBinarizer(source));
    }

    private void validate(MultipartFile file) {
        Objects.requireNonNull(file, "file");

        if (file.isEmpty()) {
            log.warn("#validate: empty file({})", file.getOriginalFilename());
            throw new IllegalArgumentException("File is empty.");
        }
    }
}
