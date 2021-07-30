# Blockchain App

This is an application that consumes the API of [Blockchain](https://www.blockchain.com/api/charts_api) and display a linear chart of the bitcoin transactions per second on a given range & other stats.
The graph is made with [whimsical](https://whimsical.com).

![GitHub Cards Preview](https://github.com/ChristopherME/Blockchain/blob/master/art/blockchain_app_architecture_diagram.png)

## Tech Stack

This project is monolithic(for now) and has only one screen. However, all inner packages are well organized in order to be scalable and modularized in the following days. It uses MVVM as Software Design Patter and is using Jetpack Compose for the UI.

- :core:functional-programming contains the Either sealed class & some helper methods. Either is the "wrapper" for handle Either an Error or a Success structure.
- :core:network contains some middlewares & extensions for execute safe retrofit calls (I write 3 articles about it -> Create a safe retrofit calls extension part [I](https://christopher-elias.medium.com/safe-retrofit-calls-extension-with-kotlin-coroutines-for-android-in-2021-part-i-d47e9e2962ad), [II](https://christopher-elias.medium.com/safe-retrofit-calls-extension-with-kotlin-coroutines-for-android-in-2021-part-ii-fd55842951cf), & [III](https://christopher-elias.medium.com/safe-retrofit-calls-extension-with-kotlin-coroutines-for-android-in-2021-part-iii-583249b0e86b))
- :core:network/middlewares middlewares are going to act as a firewall before executing any retrofit call. Only if all the middlewares are supplied then the retrofit call is allowed to be executed.
- :utils has utilities interfaces like a ResourceProvider & ConnectivityUtils, who's implementations will be in the app module.
- :features:home Home is our only feature (for now), it has it's data sources, mappers & repositories interfaces for prepare the data and send it to the HomeViewModel.
- :ui:components: contains our composables.
- :tests unit tests for repository, datasources, mappers, etc. I also write articles about this. [Unit Tests](https://proandroiddev.com/understanding-unit-tests-for-android-in-2021-71984f370240) & [Instrumented Tests](https://proandroiddev.com/easy-instrumented-tests-ui-tests-for-android-in-2021-2e28134ff309)
- :app module contains our HiltApplication in charge to create the app DI graph and the interface implementations from our libraries.

## App Screenshot v1.0.0
![GitHub Cards Preview](https://github.com/ChristopherME/Blockchain/blob/master/art/screenshot.jpg)

## Development setup

You require at least [Android Studio Arctic Fox](https://developer.android.com/studio/releases#arctic-fox) | 2020.3.1 Build #AI-203.7717.56.2031.7583922, built on July 26, 2021 for run this project. No API Keys required.

## Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency Injection
- Uses [Jetpack Compose](https://developer.android.com/jetpack/compose) for latest declarative UI features
- Uses [mockk](https://github.com/mockk/mockk) for mocking objects, interfaces & more.
- Uses [JUnit4](https://junit.org/junit4/) for unit tests assertions & more.


## 📃 License

```
Copyright 2021 Christopher Elias

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```