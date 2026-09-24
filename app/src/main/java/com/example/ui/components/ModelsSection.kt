package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.model.Currency
import com.example.model.InvitationModel
import com.example.model.ModelCategory
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
import com.example.ui.theme.TextMuted
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted
import com.example.ui.theme.WhatsAppDark
import com.example.ui.theme.WhatsAppGreen

@Composable
fun ModelsSection(
    selectedCurrency: Currency,
    onSendMessageClick: (InvitationModel) -> Unit,
    onPreviewModelClick: (InvitationModel) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(ModelCategory.CUSTOMIZABLE) }
    var detailedModelForDialog by remember { mutableStateOf<InvitationModel?>(null) }

    val filteredModels = remember(selectedCategory) {
        TatianaRepository.models.filter { it.category == selectedCategory }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmBeige.copy(alpha = 0.35f))
            .padding(vertical = 40.dp)
    ) {
        // Cabeçalho da Secção
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
                    text = "CATÁLOGO EXCLUSIVO",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    color = OliveDeep,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                )
            }

            Text(
                text = "Modelos & Preços",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = OliveDeep,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Escolha um design existente ou crie uma estrutura 100% à vossa medida. Ambos incluem envelope 3D animado com selo de cera e confirmação de presença (RSVP).",
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.5.sp,
                lineHeight = 20.sp,
                color = WarmMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ================================================================
            // TABS DE CATEGORIAS:
            // a) Convites totalmente personalizáveis
            // b) Convites com design predefinido
            // ================================================================
            TabRow(
                selectedTabIndex = if (selectedCategory == ModelCategory.CUSTOMIZABLE) 0 else 1,
                containerColor = CreamPaper,
                contentColor = OliveDeep,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BorderWarm, RoundedCornerShape(16.dp))
                    .testTag("models_category_tabs"),
                indicator = { tabPositions ->
                    val index = if (selectedCategory == ModelCategory.CUSTOMIZABLE) 0 else 1
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[index]),
                        color = GoldWax,
                        height = 3.dp
                    )
                }
            ) {
                Tab(
                    selected = selectedCategory == ModelCategory.CUSTOMIZABLE,
                    onClick = { selectedCategory = ModelCategory.CUSTOMIZABLE },
                    modifier = Modifier.testTag("tab_customizable"),
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = "Personalizáveis",
                                fontFamily = FontFamily.Serif,
                                fontWeight = if (selectedCategory == ModelCategory.CUSTOMIZABLE) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.5.sp,
                                color = if (selectedCategory == ModelCategory.CUSTOMIZABLE) OliveDeep else TextMuted
                            )
                            Text(
                                text = "Layout ao vosso gosto",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 10.5.sp,
                                color = WarmMuted
                            )
                        }
                    }
                )

                Tab(
                    selected = selectedCategory == ModelCategory.PREDEFINED,
                    onClick = { selectedCategory = ModelCategory.PREDEFINED },
                    modifier = Modifier.testTag("tab_predefined"),
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = "Design Predefinido",
                                fontFamily = FontFamily.Serif,
                                fontWeight = if (selectedCategory == ModelCategory.PREDEFINED) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.5.sp,
                                color = if (selectedCategory == ModelCategory.PREDEFINED) OliveDeep else TextMuted
                            )
                            Text(
                                text = "Troca nomes, fotos e cores",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 10.5.sp,
                                color = WarmMuted
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Legenda explicativa da categoria selecionada
        val categorySubtitle = if (selectedCategory == ModelCategory.CUSTOMIZABLE) {
            "✨ Convites com total flexibilidade estrutural: adapte o posicionamento de fotos, fontes, múltiplos endereços e itinerário."
        } else {
            "⚡ Convites prontos a personalizar: basta enviar nomes, data, fotos e local para ter o seu convite pronto em até 48h."
        }

        Text(
            text = categorySubtitle,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = WarmMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 6.dp)
        )

        // ====================================================================
        // CARROSSEL HORIZONTAL DE MODELOS COM SELO "MAIS VENDIDO", AVALIAÇÃO,
        // TEMPO DE ENTREGA E BOTÃO "VER DETALHES"
        // ====================================================================
        val modelsScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(modelsScrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("models_horizontal_carousel"),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            filteredModels.forEach { model ->
                ModelCard(
                    model = model,
                    currency = selectedCurrency,
                    onSendMessageClick = { onSendMessageClick(model) },
                    onPreviewClick = { onPreviewModelClick(model) },
                    onViewDetailsClick = { detailedModelForDialog = model }
                )
            }
        }
    }

    // Modal de Detalhes Completos do Modelo
    if (detailedModelForDialog != null) {
        ModelDetailDialog(
            model = detailedModelForDialog!!,
            currency = selectedCurrency,
            onDismiss = { detailedModelForDialog = null },
            onSendMessageClick = {
                onSendMessageClick(it)
                detailedModelForDialog = null
            },
            onPreviewClick = {
                onPreviewModelClick(it)
                detailedModelForDialog = null
            }
        )
    }
}

@Composable
private fun ModelCard(
    model: InvitationModel,
    currency: Currency,
    onSendMessageClick: () -> Unit,
    onPreviewClick: () -> Unit,
    onViewDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .shadow(4.dp, RoundedCornerShape(20.dp), spotColor = Color(0x1F000000))
            .testTag("model_card_${model.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CreamPaper),
        border = BorderStroke(1.dp, BorderWarm)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Topo do Cartão: Mockup do Telemóvel + Envelope com o Selo 3D
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(WarmBeige, LinenBackground)
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                // Mockup em miniatura do telemóvel
                Box(
                    modifier = Modifier
                        .width(175.dp)
                        .height(154.dp)
                        .shadow(6.dp, RoundedCornerShape(16.dp), spotColor = Color(0x33000000))
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1B1F1C))
                        .border(1.5.dp, GoldWax.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        .padding(5.dp)
                ) {
                    // Ecrã com o Envelope
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(LinenBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        EnvelopeGraphic(
                            envelopeColor = model.envelopeColor,
                            waxColor = model.waxSealColor,
                            sampleNames = model.coupleNames,
                            pulseScale = 1f,
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }
                }

                // Selo "Mais vendido" (se for o mais vendido)
                if (model.isBestSeller) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFC59A3F), // Ouro nobre
                        shadowElevation = 4.dp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 4.dp, start = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(11.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Mais Vendido",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = Color.White
                            )
                        }
                    }
                } else if (model.badge != null) {
                    // Outro badge secundário
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = OliveDeep,
                        shadowElevation = 3.dp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 4.dp, start = 4.dp)
                    ) {
                        Text(
                            text = model.badge,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                // Botão flutuante "Ver Prévia" sobre o mockup
                Surface(
                    shape = CircleShape,
                    color = CreamPaper.copy(alpha = 0.95f),
                    shadowElevation = 4.dp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 6.dp, end = 6.dp)
                        .clickable { onPreviewClick() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null,
                            tint = OlivePrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Abrir 3D",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = OlivePrimary
                        )
                    }
                }
            }

            // Detalhes do Modelo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Nome do Modelo
                Text(
                    text = model.name,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = OliveDeep
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Avaliação em estrelas (mock) + Tempo de entrega
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = GoldWaxDark,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${model.rating} (${model.reviewCount})",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Medium,
                            color = WarmMuted
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = OlivePrimary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = model.deliveryTime,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OliveDeep
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Descrição curta
                Text(
                    text = model.description,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.5.sp,
                    lineHeight = 17.sp,
                    color = WarmMuted,
                    minLines = 2
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Lista de recursos incluídos
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    model.features.take(3).forEach { feature ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = OlivePrimary,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = feature,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 11.5.sp,
                                color = CharcoalDark
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Preço com desconto (antigo riscado + atual promocional)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = currency.format(model.originalPriceEur),
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextMuted,
                            textDecoration = TextDecoration.LineThrough
                        )

                        Text(
                            text = currency.format(model.currentPriceEur),
                            fontFamily = FontFamily.Serif,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = OliveDeep
                        )
                    }

                    val discountPercent = ((1.0 - (model.currentPriceEur / model.originalPriceEur)) * 100).toInt()
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = GoldWax.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, GoldWax.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "-$discountPercent%",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp,
                            color = GoldWaxDark,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Botão Secundário: "Ver detalhes" (abre modal completo com fotos)
                OutlinedButton(
                    onClick = onViewDetailsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .testTag("btn_details_${model.id}"),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, OlivePrimary.copy(alpha = 0.6f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = OliveDeep)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = OlivePrimary,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ver Detalhes & Fotos",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = OliveDeep
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botão Primário: "Enviar Mensagem" (WhatsApp)
                Button(
                    onClick = onSendMessageClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .testTag("btn_whatsapp_${model.id}"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Chat,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Enviar Mensagem",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

/**
 * Modal Completo de Detalhes do Modelo
 * Exibe galeria de fotos, especificações, tempo de entrega, itens incluídos
 * e botão de WhatsApp antes do contacto final.
 */
@Composable
private fun ModelDetailDialog(
    model: InvitationModel,
    currency: Currency,
    onDismiss: () -> Unit,
    onSendMessageClick: (InvitationModel) -> Unit,
    onPreviewClick: (InvitationModel) -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxSize(0.85f)
                .shadow(16.dp, RoundedCornerShape(24.dp))
                .testTag("model_detail_dialog"),
            shape = RoundedCornerShape(24.dp),
            color = CreamPaper
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header com Botão Fechar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(OliveDeep)
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.CenterStart)) {
                        Text(
                            text = model.name,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Detalhes e Especificações do Modelo",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.5.sp,
                            color = Color(0xFFD3DEC8)
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fechar",
                            tint = Color.White
                        )
                    }
                }

                // Corpo do Modal
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Mockup + Galeria de Fotos
                    if (model.galleryPhotos.isNotEmpty()) {
                        Text(
                            text = "FOTOS DO MODELO REAL",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp,
                            letterSpacing = 1.sp,
                            color = OliveDeep
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            model.galleryPhotos.forEach { photoUrl ->
                                AsyncImage(
                                    model = photoUrl,
                                    contentDescription = "Foto do convite",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(width = 160.dp, height = 110.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .border(1.dp, BorderWarm, RoundedCornerShape(12.dp))
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Descrição detalhada
                    Text(
                        text = "SOBRE ESTE CONVITE",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.5.sp,
                        letterSpacing = 1.sp,
                        color = OliveDeep
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (model.detailedDescription.isNotBlank()) model.detailedDescription else model.description,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 13.5.sp,
                        lineHeight = 20.sp,
                        color = CharcoalDark
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Destaques / Métricas rápidas
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = WarmBeige.copy(alpha = 0.5f),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Avaliação",
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 11.sp,
                                    color = WarmMuted
                                )
                                Text(
                                    text = "★ ${model.rating}/5.0",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = GoldWaxDark
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = WarmBeige.copy(alpha = 0.5f),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Tempo de Entrega",
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 11.sp,
                                    color = WarmMuted
                                )
                                Text(
                                    text = model.deliveryTime,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = OliveDeep
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // O que está incluído
                    Text(
                        text = "O QUE ESTÁ INCLUÍDO",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.5.sp,
                        letterSpacing = 1.sp,
                        color = OliveDeep
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val items = if (model.includedItems.isNotEmpty()) model.includedItems else model.features
                    items.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 3.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = OlivePrimary,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 12.5.sp,
                                color = CharcoalDark
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Preço no rodapé do modal
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Preço Promocional",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 11.5.sp,
                                color = WarmMuted
                            )
                            Text(
                                text = currency.format(model.currentPriceEur),
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = OliveDeep
                            )
                        }

                        OutlinedButton(
                            onClick = { onPreviewClick(model) },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, GoldWax)
                        ) {
                            Text(
                                text = "Abrir Convite 3D",
                                color = GoldWaxDark,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Botão WhatsApp
                    Button(
                        onClick = { onSendMessageClick(model) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("btn_modal_whatsapp"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Chat,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(19.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Encomendar por WhatsApp",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
