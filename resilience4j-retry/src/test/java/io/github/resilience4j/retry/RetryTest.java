package io.github.resilience4j.retry;

import org.junit.Test;

import java.io.IOException;

// These tests do nothing at runtime. They are here to verify that it´s enough to catch the exception actually
// thrown by the closure. That is, if it compiles, then the tests have passed.
public class RetryTest {
    private final io.github.resilience4j.retry.Retry retry = Retry.of("test", RetryConfig.ofDefaults());

    @Test
    public void shouldHaveCorrectExceptionTypeForExceptionalSupplier() {
        try {
            retry.decorateExceptionalSupplier(() -> {
                        throw new IOException();
                    })
                    .get();
        } catch (IOException ignored) {
        }
    }

    @Test
    public void shouldHaveCorrectExceptionTypeForExceptionalRunnable() {
        // This test does nothing at runtime. It´s here to verify that it´s enough to catch the exception actually
        // thrown by the supplier. That is, if it compiles, then the test has passed.
        try {
            retry.decorateExceptionalRunnable(() -> {
                        throw new IOException();
                    })
                    .run();
        } catch (IOException ignored) {
        }
    }

    @Test
    public void shouldHaveCorrectExceptionTypeForExceptionalFunction() {
        // This test does nothing at runtime. It´s here to verify that it´s enough to catch the exception actually
        // thrown by the supplier. That is, if it compiles, then the test has passed.
        try {
            retry.decorateExceptionalFunction(i -> {
                        throw new IOException();
                    })
                    .apply(0);
        } catch (IOException ignored) {
        }
    }
}
