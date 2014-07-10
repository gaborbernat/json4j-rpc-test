package com.gravityrd.bernat;

public class TestImpl implements ITest {
    @Override
    public String name() throws Exception {
        throw new RuntimeException("what");
    }
}
