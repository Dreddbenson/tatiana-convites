package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Currency
import com.example.model.HeroEnvelopeItem
import com.example.model.InvitationModel
import com.example.model.Language
import com.example.model.TatianaRepository
import com.example.ui.components.BlogSection
import com.example.ui.components.CustomTemplateSection
import com.example.ui.components.FaqSection
import com.example.ui.components.FooterSection
import com.example.ui.components.HeroSection
import com.example.ui.components.HowItWorksSection
import com.example.ui.components.InteractiveInvitationDialog
import com.example.ui.components.ModelsSection
import com.example.ui.components.PlanComparisonSection
import com.example.ui.components.PortfolioSection
import com.example.ui.components.QuoteRequestSection
import com.example.ui.components.StandaloneInvitationScreen
import com.example.ui.components.TatianaHeader
import com.example.ui.components.TestimonialsSection
import com.example.ui.theme.GoldWax
import com.example.ui.theme.LinenBackground
import com.example.ui.theme.OliveDeep
import com.example.ui.theme.TatianaTheme
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TatianaTheme {
                TatianaApp()
            }
        }
    }
}

/**
 * Modos de visualização do ecrã:
 * 1. TATIANA_CATALOG: O website completo da marca TATIANA com todas as secções
 * 2. STANDALONE_INVITATION: Ecrã standalone que simula o convite digital que o convidado recebe
 */
enum class ScreenMode {
    TATIANA_CATALOG,
    STANDALONE_INVITATION
}

/**
 * Aplicação Principal da Marca "TATIANA" (Convites de Casamento Digitais)
 * Design elegante, mobile-first e responsivo.
 *
 * Estrutura:
 * 1. Header fixo com monograma, nome e seletor de idioma/moeda
 * 2. Hero com leque 3D de telemóveis, selo pulsante e badge "Novo"
 * 3. Como funciona (01, 02, 03)
 * 4. Modelos & Preços (selo Mais Vendido, estrelas, tempo entrega, modal de detalhes, WhatsApp)
 * 5. Galeria / Portefólio Real com Casos de Sucesso (diferencial)
 * 6. Template 100% Personalizado (upsell)
 * 7. Comparador de Planos (tabela: Básico / Premium / Personalizado) (diferencial)
 * 8. Depoimentos de Clientes em Carrossel (diferencial)
 * 9. Formulário de Pedido de Orçamento Embutido (Formspree/EmailJS) (diferencial)
 * 10. Blog / Inspiração com Dicas de Casamento (diferencial)
 * 11. FAQ em accordion expansível
 * 12. Rodapé com contactos, redes e bandeiras dos países
 */
@Composable
fun TatianaApp() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // Modo do ecrã: Inicia no Catálogo da Marca TATIANA
    var currentScreenMode by remember { mutableStateOf(ScreenMode.TATIANA_CATALOG) }

    // Estados de Moeda e Idioma (Multi-moeda e Multi-idioma)
    var selectedCurrency by remember { mutableStateOf(Currency.MZN) }
    var selectedLanguage by remember { mutableStateOf(Language.PT) }

    // Estado do Modal Interativo do Convite 3D (Tocar no Selo)
    var isPreviewOpen by remember { mutableStateOf(false) }
    var previewEnvelopeColor by remember { mutableStateOf(Color(0xFF3F513A)) }
    var previewCoupleNames by remember { mutableStateOf("Ana & Carlos") }

    // Helper para abrir WhatsApp com mensagem personalizada
    fun openWhatsApp(customText: String) {
        try {
            val encodedMessage = URLEncoder.encode(customText, StandardCharsets.UTF_8.toString())
            val uri = Uri.parse("https://wa.me/${TatianaRepository.WHATSAPP_NUMBER}?text=$encodedMessage")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "A abrir WhatsApp: ${TatianaRepository.WHATSAPP_DISPLAY}", Toast.LENGTH_LONG).show()
        }
    }

    // Helper para enviar e-mail
    fun openEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${TatianaRepository.CONTACT_EMAIL}")
                putExtra(Intent.EXTRA_SUBJECT, "Informações sobre Convites de Casamento Digitais - TATIANA")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "E-mail: ${TatianaRepository.CONTACT_EMAIL}", Toast.LENGTH_SHORT).show()
        }
    }

    // Helper para abrir Instagram
    fun openInstagram() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/tatiana.convites"))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Instagram: ${TatianaRepository.INSTAGRAM_HANDLE}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .testTag("tatiana_scaffold_root"),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Barra de alternância entre o Convite Standalone e o Catálogo da Marca
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(OliveDeep)
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFF1E281C),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldWax.copy(alpha = 0.4f))
                    ) {
                        Row(modifier = Modifier.padding(3.dp)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        if (currentScreenMode == ScreenMode.TATIANA_CATALOG) GoldWax else Color.Transparent
                                    )
                                    .clickable { currentScreenMode = ScreenMode.TATIANA_CATALOG }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .testTag("tab_tatiana_catalog"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🛍️ Catálogo TATIANA",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.5.sp,
                                    color = if (currentScreenMode == ScreenMode.TATIANA_CATALOG) Color(0xFF2C200B) else Color(0xFFD6DEC5)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        if (currentScreenMode == ScreenMode.STANDALONE_INVITATION) GoldWax else Color.Transparent
                                    )
                                    .clickable { currentScreenMode = ScreenMode.STANDALONE_INVITATION }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .testTag("tab_standalone_invitation"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "💌 Convite Standalone",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.5.sp,
                                    color = if (currentScreenMode == ScreenMode.STANDALONE_INVITATION) Color(0xFF2C200B) else Color(0xFFD6DEC5)
                                )
                            }
                        }
                    }
                }

                // Header da loja (exibido no modo Catálogo)
                if (currentScreenMode == ScreenMode.TATIANA_CATALOG) {
                    TatianaHeader(
                        selectedCurrency = selectedCurrency,
                        onCurrencyChange = { selectedCurrency = it },
                        selectedLanguage = selectedLanguage,
                        onLanguageChange = { selectedLanguage = it },
                        onWhatsAppClick = {
                            openWhatsApp("Olá Tatiana! Gostaria de obter mais informações sobre os convites de casamento digitais.")
                        },
                        modifier = Modifier.testTag("tatiana_fixed_header")
                    )
                }
            }
        },
        containerColor = if (currentScreenMode == ScreenMode.STANDALONE_INVITATION) Color(0xFF384934) else LinenBackground
    ) { innerPadding ->
        if (currentScreenMode == ScreenMode.STANDALONE_INVITATION) {
            // ECRÃ STANDALONE: Mobile-first, ocupa 100% do ecrã do telemóvel
            StandaloneInvitationScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .testTag("tatiana_main_scroll_column")
            ) {
                // 2. Hero: 5 telemóveis em leque 3D com selo pulsante e badge "Novo"
                HeroSection(
                    onExploreModelsClick = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(750)
                        }
                    },
                    onOpenInteractiveInvite = { envelopeItem ->
                        previewEnvelopeColor = envelopeItem.envelopeColor
                        previewCoupleNames = envelopeItem.sampleNames
                        isPreviewOpen = true
                    },
                    modifier = Modifier.testTag("tatiana_hero_section")
                )

                // 3. Secção "Como funciona" com 3 cartões numerados (01, 02, 03)
                HowItWorksSection(
                    modifier = Modifier.testTag("tatiana_how_it_works_section")
                )

                // 4. Secção "Modelos" em carrossel horizontal com 2 categorias (tabs),
                // selo "Mais vendido", avaliações, tempo de entrega e modal "Ver detalhes"
                ModelsSection(
                    selectedCurrency = selectedCurrency,
                    onSendMessageClick = { model ->
                        val message = "Olá Tatiana! Gostaria de encomendar o modelo de convite digital \"${model.name}\" (${selectedCurrency.format(model.currentPriceEur)})."
                        openWhatsApp(message)
                    },
                    onPreviewModelClick = { model ->
                        previewEnvelopeColor = model.envelopeColor
                        previewCoupleNames = model.coupleNames
                        isPreviewOpen = true
                    },
                    modifier = Modifier.testTag("tatiana_models_section")
                )

                // 5. Galeria / Portefólio Real com Casos de Sucesso (Diferenciação)
                PortfolioSection(
                    onOpenCaseDemo = { caseItem ->
                        previewEnvelopeColor = caseItem.envelopeColor
                        previewCoupleNames = caseItem.coupleNames
                        isPreviewOpen = true
                    }
                )

                // 6. Secção "Template 100% personalizado" (upsell com preço fixo + prazo mínimo)
                CustomTemplateSection(
                    currency = selectedCurrency,
                    onCreateCustomTemplateClick = {
                        val message = "Olá Tatiana! Tenho interesse em criar um Template 100% Personalizado exclusivo para o meu casamento (${selectedCurrency.format(TatianaRepository.CUSTOM_TEMPLATE_PRICE_EUR)})."
                        openWhatsApp(message)
                    },
                    modifier = Modifier.testTag("tatiana_custom_template_section")
                )

                // 7. Comparador de Planos (Tabela Básico vs Premium vs Personalizado) (Diferenciação)
                PlanComparisonSection(
                    selectedCurrency = selectedCurrency,
                    onSelectPlan = { planName ->
                        val message = "Olá Tatiana! Gostaria de contratar o plano \"$planName\" para o meu convite digital."
                        openWhatsApp(message)
                    }
                )

                // 8. Depoimentos de Clientes em Carrossel (Diferenciação)
                TestimonialsSection()

                // 9. Formulário de Pedido de Orçamento Embutido (EmailJS / Formspree) (Diferenciação)
                QuoteRequestSection(
                    onWhatsAppClick = { message ->
                        openWhatsApp(message)
                    }
                )

                // 10. Blog / Inspiração com Dicas de Casamento (Diferenciação)
                BlogSection()

                // 11. FAQ em accordion (perguntas expansíveis)
                FaqSection(
                    modifier = Modifier.testTag("tatiana_faq_section")
                )

                // 12. Rodapé com contactos, redes sociais e países (bandeiras)
                FooterSection(
                    onWhatsAppClick = {
                        openWhatsApp("Olá Tatiana! Gostaria de falar convosco sobre convites digitais.")
                    },
                    onEmailClick = { openEmail() },
                    onInstagramClick = { openInstagram() },
                    modifier = Modifier.testTag("tatiana_footer_section")
                )
            }
        }

        // ====================================================================
        // MODAL DO CONVITE DIGITAL INTERATIVO (ENVELOPE 3D + SELO DE CERA)
        // Permite ao utilizador tocar no selo de cera e assistir à animação
        // de abertura com o convite oficial, fotos, cronograma e RSVP!
        // ====================================================================
        if (isPreviewOpen) {
            InteractiveInvitationDialog(
                initialEnvelopeColor = previewEnvelopeColor,
                initialNames = previewCoupleNames,
                onDismiss = { isPreviewOpen = false }
            )
        }
    }
}

// Mantido para compatibilidade com testes de regressão existentes
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}
