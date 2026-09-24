package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TatianaRepository
import com.example.ui.theme.GoldWax
import com.example.ui.theme.GoldWaxLight
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.OlivePrimary
import com.example.ui.theme.WarmBeige
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
    onWhatsAppClick: () -> Unit,
    onEmailClick: () -> Unit,
    onInstagramClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(OliveDeep)
            .padding(horizontal = 24.dp, vertical = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo / Monograma Dourado
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E281C))
                .border(1.5.dp, GoldWax, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "T",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = GoldWax
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Nome da Marca
        Text(
            text = "TATIANA",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            letterSpacing = 2.5.sp,
            color = Color(0xFFF7F5F0)
        )

        Text(
            text = "CONVITES DE CASAMENTO DIGITAIS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
            color = GoldWaxLight
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Criamos convites digitais memoráveis em formato de envelope 3D interativo com selo de cera. A primeira emoção do vosso grande dia começa aqui.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 13.5.sp,
            lineHeight = 21.sp,
            color = Color(0xFFD3D8CF),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.92f)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Linha divisória suave
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFF3F4D3B))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Contactos Diretos (WhatsApp & E-mail)
        Text(
            text = "CONTACTOS & ENCOMENDAS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.5.sp,
            letterSpacing = 1.2.sp,
            color = GoldWax
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // WhatsApp Contact Card
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF232D20))
                    .clickable { onWhatsAppClick() }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Chat,
                    contentDescription = null,
                    tint = WhatsAppGreen,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "WhatsApp Oficial",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        color = Color(0xFFA5B2A1)
                    )
                    Text(
                        text = TatianaRepository.WHATSAPP_DISPLAY,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.5.sp,
                        color = Color.White
                    )
                }
            }

            // E-mail Contact Card
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF232D20))
                    .clickable { onEmailClick() }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = GoldWax,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "E-mail de Apoio",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 11.sp,
                        color = Color(0xFFA5B2A1)
                    )
                    Text(
                        text = TatianaRepository.CONTACT_EMAIL,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.5.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Países onde o serviço está disponível (com bandeiras)
        Text(
            text = "DISPONÍVEL NOS PAÍSES",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.5.sp,
            letterSpacing = 1.2.sp,
            color = GoldWax
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Serviço 100% digital e sem fronteiras. Atendemos noivos e convidados em qualquer lugar do mundo:",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.5.sp,
            color = Color(0xFFBDC7B9),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Bandeiras dos Países
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TatianaRepository.availableCountries.forEach { country ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFF232D20),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF3B4837)),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = country.flag, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = country.name,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFE4E9E1)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Redes Sociais
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF232D20),
                modifier = Modifier.clickable { onInstagramClick() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = GoldWax,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = TatianaRepository.INSTAGRAM_HANDLE,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Nota final / Copyright
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Feito com carinho para noivos inesquecíveis",
                fontFamily = FontFamily.Serif,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                fontSize = 12.sp,
                color = Color(0xFFA4B0A0)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = GoldWax,
                modifier = Modifier.size(13.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "© 2026 TATIANA • Convites de Casamento Digitais. Todos os direitos reservados.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            color = Color(0xFF768572),
            textAlign = TextAlign.Center
        )
    }
}
