package com.coderz.f1.listedits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderz.f1.listedits.data.MyItem
import com.coderz.f1.listedits.models.MainActivityViewModel
import com.coderz.f1.listedits.ui.theme.ListEditsTheme

lateinit var viewModel: MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = MainActivityViewModel()

        setContent {
            LocalLifecycleOwner.current
            ListEditsTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val myItems by viewModel.myItemsLiveData.observeAsState(initial = emptyList())
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f), contentPadding = PaddingValues(8.dp)
                        ) {
                            itemsIndexed(items = myItems) { _, item ->
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(item.title, fontSize = 20.sp)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(item.description, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Button(onClick = { viewModel.removeItem(item) }) {
                                        Text(
                                            text = "Remove Item",
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }

                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            var txtInput by rememberSaveable { mutableStateOf("") }
                            TextField(
                                txtInput, onValueChange = {
                                    txtInput = it
                                }, modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = {
                                viewModel.addItem(MyItem(txtInput, "$txtInput description"))
                                txtInput = ""
                            }) {
                                Text("AddItem")

                            }
                        }
                    }


                }
            }
        }

    }
}


