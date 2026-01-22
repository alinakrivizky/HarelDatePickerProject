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

public class Listener implements WebDriverListener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    // Логи START/END для всех вызовов WebDriver
    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        logger.info("START: {}", method.getName());
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        logger.info("END: {}", method.getName());
    }

    // Скриншоты и лог ошибок
    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        logger.error("ERROR in method: {}", method.getName());
        logger.error("Exception: ", e.getTargetException());

        if (target instanceof WebDriver) {
            takeScreenshot((WebDriver) target, method.getName());
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




