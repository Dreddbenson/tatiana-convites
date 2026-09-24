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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Currency
import com.example.model.TatianaRepository
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.GoldWax
import com.example.ui.theme.GoldWaxDark
import com.example.ui.theme.GoldWaxLight
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted
import com.example.ui.theme.WhatsAppGreen

@Composable
fun CustomTemplateSection(
    currency: Currency,
    onCreateCustomTemplateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(24.dp),
                    spotColor = OliveDeep.copy(alpha = 0.2f)
                )
                .testTag("custom_template_upsell_card"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CreamPaper),
            border = androidx.compose.foundation.BorderStroke(
                1.5.dp,
                Brush.linearGradient(listOf(GoldWaxLight, GoldWax, OlivePrimary))
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Selo Dourado de Destaque / Estrela
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                listOf(GoldWaxLight, GoldWax, GoldWaxDark)
                            )
                        )
                        .border(1.5.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = GoldWax.copy(alpha = 0.12f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GoldWax.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "SERVIÇO DE ALTA COSTURA DIGITAL",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.3.sp,
                        color = GoldWaxDark,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Título: Template 100% Personalizado
                Text(
                    text = "Template 100% Personalizado",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = OliveDeep,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Criamos uma obra de arte digital única, desenhada do zero com a história, ilustrações, monograma e paleta de cores exclusivas do vosso casamento.",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = WarmMuted,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Bloco de Preço Fixo + Prazo Mínimo
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(WarmBeige)
                        .border(1.dp, BorderWarm, RoundedCornerShape(16.dp))
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Preço Fixo
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "PREÇO FIXO",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OlivePrimary,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = currency.format(TatianaRepository.CUSTOM_TEMPLATE_PRICE_EUR),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = OliveDeep
                        )
                    }

                    // Divisor vertical
                    Box(
                        modifier = Modifier
                            .height(38.dp)
                            .width(1.dp)
                            .background(BorderWarm)
                    )

                    // Prazo Mínimo
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = GoldWaxDark,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "PRAZO MÍNIMO",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = OlivePrimary,
                                letterSpacing = 1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = TatianaRepository.CUSTOM_TEMPLATE_DELIVERY_DAYS,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = OliveDeep
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // O que está incluído
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        "Design de layout e arquitetura 100% sob medida",
                        "Ilustração digital personalizada do local ou brasão",
                        "Envelope 3D exclusivo com selo de cera customizado",
                        "Música dos noivos integrada com reprodutor elegante",
                        "RSVP avançado com gestão de acompanhantes e alergias",
                        "Revisões ilimitadas até à aprovação final completa"
                    ).forEach { item ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(OlivePrimary.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = OlivePrimary,
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = item,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 13.sp,
                                color = CharcoalDark
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ============================================================
                // BOTÃO GRANDE "CRIAR O MEU TEMPLATE" QUE ABRE WHATSAPP
                // ============================================================
                Button(
                    onClick = onCreateCustomTemplateClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("btn_create_custom_template"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Criar o meu Template",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Conversa direta com a Tatiana pelo WhatsApp • Sem compromisso",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.5.sp,
                    color = WarmMuted
                )
            }
        }
    }
}
