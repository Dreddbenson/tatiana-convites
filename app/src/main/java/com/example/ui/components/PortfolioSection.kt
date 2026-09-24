package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.PortfolioCase
import com.example.model.TatianaRepository
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.GoldWax
import com.example.ui.theme.GoldWaxDark
import com.example.ui.theme.GoldWaxLight
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted

/**
 * 1. Galeria / Portefólio Real com Casos de Sucesso
 * Diferencial exclusivo: prova social com casamentos reais, métricas de confirmação
 * e mockups dos convites entregues aos clientes.
 */
@Composable
fun PortfolioSection(
    onOpenCaseDemo: (PortfolioCase) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LinenBackground)
            .padding(vertical = 40.dp)
            .testTag("portfolio_section")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = GoldWax.copy(alpha = 0.15f),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "PORTEFÓLIO REAL",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    color = GoldWaxDark,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                )
            }

            Text(
                text = "Casos de Sucesso",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = OliveDeep,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Histórias reais de noivos que transformaram a entrega dos seus convites numa experiência inesquecível.",
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.5.sp,
                lineHeight = 20.sp,
                color = WarmMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Carrossel horizontal de casos de sucesso com mockups reais
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("portfolio_cases_row"),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TatianaRepository.portfolioCases.forEach { caseItem ->
                PortfolioCard(
                    caseItem = caseItem,
                    onOpenCaseDemo = { onOpenCaseDemo(caseItem) }
                )
            }
        }
    }
}

@Composable
private fun PortfolioCard(
    caseItem: PortfolioCase,
    onOpenCaseDemo: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(290.dp)
            .shadow(4.dp, RoundedCornerShape(20.dp), spotColor = Color(0x1F000000))
            .testTag("portfolio_card_${caseItem.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CreamPaper),
        border = BorderStroke(1.dp, BorderWarm)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Imagem de capa com overlay do estilo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            ) {
                AsyncImage(
                    model = caseItem.previewImage,
                    contentDescription = "Casamento de ${caseItem.coupleNames}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradiente escuro para legibilidade
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xBF181F1A))
                            )
                        )
                )

                // Badge com a Métrica de Sucesso (ex: 96% de confirmações em 5 dias)
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = OliveDeep.copy(alpha = 0.9f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = GoldWax,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = caseItem.metric,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp,
                            color = Color.White
                        )
                    }
                }

                // Nome do casal no rodapé da foto
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                ) {
                    Text(
                        text = caseItem.coupleNames,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = GoldWaxLight,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = caseItem.location,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            color = Color(0xFFE8ECE5)
                        )
                    }
                }
            }

            // Conteúdo do Cartão
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Estilo do convite
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = WarmBeige.copy(alpha = 0.5f),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Estilo: ${caseItem.style}",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        color = OliveDeep,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                // Depoimento do casal
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Default.FormatQuote,
                        contentDescription = null,
                        tint = GoldWax,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "\"${caseItem.quote}\"",
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = CharcoalDark
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Botão "Experimentar este estilo"
                OutlinedButton(
                    onClick = onOpenCaseDemo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, GoldWax)
                ) {
                    Icon(
                        imageVector = Icons.Default.TouchApp,
                        contentDescription = null,
                        tint = GoldWaxDark,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Testar Convite 3D",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = GoldWaxDark
                    )
                }
            }
        }
    }
}
