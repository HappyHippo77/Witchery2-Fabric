package io.github.happyhippo77.witchery2.util;

public class PowerParticleDataSetter {
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private boolean circling;
    private boolean ritualInProgress;
    public void setData(int colorR, int colorG, int colorB, int colorA, boolean circling, boolean ritualInProgress) {
        this.red = colorR;
        this.green = colorG;
        this.blue = colorB;
        this.alpha = colorA;
        this.circling = circling;
        this.ritualInProgress = ritualInProgress;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public boolean isCircling() {
        return circling;
    }

    public boolean isRitualInProgress() {
        return ritualInProgress;
    }
}
