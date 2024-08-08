Architecture
Uses concepts of the notorious Uncle Bob's architecture called [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).</br>
clean architecture 

Better separation of concerns. Each module has a clear API., Feature related classes life in different modules and can't be referenced without explicit module dependency.
Features can be developed in parallel eg. by different teams
Each feature can be developed in isolation, independently from other features
faster compile time

Design Pattern Used :
MVVM + Clean architecture for better testability , maintainability , reusability , modification 
USE CASE to reduce work before sending to viewModel ... mediator between ViewModels and Repository
Repository to get a single source a rely it with domain
DataSource to get difference betweel local and remote flow
Singleton one instance (deprecated) to do better SINGLE RESPONSABILITY ...

WHY DEPENDANCY INJECTION ? strong coupling problem, isolate dependencies / better than Factory deisgn pattern
Dependency injection has two main advantages. The first is that you can control
instantiating objects from a central location instead of spreading it across the entire codebase.
Another reason is that it will help us write unit tests, PostDataRepository because we can now
simply pass mock versions of LocalDataSource and RemoteDataSource to the PostDataRepository constructor instead of real values.

SOLID : 

SOLID:
S: Single Responsabilty, preferably each class has its responsibility 
O: open for extension / close modification
L: full replacement
I: Multifunction device:just method we need ...
D: high level component does not depend on others flexible and easy to maintain

![img.png](Screenshots%2Fimg.png)
![home.png](Screenshots%2Fhome.png)

Modules:

random-user-ui - It uses all the components and classes releated to Android Framework. It gets the data from presentation layer and shows on UI. (access all the modules)
data - The data layer implements the repository interface that the domain layer defines. This layer provide a single source of truth for data. (Kotlin module that can only access domain module)
remote - Handles data interacting with the network. (can only access data module)
cache - Handles data interacting with the local storing (Room DB). (can only access data module)
domain - The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. This task is part of the business logic of the application. (Kotlin module that cannot access any other module)
presentation - MVVM with ViewModels exposing LiveData that the UI consume. The ViewModel does not know anything about it's consumers. (Android module that can only access domain module)

    This repository code is mostly inspired by [Android-Clean-Architecture-Boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate).
