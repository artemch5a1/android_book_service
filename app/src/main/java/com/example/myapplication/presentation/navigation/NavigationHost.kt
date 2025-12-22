package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.screen.CreateOrEditScreen
import com.example.myapplication.presentation.screen.ListBookScreen
import com.example.myapplication.presentation.screen.SignInScreen

@Composable
fun NavigationHost(){

    val navController = rememberNavController()

    NavHost(navController, "signIn") {

        composable("signIn"){
            SignInScreen(navController)
        }

        composable("listBookScreen"){
            ListBookScreen(navController)
        }

        composable("createBookScreen"){
            CreateOrEditScreen(navController)
        }

    }

}