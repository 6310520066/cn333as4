package com.example.img_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.img_api.ui.theme.ImgapiTheme
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


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
                    MainDisplay()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainDisplay() {
    var category by remember { mutableStateOf("movie") }
    var width by remember { mutableStateOf("1000") }
    var height by remember { mutableStateOf("1220") }
    var imageUrl by remember { mutableStateOf("https://api.lorem.space/image/movie?w=1000&h=1220") }
    var hint by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Random Image App",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(22.dp))
            CategoryDropdown(category = category) {
                category = it
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = width,
                onValueChange = { width = it },
                label = { Text("Width") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val w = width.toIntOrNull()
                    val h = height.toIntOrNull()
                    if (w == null || h == null || w !in 8..2000 || h !in 8..2000) {
                        hint = "Hint: Width and Height are between 8 and 2000."
                        imageUrl = ""
                    } else {
                        imageUrl = "https://api.lorem.space/image/$category?w=$w&h=$h"
                        hint = ""
                    }
                }
            ) {
                Text("Display Image")
            }
            if (hint.isNotEmpty()) {
                Text(
                    hint,
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.White)
            ) {
                if (imageUrl.isNotEmpty()) {
                    DisplayImage(url = imageUrl, modifier = Modifier.fillMaxSize())
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
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.loading_img),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun CategoryDropdown(
    category: String,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text("Category:  ", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Text(
                category,
        modifier = Modifier.clickable { expanded = true }, fontSize = 20.sp
        )
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expand category dropdown"
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {

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
