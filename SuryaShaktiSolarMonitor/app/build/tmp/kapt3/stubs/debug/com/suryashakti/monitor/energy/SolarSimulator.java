package com.suryashakti.monitor.energy;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002J\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/suryashakti/monitor/energy/SolarSimulator;", "", "()V", "peakSuggestion", "", "weather", "Lcom/suryashakti/monitor/energy/WeatherCondition;", "generationKwh", "", "hour", "", "roundOne", "value", "simulateGeneration", "app_debug"})
public final class SolarSimulator {
    @org.jetbrains.annotations.NotNull()
    public static final com.suryashakti.monitor.energy.SolarSimulator INSTANCE = null;
    
    private SolarSimulator() {
        super();
    }
    
    public final double simulateGeneration(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.energy.WeatherCondition weather) {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String peakSuggestion(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.energy.WeatherCondition weather, double generationKwh, int hour) {
        return null;
    }
    
    private final double roundOne(double value) {
        return 0.0;
    }
}