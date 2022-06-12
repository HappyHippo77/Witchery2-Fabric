package io.github.happyhippo77.witchery2.util;

public class BubbleParticleDataSetter {
    private int red;
    private int green;
    private int blue;
    private int alpha;
    public void setData(int colorR, int colorG, int colorB, int colorA) {
        this.red = colorR;
        this.green = colorG;
        this.blue = colorB;
        this.alpha = colorA;
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
}
