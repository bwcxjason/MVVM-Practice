package com.example.jason.mvvm_practice.business.twowaydatabinding;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertEquals;

public class TWDBViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private TWDBViewModel mTwdbViewModel;

    @Before
    public void init() {
        mTwdbViewModel = new TWDBViewModel();
    }

    @Test
    public void testSubmit() {
        mTwdbViewModel.reset();
        mTwdbViewModel.submit();
        assertEquals("{age:20,name:Jason,isMarried:true}", mTwdbViewModel.getResult().getValue());
    }

    @Test
    public void testReset() {
        mTwdbViewModel.reset();
        assertEquals(20, mTwdbViewModel.age.get());
        assertEquals("Jason", mTwdbViewModel.name.get());
        assertEquals(true, mTwdbViewModel.isMarried.get());
    }

}
