package ru.demo.reader.qrcode.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(description = "API сервиса работы с qr-кодом")
@RestController
@RequiredArgsConstructor
@RequestMapping(DemoQrCodeReaderApplication.APP_ROOT_PATH + "/reader")
public class ReaderController {

    private final QrCodeService qrCodeService;

    @ApiOperation(value = "Раскодировать данные")
    @ApiResponses({
                          @ApiResponse(code = 200, message = "Успушное выполнение операции."),
                          @ApiResponse(code = 400, message = "Ошибка параметров запроса."),
                          @ApiResponse(code = 500, message = "Внутренняя ошибка сервера.")})
    @PostMapping("/")
    public String decode(@ApiParam(value = "Изображение", required = true) @RequestParam MultipartFile qrCodeImage)
            throws Exception {
        log.debug("#decode: img:{}", qrCodeImage.getOriginalFilename());
        return qrCodeService.decode(qrCodeImage);
    }
}