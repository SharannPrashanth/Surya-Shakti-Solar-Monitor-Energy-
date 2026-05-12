package com.suryashakti.monitor.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lcom/suryashakti/monitor/data/EnergyDao;", "", "deleteAllLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLog", "log", "Lcom/suryashakti/monitor/data/EnergyLog;", "(Lcom/suryashakti/monitor/data/EnergyLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLogs", "Lkotlinx/coroutines/flow/Flow;", "", "getLast30DaysLogs", "insertLog", "updateLog", "app_debug"})
@androidx.room.Dao()
public abstract interface EnergyDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLog(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLog(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLog(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM energy_logs ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.suryashakti.monitor.data.EnergyLog>> getAllLogs();
    
    @androidx.room.Query(value = "SELECT * FROM energy_logs ORDER BY date DESC LIMIT 30")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.suryashakti.monitor.data.EnergyLog>> getLast30DaysLogs();
    
    @androidx.room.Query(value = "DELETE FROM energy_logs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}