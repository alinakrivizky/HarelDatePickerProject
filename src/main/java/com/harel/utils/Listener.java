package com.harel.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import io.qameta.allure.Attachment;

public class Listener implements WebDriverListener {

    private static final Logger logger =
            LoggerFactory.getLogger(Listener.class);
    @Attachment(value = "{name}", type = "image/png")
    public byte[] attachScreenshotToAllure(String name, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        logger.info("START: {}", method.getName());
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        logger.info("END: {}", method.getName());
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {

        logger.error("ERROR in method: {}", method.getName());
        logger.error("Exception: ", e.getTargetException());

        if (target instanceof WebDriver) {
            takeScreenshot((WebDriver) target, method.getName());
            attachScreenshotToAllure(method.getName(), (WebDriver) target);
        }
    }

    private void takeScreenshot(WebDriver driver, String methodName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String time = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            File dest = new File(
                    System.getProperty("user.dir")
                            + "/screenshots/"
                            + methodName + "_" + time + ".png"
            );

            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());

            logger.info("Screenshot saved: {}", dest.getAbsolutePath());

        } catch (Exception ex) {
            logger.error("Failed to take screenshot", ex);
        }
    }
}










