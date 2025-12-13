package org.example;

public class Util {
    public static String toTitleCase(String str){
        if (str == null || str.isEmpty()){
            return null;
        }

        String[] words = str.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                result += Character.toUpperCase(words[i].charAt(0));
                if (words[i].length() > 1) {
                    result += words[i].substring(1).toLowerCase();
                }
            }
            if (i < words.length - 1) {
                result += " ";
            }
        }

        return result;
    }
}
