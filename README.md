# MachineCodingRoundsample

A simple Android project created for practicing Machine Coding Round assignments.
It demonstrates clean architecture, MVVM pattern, and usage of Jetpack libraries with Kotlin.

---

### 🛠 Tech Stack

* Kotlin – Programming Language
* MVVM Architecture –  separation of concerns
* ViewModel & Flows – Lifecycle-aware data handling
* Jetpack Compose – Modern declarative UI toolkit
* Coroutines – For background operations
* Hilt (Dagger) – Dependency Injection
* Retrofit – API calls
* Gson – JSON parsing

---

### 🚀 Features

* Fetches and displays data from API (Cat Breeds / Quotes etc.)
* Example of UI State Management using sealed classes
* Navigation Component integration with Compose
* Clean Repository + ViewModel flow for network requests
* Error and loading state handling

---

### 📂 Project Structure

```
com.example.machinecodingroundsample
│
├── data
│   ├── model        # Data classes
│   ├── network      # Retrofit API service
│   └── repository   # Repository layer
│
├── di               # Hilt modules
├── ui
│   ├── view         # Compose UI Screens
│   └── viewmodel    # ViewModels
│
└── utils            # Utility classes / helpers

```

---

### 🏃‍♂️ How to Run

* Clone this repository
```
git clone https://github.com/richarst12/MachineCodingRoundsample.git

```
* Open in Android Studio (latest version recommended)
* Sync Gradle & run the app on an emulator or device

---


### 🔍 TODOs / Improvements
*  Add pagination for loading 
*  MVVM Clean
*  Pull-to-refresh support on Home screen
*  Offline caching using Room
*  Unit and UI tests

---

### About Me
**Richa Sharma** |
Android Engineer | Medium Blogger
🔗 [LinkedIn](https://www.linkedin.com/in/richa-sharma-67b56a114/)
📝 [Medium](https://medium.com/@sharmaricha7724)

---

❤️ Found this useful?
Leave a ⭐ on the repo and connect with me on Medium!


