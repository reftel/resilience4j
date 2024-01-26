package io.github.resilience4j.core.functions;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalFunction<T, R, E extends Exception> {
    R apply(T t) throws E;

    default Function<T, R> unchecked() {
        return t1 -> {
            try {
                return apply(t1);
            } catch(Throwable t) {
                return sneakyThrow(t);
            }
        };
    }

    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}
