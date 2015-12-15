package com.lexmark.service.impl.real.util;

import java.util.ArrayList;
import java.util.List;


public class TestErrors {

    private long t0;

    public TestErrors() {
        t0 = System.currentTimeMillis();
    }

    static class Error {
        Throwable throwable;
        String description;

        public Error(Throwable throwable, String description) {
            super();
            this.throwable = throwable;
            this.description = description;
        }

    }

    List<Error> errors = new ArrayList<Error>();

    public void check() {
        if (errors.size() > 0) {
            System.out.println("Errors:");
            int i = 1;
            for (Error e : errors) {
                System.out.printf("[%s] ==================\n", i++);
                System.out.println(e.description);
                System.out.println(e.throwable);
                Throwable cause = e.throwable.getCause();
                while (cause != null) {
                    System.out.println("\n" + cause);
                    cause = cause.getCause();
                }
            }
            System.out.println("==================");
            System.out.println("errors.size = " + errors.size());
            System.out.printf("(total exec time = %.2f sec.)\n", (System.currentTimeMillis() - t0) / 1000.0);
            System.out.println("==================");
            throw new AssertionError("Test failed");
        }
    }

    public void add(Throwable ex, String description) {
        errors.add(new Error(ex, description));
    }
}
