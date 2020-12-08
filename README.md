# lucky_task

load some offers as a json response from api, display data in recycler view and show offer details.

## Design Pattern
MVVV design pattern as recomended in docs [guide to app archticture](https://developer.android.com/jetpack/docs/guide) , with some modification to the network layer adding Kotlin couritines, also using dagger for dependency injection 

## Technology used

* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for asynchronous programming
* [dagger](https://github.com/google/dagger) for dependency injection
* [databinding](https://developer.android.com/topic/libraries/data-binding) to bind data to ui
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [navigation](https://developer.android.com/guide/navigation/) to handle navigation between screens
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to bind data to ui
* [Retrofit] for networking
* [timper for] logging
* [customLogger] for logging formatting

## Demo

<p align="center">
<img src="https://github.com/hazem1262/lucky_task/blob/develop/demo.gif" width="300" height="600" />
</p>

## Note

this repository is based on my public Repo [MVVM starter project](https://github.com/hazem1262/mvvvm-starter-project)
