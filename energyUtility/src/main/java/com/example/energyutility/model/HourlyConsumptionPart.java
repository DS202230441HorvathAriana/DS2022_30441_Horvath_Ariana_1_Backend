package com.example.energyutility.model;

public class HourlyConsumptionPart {

    private float valueSum;
    private int counterMinutes;

    public HourlyConsumptionPart(float valueSum, int counterMinutes) {
        this.valueSum = valueSum;
        this.counterMinutes = counterMinutes;
    }

    public HourlyConsumptionPart() {
        this.valueSum = 0f;
        this.counterMinutes = 0;
    }

    public float getValueSum() {
        return valueSum;
    }

    public void setValueSum(float valueSum) {
        this.valueSum = valueSum;
    }

    public int getCounterMinutes() {
        return counterMinutes;
    }

    public void setCounterMinutes(int counterMinutes) {
        this.counterMinutes = counterMinutes;
    }
}
