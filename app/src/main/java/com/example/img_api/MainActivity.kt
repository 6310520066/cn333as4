package com.example.img_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
    var width by remember { mutableStateOf("1000") }
    var height by remember { mutableStateOf("1220") }
    var imageUrl by remember { mutableStateOf("https://loremflickr.com/1000/1220") }
    var color by remember { mutableStateOf("All Color") }
    var first by remember { mutableStateOf("")}
    var second by remember { mutableStateOf("")}
    var operation by remember { mutableStateOf("or")}


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
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = width,
                onValueChange = { width = it },
                label = { Text("Width") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 200.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 200.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        ColorDropdown(color = color, modifier = Modifier
            .fillMaxWidth()) {
            color = it
        }
        Spacer(modifier = Modifier.height(0.dp))
        Text(text = "Warning: If you do not specify a keyword, selecting a color will cause the image not to be displayed.", fontSize = 13.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Please enter a keyword. If left blank, the app will randomly select a picture of a kitten for you. Optional keyword can be ignore.", fontWeight = FontWeight.Medium, fontSize = 17.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = first,
                onValueChange = { first = it },
                label = { Text("Keyword") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 200.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = second,
                onValueChange = { second = it },
                label = { Text("Optional keyword") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 200.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        OperationDropdown(operation = operation, modifier = Modifier
            .weight(1f)
            .widthIn(max = 200.dp)) {
            operation = it
        }
        Button(
            onClick = {
                if (color == "Grey") {color = "g"}
                if (color == "Red") {color = "red"}
                if (color == "Green") {color = "green"}
                if (color == "Blue") {color = "blue"}
                if (color == "Pixel") {color = "p"}
                val w = width.toIntOrNull()
                val h = height.toIntOrNull()
                val c = color
                val f = first
                val s = second
                val o = operation
                if ( c == "All Color" && f == "" && s == "" ) {
                    imageUrl = "https://loremflickr.com/$w/$h"
                } else if (c != "All Color" && f == "" && s == "") {
                    imageUrl = "https://loremflickr.com/$c/$w/$h"
                } else if ( c == "All Color" && f != "" && s == "") {
                    imageUrl = "https://loremflickr.com/$w/$h/$f"
                } else if ( c != "All Color" && f != "" && s == "") {
                    imageUrl = "https://loremflickr.com/$c/$w/$h/$f"
                }  else if ( c == "All Color" && f != "" && o == "or") {
                    imageUrl = "https://loremflickr.com/$w/$h/$f,$s"
                } else if ( c != "All Color" && f != "" && o == "or") {
                    imageUrl = "https://loremflickr.com/$c/$w/$h/$f,$s"
                } else if ( c == "All Color" && f != "" && o == "and") {
                    imageUrl = "https://loremflickr.com/$w/$h/$f,$s/all"
                } else if ( c != "All Color" && f != "" && o == "and") {
                    imageUrl = "https://loremflickr.com/$c/$w/$h/$f,$s/all"
                } else {
                    imageUrl = "https://loremflickr.com/$w/$h"
                }
            }
        ) {
            Text("Display Image")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            if (imageUrl.isNotEmpty()) {
                DisplayImage(url = imageUrl, modifier = Modifier.fillMaxSize())
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
            error = painterResource(R.drawable.ic_broken_image),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun ColorDropdown(
    color: String,
    modifier: Modifier = Modifier,
    onCategorySelected: (String) -> Unit,

) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text("Color:  ", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Text(
            color,
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
                onCategorySelected("All Color")
                expanded = false
            }) {
                Text("All color")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("Grey")
                expanded = false
            }) {
                Text("Grey")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("Red")
                expanded = false
            }) {
                Text("Red")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("Green")
                expanded = false
            }) {
                Text("Green")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("Blue")
                expanded = false
            }) {
                Text("Blue")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("Pixel")
                expanded = false
            }) {
                Text("Pixel")
            }
        }
    }
}

@Composable
fun OperationDropdown(
    operation: String,
    modifier: Modifier = Modifier,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text("Relation of 2 Keyword(optional):  ", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Text(
            operation,
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
                onCategorySelected("and")
                expanded = false
            }) {
                Text("and")
            }
            DropdownMenuItem(onClick = {
                onCategorySelected("or")
                expanded = false
            }) {
                Text("or")
            }
        }
    }
}