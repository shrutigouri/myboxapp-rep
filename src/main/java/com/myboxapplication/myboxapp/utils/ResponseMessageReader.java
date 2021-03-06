package com.myboxapplication.myboxapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
@PropertySource("classpath:responsemessages.properties")
public class ResponseMessageReader {

    @Autowired
    private Environment env;

    public String getProperty(String property) {
        return env.getProperty(property);
    }

    public String getProperty(String property, List<Object> args) {
        String format = MessageFormat.format(getProperty(property), args.toArray());
        return format;
    }
}
