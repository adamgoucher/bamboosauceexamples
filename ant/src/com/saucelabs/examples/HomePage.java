package com.saucelabs.examples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import java.util.Properties;

import org.apache.log4j.Logger;

public class HomePage {
    static Logger log4j = Logger.getLogger("com.saucelabs.examples.HomePage");
    Selenium se;
    
    @Before
    public void setUp() throws Exception {
        // TODO - externalize somewhere
        Properties testProperties = new Properties();
        
        if (! System.getProperty("bamboo.SELENIUM_HOST").startsWith("${")) {
            log4j.debug("Se-RC host: " + System.getProperty("bamboo.SELENIUM_HOST"));
            testProperties.setProperty("serverHost", System.getProperty("bamboo.SELENIUM_HOST"));
        } else {
            testProperties.setProperty("serverHost", "localhost");            
        }

        if (! System.getProperty("bamboo.SELENIUM_PORT").startsWith("${")) {
            log4j.debug("Se-RC host: " + System.getProperty("bamboo.SELENIUM_PORT"));
            testProperties.setProperty("serverPort", System.getProperty("bamboo.SELENIUM_PORT"));
        } else {
            testProperties.setProperty("serverPort", "4444");            
        }

        if (! System.getProperty("bamboo.SELENIUM_BROWSER").startsWith("${")) {
            log4j.debug("Browser JSON: " + System.getProperty("bamboo.SELENIUM_BROWSER"));
            testProperties.setProperty("browserString", System.getProperty("bamboo.SELENIUM_BROWSER"));
        } else {
            testProperties.setProperty("browserString", "*firefox");            
        }
        
        se = new DefaultSelenium(testProperties.getProperty("serverHost"),
                                 Integer.parseInt(testProperties.getProperty("serverPort").trim()),
                                 testProperties.getProperty("browserString"),
                                 "http://saucelabs.com");

        se.start();
        se.setTimeout("30000");
        se.windowMaximize();
    }

    @After
    public void tearDown() throws Exception {
        se.stop();
    }
    
    @Test
    public void titlecard() {
        se.open("/");
        
    }
}