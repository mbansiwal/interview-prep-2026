package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MinStackApproachesTest {

    record Ops(IntConsumer push, Runnable pop, IntSupplier top, IntSupplier getMin) {}

    static Stream<Supplier<Ops>> approaches() {
        return Stream.of(
            () -> { MinStack s = new MinStack(); return new Ops(s::push, s::pop, s::top, s::getMin); },
            () -> { MinStack.PairStack s = new MinStack.PairStack(); return new Ops(s::push, s::pop, s::top, s::getMin); },
            () -> { MinStack.DiffStack s = new MinStack.DiffStack(); return new Ops(s::push, s::pop, s::top, s::getMin); });
    }

    @ParameterizedTest @MethodSource("approaches")
    void leetCodeTrace(Supplier<Ops> make) {
        Ops s = make.get();
        s.push().accept(-2); s.push().accept(0); s.push().accept(-3);
        assertEquals(-3, s.getMin().getAsInt());
        s.pop().run();
        assertEquals(0, s.top().getAsInt());
        assertEquals(-2, s.getMin().getAsInt());
    }

    @ParameterizedTest @MethodSource("approaches")
    void duplicateMinimums(Supplier<Ops> make) {
        Ops s = make.get();
        s.push().accept(1); s.push().accept(1); s.push().accept(2);
        s.pop().run(); s.pop().run();
        assertEquals(1, s.getMin().getAsInt());
        assertEquals(1, s.top().getAsInt());
    }

    @ParameterizedTest @MethodSource("approaches")
    void extremeValuesNoOverflow(Supplier<Ops> make) {
        Ops s = make.get();
        s.push().accept(Integer.MAX_VALUE);
        s.push().accept(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, s.getMin().getAsInt());
        assertEquals(Integer.MIN_VALUE, s.top().getAsInt());
        s.pop().run();
        assertEquals(Integer.MAX_VALUE, s.getMin().getAsInt());
        assertEquals(Integer.MAX_VALUE, s.top().getAsInt());
    }

    @ParameterizedTest @MethodSource("approaches")
    void singleElement(Supplier<Ops> make) {
        Ops s = make.get();
        s.push().accept(5);
        assertEquals(5, s.top().getAsInt());
        assertEquals(5, s.getMin().getAsInt());
    }
}
