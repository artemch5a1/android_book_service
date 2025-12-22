package com.example.myapplication.presentation.component

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.model.SelectedItem
import java.time.LocalDate

@Composable
fun CustomDatePicker(
    label: String,
    currentDate: LocalDate,
    onDateSelected:(LocalDate) -> Unit
){

    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context,
        {_,year, month, dayOfMonth ->

            val localDate = LocalDate.of(year, month, dayOfMonth)

            onDateSelected(localDate)

        },
        currentDate.year,
        currentDate.monthValue - 1,
        currentDate.dayOfMonth
    )

    OutlinedTextField(
        value = currentDate.toString(),
        onValueChange = {},
        readOnly = true,
        enabled = false,
        label = {
            Text(text = label)
        },
        modifier = Modifier.clickable{
            datePickerDialog.show()
        }
    )


}


@Composable
fun<T> CustomDropDown(
    label: String,
    items: List<T>,
    display:(T) -> String,
    onSelectedItem:(T) -> Unit,
    selectedItem: T? = null
){

    var currentItem by remember { mutableStateOf(selectedItem) }

    var expanded by remember { mutableStateOf(false) }

    Box(){
        OutlinedTextField(
            value = currentItem?.let { display(it) } ?: "",
            onValueChange = {

            },
            readOnly = true,
            enabled = false,
            label = {
                Text(text = label)
            },
            modifier = Modifier.clickable{
                expanded = !expanded
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        )
        {
            LazyColumn(
                modifier = Modifier
                    .height(150.dp)
                    .width(400.dp)
            ) {

                items(items.size){
                        index ->

                    DropdownMenuItem(
                        text = {
                            Text(text = display(items[index]))
                        },
                        onClick = {
                            onSelectedItem(items[index])
                            currentItem = items[index]
                            expanded = false
                        }
                    )

                }

            }
        }
    }

}