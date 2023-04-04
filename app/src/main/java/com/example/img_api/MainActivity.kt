package com.example.img_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.img_api.ui.theme.ImgapiTheme
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImgapiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var category by remember { mutableStateOf("movie") }
                    var width by remember { mutableStateOf(150) }
                    var height by remember { mutableStateOf(220) }
                    var imageUrl by remember { mutableStateOf("https://api.lorem.space/image/movie?w=150&h=220") }

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        CategoryDropdown(category = category) {
                            category = it
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Width")
                        Slider(
                            value = width.toFloat(),
                            onValueChange = { width = it.toInt() },
                            valueRange = 8f..2000f
                        )
                        Text(width.toString())
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Height")
                        Slider(
                            value = height.toFloat(),
                            onValueChange = { height = it.toInt() },
                            valueRange = 8f..2000f
                        )
                        Text(height.toString())
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                imageUrl = "https://api.lorem.space/image/$category?w=$width&h=$height"
                            }
                        ) {
                            Text("Display Image")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .background(Color.White)
                        ) {
                            DisplayImage(url = imageUrl, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DisplayImage(
    url: String,
    modifier: Modifier = Modifier
) {
    val imageUrl = "$url"
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}


@Composable
fun CategoryDropdown(
    category: String,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Text("Category")
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            category,
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            DropdownMenuItem(onClick = {
                onCategorySelected("movie")
                expanded = false
            }) {
                Text("Movie")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("game")
                expanded = false
            }) {
                Text("Game")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("album")
                expanded = false
            }) {
                Text("Album")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("book")
                expanded = false
            }) {
                Text("Book")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("face")
                expanded = false
            }) {
                Text("Face")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("fashion")
                expanded = false
            }) {
                Text("Fashion")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("shoes")
                expanded = false
            }) {
                Text("Shoes")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("watch")
                expanded = false
            }) {
                Text("Watch")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("furniture")
                expanded = false
            }) {
                Text("Furniture")
            }
        }
    }
}
