package com.suryashakti.monitor.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J:\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\nJ\u0016\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0$2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010%\u001a\u00020\u0017J\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000f0\'J\u000e\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\f\u00a8\u0006*"}, d2 = {"Lcom/suryashakti/monitor/ui/EnergyViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/suryashakti/monitor/data/EnergyRepository;", "preferences", "Lcom/suryashakti/monitor/data/AppPreferences;", "(Lcom/suryashakti/monitor/data/EnergyRepository;Lcom/suryashakti/monitor/data/AppPreferences;)V", "allLogs", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/suryashakti/monitor/data/EnergyLog;", "getAllLogs", "()Lkotlinx/coroutines/flow/StateFlow;", "electricityRate", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getElectricityRate", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "last30DaysLogs", "getLast30DaysLogs", "totalSavings", "getTotalSavings", "calculateAndSaveLog", "", "id", "", "date", "", "generation", "consumption", "batteryStart", "weather", "", "deleteLog", "log", "getLogById", "Lkotlinx/coroutines/flow/Flow;", "resetData", "simulateDay", "Lkotlin/Pair;", "updateRate", "rate", "app_debug"})
public final class EnergyViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.suryashakti.monitor.data.EnergyRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.suryashakti.monitor.data.AppPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.suryashakti.monitor.data.EnergyLog>> allLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.suryashakti.monitor.data.EnergyLog>> last30DaysLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> totalSavings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Double> electricityRate = null;
    
    public EnergyViewModel(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyRepository repository, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.AppPreferences preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.suryashakti.monitor.data.EnergyLog>> getAllLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.suryashakti.monitor.data.EnergyLog>> getLast30DaysLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTotalSavings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Double> getElectricityRate() {
        return null;
    }
    
    public final void updateRate(double rate) {
    }
    
    public final void calculateAndSaveLog(int id, long date, double generation, double consumption, int batteryStart, @org.jetbrains.annotations.NotNull()
    java.lang.String weather) {
    }
    
    public final void deleteLog(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyLog log) {
    }
    
    public final void resetData() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.String, java.lang.Double> simulateDay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.suryashakti.monitor.data.EnergyLog> getLogById(int id) {
        return null;
    }
}