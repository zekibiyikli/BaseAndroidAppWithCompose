# BaseAndroidAppWithCompose

**BaseAndroidAppWithCompose** is a production-ready Android template built with modern technologies, implementing MVVM architecture with a clean layered structure: Presentation, Domain (via Repository), and Data.

![Android](https://img.shields.io/badge/Android-API%2026%2B-green)
![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-blue)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202026.04.01-brightgreen)
![Hilt](https://img.shields.io/badge/Hilt-2.57.2-orange)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## Architecture

The project follows **MVVM** with a clean separation of concerns across four layers:

```
app/
├── core/            # Base classes (BaseActivity, BaseViewModel, BaseModel, BaseResponse)
├── data/
│   ├── flow/        # ApiResult sealed class + toResultFlow builder
│   ├── local/
│   │   ├── datastore/   # Jetpack DataStore (type-safe key-value)
│   │   └── lsp/         # EncryptedSharedPreferences
│   ├── room/        # Room database + Hilt module + DAO
│   └── server/      # Retrofit, OkHttp, ApiService, token interceptor
├── model/           # Immutable data classes + Room entities
├── ui/
│   ├── activity/    # MainActivity + MainViewModel + MainScreen
│   ├── fragment/    # Screen + ViewModel per feature
│   ├── navigation/  # Sealed NavRoutes + MainNavHost
│   ├── item/        # Reusable composable items (shimmer, user card)
│   ├── dialog/      # NoInternetScreen
│   └── theme/       # Color, Typography, Theme
├── util/            # NetworkMonitor, UserPagingSource
├── ext/             # Kotlin extension functions
└── enums/           # ErrorCodes, NetworkStatus, LocalSharedPrefKey
```

---

## Tech Stack

| Category | Library | Version |
|----------|---------|---------|
| Language | Kotlin | 2.2.21 |
| UI | Jetpack Compose BOM | 2026.04.01 |
| DI | Hilt | 2.57.2 |
| Networking | Retrofit | 3.0.0 |
| Networking | OkHttp | 5.3.2 |
| Database | Room | 2.8.4 |
| Async | Kotlin Coroutines | 1.10.2 |
| Paging | Paging 3 | 3.4.2 |
| Local Storage | Jetpack DataStore | 1.2.1 |
| Secure Storage | EncryptedSharedPreferences | 1.1.0 |
| Image Loading | Coil | 2.7.0 |
| Animations | Lottie | 6.0.0 |
| Logging | Timber | 5.0.1 |
| Splash | SplashScreen API | 1.0.1 |
| Static Analysis | Detekt | 1.23.7 |
| Symbol Processing | KSP | 2.2.21-2.0.5 |

---

## Key Features

### Networking
- `ApiResult<T, E>` sealed class — `Success`, `Error`, `Loading` states
- `toResultFlow {}` — wrapper that converts any Retrofit `Response<T>` into a `Flow<ApiResult>`
- `UserTokenHeader` — OkHttp interceptor that injects `Authorization: Bearer` token automatically
- Logging interceptor enabled only in DEBUG builds
- Configurable timeouts (connect: 20s, read: 60s, write: 120s)

### State Management
- Full **StateFlow** everywhere — no LiveData
- `BaseViewModel` exposes `showProgress: StateFlow<Boolean>` and `generalError: StateFlow<ErrorModel?>`
- `sendRequest {}`, `sendRequestWithError {}`, `sendRequestWithoutLoading {}` helpers with automatic progress handling

### Local Storage — Three Options
| Option | Use Case |
|--------|----------|
| `EncryptedSharedPreferences` | Sensitive data (token, flags) |
| `DataStore` | Type-safe user preferences |
| `Room` | Structured local database |

### Navigation
- Sealed class `NavRoutes` — type-safe routes, no raw strings
- `MainNavHost` — bottom bar visibility managed via `MainViewModel.isBottomBarVisible: StateFlow<Boolean>`
- Animated bottom bar slide-in/out per screen

### Other
- Native Compose shimmer animation (no third-party library)
- `NetworkMonitor` — real-time internet connectivity detection
- Paging 3 with custom `UserPagingSource`
- `enableEdgeToEdge()` for edge-to-edge UI
- Singleton Gson via `BaseViewModel.companion`

---

## Build Variants

### Product Flavors
| Flavor | App ID Suffix | Base URL |
|--------|--------------|----------|
| `dev` | `.dev` | `https://randomuser.me/` |
| `prod` | — | `https://api.yourdomain.com/` |

### Build Types
| Type | Minify | App ID Suffix |
|------|--------|--------------|
| `debug` | No | `.debug` |
| `release` | Yes (R8 + ProGuard) | — |

Full ProGuard rules included for Kotlin, Coroutines, Retrofit, OkHttp, Gson, Hilt, Room, DataStore, Coil, and Lottie.

---

## Requirements

- Android Studio Hedgehog or newer
- Min SDK: **26** (Android 8.0)
- Target SDK: **36**
- JVM: **17**

---

## Setup

```bash
git clone https://github.com/zekibiyikli/BaseAndroidAppWithCompose.git
cd BaseAndroidAppWithCompose
```

Open in Android Studio → select `devDebug` build variant → Run.

To change the API base URL, update `buildConfigField` values in `app/build.gradle.kts` under `productFlavors`.

---

## Testing

The project includes unit tests covering the core layers:

| File | Coverage |
|------|---------|
| `BaseViewModelTest` | showProgress, generalError StateFlow, errorDataModelFillUp |
| `MainViewModelTest` | showDialog, isBottomBarVisible state |
| `HomeViewModelTest` | usersFlow success/error/refresh |
| `ApiResultTest` | sealed class states |
| `ToResultFlowTest` | flow emission correctness |
| `MainRepoTest` | repository delegation |
| `NavRoutesTest` | route string correctness |

**Tools:** JUnit 4 · MockK · Turbine · kotlinx-coroutines-test · `MainDispatcherRule`

```bash
./gradlew test
```

---

## Static Analysis

```bash
./gradlew detekt
```

Config file: `detekt.yml` at project root.

---

## License

```
MIT License — feel free to use this template as the base for your projects.
```
