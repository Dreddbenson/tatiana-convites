package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.EnvelopeDeepForest
import com.example.ui.theme.EnvelopeDustyRose
import com.example.ui.theme.EnvelopeOlive
import com.example.ui.theme.EnvelopeSkyBlue
import com.example.ui.theme.EnvelopeWarmBeige
import com.example.ui.theme.GoldWax
import com.example.ui.theme.RoseWax

// ========================================================================
// SISTEMA DE MOEDAS (MULTI-MOEDA: MZN, EUR, USD, BRL, AOA)
// ========================================================================
enum class Currency(
    val code: String,
    val symbol: String,
    val rateFromEur: Double,
    val flag: String,
    val label: String
) {
    MZN("MZN", "MT", 68.5, "🇲🇿", "Metical (MZN)"),
    EUR("EUR", "€", 1.0, "🇪🇺", "Euro (EUR)"),
    USD("USD", "$", 1.08, "🇺🇸", "Dólar (USD)"),
    BRL("BRL", "R$", 6.10, "🇧🇷", "Real (BRL)"),
    AOA("AOA", "Kz", 980.0, "🇦🇴", "Kwanza (AOA)");

    fun format(amountInEur: Double): String {
        val converted = amountInEur * rateFromEur
        return when (this) {
            MZN -> "${"%,d".format(converted.toInt()).replace(',', '.')} MT"
            EUR -> "€${"%.0f".format(converted)}"
            USD -> "$${"%.0f".format(converted)}"
            BRL -> "R$ ${"%.0f".format(converted)}"
            AOA -> "${"%,d".format(converted.toInt()).replace(',', '.')} Kz"
        }
    }
}

// ========================================================================
// SISTEMA DE IDIOMAS (MULTI-IDIOMA: PT, EN, ES)
// ========================================================================
enum class Language(val code: String, val label: String, val flag: String) {
    PT("PT", "Português", "🇵🇹"),
    EN("EN", "English", "🇬🇧"),
    ES("ES", "Español", "🇪🇸")
}

// ========================================================================
// MODELO DE CONVITE (CATÁLOGO)
// ========================================================================
enum class ModelCategory {
    CUSTOMIZABLE,   // Convites totalmente personalizáveis (layout ao gosto)
    PREDEFINED      // Convites com design predefinido (troca nomes, fotos, cores)
}

data class InvitationModel(
    val id: String,
    val name: String,
    val category: ModelCategory,
    val description: String,
    val originalPriceEur: Double, // Preço antigo riscado
    val currentPriceEur: Double,  // Preço promocional atual
    val envelopeColor: Color,
    val envelopeColorName: String,
    val waxSealColor: Color,
    val coupleNames: String,
    val weddingDate: String,
    val venueName: String,
    val features: List<String>,
    val badge: String? = null,
    val isBestSeller: Boolean = false,
    val rating: Double = 5.0,
    val reviewCount: Int = 42,
    val deliveryTime: String = "48h a 72h",
    val galleryPhotos: List<String> = emptyList(),
    val detailedDescription: String = "",
    val includedItems: List<String> = emptyList()
)

// ========================================================================
// CASOS DE SUCESSO / PORTEFÓLIO
// ========================================================================
data class PortfolioCase(
    val id: String,
    val coupleNames: String,
    val weddingDate: String,
    val location: String,
    val style: String,
    val metric: String,
    val quote: String,
    val envelopeColor: Color,
    val waxColor: Color,
    val previewImage: String
)

// ========================================================================
// DEPOIMENTOS DE CLIENTES
// ========================================================================
data class TestimonialItem(
    val id: String,
    val coupleNames: String,
    val location: String,
    val weddingDate: String,
    val rating: Int = 5,
    val quote: String,
    val avatarInitials: String
)

// ========================================================================
// COMPARADOR DE PLANOS
// ========================================================================
data class PlanComparisonItem(
    val feature: String,
    val basic: String,
    val premium: String,
    val custom: String,
    val isHighlighted: Boolean = false
)

// ========================================================================
// ARTIGOS DO BLOG / INSPIRAÇÃO DE CASAMENTO
// ========================================================================
data class BlogPostItem(
    val id: String,
    val title: String,
    val excerpt: String,
    val category: String,
    val readTime: String,
    val date: String,
    val imageUrl: String,
    val fullContent: String
)

// ========================================================================
// PEDIDO DE ORÇAMENTO
// ========================================================================
data class QuoteRequest(
    val coupleNames: String,
    val email: String,
    val phone: String,
    val weddingDate: String,
    val guestsCount: String,
    val plan: String,
    val notes: String
)

// ========================================================================
// PASSO DO "COMO FUNCIONA"
// ========================================================================
data class HowItWorksStep(
    val number: String,
    val title: String,
    val subtitle: String,
    val description: String
)

// ========================================================================
// ITEM DE FAQ (ACCORDION)
// ========================================================================
data class FaqItem(
    val id: String,
    val question: String,
    val answer: String
)

// ========================================================================
// PAÍSES DISPONÍVEIS
// ========================================================================
data class AvailableCountry(
    val code: String,
    val name: String,
    val flag: String
)

// ========================================================================
// DADOS FICTÍCIOS DE EXEMPLO (FACILMENTE SUBSTITUÍVEIS PELO CLIENTE)
// ========================================================================
object TatianaRepository {

    // [CLIENTE: Substituir pelo vosso número de WhatsApp oficial e e-mail]
    const val WHATSAPP_NUMBER = "258841234567" // Formato internacional sem '+' ou traços
    const val WHATSAPP_DISPLAY = "+258 84 123 4567 / +351 912 345 678"
    const val CONTACT_EMAIL = "ola@tatianaconvites.com"
    const val INSTAGRAM_HANDLE = "@tatiana.convites"
    const val WEBSITE_DOMAIN = "tatianaconvites.com"

    // [CLIENTE: Preço e prazo da secção de Upsell "Template 100% Personalizado"]
    const val CUSTOM_TEMPLATE_PRICE_EUR = 145.0 // ~ 9.900 MT / 145 €
    const val CUSTOM_TEMPLATE_DELIVERY_DAYS = "5 a 7 dias úteis"

    // 5 Envelopes Clássicos apresentados no Hero
    val heroEnvelopes = listOf(
        HeroEnvelopeItem("1", "Verde-Oliva", EnvelopeOlive, GoldWax, "Ana & Carlos"),
        HeroEnvelopeItem("2", "Azul-Claro", EnvelopeSkyBlue, Color(0xFFC7D3DC), "Sofia & Tiago"),
        HeroEnvelopeItem("3", "Verde-Escuro", EnvelopeDeepForest, GoldWax, "Mariana & João"),
        HeroEnvelopeItem("4", "Rosa Antigo", EnvelopeDustyRose, RoseWax, "Beatriz & Lucas"),
        HeroEnvelopeItem("5", "Bege Linho", EnvelopeWarmBeige, GoldWax, "Inês & Miguel")
    )

    // Passos da secção "Como Funciona"
    val howItWorksSteps = listOf(
        HowItWorksStep(
            number = "01",
            title = "Conversa inicial",
            subtitle = "Sem qualquer compromisso",
            description = "Falamos por WhatsApp sobre o estilo do vosso casamento, paleta de cores, inspirações e o que não pode faltar no vosso convite."
        ),
        HowItWorksStep(
            number = "02",
            title = "Pagamento flexível",
            subtitle = "50% ou 100%",
            description = "O projeto começa logo após a confirmação. Podem optar por 50% de sinal e os restantes 50% apenas após aprovarem a versão final."
        ),
        HowItWorksStep(
            number = "03",
            title = "Entrega e partilha",
            subtitle = "Link interativo exclusivo",
            description = "Recebem o link personalizado pronto a partilhar com os convidados por WhatsApp, SMS ou redes sociais com envelope 3D e RSVP."
        )
    )

    // Catálogo de Modelos
    val models = listOf(
        // Categoria A: Convites totalmente personalizáveis
        InvitationModel(
            id = "custom_jardim",
            name = "Jardim Botânico",
            category = ModelCategory.CUSTOMIZABLE,
            description = "Estrutura orgânica com envelope verde-oliva, selo dourado botânico e animação floral suave de pétalas.",
            originalPriceEur = 120.0,
            currentPriceEur = 85.0,
            envelopeColor = EnvelopeOlive,
            envelopeColorName = "Verde-Oliva",
            waxSealColor = GoldWax,
            coupleNames = "Ana & Carlos",
            weddingDate = "18 de Outubro de 2025",
            venueName = "Quinta do Lago, Cascais",
            features = listOf("Layout sob medida", "Envelope 3D interativo", "RSVP em tempo real", "Música de fundo", "Contador decrescente"),
            badge = "Mais Popular",
            isBestSeller = true,
            rating = 5.0,
            reviewCount = 64,
            deliveryTime = "48h a 72h",
            galleryPhotos = listOf(
                "https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=800&q=80"
            ),
            detailedDescription = "O Jardim Botânico é o nosso convite assinatura. Desenvolvido para casamentos nobres e ao ar livre, combina tons de verde-oliva com cera em folha de ouro e tipografia caligráfica imperial. Inclui confirmação de presença (RSVP) com restrições alimentares, contagem decrescente ao vivo, integração direta com Waze e Google Maps, além de música suave selecionada pelos noivos.",
            includedItems = listOf(
                "Envelope 3D com selo de cera botânico",
                "Animação suave de abertura e toque no selo",
                "Galeria de até 6 fotos em alta resolução",
                "RSVP com notificação direta e contagem de pessoas",
                "Filtro para intolerâncias alimentares dos convidados",
                "Botão 'Adicionar ao Calendário' (.ics e Google Calendar)",
                "Link de localização direta com GPS / Waze"
            )
        ),
        InvitationModel(
            id = "custom_floresta",
            name = "Floresta Imperial",
            category = ModelCategory.CUSTOMIZABLE,
            description = "Tons nobres de verde-escuro profundo com monograma em folha de ouro e tipografia imperial aristocrática.",
            originalPriceEur = 135.0,
            currentPriceEur = 95.0,
            envelopeColor = EnvelopeDeepForest,
            envelopeColorName = "Verde-Escuro",
            waxSealColor = GoldWax,
            coupleNames = "Mariana & Gonçalo",
            weddingDate = "29 de Novembro de 2025",
            venueName = "Palácio dos Arcos, Oeiras",
            features = listOf("Estrutura personalizada", "Cera com monograma exclusivo", "Múltiplas localizações", "Lista de presentes interativa"),
            badge = "Exclusivo",
            isBestSeller = false,
            rating = 4.9,
            reviewCount = 38,
            deliveryTime = "3 a 5 dias",
            galleryPhotos = listOf(
                "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=800&q=80"
            ),
            detailedDescription = "Inspirado nos castelos e palácios portugueses. Textura de veludo digital no envelope, monograma real prensado em alto-relevo dourado e páginas com múltiplos eventos (cerimónia religiosa, cocktail e copo d'água em locais diferentes).",
            includedItems = listOf(
                "Monograma personalizado com as iniciais do casal",
                "Páginas dedicadas para múltiplos locais do evento",
                "Lista de presentes e número de IBAN/MB WAY seguro",
                "Guia de alojamento para convidados que vêm de fora",
                "Música orquestral de boas-vindas"
            )
        ),
        InvitationModel(
            id = "custom_minimal_bege",
            name = "Dunas de Linho",
            category = ModelCategory.CUSTOMIZABLE,
            description = "Minimalismo quente em tons bege e terra. Elegância silenciosa inspirada na brisa costeira e texturas artesanais.",
            originalPriceEur = 115.0,
            currentPriceEur = 80.0,
            envelopeColor = EnvelopeWarmBeige,
            envelopeColorName = "Bege Linho",
            waxSealColor = Color(0xFFA68766),
            coupleNames = "Inês & Miguel",
            weddingDate = "14 de Setembro de 2025",
            venueName = "Herdade do Moinho, Comporta",
            features = listOf("Design sob medida", "Galeria de fotos editorial", "Filtro de restrições alimentares", "Mapa Waze & Google Maps"),
            isBestSeller = false,
            rating = 4.9,
            reviewCount = 45,
            deliveryTime = "48h a 72h",
            galleryPhotos = listOf(
                "https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1522673607200-164d1b6ce486?auto=format&fit=crop&w=800&q=80"
            ),
            detailedDescription = "Perfeito para casamentos na praia, quintas rústicas e noivos contemporâneos. Uma ode ao 'quiet luxury' com tipografia limpa, tons linho e areia e microinterações táteis suaves.",
            includedItems = listOf(
                "Selo de cera terracota artesanal",
                "Cronograma visual em linha do tempo",
                "Dicas de Dress Code e calçado para praia/relva",
                "Confirmação de presença em 1 toque"
            )
        ),

        // Categoria B: Convites com design predefinido
        InvitationModel(
            id = "predef_brisa",
            name = "Brisa do Oceano",
            category = ModelCategory.PREDEFINED,
            description = "Azul-claro sereno com detalhes perolados. Basta enviar nomes, data, foto e local para ter o seu convite pronto.",
            originalPriceEur = 75.0,
            currentPriceEur = 50.0,
            envelopeColor = EnvelopeSkyBlue,
            envelopeColorName = "Azul-Claro",
            waxSealColor = Color(0xFFC4D5E0),
            coupleNames = "Sofia & Tiago",
            weddingDate = "12 de Julho de 2025",
            venueName = "Clube Náutico de Maputo",
            features = listOf("Troca de nomes e fotos", "Cores personalizáveis", "Envelope 3D animado", "RSVP incluído"),
            badge = "Entrega Rápida",
            isBestSeller = false,
            rating = 4.8,
            reviewCount = 29,
            deliveryTime = "24h a 48h",
            galleryPhotos = listOf(
                "https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=800&q=80"
            ),
            detailedDescription = "Modelo pronto e rápido. Troca imediata de dados com entrega em até 48 horas. Perfeito para noivos que pretendem rapidez sem abdicar do impacto do envelope 3D.",
            includedItems = listOf(
                "Personalização com fotos e dados dos noivos",
                "Envelope 3D funcional com abertura tátil",
                "Formulário de RSVP automatizado",
                "Link personalizado para partilhar no WhatsApp"
            )
        ),
        InvitationModel(
            id = "predef_romance",
            name = "Romance Blush",
            category = ModelCategory.PREDEFINED,
            description = "Romance delicado em tons rosa antigo com selo rosé gold e arabescos florais que cativam no primeiro toque.",
            originalPriceEur = 70.0,
            currentPriceEur = 48.0,
            envelopeColor = EnvelopeDustyRose,
            envelopeColorName = "Rosa Antigo",
            waxSealColor = RoseWax,
            coupleNames = "Beatriz & Lucas",
            weddingDate = "23 de Agosto de 2025",
            venueName = "Solar das Camélias, Sintra",
            features = listOf("Troca rápida de textos e fotos", "Selo rosé interativo", "Itinerário do dia do evento", "Suporte WhatsApp"),
            badge = "Favorito das Noivas",
            isBestSeller = true,
            rating = 5.0,
            reviewCount = 52,
            deliveryTime = "24h a 48h",
            galleryPhotos = listOf(
                "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=800&q=80"
            ),
            detailedDescription = "O nosso modelo predefinido mais adorado. Fundo blush suave, selo em cera rosé gold e animação de desdobramento fluida. Conquista de imediato todos os padrinhos e convidados.",
            includedItems = listOf(
                "Selo de cera rosé gold animado",
                "Galeria de 4 fotos do casal",
                "Localização com botão para rotas GPS",
                "Contador de dias até ao casamento"
            )
        ),
        InvitationModel(
            id = "predef_elegancia",
            name = "Elegância Clássica",
            category = ModelCategory.PREDEFINED,
            description = "O clássico atemporal que nunca passa de moda: fundo linho marfim, fontes serifadas nobres e selo dourado polido.",
            originalPriceEur = 68.0,
            currentPriceEur = 45.0,
            envelopeColor = EnvelopeWarmBeige,
            envelopeColorName = "Bege Champanhe",
            waxSealColor = GoldWax,
            coupleNames = "Clara & Duarte",
            weddingDate = "04 de Outubro de 2025",
            venueName = "Quinta da Fonte, Coimbra",
            features = listOf("Adaptação em 48h", "Selo de cera 3D", "Link próprio para WhatsApp", "Botão de confirmação instantânea"),
            isBestSeller = false,
            rating = 4.8,
            reviewCount = 31,
            deliveryTime = "24h a 48h",
            galleryPhotos = listOf(
                "https://images.unsplash.com/photo-1522673607200-164d1b6ce486?auto=format&fit=crop&w=800&q=80"
            ),
            detailedDescription = "Tipografia serifada clássica, proporções áureas e cores neutras que se adaptam a qualquer estilo de cerimónia tradicional ou campestre.",
            includedItems = listOf(
                "Envelope linho champanhe",
                "Selo dourado com iniciais",
                "RSVP com envio direto para o vosso WhatsApp"
            )
        )
    )

    // ========================================================================
    // CASOS DE SUCESSO / PORTEFÓLIO REAL
    // ========================================================================
    val portfolioCases = listOf(
        PortfolioCase(
            id = "case_1",
            coupleNames = "Matilde & Bernardo",
            weddingDate = "Maio de 2025",
            location = "Palácio de Monserrate, Sintra",
            style = "Verde-Oliva & Dourado Real",
            metric = "96% de confirmações em 5 dias",
            quote = "Os convidados pensavam que era um vídeo até tocarem no selo e verem a carta abrir. Foi o comentário de todo o casamento!",
            envelopeColor = EnvelopeOlive,
            waxColor = GoldWax,
            previewImage = "https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=800&q=80"
        ),
        PortfolioCase(
            id = "case_2",
            coupleNames = "Camila & Tomás",
            weddingDate = "Junho de 2025",
            location = "Quinta do Hespanhol, Torres Vedras",
            style = "Romance Blush & Rosé Gold",
            metric = "180 convidados alcançados sem custos de envio",
            quote = "Poupámos mais de 600€ em impressões e correios. A gestão de pessoas e alergias no RSVP poupou-nos semanas de trabalho.",
            envelopeColor = EnvelopeDustyRose,
            waxColor = RoseWax,
            previewImage = "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=800&q=80"
        ),
        PortfolioCase(
            id = "case_3",
            coupleNames = "Neuza & Edson",
            weddingDate = "Agosto de 2025",
            location = "Catembe Gallery Hotel, Maputo",
            style = "Azul Sereno & Prata",
            metric = "100% de resposta sem perder nenhum convidado",
            quote = "Muitos convidados estavam em Portugal e na África do Sul. Mandar o link por WhatsApp com o mapa e o calendário facilitou tudo!",
            envelopeColor = EnvelopeSkyBlue,
            waxColor = Color(0xFFC7D3DC),
            previewImage = "https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=800&q=80"
        ),
        PortfolioCase(
            id = "case_4",
            coupleNames = "Francisca & Salvador",
            weddingDate = "Setembro de 2025",
            location = "Herdade dos Salgados, Comporta",
            style = "Minimalismo Linho & Terracota",
            metric = "Mais de 1.200 visualizações do convite",
            quote = "A música que a Tatiana integrou emocionou os nossos pais. Ficou uma recordação digital para sempre.",
            envelopeColor = EnvelopeWarmBeige,
            waxColor = Color(0xFFA68766),
            previewImage = "https://images.unsplash.com/photo-1522673607200-164d1b6ce486?auto=format&fit=crop&w=800&q=80"
        )
    )

    // ========================================================================
    // DEPOIMENTOS DE CLIENTES (CARROSSEL)
    // ========================================================================
    val testimonials = listOf(
        TestimonialItem(
            id = "test_1",
            coupleNames = "Catarina & Diogo",
            location = "Sintra, Portugal",
            weddingDate = "Casaram em Julho de 2025",
            rating = 5,
            quote = "A reação dos convidados foi indescritível! Recebemos dezenas de mensagens a elogiar a ideia do selo de cera que racha e abre. Mais de 90% das presenças foram confirmadas em 48h.",
            avatarInitials = "C & D"
        ),
        TestimonialItem(
            id = "test_2",
            coupleNames = "Leonor & Afonso",
            location = "Cascais, Portugal",
            weddingDate = "Casaram em Setembro de 2025",
            rating = 5,
            quote = "A Tatiana foi de uma delicadeza e rapidez impressionantes. Alterámos a hora do cocktail uma semana antes e bastou atualizar o link — ninguém ficou desinformado. Valeu cada cêntimo!",
            avatarInitials = "L & A"
        ),
        TestimonialItem(
            id = "test_3",
            coupleNames = "Yara & Mauro",
            location = "Maputo / Luanda",
            weddingDate = "Casaram em Outubro de 2025",
            rating = 5,
            quote = "Tivemos convidados em três países diferentes. O seletor de mapa, a contagem regressiva e a compatibilidade perfeita em qualquer iPhone ou Android tornaram o processo super elegante.",
            avatarInitials = "Y & M"
        ),
        TestimonialItem(
            id = "test_4",
            coupleNames = "Marta & Tiago",
            location = "Porto / Douro",
            weddingDate = "Casaram em Junho de 2025",
            rating = 5,
            quote = "Optámos pelo Template 100% Personalizado e o resultado superou todas as expectativas. Parecia uma aplicação de luxo criada à nossa medida. Recomendo de olhos fechados!",
            avatarInitials = "M & T"
        )
    )

    // ========================================================================
    // TABELA COMPARADORA DE PLANOS
    // ========================================================================
    val planComparisons = listOf(
        PlanComparisonItem("Envelope 3D Interativo", "✓ Incluído", "✓ Incluído", "✓ Incluído"),
        PlanComparisonItem("Selo de Cera Animado", "Modelos fixos", "✓ Escolha da cor", "✓ Monograma sob medida"),
        PlanComparisonItem("Troca de Nomes, Data & Local", "✓ Sim", "✓ Sim", "✓ Sim"),
        PlanComparisonItem("Galeria de Fotos do Casal", "Até 2 fotos", "Até 6 fotos", "Fotos ilimitadas"),
        PlanComparisonItem("RSVP (Confirmação de Presença)", "Básico (Sim/Não)", "✓ Com restrições e notas", "✓ Notificação imediata"),
        PlanComparisonItem("Mapa Interativo (GPS / Waze)", "✓ Google Maps", "✓ Google Maps + Waze", "✓ Múltiplos locais"),
        PlanComparisonItem("Contador Regressivo em Tempo Real", "✓ Sim", "✓ Sim", "✓ Sim"),
        PlanComparisonItem("Música de Fundo Personalizada", "—", "✓ Incluída", "✓ Incluída com áudio HD"),
        PlanComparisonItem("Dress Code & Cronograma", "—", "✓ Incluído", "✓ Layout editorial"),
        PlanComparisonItem("Prazo de Entrega", "24h a 48h", "48h a 72h", "5 a 7 dias úteis", isHighlighted = true),
        PlanComparisonItem("Alterações de Texto Gratuitas", "1 revisão", "Revisões ilimitadas", "Suporte contínuo"),
        PlanComparisonItem("Domínio Próprio Exclusivo", "—", "—", "✓ Opcional (Link VIP)")
    )

    // ========================================================================
    // ARTIGOS DO BLOG / INSPIRAÇÃO PARA NOIVOS (SEO & TRÁFEGO)
    // ========================================================================
    val blogPosts = listOf(
        BlogPostItem(
            id = "blog_1",
            title = "Como definir o Dress Code sem constranger os convidados",
            excerpt = "Descubra termos elegantes como 'Passeio Completo' e 'Boho Chic' e saiba como pedir gentilmente que evitem tons brancos.",
            category = "Etiqueta & Dicas",
            readTime = "4 min de leitura",
            date = "15 Setembro 2025",
            imageUrl = "https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=800&q=80",
            fullContent = "Definir o Dress Code é essencial para que todos os convidados se sintam confortáveis e integrados no ambiente do casamento.\n\n1. Seja claro e específico: Em vez de termos vagos, utilize descrições claras acompanhadas de uma pequena frase no convite digital.\n2. Cores proibidas e recomendadas: É perfeitamente aceitável e elegante incluir uma nota delicada informando que o branco e marfim pertencem à noiva.\n3. Calçado e terreno: Se o casamento for na praia ou relva, avise sobre o tipo de calçado mais indicado."
        ),
        BlogPostItem(
            id = "blog_2",
            title = "Convite digital vs Convite em papel: a poupança real e sustentabilidade",
            excerpt = "Analisamos os custos de impressão, caligrafia, correios e o impacto ambiental. Veja quanto pode poupar no orçamento global.",
            category = "Planeamento",
            readTime = "5 min de leitura",
            date = "02 Outubro 2025",
            imageUrl = "https://images.unsplash.com/photo-1511285560929-80b456fea0bc?auto=format&fit=crop&w=800&q=80",
            fullContent = "Em média, um casal gasta entre 400€ a 1.200€ em convites de papel, envelopes especiais, selos de cera manuais e despesas de envio pelos correios.\n\nCom o convite digital interativo TATIANA, o investimento é único, sem limites de convidados, com confirmação de presença automática e zero desperdício de papel, aliando sofisticação moderna à consciência ambiental."
        ),
        BlogPostItem(
            id = "blog_3",
            title = "As 10 Músicas mais pedidas para a entrada do casal e corte do bolo",
            excerpt = "Da música clássica instrumental aos sucessos acústicos contemporâneos: playlist recomendada para tocar no vosso convite.",
            category = "Música & Ambiente",
            readTime = "3 min de leitura",
            date = "20 Outubro 2025",
            imageUrl = "https://images.unsplash.com/photo-1583939003579-730e3918a45a?auto=format&fit=crop&w=800&q=80",
            fullContent = "A música tem o poder de despertar memórias e emoções imediatas. Quando os convidados tocam no selo e ouvem a melodia do vosso amor, a experiência torna-se inesquecível.\n\nSugestões clássicas: 'Can't Help Falling in Love' (acústico), 'A Thousand Years' (violoncelo), e composições orquestrais de Ludovico Einaudi."
        )
    )

    // Perguntas Frequentes (FAQ)
    val faqList = listOf(
        FaqItem(
            id = "faq_1",
            question = "Como os convidados acedem ao convite?",
            answer = "É muito simples e inovador! O convidado recebe um link exclusivo (ex: tatianaconvites.com/ana-e-carlos) por WhatsApp, SMS ou e-mail. Ao abrir em qualquer telemóvel ou computador, depara-se com um envelope 3D realista com selo de cera. Ao tocar no selo, o envelope abre com uma animação elegante e revela todos os detalhes."
        ),
        FaqItem(
            id = "faq_2",
            question = "Como funciona a confirmação de presença (RSVP)?",
            answer = "Dentro do convite existe um botão interativo de RSVP onde os convidados preenchem o seu nome, confirmam se vão ou não, indicam restrições alimentares (vegetariano, alergias) e número de acompanhantes. Todas as respostas chegam de forma organizada diretamente ao vosso WhatsApp ou numa folha de cálculo."
        ),
        FaqItem(
            id = "faq_3",
            question = "Qual é o prazo de entrega do convite?",
            answer = "Para os modelos com design predefinido, entregamos a primeira versão em 48 a 72 horas. Para os modelos totalmente personalizados ou templates sob medida, o prazo típico é de 5 a 7 dias úteis após a receção de todas as fotos e informações."
        ),
        FaqItem(
            id = "faq_4",
            question = "Como funciona o pagamento (50% ou 100%)?",
            answer = "Para vos dar total tranquilidade, podem pagar 50% de sinal para dar início à criação e os restantes 50% apenas após verem o vosso convite pronto e aprovarem todos os detalhes. Aceitamos pagamentos via M-Pesa, E-Mola, Transferência Bancária (IBAN), MB WAY e PIX."
        ),
        FaqItem(
            id = "faq_5",
            question = "E se precisarmos de alterar a data, hora ou local mais tarde?",
            answer = "Ao contrário do convite em papel (que teria de ser reimpresso do zero), no convite digital qualquer alteração de última hora é atualizada instantaneamente no mesmo link, sem custos adicionais nos primeiros 3 meses!"
        ),
        FaqItem(
            id = "faq_6",
            question = "É possível incluir música de fundo e contador de dias?",
            answer = "Sim! Todos os nossos convites incluem contador decrescente em tempo real e podem incluir uma música especial escolhida por vocês que toca suavemente ao abrir o envelope (com controle de pausa para o convidado)."
        )
    )

    // Países com serviço ativo
    val availableCountries = listOf(
        AvailableCountry("MZ", "Moçambique", "🇲🇿"),
        AvailableCountry("PT", "Portugal", "🇵🇹"),
        AvailableCountry("BR", "Brasil", "🇧🇷"),
        AvailableCountry("AO", "Angola", "🇦🇴"),
        AvailableCountry("ES", "Espanha", "🇪🇸"),
        AvailableCountry("CV", "Cabo Verde", "🇨🇻"),
        AvailableCountry("UK", "Reino Unido", "🇬🇧"),
        AvailableCountry("US", "Estados Unidos", "🇺🇸")
    )
}

data class HeroEnvelopeItem(
    val id: String,
    val colorName: String,
    val envelopeColor: Color,
    val waxSealColor: Color,
    val sampleNames: String
)
