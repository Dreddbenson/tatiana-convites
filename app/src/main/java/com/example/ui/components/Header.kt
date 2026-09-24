package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Currency
import com.example.model.Language
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.GoldWax
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WhatsAppGreen

@Composable
fun TatianaHeader(
    selectedCurrency: Currency,
    onCurrencyChange: (Currency) -> Unit,
    selectedLanguage: Language,
    onLanguageChange: (Language) -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currencyMenuExpanded by remember { mutableStateOf(false) }
    var languageMenuExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, spotColor = Color(0x1A000000)),
        color = CreamPaper.copy(alpha = 0.98f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. Logo / Monograma à esquerda + Nome da marca
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.testTag("brand_header_container")
            ) {
                // Selo / Monograma circular com a letra 'T'
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(OlivePrimary)
                        .border(1.5.dp, GoldWax, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "T",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = GoldWax
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "TATIANA",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        letterSpacing = 2.sp,
                        color = OliveDeep
                    )
                    Text(
                        text = "CONVITES DIGITAIS",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 8.5.sp,
                        letterSpacing = 1.5.sp,
                        color = GoldWax
                    )
                }
            }

            // 2. Seletor de Idioma e Moeda à direita + Ação Rápida WhatsApp
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Seletor de Idioma
                Box {
                    Row(
                        modifier = Modifier
                            .testTag("language_selector_button")
                            .clip(RoundedCornerShape(20.dp))
                            .background(WarmBeige)
                            .border(1.dp, BorderWarm, RoundedCornerShape(20.dp))
                            .clickable { languageMenuExpanded = true }
                            .padding(horizontal = 8.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = selectedLanguage.flag, fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = selectedLanguage.code,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OliveDeep
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Selecionar idioma",
                            modifier = Modifier.size(16.dp),
                            tint = OliveDeep
                        )
                    }

                    DropdownMenu(
                        expanded = languageMenuExpanded,
                        onDismissRequest = { languageMenuExpanded = false },
                        modifier = Modifier.background(CreamPaper)
                    ) {
                        Language.values().forEach { lang ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = lang.flag, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = lang.label,
                                            fontWeight = if (lang == selectedLanguage) FontWeight.Bold else FontWeight.Normal,
                                            color = OliveDeep,
                                            fontSize = 13.sp
                                        )
                                        if (lang == selectedLanguage) {
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = OlivePrimary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                },
                                onClick = {
                                    onLanguageChange(lang)
                                    languageMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                // Seletor de Moeda
                Box {
                    Row(
                        modifier = Modifier
                            .testTag("currency_selector_button")
                            .clip(RoundedCornerShape(20.dp))
                            .background(WarmBeige)
                            .border(1.dp, BorderWarm, RoundedCornerShape(20.dp))
                            .clickable { currencyMenuExpanded = true }
                            .padding(horizontal = 8.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedCurrency.symbol,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = OlivePrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = selectedCurrency.code,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OliveDeep
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Selecionar moeda",
                            modifier = Modifier.size(16.dp),
                            tint = OliveDeep
                        )
                    }

                    DropdownMenu(
                        expanded = currencyMenuExpanded,
                        onDismissRequest = { currencyMenuExpanded = false },
                        modifier = Modifier.background(CreamPaper)
                    ) {
                        Currency.values().forEach { curr ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = curr.flag, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = curr.label,
                                            fontWeight = if (curr == selectedCurrency) FontWeight.Bold else FontWeight.Normal,
                                            color = OliveDeep,
                                            fontSize = 13.sp
                                        )
                                        if (curr == selectedCurrency) {
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = OlivePrimary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                },
                                onClick = {
                                    onCurrencyChange(curr)
                                    currencyMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                // Botão de contacto rápido
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(WhatsAppGreen)
                        .clickable { onWhatsAppClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = "Falar pelo WhatsApp",
                        tint = Color.White,
                        modifier = Modifier.size(17.dp)
                    )
                }
            }
        }
    }
}
