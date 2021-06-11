# Blockchain App

This is an application that consumes the API of [Blockchain](https://www.blockchain.com/api/charts_api) and display a linear chart of the bitcoin transactions per second on a given range & other stats.
The graph is made with [whimsical](https://whimsical.com).

![GitHub Cards Preview](https://github.com/ChristopherME/Blockchain/blob/master/art/blockchain_app_architecture_diagram.png)

## App Screenshot v0.0.1
![GitHub Cards Preview](https://github.com/ChristopherME/Blockchain/blob/master/art/screenshot.jpg)

## Tech Stack

This project is monolithic(for now) and has only one screen. However, all inner packages are well organized in order to be scalable and modularized in the following days. It uses MVVM as Software Design Patter and is using Jetpack Compose for the UI.

- :core:functional-programming contains the Either sealed class & some helper methods. Either is the "wrapper" for handle Either an Error or a Success structure.
- :core:network contains some extensions for execute safe retrofit calls (I write 3 articles about it -> Create a safe retrofit calls extension part [I](https://christopher-elias.medium.com/safe-retrofit-calls-extension-with-kotlin-coroutines-for-android-in-2021-part-i-d47e9e2962ad), [II](https://christopher-elias.medium.com/safe-retrofit-calls-extension-with-kotlin-coroutines-for-android-in-2021-part-ii-fd55842951cf), & [III](https://christopher-elias.medium.com/safe-retrofit-calls-extension-with-kotlin-coroutines-for-android-in-2021-part-iii-583249b0e86b))
- :utils has utilities classes like a ResourceProvider for loading string resources & a NetworkChecker interface which implementation will tell us if the user is connected to some network.
- :middlewares has middlewares that going to act as a firewall before executing any retrofit call. Only if all the middlewares are supplied then the retrofit call is allowed to be executed.
- :features:home Home is our only feature, it has it's data sources & repositories interfaces for prepare the data and send it to the view model.
- :ui:components: contains our composables.
- app HiltApplication in charge to create the app DI graph.

## Development setup

You require at least Android Studio Arctic Fox | 2020.3.1 Beta 3 Build #AI-203.7717.56.2031.7395685 for run this project. No API Keys need it.

## Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency Injection
- Uses [Jetpack Compose](https://developer.android.com/jetpack/compose) for latest declarative UI features
