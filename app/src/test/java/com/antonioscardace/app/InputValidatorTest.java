package com.antonioscardace.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.antonioscardace.app.UserInterface.InputValidator;

public class InputValidatorTest {
    @Test
    public void checkIpAddress() {
        assertTrue(InputValidator.isIpAddress("8.8.8.8"));
        assertTrue(InputValidator.isIpAddress("192.168.0.16"));

        assertFalse(InputValidator.isIpAddress("8888.11.0.0"));
        assertFalse(InputValidator.isIpAddress("59.8.8"));
        assertFalse(InputValidator.isIpAddress("test.59.8.8"));
    }

    @Test
    public void checkUrlAddress() {
        assertTrue(InputValidator.isUrlAddress("http://antonioscardace.org"));
        assertTrue(InputValidator.isUrlAddress("http://www.antonioscardace.org"));
        assertTrue(InputValidator.isUrlAddress("https://www.antonioscardace.org"));

        assertFalse(InputValidator.isUrlAddress("http://antonioscardace.org/file.html"));
        assertFalse(InputValidator.isUrlAddress("http://www.antonioscardace."));
        assertFalse(InputValidator.isUrlAddress("http://www..org"));
        assertFalse(InputValidator.isUrlAddress("www.antonioscardace.org"));
    }

    @Test
    public void checkTelegramHandle() {
        assertTrue(InputValidator.isTelegramHandle("@test"));
        assertTrue(InputValidator.isTelegramHandle("@test_ABC"));
        assertTrue(InputValidator.isTelegramHandle("@Test1234"));

        assertFalse(InputValidator.isTelegramHandle("@"));
        assertFalse(InputValidator.isTelegramHandle("@test test"));
        assertFalse(InputValidator.isTelegramHandle("test_1234"));
    }

    @Test
    public void checkEmail() {
        assertTrue(InputValidator.isEmail("test@example.com"));
        assertTrue(InputValidator.isEmail("test+123@example.it"));

        assertFalse(InputValidator.isEmail("test@example"));
        assertFalse(InputValidator.isEmail("test@.com"));
        assertFalse(InputValidator.isEmail("test@"));
        assertFalse(InputValidator.isEmail("@example.com"));
    }

    @Test
    public void checkContactType() {
        assertTrue(InputValidator.isAllowedContactType("telegram"));
        assertTrue(InputValidator.isAllowedContactType("email"));

        assertFalse(InputValidator.isAllowedContactType("phone"));
        assertFalse(InputValidator.isAllowedContactType("test"));
    }

    @Test
    public void checkAddressType() {
        assertTrue(InputValidator.isAllowedAddressType("ip"));
        assertTrue(InputValidator.isAllowedAddressType("url"));

        assertFalse(InputValidator.isAllowedAddressType("ssh"));
        assertFalse(InputValidator.isAllowedAddressType("test"));
    }
}
