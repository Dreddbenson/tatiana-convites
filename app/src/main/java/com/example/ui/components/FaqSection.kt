package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.FaqItem
import com.example.model.TatianaRepository
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted

@Composable
fun FaqSection(
    modifier: Modifier = Modifier
) {
    // Mapa para controlar estado expansível de cada item da FAQ (accordion)
    val expandedStates = remember {
        mutableStateMapOf<String, Boolean>().apply {
            // Primeiro item aberto por defeito para convite visual
            put("faq_1", true)
        }
    }

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
                text = "DÚVIDAS FREQUENTES",
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
            text = "Perguntas Frequentes",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = OliveDeep,
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag("faq_section_title")
        )

        Text(
            text = "Respostas claras sobre como funcionam os nossos convites de casamento digitais interativos.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp,
            color = WarmMuted,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 28.dp)
                .fillMaxWidth(0.92f)
        )

        // Accordion com as perguntas
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TatianaRepository.faqList.forEach { item ->
                val isExpanded = expandedStates[item.id] == true
                FaqAccordionCard(
                    faqItem = item,
                    isExpanded = isExpanded,
                    onToggle = {
                        expandedStates[item.id] = !isExpanded
                    }
                )
            }
        }
    }
}

@Composable
private fun FaqAccordionCard(
    faqItem: FaqItem,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "faq_chevron_rotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (isExpanded) 3.dp else 1.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color(0x14000000)
            )
            .testTag("faq_item_${faqItem.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpanded) CreamPaper else CreamPaper.copy(alpha = 0.85f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isExpanded) OlivePrimary.copy(alpha = 0.4f) else BorderWarm
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(18.dp)
        ) {
            // Pergunta + Ícone de expansão (chevron com rotação animada)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = faqItem.question,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = OliveDeep,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Recolher" else "Expandir",
                    tint = if (isExpanded) OlivePrimary else WarmMuted,
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(rotationAngle)
                )
            }

            // Resposta expansível com animação suave
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(BorderWarm.copy(alpha = 0.5f))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = faqItem.answer,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = WarmMuted
                    )
                }
            }
        }
    }
}
