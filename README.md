# PraktikumVSGA

This repository contains an Android application developed as part of the Junior Mobile Developer training program, leveraging the skills and knowledge acquired during the VSGA program. The application utilizes DB-browser SQLite for data storage.

## Key Features & Benefits

*   **Login:** Secure user authentication functionality.
*   **CRUD (Create, Read, Update, Delete):** Comprehensive data management operations.
*   **Splashscreen:** Engaging initial loading screen.

## Prerequisites & Dependencies

*   **Java Development Kit (JDK):** Version 8 or higher.
*   **Android SDK:** Minimum API level 21 (Android 5.0 Lollipop).
*   **Android Studio:** Latest stable version recommended.
*   **Gradle:** Version 7.0 or higher (managed by Android Studio).
*   **DB Browser for SQLite:** (Optional) For viewing and managing the SQLite database.

## Installation & Setup Instructions

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/Adamyasin/PraktikumVSGA.git
    ```

2.  **Open the project in Android Studio:**

    *   Launch Android Studio.
    *   Select "Open an Existing Project."
    *   Navigate to the cloned repository directory and select the root folder.

3.  **Sync Gradle:**

    *   Android Studio will automatically prompt you to sync the Gradle files. Click "Sync" or "Sync Now."

4.  **Configure SDK location (if necessary):**

    *   If Android Studio cannot locate the Android SDK, you may need to configure the SDK location manually.
        *   Go to `File > Project Structure > SDK Location`.
        *   Specify the path to your Android SDK installation.

5.  **Build and Run the application:**

    *   Connect an Android device or emulator to your computer.
    *   Click the "Run" button (green play icon) in the Android Studio toolbar.
    *   Select your connected device or emulator from the device chooser.

## Usage Examples & API Documentation (if applicable)

This application uses standard Android UI components and SQLite database interactions. The core logic is implemented within the `app/src/main/java/com/example/praktikumvsga/` directory.

Specific examples of usage include:

*   **Login:** User enters credentials (username/password) which are validated against the SQLite database.
*   **CRUD:** Uses SQLite database operations to create, read, update and delete data. Look into the respective Activity files related to CRUD functionality.

## Configuration Options

The application's configuration can be adjusted via:

*   **`build.gradle.kts` file:**  Dependencies, compile SDK version, and other build settings can be configured here.
*   **`AndroidManifest.xml` file:**  Permissions, activity declarations, and other application-level settings are defined in this file.
*   **Database configuration:** Database name and tables are defined within the Java code related to database interactions (likely using SQLiteOpenHelper).

## Contributing Guidelines

We welcome contributions to improve this project. To contribute:

1.  **Fork the repository.**
2.  **Create a new branch for your feature or bug fix.**
3.  **Implement your changes and write appropriate tests.**
4.  **Submit a pull request with a clear description of your changes.**

Please adhere to the following guidelines:

*   Write clean, well-documented code.
*   Follow the existing coding style.
*   Ensure your changes do not introduce new bugs.

## License Information

This project has no specified license. All rights are reserved to the owner of the repository.

## Acknowledgments

This project was developed as part of the Junior Mobile Developer training program within the VSGA program. We would like to thank the instructors and organizers for their guidance and support.
