package com.example.dz19radiobutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dz19radiobutton.ui.theme.DZ19RadioButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DZ19RadioButtonTheme {

                val carImageResourceMap = mapOf(
                    "white" to R.drawable.wite,
                    "blue" to R.drawable.blu,
                    "red" to R.drawable.red,
                    "blek" to R.drawable.blek
                )

                val carColorIconMap = mapOf(
                    "white" to Pair(Color(0xffd43539), Color(0xffe2e2e2)),
                    "blue" to Pair(Color(0xff020408), Color(0xff3e5eb0)),
                    "red" to Pair(Color(0xff020408), Color(0xffd0363b)),
                    "blek" to Pair(Color(0xFF262322), Color(0xFFA6A3A3))
                )

                val carEquipmentMap = mapOf(
                    "Classic" to "3 00 000 ₽",
                    "Luxe" to "3 500 000 ₽",
                    "Prestige" to "3 600 000 ₽",
                    "Style" to "4 000 000 ₽",
                    "Premium" to "4 100 000 ₽",
                    "Premium+" to "5 600 000 ₽",
                )

                var selectedColor by rememberSaveable {
                    mutableStateOf("white")
                }

                var selectedEquipment by rememberSaveable {
                    mutableStateOf("Style")
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp, 8.dp),
                        text = buildAnnotatedString {
                            append("Китайское чудо ")
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("выбор комплектации")
                            }
                        }
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {



                        Image(
                            painter = painterResource(
                                carImageResourceMap[selectedColor] ?: R.drawable.wite
                            ),
                            contentDescription = "Изображение автомобиля",
                            modifier = Modifier.height(200.dp)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row {
                        for ((key, value) in carColorIconMap) {
                            Column(modifier = Modifier
                                .padding(4.dp)
                                .size(24.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    if (key == selectedColor) Color.DarkGray else Color.Transparent,
                                    CircleShape
                                )
                                .selectable(
                                    selected = key == selectedColor,
                                    onClick = { selectedColor = key }
                                )
                            ) {

                                Box(
                                    Modifier
                                        .size(24.dp, 12.dp)
                                        .background(value.first)
                                )
                                Box(
                                    Modifier
                                        .size(24.dp, 12.dp)
                                        .background(value.second)
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .selectableGroup()) {

                        for ((key, _) in carEquipmentMap) {
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .selectable(
                                        selected = selectedEquipment == key,
                                        onClick = { selectedEquipment = key }
                                    )
                                    .fillMaxWidth()
                            ) {
                                RadioButton(
                                    selected = selectedEquipment == key,
                                    onClick = { selectedEquipment = key }
                                )
                                Text(text = key)
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp, 8.dp),
                        text = buildAnnotatedString {
                            append("Стоимость от ")
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(carEquipmentMap[selectedEquipment])
                            }
                        }
                    )
                }
            }
        }
    }
}