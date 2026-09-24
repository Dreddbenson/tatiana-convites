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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Currency
import com.example.model.PlanComparisonItem
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
import com.example.ui.theme.WhatsAppGreen

/**
 * 3. Comparador de Planos (Tabela Comparativa)
 * Diferencial exclusivo: transparência total com tabela detalhada comparando
 * Básico, Premium (Mais Vendido) e 100% Personalizado.
 */
@Composable
fun PlanComparisonSection(
    selectedCurrency: Currency,
    onSelectPlan: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LinenBackground)
            .padding(vertical = 40.dp)
            .testTag("plan_comparison_section")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = OlivePrimary.copy(alpha = 0.12f),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "TRANSPARÊNCIA TOTAL",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    color = OliveDeep,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                )
            }

            Text(
                text = "Comparador de Planos",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = OliveDeep,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Veja detalhadamente tudo o que cada versão inclui para escolher a solução perfeita para o vosso casamento.",
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.5.sp,
                color = WarmMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tabela de comparação com scroll horizontal
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(6.dp, RoundedCornerShape(20.dp), spotColor = Color(0x1F000000)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CreamPaper),
                border = BorderStroke(1.dp, BorderWarm)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState)
                ) {
                    // Header da Tabela (Planos e Preços)
                    Row(
                        modifier = Modifier
                            .background(OliveDeep)
                            .padding(vertical = 16.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Recursos & Benefícios",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color.White,
                            modifier = Modifier.width(160.dp)
                        )

                        // Coluna Básico
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text(
                                text = "Básico",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = selectedCurrency.format(48.0),
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = Color(0xFFD6DEC5)
                            )
                        }

                        // Coluna Premium (Destacada)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(135.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(GoldWax.copy(alpha = 0.25f))
                                .padding(vertical = 4.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = GoldWax,
                                modifier = Modifier.padding(bottom = 2.dp)
                            ) {
                                Text(
                                    text = "★ MAIS POPULAR",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Text(
                                text = "Premium",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = selectedCurrency.format(85.0),
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = GoldWaxLight
                            )
                        }

                        // Coluna Personalizado
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(135.dp)
                        ) {
                            Text(
                                text = "100% Exclusivo",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = selectedCurrency.format(145.0),
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = Color(0xFFD6DEC5)
                            )
                        }
                    }

                    // Linhas de Comparação
                    TatianaRepository.planComparisons.forEachIndexed { index, item ->
                        val isEven = index % 2 == 0
                        val rowBg = if (isEven) CreamPaper else LinenBackground

                        Row(
                            modifier = Modifier
                                .background(rowBg)
                                .padding(vertical = 12.dp, horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.feature,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = CharcoalDark,
                                modifier = Modifier.width(160.dp)
                            )

                            Text(
                                text = item.basic,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 11.5.sp,
                                color = WarmMuted,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(120.dp)
                            )

                            Text(
                                text = item.premium,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.5.sp,
                                color = OliveDeep,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(135.dp)
                            )

                            Text(
                                text = item.custom,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.5.sp,
                                color = GoldWaxDark,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(135.dp)
                            )
                        }
                    }

                    // Rodapé com botões de ação para cada plano
                    Row(
                        modifier = Modifier
                            .background(WarmBeige.copy(alpha = 0.5f))
                            .padding(vertical = 14.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(160.dp))

                        // Botão Básico
                        OutlinedButton(
                            onClick = { onSelectPlan("Básico") },
                            modifier = Modifier
                                .width(120.dp)
                                .height(36.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, OlivePrimary)
                        ) {
                            Text("Escolher", fontSize = 11.sp, color = OliveDeep)
                        }

                        // Botão Premium (Destaque)
                        Button(
                            onClick = { onSelectPlan("Premium") },
                            modifier = Modifier
                                .width(135.dp)
                                .height(36.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldWax)
                        ) {
                            Text("Quero Este", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        // Botão Personalizado
                        Button(
                            onClick = { onSelectPlan("100% Personalizado") },
                            modifier = Modifier
                                .width(135.dp)
                                .height(36.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = OliveDeep)
                        ) {
                            Text("Criar Meu", fontSize = 11.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
