# multiplatform-viewmodel 🗳

Create shared `ViewModel`'s for shared business logic using our `ViewModel` base class.

## Features
- [x] Uses Jetpack `ViewModel` on Android for lifecycled 
- [x] Exposes a `CoroutineScope` to be used in your methods

## Overview of Package
This package has 1 component to it:
A `ViewModel` class you can base your view models on.

## Getting Started

TBD once package is published.

## Using it on iOS with a `UIViewController`
When using it on iOS you'll want to make sure that you call `clear()` on your ViewModel on `deinit` to properly kill the `CoroutineScope`

