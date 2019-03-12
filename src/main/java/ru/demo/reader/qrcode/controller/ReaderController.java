package ru.demo.reader.qrcode.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.demo.reader.qrcode.DemoQrCodeReaderApplication;
import ru.demo.reader.qrcode.service.QrCodeService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(DemoQrCodeReaderApplication.APP_ROOT_PATH + "/reader")
public class ReaderController {

    private final QrCodeService qrCodeService;

    @PostMapping("/")
    public String read(@RequestParam MultipartFile qrCodeImage) throws Exception {
        log.debug("#read: img:{}", qrCodeImage.getOriginalFilename());
        return qrCodeService.read(qrCodeImage);
    }
}