package ru.demo.reader.qrcode.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.demo.reader.qrcode.DemoQrCodeReaderApplication;
import ru.demo.reader.qrcode.util.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoQrCodeReaderApplication.class)
class QrCodeServiceTest {

    private final String FILE_QR_CODE = "images/01_qrcode.png";

    @Autowired
    private QrCodeService qrCodeService;

    @Test
    void read_null_file() {
        assertThrows(NullPointerException.class, () -> qrCodeService.decode(null));
    }

    @Test
    void read_empty_file() {
        //given
        MultipartFile file = new MockMultipartFile("file_name", new byte[0]);
        //when-then
        assertThrows(IllegalArgumentException.class, () -> qrCodeService.decode(file));
    }

    @Test
    void read_file_success() throws Exception {
        //given
        byte[] fileBytes = TestUtils.readBytes(FILE_QR_CODE);
        MultipartFile file = new MockMultipartFile("file_name", fileBytes);
        //when
        String result = qrCodeService.decode(file);
        //then
        assertEquals("Hello max", result);
    }
}