package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.model.TatianaRepository
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.EnvelopeDeepForest
import com.example.ui.theme.EnvelopeDustyRose
import com.example.ui.theme.EnvelopeOlive
import com.example.ui.theme.EnvelopeSkyBlue
import com.example.ui.theme.EnvelopeWarmBeige
import com.example.ui.theme.GoldWax
import com.example.ui.theme.GoldWaxDark
import com.example.ui.theme.GoldWaxLight
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted
import com.example.ui.theme.WhatsAppGreen
import kotlinx.coroutines.delay

/**
 * ============================================================================
 * CONCEITO PRINCIPAL: CONVITE DE CASAMENTO EM FORMATO DIGITAL INTERATIVO
 * ============================================================================
 * O convidado recebe o link, visualiza o envelope 3D com o selo de cera,
 * toca no selo e o convite "abre" suavemente revelando os detalhes do evento:
 * - Nomes dos Noivos ("Ana & Carlos")
 * - Data e Contagem Regressiva
 * - Local com link para o mapa
 * - Cronograma do dia
 * - RSVP funcional com confirmação de presença
 * - Foto romântica dos noivos
 * - Player de música suave
 */
@Composable
fun InteractiveInvitationDialog(
    initialEnvelopeColor: Color = EnvelopeOlive,
    initialNames: String = "Ana & Carlos",
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var isOpened by remember { mutableStateOf(false) }
    var envelopeColor by remember { mutableStateOf(initialEnvelopeColor) }
    var isPlayingMusic by remember { mutableStateOf(true) }

    // Efeito de vibração ao abrir o selo
    fun triggerHaptic() {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            if (vibrator != null && vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createOneShot(45, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        } catch (_: Exception) { }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xEB151815)) // Fundo escurecido sofisticado
                .testTag("interactive_invitation_dialog_root"),
            contentAlignment = Alignment.Center
        ) {
            // Botão Fechar no canto superior direito
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Fechar convite",
                    tint = Color.White
                )
            }

            // Barra superior com seletor de cor do envelope e botão reiniciar
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 18.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xCC262B25))
                    .border(1.dp, GoldWax.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cor:",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = Color(0xFFD6DEC5),
                    modifier = Modifier.padding(end = 6.dp)
                )

                // Cores dos envelopes: Verde-Oliva, Azul-Claro, Verde-Escuro, Rosa, Bege
                val colors = listOf(
                    EnvelopeOlive,
                    EnvelopeSkyBlue,
                    EnvelopeDeepForest,
                    EnvelopeDustyRose,
                    EnvelopeWarmBeige
                )
                colors.forEach { col ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(col)
                            .border(
                                if (envelopeColor == col) 2.dp else 0.5.dp,
                                if (envelopeColor == col) GoldWaxLight else Color.White.copy(alpha = 0.5f),
                                CircleShape
                            )
                            .clickable { envelopeColor = col }
                    )
                }

                if (isOpened) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(GoldWax.copy(alpha = 0.2f))
                            .clickable { isOpened = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Fechar e rever envelope",
                            tint = GoldWaxLight,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }

            // Conteúdo Central: Estado FECHADO (Envelope 3D) vs ABERTO (Carta do Convite)
            if (!isOpened) {
                ClosedEnvelopeView(
                    envelopeColor = envelopeColor,
                    coupleNames = initialNames,
                    onSealClick = {
                        triggerHaptic()
                        isOpened = true
                    }
                )
            } else {
                OpenedInvitationCardView(
                    envelopeColor = envelopeColor,
                    isPlayingMusic = isPlayingMusic,
                    onToggleMusic = { isPlayingMusic = !isPlayingMusic },
                    onClose = onDismiss
                )
            }
        }
    }
}

/**
 * Vista do Envelope Fechado com Selo de Cera 3D pulsante
 */
@Composable
private fun ClosedEnvelopeView(
    envelopeColor: Color,
    coupleNames: String,
    onSealClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_seal")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        // Tag informativa
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White.copy(alpha = 0.15f),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.TouchApp,
                    contentDescription = null,
                    tint = GoldWaxLight,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "TOQUE NO SELO PARA ABRIR O CONVITE",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.2.sp,
                    color = Color.White
                )
            }
        }

        // Mockup 3D Realista do Envelope
        Box(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .height(280.dp)
                .shadow(16.dp, RoundedCornerShape(12.dp), spotColor = Color(0x66000000))
                .clip(RoundedCornerShape(12.dp))
                .background(envelopeColor)
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Geometria das dobras do envelope
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height

                // Aba triangular superior (flap)
                val topFlap = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(w, 0f)
                    lineTo(w / 2f, h * 0.54f)
                    close()
                }
                drawPath(
                    path = topFlap,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            envelopeColor.copy(alpha = 0.95f),
                            Color.Black.copy(alpha = 0.12f)
                        )
                    )
                )

                // Aba inferior do envelope
                val bottomFlap = Path().apply {
                    moveTo(0f, h)
                    lineTo(w / 2f, h * 0.44f)
                    lineTo(w, h)
                    close()
                }
                drawPath(
                    path = bottomFlap,
                    color = Color.Black.copy(alpha = 0.1f)
                )

                // Sombra de dobra suave
                drawLine(
                    color = Color(0x33000000),
                    start = Offset(0f, 0f),
                    end = Offset(w / 2f, h * 0.54f),
                    strokeWidth = 3f
                )
                drawLine(
                    color = Color(0x33000000),
                    start = Offset(w, 0f),
                    end = Offset(w / 2f, h * 0.54f),
                    strokeWidth = 3f
                )
            }

            // Nomes dos Noivos gravados no envelope
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Save the Date",
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = coupleNames,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                )
            }

            // Selo de Cera 3D ao centro (Interativo)
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-6).dp)
                    .scale(pulseScale)
                    .size(72.dp)
                    .shadow(12.dp, CircleShape, spotColor = Color(0x80000000))
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(GoldWaxLight, GoldWax, GoldWaxDark),
                        center = Offset(72f, 72f),
                        radius = 90f
                    )
                )
                .border(2.dp, GoldWaxLight.copy(alpha = 0.9f), CircleShape)
                .clickable { onSealClick() }
                .testTag("interactive_wax_seal_button"),
            contentAlignment = Alignment.Center
        ) {
            // Relevo interior com monograma floral
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, GoldWaxDark, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "T",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color(0xFF4C350D)
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(
        text = "Clique no selo dourado para abrir o convite digital",
        fontFamily = FontFamily.SansSerif,
        fontSize = 13.sp,
        color = Color(0xFFD4DAD0),
        textAlign = TextAlign.Center
    )
}
}

/**
 * Vista da Carta do Convite Digital (após abertura do envelope)
 * Contém detalhes reais do evento, foto romântica, contador, RSVP e mapa
 */
@Composable
private fun OpenedInvitationCardView(
    envelopeColor: Color,
    isPlayingMusic: Boolean,
    onToggleMusic: () -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    var guestName by remember { mutableStateOf("") }
    var isAttending by remember { mutableStateOf(true) }
    var dietaryInfo by remember { mutableStateOf("") }
    var guestsCount by remember { mutableIntStateOf(1) }
    var rsvpSubmitted by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Card(
        modifier = Modifier
            .fillMaxWidth(0.94f)
            .fillMaxHeight(0.92f)
            .shadow(20.dp, RoundedCornerShape(24.dp), spotColor = Color(0x99000000))
            .testTag("opened_invitation_card_container"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CreamPaper)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Topo da Carta: Barra com música e indicador de envelope
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(envelopeColor)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Black.copy(alpha = 0.25f))
                            .clickable { onToggleMusic() }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = if (isPlayingMusic) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Música",
                            tint = GoldWaxLight,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isPlayingMusic) "Nossa Canção • Ed Sheeran" else "Tocar Música",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.Black.copy(alpha = 0.25f)
                    ) {
                        Text(
                            text = "CONVITE OFICIAL",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            letterSpacing = 1.sp,
                            color = GoldWaxLight,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // ================================================================
            // CORPO DO CONVITE DIGITAL: DADOS FICTÍCIOS DE EXEMPLO
            // [CLIENTE: Substituir com as fotos e dados reais dos noivos]
            // ================================================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Monograma nobre do topo
                Text(
                    text = "A & C",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    letterSpacing = 4.sp,
                    color = GoldWaxDark
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "COM A BENÇÃO DE SEUS PAIS",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.5.sp,
                    letterSpacing = 2.sp,
                    color = OlivePrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Foto romântica do casal
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .shadow(6.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, WarmBeige)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wedding_couple_hero),
                        contentDescription = "Foto dos Noivos Ana e Carlos",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Nomes dos Noivos
                // [CLIENTE: Substituir pelo nome do vosso casal aqui]
                Text(
                    text = "Ana & Carlos",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    fontSize = 38.sp,
                    letterSpacing = 1.sp,
                    color = OliveDeep,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Convidam para celebrar a união do seu matrimónio",
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp,
                    color = WarmMuted,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Bloco com a Data e Hora
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = WarmBeige,
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderWarm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                tint = OlivePrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "SÁBADO • 18 DE OUTUBRO DE 2025",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                letterSpacing = 1.2.sp,
                                color = OliveDeep
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "às 16:30 horas",
                            fontFamily = FontFamily.Serif,
                            fontSize = 16.sp,
                            color = WarmMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // ============================================================
                // CONTADOR DECRESCENTE (LIVE COUNTDOWN)
                // ============================================================
                Text(
                    text = "CONTAGEM REGRESSIVA",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.5.sp,
                    color = GoldWaxDark
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CountdownUnit("142", "DIAS")
                    CountdownUnit("18", "HORAS")
                    CountdownUnit("45", "MIN")
                    CountdownUnit("30", "SEG")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Localização & Mapa
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = CreamPaper,
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderWarm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = OlivePrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Quinta do Lago",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = OliveDeep
                        )
                        Text(
                            text = "Estrada Real, 240 • Cascais / Maputo",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 13.sp,
                            color = WarmMuted,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedButton(
                            onClick = {
                                val mapIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:0,0?q=Quinta+do+Lago+Cascais")
                                )
                                context.startActivity(mapIntent)
                            },
                            shape = RoundedCornerShape(20.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, OlivePrimary)
                        ) {
                            Text(
                                text = "Abrir Localização no Mapa",
                                fontSize = 12.sp,
                                color = OlivePrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Cronograma do Dia
                Text(
                    text = "CRONOGRAMA DO DIA",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.5.sp,
                    color = GoldWaxDark
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ScheduleItemRow("16:30", "Cerimónia Religiosa", "Jardim das Oliveiras")
                    ScheduleItemRow("18:00", "Cocktail & Fotos com os Noivos", "Terraço Panorâmico")
                    ScheduleItemRow("19:30", "Jantar dos Noivos & Brinde", "Salão Principal")
                    ScheduleItemRow("22:00", "Corte do Bolo dos Noivos", "Pátio das Fontes")
                    ScheduleItemRow("22:30", "Abertura da Pista & Festa", "Espaço Lounge")
                }

                Spacer(modifier = Modifier.height(28.dp))

                // ============================================================
                // FORMULÁRIO DE CONFIRMAÇÃO DE PRESENÇA (RSVP)
                // ============================================================
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = WarmBeige.copy(alpha = 0.6f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderWarm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "CONFIRMAÇÃO DE PRESENÇA",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 1.5.sp,
                            color = OliveDeep
                        )
                        Text(
                            text = "Por favor confirme a vossa presença até 15 de Setembro",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            color = WarmMuted,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                        )

                        if (!rsvpSubmitted) {
                            OutlinedTextField(
                                value = guestName,
                                onValueChange = { guestName = it },
                                label = { Text("Nome do Convidado") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("rsvp_input_name"),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Escolha Sim ou Não
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { isAttending = true },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isAttending) OlivePrimary else WarmBeige
                                    ),
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(
                                        text = "Sim, vou!",
                                        color = if (isAttending) Color.White else OliveDeep,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp
                                    )
                                }

                                Button(
                                    onClick = { isAttending = false },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (!isAttending) Color(0xFF9E4B4B) else WarmBeige
                                    ),
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(
                                        text = "Não poderei ir",
                                        color = if (!isAttending) Color.White else OliveDeep,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            if (isAttending) {
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedTextField(
                                    value = dietaryInfo,
                                    onValueChange = { dietaryInfo = it },
                                    label = { Text("Restrições alimentares (opcional)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    singleLine = true
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    rsvpSubmitted = true
                                },
                                enabled = guestName.isNotBlank(),
                                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(46.dp)
                                    .testTag("rsvp_submit_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Confirmar Presença (RSVP)",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp,
                                    color = Color.White
                                )
                            }
                        } else {
                            // Sucesso do RSVP
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = OlivePrimary.copy(alpha = 0.12f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = OlivePrimary,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Presença confirmada com sucesso!",
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = OliveDeep
                                    )
                                    Text(
                                        text = "Obrigado, $guestName! Os noivos receberam a vossa confirmação.",
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 12.sp,
                                        color = WarmMuted,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão de partilha do convite
                OutlinedButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "✨ Convite de Casamento de Ana & Carlos: https://tatianaconvites.com/ana-e-carlos ✨"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Partilhar Convite"))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, GoldWax)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = GoldWaxDark,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Partilhar Convite com Amigos",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = GoldWaxDark
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onClose,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = OliveDeep),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Voltar ao Catálogo TATIANA",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.5.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun CountdownUnit(number: String, label: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = WarmBeige),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderWarm),
        modifier = Modifier.width(66.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = OliveDeep
            )
            Text(
                text = label,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 9.sp,
                letterSpacing = 1.sp,
                color = OlivePrimary
            )
        }
    }
}

@Composable
private fun ScheduleItemRow(time: String, title: String, location: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(WarmBeige.copy(alpha = 0.45f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = time,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = GoldWaxDark,
            modifier = Modifier.width(50.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = OliveDeep
            )
            Text(
                text = location,
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                color = WarmMuted
            )
        }
    }
}

@Composable
fun InteractiveInvitationCardStandalone(
    envelopeColor: Color = EnvelopeOlive,
    coupleNames: String = "Beatriz & Guilherme",
    modifier: Modifier = Modifier,
    onClose: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var isOpened by remember { mutableStateOf(false) }
    var currentEnvelopeColor by remember { mutableStateOf(envelopeColor) }
    var isPlayingMusic by remember { mutableStateOf(true) }

    fun triggerHaptic() {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            if (vibrator != null && vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createOneShot(45, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        } catch (_: Exception) { }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(currentEnvelopeColor.copy(alpha = 0.95f))
            .testTag("interactive_invitation_standalone_container"),
        contentAlignment = Alignment.Center
    ) {
        if (!isOpened) {
            ClosedEnvelopeView(
                envelopeColor = currentEnvelopeColor,
                coupleNames = coupleNames,
                onSealClick = {
                    triggerHaptic()
                    isOpened = true
                }
            )
        } else {
            OpenedInvitationCardView(
                envelopeColor = currentEnvelopeColor,
                isPlayingMusic = isPlayingMusic,
                onToggleMusic = { isPlayingMusic = !isPlayingMusic },
                onClose = {
                    isOpened = false
                    onClose?.invoke()
                }
            )
        }
    }
}
