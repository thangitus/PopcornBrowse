# PorconBrowe

PorconBrowe is a **Kotlin Multiplatform Mobile** (KMM) project that displays today's trending movies using the [TMDB API](https://developer.themoviedb.org/reference/trending-movies) (you'll need your own API key). It is structured following **Clean Architecture** principles to clearly separate domain, data, and presentation layers, ensuring maintainability and testability.

<p float="left">
  <img src=".images/home.png" width="400" />
  <img src=".images/detail.png" width="400" />
  <img src=".images/search.png" width="400" />
</p>

## 🚀 Features

* **Trending Movies**: Fetches and caches today’s trending movies from TMDB.
* **Search**: Inline search for movies (online only).
* **Detail View**: Shows full movie details with offline caching.

## 🛠 Technologies & Libraries

* **[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)** – UI framework that targets Android, desktop, and iOS.
* **[Voyager](https://github.com/adrielcafe/voyager)** – A simple, type-safe navigation library for Compose.
* **[Koin](https://insert-koin.io/)** – Dependency injection for Kotlin.
* **[Ktor](https://ktor.io/)** – HTTP client for networking.
* **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)** – JSON serialization.
* **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** – Asynchronous programming.
* **[Napier](https://github.com/AAkira/Napier)** – Logger for Kotlin Multiplatform.
* **[Coil](https://coil-kt.github.io/coil/)** – Image loading library for Compose.
* **[Kotlinx DateTime](https://github.com/Kotlin/kotlinx-datetime)** – Multiplatform date/time library.
* **[Room DB Multiplatform](https://developer.android.com/kotlin/multiplatform/room)** – Database for offline caching.
* **[Mokkery](https://mokkery.dev/)** – Multiplatform mocking for tests. (See: [HomeRepositoryImplCommonTest](/features/home/src/commonTest/kotlin/com/app/movie/home/data/repository/HomeRepositoryImplCommonTest.kt), [MovieDetailRepositoryImplTest](/features/detail/src/commonTest/kotlin/com/app/movie/detail/data/remote/repository/MovieDetailRepositoryImplTest.kt))

## 🔭 Upcoming Work

* **iOS & Desktop Support**: Currently only Android is built; integrate and test on iOS and desktop targets.
* **Paging Integration**: Load additional pages as the user scrolls, to reduce memory footprint.
* **UI Testing**: Automated Compose UI tests.

## ⚙️ Usage

### Prerequisites

* Android Studio 2024.3.2 Patch 1 (or later)
* TMDB API Key

### Steps

1. Clone this repository:

   ```bash
   git clone https://github.com/thangitus/PopcornBrowse
   ```
2. Open in Android Studio.
3. Add your TMDB API Key to `local.properties`:

   ```properties
   API_KEY=your_tmdb_api_key_here
   ```
4. Select the `androidApp` configuration and run.

---

<p align="center">Made with ❤ using Kotlin Multiplatform</p>
