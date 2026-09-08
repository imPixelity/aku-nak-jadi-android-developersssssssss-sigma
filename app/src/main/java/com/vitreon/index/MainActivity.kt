package com.vitreon.index

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { ScaffoldTopBar() }
                ) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        padding = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier, padding: PaddingValues) {
    Column(
        modifier = modifier
    ) {
        for (i in 1..10) {
            val baseMod = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
            val mod = when (i % 3) {
                0 -> baseMod.background(Color.Cyan)
                1 -> baseMod.background(Color.Yellow)
                else -> baseMod.background(Color.Gray)
            }
            Text("text $i", modifier = mod)
        }

        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProvideTextStyle(
                    value = LocalTextStyle.current.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                ) {
                    Text("Top padding: ${padding.calculateTopPadding()}")
                    Text("Bottom padding: ${padding.calculateBottomPadding()}")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProvideTextStyle(
                    value = LocalTextStyle.current.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                ) {
                    Text("Right padding: ${padding.calculateRightPadding(LocalLayoutDirection.current)}")
                    Text("Left padding: ${padding.calculateLeftPadding(LocalLayoutDirection.current)}")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Left")
                Spacer(Modifier.weight(1f))
                Text("Right")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Magenta),
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red),
                ) {
                    for (i in 0..4) {
                        val mod = when (i) {
                            0 -> Modifier.align(Alignment.Center)
                            1 -> Modifier.align(Alignment.BottomStart)
                            2 -> Modifier.align(Alignment.TopStart)
                            3 -> Modifier.align(Alignment.BottomEnd)
                            4 -> Modifier.align(Alignment.TopEnd)
                            else -> Modifier
                        }
                        Text("text $i", modifier = mod, color = Color.White)
                    }
                }

                Spacer(Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Blue)
                ) {}
            }

            Counter()
        }
    }
}

@Composable
fun Counter() {
    var count by remember { mutableIntStateOf(1) }
    val state = remember { mutableStateOf(0) }
    val anotherState = remember(fun(): MutableState<Int> {
        return mutableStateOf(0)
    })
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Jumlah: $count")
        Button(
            onClick = { count *= 5 }
        ) {
            Text("Klik")
        }

        Text("Jumlah: ${state.value}")
        Button(
            onClick = { state.value++ }
        ) {
            Text("Klik")
        }

        LaunchedEffect(Unit) {
            while (true) {
                delay(1.seconds)
                state.value++
            }
        }

        Text("Jumlah: ${anotherState.value}")
        Button(
            onClick = { anotherState.value-- }
        ) {
            Text("Klik")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "Counter", fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )
}


@Composable
fun ScaffoldFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.add),
            contentDescription = "Add+"
        )
    }
}