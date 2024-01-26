package io.github.resilience4j.core.functions;

import java.util.Objects;
import java.util.function.Supplier;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Exception> {
    T get() throws E;

    default <V> ExceptionalSupplier<V, E> andThen(ExceptionalFunction<? super T, ? extends V, E> after) {
        Objects.requireNonNull(after, "after is null");
        return () -> after.apply(get());
    }

    default Supplier<T> unchecked() {
        return () -> {
            try {
                return get();
            } catch (Throwable t) {
                return sneakyThrow(t);
            }
        };
    }

    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}
