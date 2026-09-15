package com.vitreon.quadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vitreon.quadrant.ui.theme.QuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuadrantTheme {
                QuadrantScreen()
            }
        }
    }
}

data class QuadrantData(
    val color: Color,
    val headerText: String,
    val descText: String
)

@Composable
fun QuadrantScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            QuadrantCard(
                modifier = Modifier.weight(1f),
                quadrantData = QuadrantData(
                    color = colorResource(id = R.color.q1),
                    headerText = stringResource(id = R.string.q1_header),
                    descText = stringResource(id = R.string.q1_desc)
                )
            )

            QuadrantCard(
                modifier = Modifier.weight(1f),
                quadrantData = QuadrantData(
                    color = colorResource(id = R.color.q2),
                    headerText = stringResource(id = R.string.q2_header),
                    descText = stringResource(id = R.string.q2_desc)
                )
            )
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            QuadrantCard(
                modifier = Modifier.weight(1f),
                quadrantData = QuadrantData(
                    color = colorResource(id = R.color.q3),
                    headerText = stringResource(id = R.string.q3_header),
                    descText = stringResource(id = R.string.q3_desc)
                )
            )

            QuadrantCard(
                modifier = Modifier.weight(1f),
                quadrantData = QuadrantData(
                    color = colorResource(id = R.color.q4),
                    headerText = stringResource(id = R.string.q4_header),
                    descText = stringResource(id = R.string.q4_desc)
                )
            )
        }
    }
}

@Composable
fun QuadrantCard(
    modifier: Modifier = Modifier,
    quadrantData: QuadrantData
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(quadrantData.color)
            .padding(all = 16.dp)
    ) {
        Text(
            text = quadrantData.headerText,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Text(
            text = quadrantData.descText,
            textAlign = TextAlign.Justify
        )
    }
}