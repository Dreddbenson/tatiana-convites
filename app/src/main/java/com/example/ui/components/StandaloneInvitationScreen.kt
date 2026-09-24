package com.example.ui.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EnvelopeDeepForest
import com.example.ui.theme.EnvelopeDustyRose
import com.example.ui.theme.EnvelopeOlive
import com.example.ui.theme.EnvelopeSkyBlue
import com.example.ui.theme.EnvelopeWarmBeige
import com.example.ui.theme.GoldWax

data class EnvelopeColorOption(
    val color: Color,
    val name: String
)

/**
 * Ecrã Standalone do Convite de Casamento Digital Interativo (100% Jetpack Compose Nativo)
 *
 * Implementa os 2 estados solicitados:
 * ESTADO 1 (Fechado): Fundo cheio com cor do envelope, selo de cera com iniciais dos noivos e pulso suave
 * ESTADO 2 (Aberto): Desdobrar do envelope, tipografia elegante, data/local/mapa, fotos, contagem regressiva,
 *                     RSVP interativo (Vou / Não poderei), dress code e adicionar ao calendário.
 *
 * Renderização 100% nativa em Compose sem WebView, eliminando erros de GPU/Mesa rendernode em containers headless.
 */
@Composable
fun StandaloneInvitationScreen(
    modifier: Modifier = Modifier,
    onShareInvite: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var selectedEnvelopeColor by remember { mutableStateOf(EnvelopeOlive) }
    var resetTrigger by remember { mutableIntStateOf(0) }

    val envelopePalette = remember {
        listOf(
            EnvelopeColorOption(EnvelopeOlive, "Verde Oliva"),
            EnvelopeColorOption(EnvelopeSkyBlue, "Azul Suave"),
            EnvelopeColorOption(EnvelopeDeepForest, "Verde Floresta"),
            EnvelopeColorOption(EnvelopeDustyRose, "Rosa Blush"),
            EnvelopeColorOption(EnvelopeWarmBeige, "Bege Linho")
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("standalone_invitation_screen")
    ) {
        // Convite Interativo Nativo Compose (chaveada por resetTrigger e cor para reiniciar o envelope)
        key(resetTrigger, selectedEnvelopeColor) {
            InteractiveInvitationCardStandalone(
                envelopeColor = selectedEnvelopeColor,
                coupleNames = "Beatriz & Guilherme",
                modifier = Modifier.fillMaxSize()
            )
        }

        // Barra flutuante superior para testar as cores dos envelopes
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .testTag("standalone_color_selector"),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xCC1A2318),
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Envelope:",
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
                envelopePalette.forEach { option ->
                    val isSelected = option.color == selectedEnvelopeColor
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(option.color)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) GoldWax else Color.White.copy(alpha = 0.4f),
                                shape = CircleShape
                            )
                            .clickable { selectedEnvelopeColor = option.color }
                            .testTag("color_pill_${option.name.replace(" ", "_").lowercase()}")
                    )
                }
            }
        }

        // Botões flutuantes inferiores: Partilhar e Recarregar (Voltar a fechar o envelope)
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão Partilhar
            SmallFloatingActionButton(
                onClick = {
                    if (onShareInvite != null) {
                        onShareInvite()
                    } else {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "💌 Convite de Casamento de Beatriz & Guilherme:\nToque no selo de cera para abrir os detalhes: https://tatiana-convites.com/invite/beatriz-guilherme"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Partilhar Convite"))
                    }
                },
                modifier = Modifier.testTag("fab_share_standalone_invitation"),
                containerColor = Color(0xFF2C3928),
                contentColor = GoldWax
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Partilhar convite"
                )
            }

            // Botão Fechar/Recarregar envelope para experimentar de novo
            FloatingActionButton(
                onClick = {
                    resetTrigger++
                },
                modifier = Modifier.testTag("fab_reload_standalone_invitation"),
                containerColor = GoldWax,
                contentColor = Color(0xFF2C200B)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Fechar envelope e experimentar novamente"
                )
            }
        }
    }
}
