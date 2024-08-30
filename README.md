# StudentAttendanceManager

**StudentAttendanceManager** is an Android application developed in Kotlin that allows users to manage attendance data efficiently. The app provides features for login and signup using Firebase Authentication and securely stores attendance data in Firebase Realtime Database.

## Features

- **User Authentication:**
  - Secure login and signup using Firebase Authentication.
  - Password recovery via email.

- **Manage Attendance:**
  - Add, update, and view attendance records.
  - Organize attendance data for multiple classes or subjects.
  - Data is stored securely in Firebase Realtime Database.

- **Real-time Data Synchronization:**
  - Automatic synchronization of attendance data across devices.
  - Offline access to previously stored data.

## Tech Stack

- **Kotlin:** Primary programming language used for developing the app.
- **Firebase Authentication:** Used for user login and signup functionalities.
- **Firebase Realtime Database:** Used to store and retrieve attendance data.
- **XML:** Used for designing the user interface.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sohan-beniwal/StudentAttendanceManager.git
   ```
2. **Open the project in Android Studio.**

3. **Set up Firebase:**
   - Create a Firebase project in the [Firebase Console](https://console.firebase.google.com/).
   - Add your Android app to the Firebase project.
   - Download the `google-services.json` file and place it in the `app` directory.
   - Enable Firebase Authentication and Realtime Database in your Firebase console.

4. **Build the project:**
   - Sync the project with Gradle files.
   - Build and run the app on an emulator or a physical device.

## Usage

1. **Sign Up / Login:**
   - Open the app and create an account or log in with an existing account.

2. **Manage Attendance:**
   - Add new attendance records.
   - View and update existing attendance records.

3. **View Data:**
   - Access your attendance data from any device with real-time synchronization.

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
