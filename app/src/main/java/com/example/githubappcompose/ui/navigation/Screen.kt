package com.example.githubappcompose.ui.navigation

sealed class Screen(val route:String){
    object Home:Screen("home")
    object Detail:Screen("home/{username}"){
        fun createRoute(username:String) = "home/$username"
    }

}