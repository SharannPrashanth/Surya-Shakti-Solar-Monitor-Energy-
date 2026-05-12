package com.suryashakti.monitor.energy;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002\u00a8\u0006\u000e"}, d2 = {"Lcom/suryashakti/monitor/energy/EnergyCalculator;", "", "()V", "calculate", "Lcom/suryashakti/monitor/energy/EnergyStats;", "generationKwh", "", "consumptionKwh", "batteryPercent", "", "unitRate", "roundMoney", "value", "roundOne", "app_debug"})
public final class EnergyCalculator {
    @org.jetbrains.annotations.NotNull()
    public static final com.suryashakti.monitor.energy.EnergyCalculator INSTANCE = null;
    
    private EnergyCalculator() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.suryashakti.monitor.energy.EnergyStats calculate(double generationKwh, double consumptionKwh, int batteryPercent, double unitRate) {
        return null;
    }
    
    private final double roundOne(double value) {
        return 0.0;
    }
    
    private final double roundMoney(double value) {
        return 0.0;
    }
}