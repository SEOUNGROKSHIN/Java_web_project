package org.zerock.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter {
    @Override
    public Object parse(String text, Locale locale) throws ParseException {

        if(text == null) {

        return false;
        }

        return text.equals("on");
    }


    @Override
    public String print(Object object, Locale locale) {
        return object.toString();
    }
}
