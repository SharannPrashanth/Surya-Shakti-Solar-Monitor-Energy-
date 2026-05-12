# Surya-Shakti Solar Monitor

Surya-Shakti is a Kotlin Android app for the internship project "Android App Development using GenAI". It helps a rooftop-solar household log daily generation, calculate consumption from meter readings, view a green energy independence score, and track 30-day savings.

## Built With

- Kotlin
- Jetpack Compose UI
- Room DB for persistent daily energy logs
- Local Android notification for peak-sun suggestions
- JUnit tests for savings and over-generation logic

## Features

- Generation Log: enter daily solar generation or simulate it from Sunny, Cloudy, or Rainy weather.
- Consumption Tracker: enter previous and current meter readings to calculate usage.
- Circular Progress: shows the solar share / green energy independence score.
- Peak Suggestion: "High Sun: Ideal time for heavy appliances."
- Savings Report: 30-day savings chart and recent daily logs.
- Over-generation: surplus solar is shown as export to grid.

## Calculation Notes

The demo uses simple 1:1 net metering:

```text
consumption = current meter reading - previous meter reading
solar used = min(generation, consumption)
grid import = max(consumption - generation, 0)
export to grid = max(generation - consumption, 0)
net savings = (solar used + export to grid) * per-unit rate
green score = min(generation / consumption * 100, 100)
```

## How To Run

1. Open this folder in Android Studio.
2. Let Gradle sync download dependencies.
3. Run the `app` configuration on an emulator or Android phone.

If Android Studio asks for a JDK, select JDK 17.
