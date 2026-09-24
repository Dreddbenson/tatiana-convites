package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.HowItWorksStep
import com.example.model.TatianaRepository
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.GoldWax
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted

@Composable
fun HowItWorksSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LinenBackground)
            .padding(horizontal = 20.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tag / Subtítulo superior
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = WarmBeige,
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderWarm),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = "PASSO A PASSO",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                letterSpacing = 1.2.sp,
                color = OlivePrimary,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
            )
        }

        // Título da secção
        Text(
            text = "Como Funciona",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = OliveDeep,
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag("how_it_works_title")
        )

        Text(
            text = "Três passos simples para transformar o convite do vosso casamento numa experiência inesquecível.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp,
            color = WarmMuted,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 28.dp)
                .fillMaxWidth(0.92f)
        )

        // Os 3 Cartões numerados (01, 02, 03)
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TatianaRepository.howItWorksSteps.forEachIndexed { index, step ->
                val icon = when (index) {
                    0 -> Icons.Default.ChatBubbleOutline
                    1 -> Icons.Default.CreditCard
                    else -> Icons.Default.Send
                }
                HowItWorksCard(
                    step = step,
                    icon = icon,
                    modifier = Modifier.testTag("how_it_works_card_${step.number}")
                )
            }
        }
    }
}

@Composable
private fun HowItWorksCard(
    step: HowItWorksStep,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(18.dp),
                spotColor = Color(0x14000000)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CreamPaper),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderWarm.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Número estilizado (01, 02, 03) com selo e ícone
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(52.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(WarmBeige)
                        .border(1.dp, GoldWax.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = step.number,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = OliveDeep
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = GoldWax,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textos informativos
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = step.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = OliveDeep
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = OlivePrimary.copy(alpha = 0.08f),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = step.subtitle,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.5.sp,
                        color = OlivePrimary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = step.description,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 13.5.sp,
                    lineHeight = 20.sp,
                    color = WarmMuted
                )
            }
        }
    }
}
