package org.project.test;

import org.project.utils.Colors;

import java.awt.*;

public class Test {
    public static void test(boolean passed, String message) {
        try {
            throw new AssertionError(message);
        }
        catch (AssertionError error) {
            StackTraceElement trace = error.getStackTrace()[1];
            System.out.print(trace.getFileName());
            System.out.print(":");
            System.out.print(trace.getLineNumber());
            System.out.print(": ");
            System.out.print((passed) ? Colors.ANSI_GREEN + "passed" + Colors.ANSI_RESET : Colors.ANSI_RED + "error" + Colors.ANSI_RESET);
            System.out.print(": ");
            System.out.println(message);
        }
    }
}
