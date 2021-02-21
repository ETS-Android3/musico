# Music Wiki

<img src="/screenshots/icon.png" height="200px"/> <br>
MusicWiki is an unofficial Last.fm app that contains information about different music genres, the albums, artists and tracks listed under the genre.


##### How to build the project?
Get your api key from [lastFm](https://www.last.fm/) and put that key in gradle.properties and build the project, and you are good to go.

Build the project in studio or write following commands
Open terminal and type the below command to generate debug build below are commands for Linux and Mac OS users
```
./gradlew assembleDebug
```

To build and install the app in running emulator
```
./gradlew installDebug
```

##### Or Just download the apk file in from [here](/builds/app-debug.apk)

##### How its being developed?:

All the android new Best Practises have been implemented:

- Android **navigational Component** for navigation between fragment and achieving a single activity based application
- Simple **MVVM** architecture with deferring all complexities of UI to viewModel
- Using **Dagger** for dependency Injection
- Simplicity and easy design of the app (Inspired by Indicative UI provided )
- Android View Binding to avoid monotonous findViewById call and run-time exceptions and crashes.


##### Some Screenshots:

<div style="display:flex | space-evenly;" >
    <img src="/screenshots/1.png" height="400px" />
    <img src="/screenshots/2.png" height="400px" />
</div>
<div>
<br>
<div style="display:flex | space-evenly;" >
    <img src="/screenshots/3.png" height="400px" />
    <img src="/screenshots/4.png" height="400px" />
</div>
<br>
<div style="display:flex | space-evenly;" >
    <img src="/screenshots/5.png" height="400px" />
    <img src="/screenshots/6.png" height="400px" />
</div>
<br>
<div style="display:flex | space-evenly;" >
    <img src="/screenshots/7.png" height="400px" />
    <img src="/screenshots/8.png" height="400px" />
</div>


##### Features
**The application has following features implemented**

- Users can explore various genres of songs
- Explore artists, albums, tracks
- Know their artist more closely
- Open tracks in custom chrome tabs
- Clean and simple UI
- Modularized App with dagger


##### Decisions

1. Since Offline working of the application was not primary requirement, thus I had decided not to implement disk cache with Room,
and hence we have a single source of data, and as a result **no repository layer** was created. Creating a repository with single won't have been much useful
ViewModel directly is making api call and interacting with model layer and deferring complexity from UI layer.

2. Decision of not using rx-java as it had not much use-case to server in present app, using rx-java would have been much beneficial in some
core problem like search functionality and profile details call for each person
and power it gives with Zip, flatMap ,debounce, delay operators and other operators.
**Rx-java can be much useful when we want to make a better pub-sub communication between android components**. But for this application **live-data**
solved much of the use-cases, and hence rx-java was not missed much!

3. Server sends star like image for artist profile's avatar and for tracks's cover, and because of this I created a rule based avatar for artist's
avatar it can be found in CommonUIUtils.java

4. Tried to resuse the code as much as possible like Shared viewModel between tab fragments, and common adapter for 3 recyclerviews

5. Decision of handling the api-errors which occurs in the app usage, following are the screens that shows some of the illustrations of error handling

<div style="display:flex | space-evenly;" >
    <img src="/screenshots/9.png" height="400px" />
    <img src="/screenshots/10.png" height="400px" />
</div>

6. Singleton design pattern and OOPs concepts are demonstrated in the source code.

##### Assumptions

- Assumption that data would always be **non-null** from server, although I have surrounded code blocks with try and
catch block (can be found in ArtistDetailsFrag and AlbumDetailsFrag),
and that would prevent the application from crash and silently it would fail.

##### Testing
- Unit tests for CommonUIUtils and Timely greeting can be found in test directory
-



