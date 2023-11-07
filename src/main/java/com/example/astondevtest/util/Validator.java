package com.example.astondevtest.util;

import com.example.astondevtest.exceptions.BalanceException;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Validator {

    public static void checkRegistrationPincode(String pincode) throws IncorrectPinCodeException {
        if (pincode.length() != 4) {
            throw new IncorrectPinCodeException("Length of pincode must be 4 digits");
        }
        char[] chars = pincode.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) { throw new IncorrectPinCodeException("PinCode must be FOUR DIGITS"); }
        }
    }

    public static void moneyTransferPincodeCheck(String badPincode, String nicePincode) throws IncorrectPinCodeException {
        if (!badPincode.equals(nicePincode)) {
            throw new IncorrectPinCodeException("Incorrect PINCODE for this SuperAccount");
        }
    }

    public static void checkSenderAccountNumberBalance(BigDecimal balanceValue, BigDecimal inputValue) throws BalanceException {
        if (balanceValue.compareTo(BigDecimal.ZERO) == 0) {
            throw new BalanceException("Sender balance equals ZERO");
        } else if (inputValue.compareTo(balanceValue) == 1) {
            throw new BalanceException("Sender Balance NOT ENOUGH for this transfer");
        }
    }
}
