package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.model.BlogPostItem
import com.example.model.TatianaRepository
import com.example.ui.theme.BorderWarm
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CreamPaper
import com.example.ui.theme.GoldWax
import com.example.ui.theme.GoldWaxDark
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.TextMuted
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WarmMuted

/**
 * 5. Secção de Blog / Inspiração com Dicas de Casamento
 * Diferencial de SEO e autoridade orgânica para noivos.
 */
@Composable
fun BlogSection(modifier: Modifier = Modifier) {
    var selectedPostForReading by remember { mutableStateOf<BlogPostItem?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmBeige.copy(alpha = 0.25f))
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
                color = OlivePrimary.copy(alpha = 0.08f),
                border = BorderStroke(1.dp, OlivePrimary.copy(alpha = 0.2f)),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "BLOG & INSPIRAÇÃO",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    letterSpacing = 1.2.sp,
                    color = OlivePrimary,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                )
            }

            Text(
                text = "Dicas para o Vosso Casamento",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = OliveDeep,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("blog_section_title")
            )

            Text(
                text = "Artigos pensados para inspirar, economizar no orçamento e criar uma celebração memorável.",
                fontFamily = FontFamily.SansSerif,
                fontSize = 13.5.sp,
                color = WarmMuted,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 20.dp)
                    .fillMaxWidth(0.9f)
            )
        }

        // Carrossel de Artigos
        val blogScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(blogScrollState)
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .testTag("blog_posts_row"),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TatianaRepository.blogPosts.forEach { post ->
                BlogPostCard(
                    post = post,
                    onClick = { selectedPostForReading = post }
                )
            }
        }
    }

    // Modal de Leitura Completa do Artigo
    if (selectedPostForReading != null) {
        BlogPostDialog(
            post = selectedPostForReading!!,
            onDismiss = { selectedPostForReading = null }
        )
    }
}

@Composable
private fun BlogPostCard(
    post: BlogPostItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .shadow(4.dp, RoundedCornerShape(20.dp), spotColor = Color(0x1F000000))
            .clickable { onClick() }
            .testTag("blog_card_${post.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CreamPaper),
        border = BorderStroke(1.dp, BorderWarm)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Imagem de Capa do Artigo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(LinenBackground)
            ) {
                AsyncImage(
                    model = post.imageUrl,
                    contentDescription = post.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Categoria
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = OliveDeep.copy(alpha = 0.85f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = post.category,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Conteúdo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Meta info: Data e Tempo de Leitura
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = post.date,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        color = TextMuted
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = GoldWaxDark,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = post.readTime,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            color = GoldWaxDark,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Título
                Text(
                    text = post.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = OliveDeep,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Excerto
                Text(
                    text = post.excerpt,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.5.sp,
                    lineHeight = 17.sp,
                    color = WarmMuted,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Ação: "Ler Artigo"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Ler artigo",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = OlivePrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = OlivePrimary,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BlogPostDialog(
    post: BlogPostItem,
    onDismiss: () -> Unit
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
                .testTag("blog_dialog_${post.id}"),
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
                            text = post.category.uppercase(),
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.5.sp,
                            letterSpacing = 1.sp,
                            color = GoldWax
                        )
                        Text(
                            text = post.date + " • " + post.readTime,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.5.sp,
                            color = Color.White.copy(alpha = 0.8f)
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

                // Imagem de Capa do Artigo no Dialog
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    AsyncImage(
                        model = post.imageUrl,
                        contentDescription = post.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Conteúdo
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                ) {
                    Text(
                        text = post.title,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        color = OliveDeep
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = post.fullContent,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = CharcoalDark
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Dica de rodapé do blog
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = WarmBeige.copy(alpha = 0.5f),
                        border = BorderStroke(1.dp, BorderWarm),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = GoldWaxDark,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Gostou deste artigo? Os nossos convites digitais já incluem opções adaptadas para o vosso Dress Code e playlist!",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 12.sp,
                                color = OliveDeep,
                                lineHeight = 17.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, OlivePrimary)
                    ) {
                        Text("Fechar artigo", color = OlivePrimary)
                    }
                }
            }
        }
    }
}
