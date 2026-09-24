package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TatianaRepository
import com.example.model.TestimonialItem
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.GoldWax
import com.example.ui.theme.GoldWaxDark
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted

/**
 * 2. Depoimentos de Clientes em Carrossel
 * Diferencial: Avaliações reais de noivos com detalhes da experiência,
 * 5 estrelas e depoimentos emocionais.
 */
@Composable
fun TestimonialsSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmBeige.copy(alpha = 0.45f))
            .padding(vertical = 40.dp)
            .testTag("testimonials_section")
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
                    text = "AVALIAÇÕES REAIS",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    color = OliveDeep,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                )
            }

            Text(
                text = "O que dizem os nossos Noivos",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = OliveDeep,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Mais de 180 casais confiaram na TATIANA para criar o primeiro contacto com os seus convidados.",
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.5.sp,
                color = WarmMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Carrossel horizontal de depoimentos
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("testimonials_carousel_row"),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TatianaRepository.testimonials.forEach { testimonial ->
                TestimonialCard(testimonial = testimonial)
            }
        }
    }
}

@Composable
private fun TestimonialCard(testimonial: TestimonialItem) {
    Card(
        modifier = Modifier
            .width(285.dp)
            .shadow(4.dp, RoundedCornerShape(20.dp), spotColor = Color(0x1F000000))
            .testTag("testimonial_card_${testimonial.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CreamPaper),
        border = BorderStroke(1.dp, BorderWarm)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Estrelas + Ícone de citação
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(testimonial.rating) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = GoldWaxDark,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.FormatQuote,
                    contentDescription = null,
                    tint = GoldWax.copy(alpha = 0.5f),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Depoimento em itálico
            Text(
                text = "\"${testimonial.quote}\"",
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 13.5.sp,
                lineHeight = 20.sp,
                color = CharcoalDark,
                minLines = 4
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Avatar com iniciais + Informação dos Noivos
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(OlivePrimary.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = testimonial.avatarInitials,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = OliveDeep
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = testimonial.coupleNames,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = OliveDeep
                    )
                    Text(
                        text = "${testimonial.location} • ${testimonial.weddingDate}",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        color = WarmMuted
                    )
                }
            }
        }
    }
}
