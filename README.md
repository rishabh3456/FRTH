# FRTH

This test project was created using Android Studio. Please download the repo directly to Android Studio to run it.

## Functions

The test project accesses the provided API endpoint and gets the data into a list. This data is then filtered to remove any items with a null or empty name, and grouped by the listId values. The data is then displayed in these groups sorted by the name.

## Layout

The most important files lie in the `FRTH/app/src/main/java/com/example/frth/` folder. In this folder,
1. The api directory consists of 2 files which define the class and interface required to connect to the provided endpoint.
2. The model directory consists of the class used to store the data recieved from the api endpoint.
3. MainActivity.kt holds the functions for calling the api and transforming and displaying the data.

The test for the api endpoint is in the `FRTH/app/src/test/java/com/example/frth/` directory.
