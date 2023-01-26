package com.datadog.ci.test;

public class StringJoiner {

    static {
        System.out.println(CommonDependency.INSTANCE);
    }

    public String join(String delimeter, String... tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            if (i > 0) {
                sb.append(delimeter);
            }
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

}
