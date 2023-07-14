# RecipesTest
 
### Introduction 
Para este proyecto, se utiliza el concepto de arquitectura limpia, que intenta organizar los elementos
que componen el diseño del software en capas que separan las diferentes responsabilidades de su lógica.

Los módulos creados están organizados de manera que indican el lugar adecuado para desarrollar el código.
A continuación, se presenta una breve descripción de cada uno de los módulos creados y su uso específico:

- `app` (biblioteca de Android): lógica relacionada con la presentación (Activities, Fragments, 
- Components, ViewModels, etc.).
- `data` (biblioteca de Android): lógica relacionada con el procesamiento de datos (Retrofit, Room, 
Response, Datasource, Repository, etc).
- `domain` (biblioteca pura de Java/Kotlin): aqui se centra todo lo que representa la lógica de negocio 
(Use cases, Repository Interface, etc.).
- `di` este es un módulo conveniente utilizado para centralizar y satisfacer las dependencias 
utilizadas por el proyecto.
- `common` este es un modulo que nos ayuda a centralizar los objetos que tienen en comun todos los modulos
(Either, ErrorEntity, etc.)

Para la logica en la capa de presentación se trabajo con el patron MVVM, el cual nos ayuda a separar
la logica de la vista y tenerla toda en el view model, ademas que nos facilita la implementación del 
patron observer para que las vistas sean mas reactivas.

La estructura del codigo en este repositorio contiene las siguientes herramientas: 

- [Basic Modularization](https://android-developers.googleblog.com/2021/12/rebuilding-our-guide-to-app-architecture.html) (app, domain, data layers)
- [Clean Architecture](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Compose](https://developer.android.com/jetpack/compose)
- [Flow](https://developer.android.com/kotlin/flow)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [MockK](https://mockk.io/)
- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/training/data-storage/room?hl=es-419)

