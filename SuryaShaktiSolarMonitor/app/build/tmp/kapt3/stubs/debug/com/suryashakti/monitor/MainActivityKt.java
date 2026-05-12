package com.suryashakti.monitor;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007\u00a2\u0006\u0002\u0010\b\u001a\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0007\u001a \u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0007\u001a\u0018\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a \u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007\u001a\u0010\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0007\u001a\b\u0010\u001f\u001a\u00020\u0001H\u0007\u00a8\u0006 "}, d2 = {"AddDataScreen", "", "navController", "Landroidx/navigation/NavHostController;", "viewModel", "Lcom/suryashakti/monitor/ui/EnergyViewModel;", "logId", "", "(Landroidx/navigation/NavHostController;Lcom/suryashakti/monitor/ui/EnergyViewModel;Ljava/lang/Integer;)V", "AppNavigation", "BottomNavigationBar", "currentRoute", "", "CircularIndependenceIndicator", "score", "", "DashboardScreen", "DetailItem", "label", "value", "DetailScreen", "HistoryScreen", "QuickStats", "log", "Lcom/suryashakti/monitor/data/EnergyLog;", "ReportsScreen", "SettingsScreen", "StatBox", "modifier", "Landroidx/compose/ui/Modifier;", "StatusCard", "SuggestionCard", "app_debug"})
public final class MainActivityKt {
    
    @androidx.compose.runtime.Composable()
    public static final void AppNavigation(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CircularIndependenceIndicator(float score) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatusCard(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyLog log) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SuggestionCard() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickStats(@org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.data.EnergyLog log) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatBox(@org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AddDataScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel, @org.jetbrains.annotations.Nullable()
    java.lang.Integer logId) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DetailScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel, int logId) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DetailItem(@org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ReportsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.suryashakti.monitor.ui.EnergyViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BottomNavigationBar(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    java.lang.String currentRoute) {
    }
}