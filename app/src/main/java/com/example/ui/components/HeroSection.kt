package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.model.HeroEnvelopeItem
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

@Composable
fun HeroSection(
    onExploreModelsClick: () -> Unit,
    onOpenInteractiveInvite: (HeroEnvelopeItem) -> Unit,
    modifier: Modifier = Modifier
) {
    // Animação de entrada (Fade + Slide Up) ao carregar a página
    var isEntered by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isEntered = true
    }

    val entranceAlpha by animateFloatAsState(
        targetValue = if (isEntered) 1f else 0f,
        animationSpec = tween(durationMillis = 850, easing = FastOutSlowInEasing),
        label = "hero_entrance_alpha"
    )

    val entranceSlideY by animateDpAsState(
        targetValue = if (isEntered) 0.dp else 40.dp,
        animationSpec = tween(durationMillis = 850, easing = FastOutSlowInEasing),
        label = "hero_entrance_slide"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "seal_pulse"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CreamPaper, LinenBackground, WarmBeige.copy(alpha = 0.5f))
                )
            )
            .padding(top = 28.dp, bottom = 36.dp)
            .alpha(entranceAlpha)
            .offset(y = entranceSlideY),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tag / Badge de apresentação
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = OlivePrimary.copy(alpha = 0.08f),
            border = androidx.compose.foundation.BorderStroke(1.dp, OlivePrimary.copy(alpha = 0.2f)),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(GoldWax)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "A NOVA ERA DOS CONVITES DE CASAMENTO",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.5.sp,
                    letterSpacing = 1.2.sp,
                    color = OlivePrimary
                )
            }
        }

        // Título "Save the Date"
        Text(
            text = "Save the Date",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 42.sp,
            letterSpacing = 1.sp,
            color = OliveDeep,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .testTag("hero_title_save_the_date")
        )

        // Subtítulo curto
        Text(
            text = "Convites digitais interativos em formato de envelope 3D.\nO convidado toca no selo de cera e descobre a magia do vosso dia.",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 14.5.sp,
            lineHeight = 22.sp,
            color = WarmMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // ====================================================================
        // MOCKUP DE TELEMÓVEIS EM LEQUE 3D (PERSPECTIVA FLUIDA)
        // Telemóvel central maior e em destaque (scale 1.15x, zIndex alto)
        // Telemóveis laterais com rotação simétrica em leque (-14°, -7°, 0°, +7°, +14°)
        // Badge "NOVO" em destaque no modelo de entrada
        // ====================================================================
        Text(
            text = "✨ Toca num telemóvel para abrir o convite 3D",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = GoldWaxDark,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val heroScrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .horizontalScroll(heroScrollState)
                    .padding(horizontal = 32.dp, vertical = 18.dp)
                    .testTag("hero_phones_mockups_row"),
                horizontalArrangement = Arrangement.spacedBy((-10).dp), // Sobreposição elegante de leque
                verticalAlignment = Alignment.CenterVertically
            ) {
                TatianaRepository.heroEnvelopes.forEachIndexed { index, item ->
                    // Configuração da rotação e escala em leque
                    val (rotZ, scaleVal, offsetY, zIdx, isCenter) = when (index) {
                        0 -> FanProps(rotZ = -13f, scale = 0.92f, offsetY = 12.dp, zIndex = 2f, isCenter = false)
                        1 -> FanProps(rotZ = -6.5f, scale = 1.02f, offsetY = 0.dp, zIndex = 3f, isCenter = false)
                        2 -> FanProps(rotZ = 0f, scale = 1.16f, offsetY = (-16).dp, zIndex = 5f, isCenter = true)
                        3 -> FanProps(rotZ = 6.5f, scale = 1.02f, offsetY = 0.dp, zIndex = 3f, isCenter = false)
                        else -> FanProps(rotZ = 13f, scale = 0.92f, offsetY = 12.dp, zIndex = 2f, isCenter = false)
                    }

                    PhoneMockupCard(
                        envelopeItem = item,
                        pulseScale = if (isCenter) pulseScale else 1f,
                        isFeatured = isCenter,
                        rotationZ = rotZ,
                        scaleVal = scaleVal,
                        offsetY = offsetY,
                        zIndexVal = zIdx,
                        showNovoBadge = (index == 0), // Badge "Novo" num dos modelos
                        onClick = { onOpenInteractiveInvite(item) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Botões de Ação (CTA "Ver modelos" + "Experimentar Convite")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onExploreModelsClick,
                colors = ButtonDefaults.buttonColors(containerColor = OlivePrimary),
                shape = RoundedCornerShape(24.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                modifier = Modifier
                    .height(48.dp)
                    .testTag("hero_cta_ver_modelos")
            ) {
                Text(
                    text = "Ver Modelos",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            OutlinedButton(
                onClick = { onOpenInteractiveInvite(TatianaRepository.heroEnvelopes.first()) },
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GoldWax),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldWaxDark),
                modifier = Modifier
                    .height(48.dp)
                    .testTag("hero_cta_test_invite")
            ) {
                Icon(
                    imageVector = Icons.Default.TouchApp,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = GoldWaxDark
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Tocar no Selo",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = GoldWaxDark
                )
            }
        }
    }
}

private data class FanProps(
    val rotZ: Float,
    val scale: Float,
    val offsetY: androidx.compose.ui.unit.Dp,
    val zIndex: Float,
    val isCenter: Boolean
)

// Card que simula um smartphone com ecrã exibindo o envelope e o selo 3D
@Composable
private fun PhoneMockupCard(
    envelopeItem: HeroEnvelopeItem,
    pulseScale: Float,
    isFeatured: Boolean,
    rotationZ: Float,
    scaleVal: Float,
    offsetY: androidx.compose.ui.unit.Dp,
    zIndexVal: Float,
    showNovoBadge: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .zIndex(zIndexVal)
            .offset(y = offsetY)
            .graphicsLayer {
                this.rotationZ = rotationZ
                this.scaleX = scaleVal
                this.scaleY = scaleVal
                this.cameraDistance = 12f * density
            }
            .width(138.dp)
            .clickable { onClick() }
    ) {
        // Carcaça do Telemóvel (Moldura realista com bordas arredondadas)
        Box(
            modifier = Modifier
                .width(138.dp)
                .height(238.dp)
                .shadow(
                    elevation = if (isFeatured) 16.dp else 8.dp,
                    shape = RoundedCornerShape(28.dp),
                    spotColor = if (isFeatured) OliveDeep.copy(alpha = 0.45f) else Color(0x35000000)
                )
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFF181B19)) // Moldura preta fosca do telemóvel
                .border(2.dp, if (isFeatured) GoldWax else Color(0xFF383D39), RoundedCornerShape(28.dp))
                .padding(6.dp)
        ) {
            // Ecrã do telemóvel
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(22.dp))
                    .background(LinenBackground)
            ) {
                // Speaker / Dynamic Island do telemóvel no topo
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp))
                        .background(Color(0xFF181B19))
                        .align(Alignment.TopCenter)
                )

                // Conteúdo do Ecrã: Envelope 3D com a cor específica
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 6.dp, vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    EnvelopeGraphic(
                        envelopeColor = envelopeItem.envelopeColor,
                        waxColor = envelopeItem.waxSealColor,
                        sampleNames = envelopeItem.sampleNames,
                        pulseScale = pulseScale
                    )
                }

                // Badge pequena "NOVO" em destaque no topo direito do ecrã
                if (showNovoBadge) {
                    Surface(
                        shape = RoundedCornerShape(bottomStart = 8.dp, topEnd = 8.dp),
                        color = GoldWax,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 2.dp, end = 2.dp)
                    ) {
                        Text(
                            text = "NOVO",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2C200B),
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                }

                // Se for o do meio, pequena tag "Destaque"
                if (isFeatured) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = OliveDeep.copy(alpha = 0.85f),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = (-14).dp)
                    ) {
                        Text(
                            text = "★ DESTAQUE",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 7.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldWaxLight,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                // Barra de navegação inferior do telefone
                Box(
                    modifier = Modifier
                        .width(36.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0xFFB4B0A7))
                        .align(Alignment.BottomCenter)
                        .offset(y = (-4).dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Legenda com o nome da cor do envelope
        Text(
            text = envelopeItem.colorName,
            fontFamily = FontFamily.SansSerif,
            fontWeight = if (isFeatured) FontWeight.Bold else FontWeight.Medium,
            fontSize = 11.5.sp,
            color = if (isFeatured) OliveDeep else WarmMuted
        )
    }
}

// Representação visual de alta fidelidade do envelope com abas e selo central
@Composable
fun EnvelopeGraphic(
    envelopeColor: Color,
    waxColor: Color,
    sampleNames: String,
    pulseScale: Float = 1f,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .shadow(4.dp, RoundedCornerShape(6.dp), spotColor = Color(0x33000000))
            .clip(RoundedCornerShape(6.dp))
            .background(envelopeColor)
    ) {
        // Desenho das abas triangulares do envelope
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Aba triangular superior que dobra para baixo
            val topFlap = Path().apply {
                moveTo(0f, 0f)
                lineTo(w, 0f)
                lineTo(w / 2f, h * 0.52f)
                close()
            }
            drawPath(
                path = topFlap,
                color = envelopeColor.copy(alpha = 0.93f)
            )

            // Linha suave de sombra/dobra da aba superior
            val creaseLine = Path().apply {
                moveTo(0f, 0f)
                lineTo(w / 2f, h * 0.52f)
                lineTo(w, 0f)
            }
            drawPath(
                path = creaseLine,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0x33000000), Color(0x1A000000)),
                    startY = 0f,
                    endY = h * 0.52f
                )
            )

            // Abas laterais e inferior
            val bottomPocket = Path().apply {
                moveTo(0f, h)
                lineTo(w / 2f, h * 0.45f)
                lineTo(w, h)
                close()
            }
            drawPath(
                path = bottomPocket,
                color = Color.Black.copy(alpha = 0.08f)
            )
        }

        // Nomes dos Noivos gravados suavemente na aba inferior
        Text(
            text = sampleNames,
            fontFamily = FontFamily.Serif,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            fontSize = 10.sp,
            color = Color.White.copy(alpha = 0.85f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        )

        // Selo de Cera 3D ao centro da dobra
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-4).dp)
                .scale(pulseScale)
                .size(38.dp)
                .shadow(5.dp, CircleShape, spotColor = Color(0x66000000))
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(GoldWaxLight, waxColor, GoldWaxDark),
                        center = Offset(38f, 38f),
                        radius = 60f
                    )
                )
                .border(1.2.dp, GoldWaxLight.copy(alpha = 0.8f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Relevo interior com monograma floral
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .border(1.dp, GoldWaxDark.copy(alpha = 0.6f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "T",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF5C4113)
                )
            }
        }
    }
}
