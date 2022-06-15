package com.saucedemo.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("Test status Started");
        result.setStatus(ITestResult.STARTED);
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test status Success");
        result.setStatus(ITestResult.SUCCESS);
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test status Failure");
        result.setStatus(ITestResult.FAILURE);
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test status Skipped");
        result.setStatus(ITestResult.SKIP);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test status Success");
        result.setStatus(ITestResult.SUCCESS);
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("Test status Failure");
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        System.out.println("onStart " + context.getStartDate());
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish " + context.getEndDate());
    }
}
