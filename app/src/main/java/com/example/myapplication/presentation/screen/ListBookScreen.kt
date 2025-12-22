package com.example.myapplication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.domain.model.Book
import com.example.myapplication.presentation.model.ResultState
import com.example.myapplication.presentation.viewmodel.ListBookViewModel

@Composable
fun ListBookScreen(
    navController: NavController,
    viewModel: ListBookViewModel = hiltViewModel()
){

    val books = viewModel.books.observeAsState(emptyList())

    val category = viewModel.category.observeAsState(emptyList())

    val resultState = viewModel.resultState.collectAsState()

    val textSearch = viewModel.textSearch.collectAsState()

    Box(){

        Column(modifier = Modifier.padding(top = 20.dp)) {

            OutlinedTextField(
                value = textSearch.value,
                onValueChange = {
                    viewModel.updateTextSearch(
                        it
                    )
                    viewModel.filterBook()
                },
                label = {
                    Text(text = "Поиск")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow {

                items(category.value.size){
                    index ->

                    Button(
                        onClick = {
                            category.value[index].updateSelected(
                                !category.value[index].isSelected.value
                            )
                            viewModel.filterBook()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(
                                category.value[index].isSelected.collectAsState().value
                            ){
                                Color.Gray
                            }
                            else{
                                Color.Red
                            }
                        )
                    )
                    {
                        Text(text = category.value[index].item.categoryTitle)
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                navController.navigate("createBookScreen")
            })
            {
                Text(text = "Создать")
            }
        }

        when(resultState.value){
            is ResultState.Error -> {
                Text(text = (resultState.value as ResultState.Error).message,
                    modifier = Modifier.align(
                    Alignment.Center))
            }
            ResultState.Init -> {
                ListingBook(books.value, navController)
            }
            ResultState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center)
                )
            }
            is ResultState.Success -> {
                ListingBook(books.value, navController)
            }
        }

    }
}

@Composable
fun ListingBook(
    books: List<Book>,
    navController: NavController
){

    Box(modifier = Modifier.padding(top = 280.dp, start = 10.dp, end = 10.dp)){

        LazyColumn {

            items(books.size){
                index ->

                Box(
                    modifier = Modifier.background(
                        color = Color.Gray
                    )
                        .width(400.dp)
                        .padding(40.dp)
                ){

                    Column {

                        Text(text = "Название: ${books[index].name}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Описание: ${books[index].description}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Дата выпуска: ${books[index].datePublish.toLocalDate()}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            navController.navigate("updateBookScreen/${books[index].id}")
                        }) {
                            Text(text = "Обновить")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(18.dp))

            }

        }

    }

}