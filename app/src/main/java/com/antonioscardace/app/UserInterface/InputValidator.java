package com.antonioscardace.app.UserInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public final class InputValidator {
    private static final Set<String> ALLOWED_CONTACT_TYPES = new HashSet<>(Arrays.asList("telegram", "email"));
    private static final Set<String> ALLOWED_ADDRESS_TYPES = new HashSet<>(Arrays.asList("ip", "url"));

    private static boolean regexCheck(String regex, String text) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(text).find();
    }

    public static boolean isIpAddress(String ip) {
        return InputValidator.regexCheck("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$", ip);
    }

    public static boolean isUrlAddress(String url) {
        return InputValidator.regexCheck("^http(s)?:\\/\\/(www.)?([.a-z]{9,})(.[a-z]{2,3})$", url);
    }

    public static boolean isTelegramHandle(String handle) {
        return InputValidator.regexCheck("@[A-Za-z1-9_]{1,}$", handle);
    }

    public static boolean isEmail(String email) {
        return InputValidator.regexCheck("^[\\w-\\.+]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }

    public static boolean isAllowedContactType(String contactType) {
        return InputValidator.ALLOWED_CONTACT_TYPES.contains(contactType);
    }

    public static boolean isAllowedAddressType(String addressType) {
        return InputValidator.ALLOWED_ADDRESS_TYPES.contains(addressType);
    }
}