# RecipesTest
 
### Introduction 
For this project, the concept of clean architecture is utilized, aiming to organize the software 
design elements into layers that separate different responsibilities of its logic.

The created modules are organized in a way that indicates the appropriate place to develop the code. 
Below is a brief description of each of the created modules and their specific uses:

- `app` (Android library): Contains logic related to the presentation layer (Activities, Fragments, 
Components, ViewModels, etc.).
- `data` (Android library): Involves logic related to data processing (Retrofit, Room, Response,
Datasource, Repository, etc.).
- `domain` (Pure Java/Kotlin library): This module focuses on all the business logic (Use cases, 
Repository Interface, etc.).
- `di` This is a convenient module used to centralize and satisfy the dependencies used by the project.
- `common` This module helps centralize objects that are common to all modules (Either, ErrorEntity, etc.).

In the presentation layer, the MVVM pattern is employed, which helps separate the view logic and keeps 
it all in the view model. Additionally, it facilitates the implementation of the observer pattern to 
make the views more reactive.

The code structure in this repository incorporates the following tools:

- [Basic Modularization](https://android-developers.googleblog.com/2021/12/rebuilding-our-guide-to-app-architecture.html) (app, domain, data layers)
- [Clean Architecture](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Compose](https://developer.android.com/jetpack/compose)
- [Flow](https://developer.android.com/kotlin/flow)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [MockK](https://mockk.io/)
- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/training/data-storage/room?hl=es-419)

### Tests
Unit tests were conducted in all modules, covering all success cases and different error scenarios. 
This validation ensures that all functions used throughout the flow of each feature work correctly.
