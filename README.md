# Project Structure

## Build

The build is set up using convention plugins that take care of common build logic across modules. Camera and Tenserflow libraries have been given their own separate plugins to make them easier to separate

# Object Detection
A reference object(cup) is used to estimate the dimensions of other objects in the scene. The height of the reference is 10cm while the width is 8cm

## Multi-module

The thought is to have the app module depend on different feature modules each of them divided into

- domain
- navigation
- ui

The dependency structure between them looks like this
navigation -> domain
ui -> domain and navigation
domain -> should not depend on any feature module but can depend on core modules

## Concurrency

Concurrency is achieved by standard kotlin coroutines and flow apis

## UI

UI is constructed through jetpack compose using material3 components. It is all wrapped inside a
custom Meq theme.

## Navigation

Navigation is done through compose-navigation library. Each `ui` module is supposed to provide their
navGraph into a set through dagger. The set is then looped through and loaded in the `app` module.

## Code structure

The project follows general clean architecture guidelines with multiple layers including domain, navigation, ui, etc.

# Linting

For linting, spotless(ktlint) has been integrated with a few custom rules

# What could be better given more time
- Some of the code could be moved from the UI to viewmodel
- Adding tests for ImageDetector or ImageAnalyzer
