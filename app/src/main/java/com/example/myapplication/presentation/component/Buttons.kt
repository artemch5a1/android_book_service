package com.example.myapplication.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.model.ResultState

@Composable
fun StateButton(
    label: String,
    onClick:() -> Unit,
    resultState: ResultState,
    onSuccess:(() -> Unit)? = null
){

    when(resultState){
        is ResultState.Error -> {

            Button(onClick = onClick)
            {
                Text(text = label)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = resultState.message, color = Color.Red)

        }
        ResultState.Init -> {

            Button(onClick = onClick)
            {
                Text(text = label)
            }

        }
        ResultState.Loading -> {

        }

        is ResultState.Success -> {

            if(onSuccess == null){
                Button(onClick = onClick)
                {
                    Text(text = label)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = resultState.message, color = Color.Green)
            }
            else{

                onSuccess()

            }

        }
    }

}