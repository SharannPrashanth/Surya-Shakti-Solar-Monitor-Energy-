package com.suryashakti.monitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.suryashakti.monitor.data.AppPreferences
import com.suryashakti.monitor.data.EnergyDatabase
import com.suryashakti.monitor.data.EnergyLog
import com.suryashakti.monitor.data.EnergyRepository
import com.suryashakti.monitor.ui.EnergyViewModel
import com.suryashakti.monitor.ui.EnergyViewModelFactory
import com.suryashakti.monitor.ui.theme.SuryaShaktiTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = EnergyDatabase.getDatabase(this)
        val repository = EnergyRepository(database.energyDao())
        val preferences = AppPreferences(this)
        val factory = EnergyViewModelFactory(repository, preferences)

        setContent {
            SuryaShaktiTheme {
                val navController = rememberNavController()
                val viewModel: EnergyViewModel = viewModel(factory = factory)
                
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = { 
                        if (currentRoute != null && 
                            !currentRoute.startsWith("add_data") && 
                            !currentRoute.startsWith("detail") &&
                            !currentRoute.startsWith("edit_data")) {
                            BottomNavigationBar(navController, currentRoute)
                        }
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        AppNavigation(navController, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: EnergyViewModel) {
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") { DashboardScreen(navController, viewModel) }
        composable("add_data") { AddDataScreen(navController, viewModel) }
        composable(
            "edit_data/{logId}",
            arguments = listOf(navArgument("logId") { type = NavType.IntType })
        ) { backStackEntry ->
            val logId = backStackEntry.arguments?.getInt("logId") ?: 0
            AddDataScreen(navController, viewModel, logId)
        }
        composable("history") { HistoryScreen(navController, viewModel) }
        composable(
            "detail/{logId}",
            arguments = listOf(navArgument("logId") { type = NavType.IntType })
        ) { backStackEntry ->
            val logId = backStackEntry.arguments?.getInt("logId") ?: 0
            DetailScreen(navController, viewModel, logId)
        }
        composable("reports") { ReportsScreen(navController, viewModel) }
        composable("settings") { SettingsScreen(navController, viewModel) }
    }
}

// --- Dashboard Screen ---
@Composable
fun DashboardScreen(navController: NavHostController, viewModel: EnergyViewModel) {
    val allLogs by viewModel.allLogs.collectAsState()
    val latestLog = allLogs.firstOrNull()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                "Surya-Shakti Dashboard",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (latestLog != null) {
            val greenScore = if (latestLog.consumption > 0) (latestLog.solarUsed / latestLog.consumption) * 100 else 0.0
            
            item {
                CircularIndependenceIndicator(greenScore.toFloat())
            }

            item {
                StatusCard(latestLog)
            }

            item {
                if (latestLog.generation >= 7.0) {
                    SuggestionCard()
                }
            }

            item {
                QuickStats(latestLog)
            }
        } else {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.WbSunny, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.DarkGray)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No logs found. Tap '+' below to add data.", color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun CircularIndependenceIndicator(score: Float) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(240.dp)) {
        CircularProgressIndicator(
            progress = { (score / 100f).coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 20.dp,
            trackColor = Color.DarkGray
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "${score.toInt()}%",
                fontSize = 64.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Text("GREEN SCORE", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StatusCard(log: EnergyLog) {
    val status = when {
        log.exported > 0 -> "Exporting to Grid"
        log.gridUsed > 0 -> "Using Grid Power"
        else -> "Using Solar Power"
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Bolt, contentDescription = null, tint = Color.Black, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("CURRENT STATUS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black.copy(alpha = 0.6f))
                Text(status, fontWeight = FontWeight.Black, color = Color.Black, fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun SuggestionCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "High Sun: Ideal time for heavy appliances",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun QuickStats(log: EnergyLog) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        StatBox("Gen", "${log.generation}kWh", Modifier.weight(1f))
        StatBox("Cons", "${log.consumption}kWh", Modifier.weight(1f))
        StatBox("Battery", "${log.batteryEnd}%", Modifier.weight(1f))
    }
}

@Composable
fun StatBox(label: String, value: String, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = Color(0xFF111111))) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(label, fontSize = 10.sp, color = Color.Gray)
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

// --- Add Data Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(navController: NavHostController, viewModel: EnergyViewModel, logId: Int? = null) {
    val existingLogFlow = if (logId != null && logId != 0) viewModel.getLogById(logId) else null
    val existingLog by (existingLogFlow?.collectAsState(null) ?: remember { mutableStateOf(null) })
    
    var genInput by remember { mutableStateOf("") }
    var consInput by remember { mutableStateOf("") }
    var batInput by remember { mutableStateOf("50") }
    var weatherType by remember { mutableStateOf("Sunny") }

    LaunchedEffect(existingLog) {
        existingLog?.let {
            genInput = it.generation.toString()
            consInput = it.consumption.toString()
            batInput = it.batteryStart.toString()
            weatherType = it.weather
        }
    }

    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text(if (logId == null || logId == 0) "Log New Entry" else "Edit Entry") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            ) 
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = genInput,
                onValueChange = { genInput = it },
                label = { Text("Solar Generation (kWh)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = consInput,
                onValueChange = { consInput = it },
                label = { Text("Consumption (kWh)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = batInput,
                onValueChange = { batInput = it },
                label = { Text("Battery Start (%)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            
            Text("Weather: $weatherType", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        val sim = viewModel.simulateDay()
                        genInput = sim.second.toString()
                        weatherType = sim.first
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("Simulate")
                }
                Button(
                    onClick = {
                        viewModel.calculateAndSaveLog(
                            id = logId ?: 0,
                            date = existingLog?.date ?: System.currentTimeMillis(),
                            generation = genInput.toDoubleOrNull() ?: 0.0,
                            consumption = consInput.toDoubleOrNull() ?: 0.0,
                            batteryStart = batInput.toIntOrNull() ?: 0,
                            weather = weatherType
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
            }
        }
    }
}

// --- History Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavHostController, viewModel: EnergyViewModel) {
    val logs by viewModel.allLogs.collectAsState()

    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text("History") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            ) 
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(logs) { log ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail/${log.id}") },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF111111))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            val dateStr = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(log.date))
                            Text(dateStr, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Text(log.weather, fontSize = 12.sp, color = Color.Gray)
                        }
                        Text("₹${String.format("%.2f", log.savings)}", fontWeight = FontWeight.Black, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

// --- Detail Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, viewModel: EnergyViewModel, logId: Int) {
    val logFlow = viewModel.getLogById(logId)
    val log by logFlow.collectAsState(null)

    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("edit_data/$logId") }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { 
                        log?.let { viewModel.deleteLog(it) }
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            ) 
        }
    ) { padding ->
        log?.let { l ->
            Column(modifier = Modifier.padding(padding).padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                DetailItem("Date", SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date(l.date)))
                DetailItem("Weather", l.weather)
                HorizontalDivider(color = Color.DarkGray)
                DetailItem("Solar Used", "${String.format("%.2f", l.solarUsed)} kWh")
                DetailItem("Grid Used", "${String.format("%.2f", l.gridUsed)} kWh")
                DetailItem("Exported", "${String.format("%.2f", l.exported)} kWh")
                DetailItem("Battery", "${l.batteryStart}% -> ${l.batteryEnd}%")
                DetailItem("Savings", "₹${String.format("%.2f", l.savings)}")
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

// --- Reports Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(navController: NavHostController, viewModel: EnergyViewModel) {
    val logs by viewModel.last30DaysLogs.collectAsState()
    val totalSaved by viewModel.totalSavings.collectAsState()

    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text("30-Day Analysis") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            ) 
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Column(modifier = Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("TOTAL SAVINGS", fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                    Text("₹${String.format("%.2f", totalSaved)}", fontSize = 56.sp, fontWeight = FontWeight.Black, color = Color.Black)
                }
            }
            
            Text("Usage Trend (kWh)", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(24.dp))
            
            if (logs.isNotEmpty()) {
                val chartLogs = logs.reversed().takeLast(10).toList()
                Canvas(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                    val width = size.width
                    val height = size.height
                    val spacing = if (chartLogs.size > 1) width / (chartLogs.size - 1) else width
                    val maxVal = (chartLogs.maxOf { it.generation.coerceAtLeast(it.consumption) }).coerceAtLeast(1.0).toFloat()
                    
                    val genPath = Path()
                    val consPath = Path()
                    
                    chartLogs.forEachIndexed { index, log ->
                        val x = index * spacing
                        val yGen = height - (log.generation.toFloat() / maxVal * height)
                        val yCons = height - (log.consumption.toFloat() / maxVal * height)
                        
                        if (index == 0) {
                            genPath.moveTo(x, yGen)
                            consPath.moveTo(x, yCons)
                        } else {
                            genPath.lineTo(x, yGen)
                            consPath.lineTo(x, yCons)
                        }
                    }
                    
                    drawPath(genPath, color = Color(0xFFFFD700), style = Stroke(width = 4f))
                    drawPath(consPath, color = Color.Gray, style = Stroke(width = 4f))
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                    Text("No chart data.", color = Color.DarkGray)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Yellow: Solar", fontSize = 10.sp, color = MaterialTheme.colorScheme.primary)
                Text("Gray: Consumption", fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}

// --- Settings Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController, viewModel: EnergyViewModel) {
    val rate by viewModel.electricityRate.collectAsState()
    var rateInput by remember { mutableStateOf(rate.toString()) }

    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            ) 
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(24.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Electricity Rate", fontWeight = FontWeight.Bold, color = Color.Gray)
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = rateInput,
                        onValueChange = { rateInput = it },
                        label = { Text("₹ / kWh") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = { viewModel.updateRate(rateInput.toDoubleOrNull() ?: 6.0) }) {
                        Text("Update")
                    }
                }
            }
            
            Divider(color = Color.DarkGray)
            
            Button(
                onClick = { viewModel.resetData(); navController.navigate("dashboard") { popUpTo(0) } },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF220000)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset All Data", color = Color.Red)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, currentRoute: String) {
    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Dashboard, contentDescription = null) },
            label = { Text("Home") },
            selected = currentRoute == "dashboard",
            onClick = { navController.navigate("dashboard") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Gray,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.History, contentDescription = null) },
            label = { Text("History") },
            selected = currentRoute == "history",
            onClick = { navController.navigate("history") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Gray,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        Box(contentAlignment = Alignment.TopCenter) {
            FloatingActionButton(
                onClick = { navController.navigate("add_data") },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.offset(y = (-20).dp).size(56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
            }
        }
        NavigationBarItem(
            icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
            label = { Text("Reports") },
            selected = currentRoute == "reports",
            onClick = { navController.navigate("reports") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Gray,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text("Settings") },
            selected = currentRoute == "settings",
            onClick = { navController.navigate("settings") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Gray,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}
