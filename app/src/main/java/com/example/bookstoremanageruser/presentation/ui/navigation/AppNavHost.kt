package com.example.bookstoremanageruser.presentation.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.presentation.ui.screen.AdminScreen
import com.example.bookstoremanageruser.presentation.ui.screen.DetailScreen
import com.example.bookstoremanageruser.presentation.ui.screen.MainScreen
import com.example.bookstoremanageruser.presentation.ui.screen.SaveScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable(route = "main_screen") {
            MainScreen(onNavigationToSaveScreen = { navController.navigate("save_screen") },
                onNavigateToAdminScreen = { navController.navigate("admin_screen") },
                onNavigateToDetailScreen = {
                    navController.navigate(
                        "detail_screen/${
                            URLEncoder.encode(
                                Gson().toJson(it),
                                "UTF-8"
                            )
                        }"
                    )
                })
        }
        composable(route = "detail_screen/{book_item}") {
            val bookItemJson = it.arguments?.getString("book_item")
            val decodedJson = URLDecoder.decode(bookItemJson, "UTF-8")
            val bookEntity = Gson().fromJson(decodedJson, BookEntity::class.java)
            DetailScreen(
                bookEntityItem = bookEntity
            )
        }
        composable(route = "save_screen") {
            SaveScreen {
                navController.navigate("detail_screen/${Gson().toJson(it)}")
            }
        }
        composable(route = "admin_screen") {
            AdminScreen()
        }
    }
}