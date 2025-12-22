package com.example.myapplication.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.presentation.component.CustomDatePicker
import com.example.myapplication.presentation.component.CustomDropDown
import com.example.myapplication.presentation.component.StateButton
import com.example.myapplication.presentation.viewmodel.CreateOrEditViewModel

@Composable
fun CreateOrEditScreen(
    navController: NavController,
    id: String? = null,
    viewModel: CreateOrEditViewModel = hiltViewModel()
){

    val resultState = viewModel.resultState.collectAsState()

    val book = viewModel.book.collectAsState()

    val category = viewModel.category.observeAsState(emptyList())

    val selectedCategory = viewModel.selectedCategory.collectAsState()

    LaunchedEffect(Unit)
    {
        if(id != null){
            viewModel.loadBook(id)
        }
    }

    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.align(Alignment.Center)) {


            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    ""
                )
            }

            OutlinedTextField(
                value = book.value.name,
                onValueChange = {
                    viewModel.updateLogin(
                        book.value.copy(
                            name = it
                        )
                    )
                },
                label = {
                    Text(
                        text = "Название"
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = book.value.description,
                onValueChange = {
                    viewModel.updateLogin(
                        book.value.copy(
                            description = it
                        )
                    )
                },
                label = {
                    Text(
                        text = "Описание"
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomDatePicker(
                label = "",
                currentDate = book.value.datePublish.toLocalDate(),
                onDateSelected = {
                    viewModel.updateLogin(
                        book.value.copy(
                            datePublish = book.value.datePublish
                                .withYear(it.year)
                                .withMonth(it.monthValue)
                                .withDayOfMonth(it.dayOfMonth)
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomDropDown(
                label = "",
                items = category.value,
                display = { it.categoryTitle },
                onSelectedItem = {
                    viewModel.updateLogin(
                        book.value.copy(
                            category = it.id
                        )
                    )

                },
                selectedItem = selectedCategory.value
            )

            Spacer(modifier = Modifier.height(8.dp))


            StateButton(
                label = "Сохранить",
                onClick = {
                    viewModel.execute()
                },
                resultState = resultState.value,
                onSuccess = {
                    navController.navigate("listBookScreen")
                }
            )

        }

    }

}