package com.google.drive.dictionary;

import org.apache.commons.lang3.StringUtils;

public class Messages {
    public void sayGreetings() {
        String string = "Hello, my dear friend! How are you? I missed you so much";
        System.out.println(StringUtils.remove(string, " I missed you so much"));
    }
}