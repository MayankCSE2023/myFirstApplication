package com.example.thefirstapplicationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thefirstapplicationapp.ui.theme.TheFirstApplicationAppTheme
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheFirstApplicationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JourneyProgressScreen()
                }
            }


        }

    }

    data class Stop(val name: String, private var _distanceKm: Double) {
        var distanceKm: Double
            get() = _distanceKm
            set(value) {
                _distanceKm = value
            }

        val distanceMiles: Double
            get() = convertDistance(_distanceKm, "km", "miles")

        // Function to format distance based on the selected unit
        fun getFormattedDistance(unit: String, isShowingKilometers: Boolean): String {
            val distance = if (isShowingKilometers) _distanceKm else distanceMiles
            return "${String.format("%.2f", distance)} $unit"
        }
        // Function to convert miles to kilometers and vice versa
        private fun convertDistance(value: Double, fromUnit: String, toUnit: String): Double {
            return when {
                fromUnit == "miles" && toUnit == "km" -> value * 1.6
                fromUnit == "km" && toUnit == "miles" -> value / 1.6
                else -> value
            }
        }

    }


    @Composable
    fun JourneyProgressScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Your other UI components here

            // ProgressBar
            ProgressSection()

            // Your other UI components here
        }
    }

    @Composable
    fun ProgressSection() {


        // You can use a state variable to represent the progress value
        var progress by remember { mutableStateOf(0.0f) }

        // Track the index of the current stop
        var currentStopIndex by remember { mutableStateOf(0) }

        // Example list of stops (you can replace it with your actual data)
        val stops = listOf(
            Stop("Stop 1", 10.0),
            Stop("Stop 2", 20.0),
            Stop("Stop 3", 15.0),
            Stop("Stop 4", 10.0),
            Stop("Stop 5", 20.0),
            Stop("Stop 6", 15.0),
            Stop("Stop 7", 10.0),
            Stop("Stop 8", 20.0),
            Stop("Stop 9", 15.0),
            Stop("Stop 10", 15.0),
            // Add more stops as needed
        )

        // Button to switch between kilometers and miles
        var isShowingKilometers by remember { mutableStateOf(true) }

        // Calculate total distance
        val totalDistance = stops.sumByDouble { it.distanceKm }
        // Calculate distance covered and distance left with unit conversion
        val distanceCovered = convertDistance(totalDistance * progress, "km", if (isShowingKilometers) "km" else "miles")
        val distanceLeft = convertDistance(totalDistance - distanceCovered, "km", if (isShowingKilometers) "km" else "miles")

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        // Display the progress value as text
        Text(
            text = "Progress: ${(progress * 100).toInt()}%",
            modifier = Modifier.padding(bottom = 16.dp)
        )


//        Card() {
//            // Display stops information with the selected unit
//            // Display stops information with the selected unit
//            Column {
//                stops.forEachIndexed { index, stop ->
//                    val distanceUnit = if (isShowingKilometers) "km" else "miles"
//                    val distanceValue = if (isShowingKilometers) stop.distanceKm else stop.distanceMiles
//                    Text(
//                        text = "Stop ${index + 1}: Distance: ${String.format("%.2f", distanceValue)} $distanceUnit",
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//            }
//
//            // Display total distance covered and total distance left with unit conversion
//            Text(
//                text = "Total Distance Covered: ${String.format("%.2f", convertDistance(distanceCovered, if (isShowingKilometers) "km" else "miles", if (isShowingKilometers) "km" else "miles"))} ${if (isShowingKilometers) "km" else "miles"}",
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Text(
//                text = "Total Distance Left: ${String.format("%.2f", convertDistance(distanceLeft, if (isShowingKilometers) "km" else "miles", if (isShowingKilometers) "km" else "miles"))} ${if (isShowingKilometers) "km" else "miles"}",
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//        }
        Card() {
            // Display stops information with the selected unit
            Column {
                stops.forEachIndexed { index, stop ->
                    val distanceUnit = if (isShowingKilometers) "km" else "miles"
                    val distanceValue = if (isShowingKilometers) stop.distanceKm else stop.distanceMiles

                    // Check if the current stop has been reached and apply the color accordingly
                    val textColor = if (index == currentStopIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface

                    Text(
                        text = "Stop ${index + 1}: Distance: ${String.format("%.2f", distanceValue)} $distanceUnit",
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = textColor
                    )
                }
            }

            // Display total distance covered and total distance left with unit conversion
            Text(
                text = "Total Distance Covered: ${String.format("%.2f", convertDistance(distanceCovered, if (isShowingKilometers) "km" else "miles", if (isShowingKilometers) "km" else "miles"))} ${if (isShowingKilometers) "km" else "miles"}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Total Distance Left: ${String.format("%.2f", convertDistance(distanceLeft, if (isShowingKilometers) "km" else "miles", if (isShowingKilometers) "km" else "miles"))} ${if (isShowingKilometers) "km" else "miles"}",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    isShowingKilometers = !isShowingKilometers
                    // Update the distances of each stop
                    stops.forEach { stop ->
                        stop.distanceKm = convertDistance(stop.distanceKm, if (isShowingKilometers) "km" else "miles", if (isShowingKilometers) "km" else "miles")
                    }
                    // Update the progress value to trigger recomposition
                    progress += 0.0f
                }
            ) {
                Text(text = if (isShowingKilometers) "Show in Miles" else "Show in Kilometers")
            }

            Spacer(modifier = Modifier.width(6.dp)) // Adjust the space as needed

            Button(
                onClick = {
                    if (progress >= 1.0) {
                        // Reset journey when progress is full
                        progress = 0.0f
                        currentStopIndex = 0
                    } else {
                        // Handle next stop logic here
                        progress += 0.1f // Increase progress by 10% for each next stop
                        currentStopIndex = (progress * stops.size).toInt()
                    }
                }
            ) {
                Text(text = if (progress >= 1.0) "Reset Journey" else "Next Stop")
            }
        }

    }



    // Function to convert miles to kilometers and vice versa
    fun convertDistance(value: Double, fromUnit: String, toUnit: String): Double {
        return when {
            fromUnit == "miles" && toUnit == "km" -> value * 1.6
            fromUnit == "km" && toUnit == "miles" -> value / 1.6
            else -> value
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewJourneyProgressScreen() {
        JourneyProgressScreen()
    }
}


