
# 📌 Manual Dependency Injection in Android  

This repository demonstrates **Manual Dependency Injection (Manual DI)** in Android, following the official Android Developer guide:  
👉 [Manual Dependency Injection in Android](https://developer.android.com/training/dependency-injection/manual).  

## 🚀 Application Flow  

The repository is structured into **four branches**, each showcasing a different way of handling dependencies **without relying on DI frameworks** like Dagger or Hilt.  

| Branch                        | Description |
|--------------------------------|------------|
| `No-DI`                       | ❌ No dependency injection. Dependencies are created inside classes, making the code tightly coupled and hard to test. |
| `With-AppContainer`           | ✅ Introduces an **AppContainer**, which holds shared dependencies at the application level. |
| `With-Factory-AppContainer`   | ✅ Uses the **Factory pattern** to create dependencies only when needed, improving lifecycle management. |
| `ApplicationFlow`              | ✅ Depends upon the application flow of the project to divide in seperate containers. |

---

## 🔹 **Detailed Explanation of Each Branch**  

### 1️⃣ `No-DI` (No Dependency Injection ❌)  

📌 **Approach**:  
- Dependencies are instantiated **directly inside classes**, leading to tight coupling.  
- **Difficult to scale and test** because dependencies cannot be easily replaced.  

📌 **Problems**:  
- ❌ Hardcoded dependencies  
- ❌ Difficult to write unit tests  
- ❌ Not flexible for future changes  

📌 **Example**:  
```kotlin
class Repository {
    fun fetchData(): String {
        return "Data from Repository"
    }
}

class MainViewModel {
    private val repository = Repository() // ❌ Hardcoded dependency

    fun getData(): String {
        return repository.fetchData()
    }
}
```
🔴 This approach is **not recommended** for real-world applications.  

---

### 2️⃣ `With-AppContainer` (Basic Dependency Injection ✅)  

📌 **Approach**:  
- Introduces an `AppContainer` inside the `Application` class to **hold shared dependencies**.  
- Dependencies are **centralized** instead of being instantiated everywhere.  

📌 **Benefits**:  
✅ Centralized dependency management  
✅ Easier to replace dependencies for testing  
✅ More scalable  

📌 **Example**:  
```kotlin
class AppContainer {
    val repository = Repository()
}

class MyApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer() // ✅ Centralized dependency creation
    }
}

class MainViewModel(repository: Repository) {
    private val repository = repository // ✅ Injected via constructor

    fun getData(): String {
        return repository.fetchData()
    }
}
```
🟢 This is a **better approach** than `No-DI`, but it still creates all dependencies at startup, even if they’re not used.  

---

### 3️⃣ `With-Factory-AppContainer` (Factory Pattern ✅✅)  

📌 **Approach**:  
- Extends `With-AppContainer` by introducing a **Factory pattern** to create objects only when needed.  
- This **optimizes memory usage** and **avoids unnecessary object creation**.  

📌 **Benefits**:  
✅ Only creates objects when required  
✅ Reduces unnecessary memory usage  
✅ More flexible dependency management  

📌 **Example**:  
```kotlin
class AppContainer {
    val repository = Repository()
    
    fun createMainViewModel(): MainViewModel {
        return MainViewModel(repository) // ✅ Factory method
    }
}
```
🟢 This is a **more scalable approach**, preventing unnecessary object creation at app startup.  

---

### 4️⃣ `Application Flow` (Dividing the application into different containers ✅✅✅)  

📌 **Approach**:  
- Uses **interfaces** to abstract dependencies, improving flexibility and making it easier to switch implementations.  
- Helps in **unit testing** by allowing dependency replacement via mock implementations.  

📌 **Benefits**:  
✅ Increases flexibility (easier to replace dependencies)  
✅ Improves unit testability (can use fake implementations)  
✅ Decouples dependencies from concrete implementations  

📌 **Example**:  
```kotlin
interface Repository {
    fun fetchData(): String
}

class RemoteRepository : Repository {
    override fun fetchData(): String {
        return "Data from Remote Source"
    }
}

class MainViewModel(private val repository: Repository) {
    fun getData(): String {
        return repository.fetchData()
    }
}
```
🟢 This is a **better design choice** because dependencies are loosely coupled, making code more maintainable.  

---

## 📂 Project Structure  

```
📂 app
 ┣ 📂 di                # Manual Dependency Injection setup
 ┃ ┣ 📜 AppContainer.kt
 ┃ ┣ 📜 Factory.kt
 ┣ 📂 ui                # UI-related classes (Activities, ViewModels)
 ┣ 📂 data              # Repository and data sources
 ┣ 📂 network           # Network layer (API clients)
 ┣ 📂 db                # Database layer
 ┣ 📜 MainActivity.kt   # Entry point of the app
```

---

## 🛠 How to Run the Project  

1. Clone the repository:  
   ```bash
   git clone https://github.com/rockyhappy/Manual-DI.git
   ```
2. Open the project in **Android Studio**.  
3. Checkout the branch you want to explore:  
   ```bash
   git checkout <branch-name>
   ```
   Replace `<branch-name>` with:  
   - `No-DI`  
   - `With-AppContainer`  
   - `With-Factory-AppContainer`  
   - `With-Interface`  
4. Run the app on an emulator or physical device.  

---

## 📚 Reference  

- Official Guide: [Manual Dependency Injection in Android](https://developer.android.com/training/dependency-injection/manual)  

---

## 🤝 Contributing  

Contributions are welcome! Feel free to submit **issues** and **pull requests** to improve this project. 🚀  

---

This README **now includes four branches** (`No-DI`, `With-AppContainer`, `With-Factory-AppContainer`, and `With-Interface`). If you have a **different fourth branch**, let me know and I’ll update the documentation accordingly! 😊
