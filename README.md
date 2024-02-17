# Journey Progress App

This is a simple Android application implemented using Jetpack Compose. The app visualizes the progress of a journey with multiple stops, displaying a linear progress bar, stop information, and allowing the user to switch between kilometers and miles.

## Features

- **Progress Bar**: A linear progress bar at the top of the screen indicates the overall progress of the journey.

- **Stop Information**: The stops are displayed in a card, showing the distance of each stop and highlighting the current stop.

- **Unit Switching**: Users can switch between kilometers and miles, updating the distances of each stop accordingly.

- **Total Distance**: The app calculates and displays the total distance covered and the remaining distance based on the current progress.

- **Next Stop**: Users can progress to the next stop by tapping the "Next Stop" button.

## Implementation

### Data Model

- The `Stop` data class represents each stop with a name and distance in kilometers.

- The `convertDistance` function is used to convert distances between miles and kilometers.

### UI Components

- The `JourneyProgressScreen` composable sets up the main screen structure, including the progress bar and other UI components.

- The `ProgressSection` composable contains the linear progress indicator and the card displaying stop information.

- The lazy column is used to dynamically display a scrollable list of stops with their distances.

### State Management

- State variables are used to track the progress, current stop index, and the unit of distance.

- The progress is updated when the user taps the "Next Stop" button, and the distances are recalculated.

### Unit Conversion

- The app dynamically converts distances between kilometers and miles based on user preferences.

### Buttons

- The "Show in Miles" button toggles the unit of distance for display.

- The "Next Stop" button progresses the journey to the next stop, and it resets the journey if the progress is already complete.



