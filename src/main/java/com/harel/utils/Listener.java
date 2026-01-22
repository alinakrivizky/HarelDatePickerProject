package com.harel.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Listener implements WebDriverListener {
    Logger logger=LoggerFactory.getLogger(Listener.class);

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("Test failed");
        logger.info("********************");

        logger.info("Method----->"+method.getName());
        logger.info("********************");

        logger.info("TargetException----->"+e.getTargetException());
        logger.info("*********************");

        logger.info("ObjectTarget----->"+target.toString());
        logger.info("********************");
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("The locator will find" +locator);
        logger.info("********************");
        WebDriverListener.super.beforeFindElement(driver, locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        logger.info("The locator is " +locator);
        logger.info("********************");
        WebDriverListener.super.afterFindElement(driver, locator, result);
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        logger.info("before find elements" +locator);
        logger.info("********************");
        WebDriverListener.super.beforeFindElements(driver, locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        logger.info("list size" +result.size());
        logger.info("********************");
        WebDriverListener.super.afterFindElements(driver, locator, result);
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        WebDriverListener.super.beforeQuit(driver);
    }

    @Override
    public void afterQuit(WebDriver driver) {
        WebDriverListener.super.afterQuit(driver);
    }
}