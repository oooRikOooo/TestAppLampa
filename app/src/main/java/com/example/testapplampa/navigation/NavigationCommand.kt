package com.example.testapplampa.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ToDirection(val direction : NavDirections) : NavigationCommand()

    object Back: NavigationCommand()
}