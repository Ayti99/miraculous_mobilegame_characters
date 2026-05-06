package com.lasalleaytana.p2_characters_nd_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lasalleaytana.p2_characters_nd_navigation.ui.theme.P2_Characters_nd_NavigationTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

// FONTS
val lobster_mlb_font = FontFamily(
    Font(R.font.lobster_mlb_font)
)
val poppins_semibold_mlb_font = FontFamily(
    Font(R.font.poppins_semibold_mlb_font)
)

// ITEM: DATA
data class ItemData(
    val id: Int,
    val imageCard: Int,
    val imageRes: Int,
    val title: String,
    val description1: String,
    val imageJewel: Int,
    val jewelDes: String,
    val imageAbility: List<Int>,
    val description2: String,
    val imageCivilian: Int,
    val civilianDes: String,
    val kwamiImage: List<Int>,
    val kwamiDes: String,
    val route: String,
    val backgroundRes: Int,
    val buttonColor: Color,
    val cardColor: List<Color>,
    val imageHolder: Int
)

// ITEM: LIST
val itemList = listOf(
    // 1-LADYBUG
    ItemData(
        id = 1,
        imageCard = R.drawable.ladybug_profile_card,
        imageRes = R.drawable.ladybug_picture,
        title = "Ladybug",
        description1 = "Miraculous: EARRINGS\n"
                + "Transformation: SPOTS ON\n"
                + "Ability: LUCKY CHARM\n"
                + "Power: CREATION\n"
                + "Weapon: YO-YO\n"
                + "Kwami: TIKKI",
        imageJewel = R.drawable.ladybug_jewel,
        jewelDes = "A pair of black circular earrings with a silver lining inhabited by the kwami Tikki, triggered by \"Lucky Charm\", these earrings grant the power of creation.",
        imageAbility = listOf(R.drawable.ladybug_lucky_charm_gif),
        description2 = "Ladybug's Lucky Charms are objects that Ladybug creates whenever she uses Lucky Charm to help her achieve her objective. \n" + "Ladybug can also throw that object skywards to activate Miraculous Ladybug, which can repair all the damage.",
        imageCivilian = R.drawable.marinette_picture,
        civilianDes = "Marinette Dupain-Cheng, passionate fashion design student and baker’s daughter. \n" + "Although highly optimistic towards others constantly doubts herself, juggling high school life and duty as guardian of the miraculous.",
        kwamiImage = listOf(R.drawable.kwami_tikki),
        kwamiDes = "Tikki, Kwami of Creation. Connected to the Ladybug Miraculous, she is calm, kind, wise, and nurturing, acting as a loyal advisor to her wielders. Provides the power of creation.",
        route = "character1-ladybug",
        backgroundRes = R.drawable.background_ladybug,
        buttonColor = Color.Red.copy(alpha = 0.5f),
        cardColor = listOf(Color.Red, Color.White),
        imageHolder = R.drawable.marinette_holder_picture
    ),
    // 2-CHAT NOIR
    ItemData(
        id = 2,
        imageCard = R.drawable.chat_noir_profile_card,
        imageRes = R.drawable.chat_noir_picture,
        title = "Chat Noir",
        description1 = "Miraculous: RING\n"
                + "Transformation: CLAWS OUT\n"
                + "Ability: CATACLYSM\n"
                + "Power: DESTRUCTION\n"
                + "Weapon: STAFF\n"
                + "Kwami: PLAGG",
        imageJewel = R.drawable.chat_noir_jewel,
        jewelDes = "A ring inhabited by the Kwami Plagg, triggered by \"Cataclysm\", the ring grants the power of destruction.",
        imageAbility = listOf(R.drawable.chat_noir_cataclysm_gif),
        description2 = "Cataclysm is Cat Noir's signature, destructive superpower, allowing him to destroy, rust, or break any object or structure with a single touch by corrupting the molecular structure of whatever he touches.",
        imageCivilian = R.drawable.adrien_picture,
        civilianDes = "Adrien Agreste, a wealthy, kind-hearted student and fashion model son of the acclaimed fashion designer Gabriel Agreste. \n" + "While charming and popular, he often feels lonely due to his strict upbringing and seeks to live a normal life.",
        kwamiImage = listOf(R.drawable.kwami_plagg),
        kwamiDes = "Plagg, kwami of destruction. Connected to the Cat Miraculous, he is generally carefree, easygoing, and lazy. However, can be cool, calm, and supportive when necessary. Provides the power to destroy.",
        route = "character2-chat noir",
        backgroundRes = R.drawable.background_chat_noir,
        buttonColor = Color.Green.copy(alpha = 0.5f),
        cardColor = listOf(Color.Green, Color.White),
        imageHolder = R.drawable.adrien_holder_picture
    ),
    // 3-VESPERIA
    ItemData(
        id = 3,
        imageCard = R.drawable.vesperia_profile_card,
        imageRes = R.drawable.vesperia_picture,
        title = "Vesperia",
        description1 = "Miraculous: HAIR COMB\n"
                + "Transformation: BUZZ ON\n"
                + "Ability: VENOM\n"
                + "Power: INTUITION\n"
                + "Weapon: SPINNING TOP\n"
                + "Kwami: POLLEN",
        imageJewel = R.drawable.vesperia_jewel,
        jewelDes = "A hair comb jewel inhabited by the kwami Pollen, triggered by \"Venom\", the hair comb grants the power to paralyze targets.",
        imageAbility = listOf(R.drawable.vesperia_venom_gif),
        description2 = "Venom is Vesperia's signature, power to paralyze targets. It affects living beings, magical spirits, and even inorganic objects, leaving a glowing sting mark that vanishes when the effect overwears off.",
        imageCivilian = R.drawable.zoe_picture,
        civilianDes = "Zoé Lee, a kind-hearted teenage girl from New York half-sister of Chloé Bourgeois. \n" + "Initially struggled to fit in, mimicking others' personalities before finding her own identity.",
        kwamiImage = listOf(R.drawable.kwami_pollen),
        kwamiDes = "Pollen, kwami of Action. Connected to the Bee Miraculous, she is formal, loyal, and submissive, often addressing her holders as My Queen. Provides the power to paralyze opponents.",
        route = "character3-vesperia",
        backgroundRes = R.drawable.background_vesperia,
        buttonColor = Color.Yellow.copy(alpha = 0.5f),
        cardColor = listOf(Color.Yellow, Color.White),
        imageHolder = R.drawable.zoe_holder_picture
    ),
    // 4-VIPERION
    ItemData(
        id = 4,
        imageCard = R.drawable.viperion_profile_card,
        imageRes = R.drawable.viperion_picture,
        title = "Viperion",
        description1 = "Miraculous: OUROBOROS BRACELET\n"
                    + "Transformation: SCALES SLITHER\n"
                    + "Ability: SECOND CHANCE\n"
                    + "Power: INTUITION\n"
                    + "Weapon: LYRE\n"
                    + "Kwami: SASS",
        imageJewel = R.drawable.viperion_jewel,
        jewelDes = "An ouroboros bracelet inhabited by the kwami Sass, triggered by \"Second Chance\", the bracelet grants the power to mark a moment and reverse time to it.",
        imageAbility = listOf(R.drawable.viperion_second_chance_gif),
        description2 = "Second Chance is Viperion's signature, power to mark a moment in time and rewind to it instantly within a five-minute window.",
        imageCivilian = R.drawable.luka_picture,
        civilianDes = "Luka Couffaine, a kind, calm, and musical teenager who serves as Juleka’s older twin brother. \n" + "Known for being empathetic, he \"reads\" people's inner melodies and often acts as a reliable confidant.",
        kwamiImage = listOf(R.drawable.kwami_sass),
        kwamiDes = "Sass, Kwami of Intuition. Connected to the Snake Miraculous, he is wise, insightful and charming demeanor. Provides the power to mark a moment and reverse time to it.",
        route = "character4-viperion",
        backgroundRes = R.drawable.background_viperion,
        buttonColor = Color.Cyan.copy(alpha = 0.5f),
        cardColor = listOf(Color.Cyan, Color.White),
        imageHolder = R.drawable.luka_holder_picture
    ),
    // 5-RYUKO
    ItemData(
        id = 5,
        imageCard = R.drawable.ryuko_profile_card,
        imageRes = R.drawable.ryuko_picture,
        title = "Ryuko",
        description1 = "Miraculous: CHOKER\n"
                + "Transformation: BRING THE STORM\n"
                + "Ability: WATER, WIND & LIGHTNING\n"
                + "Power: PERFECTION\n"
                + "Weapon: SWORD\n"
                + "Kwami: LONGG",
        imageJewel = R.drawable.ryuko_jewel,
        jewelDes = "A choker inhabited by the kwami Longg, triggered by \"Water Dragon\", \"Wind Dragon\" & \"Lightning Dragon\", grants the power to transform into those elemnts or control them.",
        imageAbility = listOf(R.drawable.ryuko_waterdragon_gif),
        description2 = "Water Dragon, Wind Dragon & Lightning Dragon are Ryuko's signature, power to control the elements of wind, water, and lightning. She can also transform into these elements often using her sword to enhance attacks.",
        imageCivilian = R.drawable.kagami_picture,
        civilianDes = "Kagami Tsurugi, a talented Japanese fencer, she is the daughter of Tomoe Tsurugi, a world-class fencing champion and tech mogul. \n" + "She focuses to gain independence from her protective mother and struggles to make friends.",
        kwamiImage = listOf(R.drawable.kwami_longg),
        kwamiDes = "Longg, kwami of Perfection. Connected to the Dragon Miraculous, he is associated with perfection, balance and harmony. Provides the power to transform & harness elements of wind, water and lightning.",
        route = "character5-ryuko",
        backgroundRes = R.drawable.background_ryuko,
        buttonColor = Color.Red.copy(alpha = 0.5f),
        cardColor = listOf(Color.Red, Color.White),
        imageHolder = R.drawable.kagami_holder_picture
    ),
    // 6-BUNNYX
    ItemData(
        id = 6,
        imageCard = R.drawable.bunnyx_profile_card,
        imageRes = R.drawable.bunnyx_picture,
        title = "Bunnyx",
        description1 = "Miraculous: POCKET WATCH\n"
                + "Transformation: CLOCKWISE\n"
                + "Ability: BURROW\n"
                + "Power: EVOLUTION\n"
                + "Weapon: UMBRELLA\n"
                + "Kwami: FLUFF",
        imageJewel = R.drawable.bunnyx_jewel,
        jewelDes = "A pocket watch inhabited by the kwami Fluff, triggered by \"Burrow\", the pocket watch grants the power for time travel & manipulation of time.",
        imageAbility = listOf(R.drawable.bunnyx_burrow_gif),
        description2 = "Burrow is Bunnyx's signature, which lets her create portals through a pocket dimension to travel anywhere in time. She can view and visit the past, present, or future.",
        imageCivilian = R.drawable.alix_picture,
        civilianDes = "Alix Kubdel, a tomboyish, roller-skating student known for her confident, bold personality and her crucial, specialized role as the protector of time. \n" + "As a future adult, she is more strategic, confident, and takes her duty as the guardian of time seriously.",
        kwamiImage = listOf(R.drawable.kwami_fluff),
        kwamiDes = "Fluff, kwami of Evolution. Connected to the Rabbit Miraculous, she is energetic, curious and easily scattered due to her constant travels through time. Provides the power to travel through time & space.",
        route = "character6-bunnyx",
        backgroundRes = R.drawable.background_bunnyx,
        buttonColor = Color.Blue.copy(alpha = 0.5f),
        cardColor = listOf(Color.Blue, Color.White),
        imageHolder = R.drawable.alix_holder_picture
    ),
    // 7-LADY DRAGON
    ItemData(
        id = 7,
        imageCard = R.drawable.lady_dragon_profile_card,
        imageRes = R.drawable.lady_dragon_picture,
        title = "LadyDragon",
        description1 = "Miraculous: MEDALLION\n"
                + "Transformation: 8 RENLING'S NAMES\n"
                + "Ability: SHAPESHIFT\n"
                + "Power: 8 RENREN ANIMALS\n"
                + "Weapon: PRODIGIOUS\n"
                + "Renlings: 8 SPIRITS OF JUSTICE",
        imageJewel = R.drawable.lady_dragon_jewel,
        jewelDes = "An ancient powerful medallion pendant inhabited by eight \"Renlings\" magical creatures, triggered by any of the Renling's name: Long, Xiong, Ying, She, Hou, Hu, Ma & Tang. The medallion grants the power to shapeshift into dragon, bear, eagle, snake, monkey, tiger, horse & mantis forms based on virtues.",
        imageAbility = listOf(
            R.drawable.lady_dragon_long_gif, //-1: DRAGON (LONG)
            R.drawable.lady_dragon_xiong_gif, //-2: BEAR (XIONG)
            R.drawable.lady_dragon_ying_gif, //-3: EAGLE (YING)
            R.drawable.lady_dragon_she_gif, //-4: SNAKE (SHE)
            R.drawable.lady_dragon_hou_gif, //-5: MONKEY (HOU)
            R.drawable.lady_dragon_hu_gif, //-6: TIGER (HU)
            R.drawable.lady_dragon_ma_gif, //-7: HORSE (MA)
            R.drawable.lady_dragon_tang_gif //-8: MANTIS (TANG)
        ),
        description2 = "Shapeshifting is Lady Dragon's signature, power to transform into eight distinct Renren animals. As a dragon, she controls the elements of water, wind, & lightning.",
        imageCivilian = R.drawable.fei_picture,
        civilianDes = "Fei Wu, a teenage martial artist adoptive daughter of Wu Shifu and protector of the Sacred Cave a secure vault for the Prodigious. \n" + "She is highly skilled in kung fu, quick-tempered but ultimately compassionate. Having inherited the responsibility of being the guardian of the Prodigious Fei's primary goal is to protect it.",
        kwamiImage = listOf(
            R.drawable.kwami_renling_long_long, //1-: LONG-LONG
            R.drawable.kwami_renling_xiong_xiong,
            R.drawable.kwami_renling_ying_ying,
            R.drawable.kwami_renling_she_she,
            R.drawable.kwami_renling_hou_hou,
            R.drawable.kwami_renling_hu_hu,
            R.drawable.kwami_renling_ma_ma,
            R.drawable.kwami_renling_tang_tang
        ),
        kwamiDes = "Eight divine spirit-like beings requiring user to embody certain values for them to be used.\n"
                + "\n"
                + "▶LONG-LONG ➞ DRAGON: controls elements of water, wind & lightning, requires value of justice.\n"
                + "\n"
                + "▶XIONG-XIONG ➞ BEAR: requires value of calmness.\n"
                + "\n"
                + "▶YING-YING ➞ EAGLE: requires value of confidence.\n"
                + "\n"
                + "▶SHE-SHE ➞ SNAKE: requires value of courage.\n"
                + "\n"
                + "▶HOU-HOU ➞ MONKEY: requires value of compassion.\n"
                + "\n"
                + "▶HU-HU ➞ TIGER: requires value of discipline.\n"
                + "\n"
                + "▶MA-MA ➞ HORSE: requires value of honor.\n"
                + "\n"
                + "▶TANG-TANG ➞ MANTIS: requires value of patience."
                + "\n",
        route = "character7-lady dragon",
        backgroundRes = R.drawable.background_lady_dragon,
        buttonColor = Color(0xFFFF69B4).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFFF69B4), Color.White),
        imageHolder = R.drawable.fei_holder_picture
    ),
    // 8-PURPLE TIGRESS
    ItemData(
        id = 8,
        imageCard = R.drawable.purple_tigress_profile_card,
        imageRes = R.drawable.purple_tigress_picture,
        title = "Purple Tigress",
        description1 = "Miraculous: RING BRACELET\n"
                + "Transformation: STRIPES ON\n"
                + "Ability: CLOUT\n"
                + "Power: ELATION\n"
                + "Weapon: TRIPLE BALL BOLAS\n"
                + "Kwami: ROAAR",
        imageJewel = R.drawable.purple_tigress_jewel,
        jewelDes = "A ring bracelet inhabited by the kwami Roaar, triggered by \"Clout\", the ring bracelet grants the power to deliver a massive high-impact punch.",
        imageAbility = listOf(R.drawable.purple_tigress_clout_gif),
        description2 = "Clout is Purple Tigress signature, allows her to deliver a massive high-impact that sends targets flying or creates immense explosions.",
        imageCivilian = R.drawable.juleka_picture,
        civilianDes = "Juleka Couffaine, a major shy person known as Luka's twin sister and Rose Lavilllant's girlfriend. \n" + "Loves scary things and cares deeply for her friends, she is interest in becoming a mortician.",
        kwamiImage = listOf(R.drawable.kwami_roaar),
        kwamiDes = "Roaar, kwami of Elation. Connected to the Tiger Miraculous, characterized as fierce, confident and responsible. Provides the power of immense strength allowing to deliver a powerful long-range punch.",
        route = "character8-purple tigress",
        backgroundRes = R.drawable.background_purple_tigress,
        buttonColor = Color.Magenta.copy(alpha = 0.5f),
        cardColor = listOf(Color.Magenta, Color.White),
        imageHolder = R.drawable.juleka_holder_picture
    ),
    // 9-RENA ROUGE
    ItemData(
        id = 9,
        imageCard = R.drawable.rena_rouge_profile_card,
        imageRes = R.drawable.rena_rouge_picture,
        title = "Rena Rouge",
        description1 = "Miraculous: NECKLACE\n"
                + "Transformation: LET'S POUNCE\n"
                + "Ability: MIRAGE\n"
                + "Power: ILLUSION\n"
                + "Weapon: FLUTE\n"
                + "Kwami: TRIXX",
        imageJewel = R.drawable.rena_rouge_jewel,
        jewelDes = "A necklace inhabited by the kwami Trixx, triggered by \"Mirage\", the necklace grants the power to create highly realistic illusions.",
        imageAbility = listOf(R.drawable.rena_rouge_mirage_gif),
        description2 = "Mirage is Rena Rouge's signature, power that allows to create hyper-realistic, customizable visual and audio illusions, but illusions disappear if touched.",
        imageCivilian = R.drawable.alya_picture,
        civilianDes = "Alya Césaire, an aspiring reporter, runs the Ladyblog, she is Marinette's best friend and Nino Lahiffe's girlfriend. \n" + "She is courageous, energetic and loyal, often seen as protective, supportive friend despite sometimes being too reckless in her pursuit of stories for her blog.",
        kwamiImage = listOf(R.drawable.kwami_trixx),
        kwamiDes = "Trixx, kwami of Illusion. Connected to the Fox Miraculous, he is a friendly, wise and encouraging, often guides his owners with calm advice. Provides the power to create complex illusions that are convincing enough to trick those who sees them.",
        route = "character9-rena rouge",
        backgroundRes = R.drawable.background_rena_rouge,
        buttonColor = Color(0xFFFFA500).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFFFA500), Color.White),
        imageHolder = R.drawable.alya_holder_picture
    ),
    // 10-CARAPACE
    ItemData(
        id = 10,
        imageCard = R.drawable.carapace_profile_card,
        imageRes = R.drawable.carapace_picture,
        title = "Carapace",
        description1 = "Miraculous: BRACELET\n"
                + "Transformation: SHELL ON\n"
                + "Ability: SHELL-TER\n"
                + "Power: PROTECTION\n"
                + "Weapon: SHIELD\n"
                + "Kwami: WAYZZ",
        imageJewel = R.drawable.carapace_jewel,
        jewelDes = "A bracelet inhabited by the kwami Wayzz, triggered by \"Shell-ter\", the bracelet grants the power create durable forcefields.",
        imageAbility = listOf(R.drawable.carapace_shellter_gif),
        description2 = "Shell-ter is Carapace's signature, creates a massive, durable, green spherical barrier with hexagonal pattern to protect himself and allies from attacks.",
        imageCivilian = R.drawable.nino_picture,
        civilianDes = "Nino Lahiffe, a talented DJ and laid-back student, known as Adrien's best friend and Alya's boyfriend. \n" + "He is loyal, fun-loving and protective friend, passionate about music and film directing.",
        kwamiImage = listOf(R.drawable.kwami_wayzz),
        kwamiDes = "Wayzz, kwami of Protection. Connected to the Turtle Miraculous, he is compassionate, wise, helpful and often worries when the miraculous are misused. Provides the power to create protective barriers.",
        route = "character10-carapace",
        backgroundRes = R.drawable.background_carapace,
        buttonColor = Color.Green.copy(alpha = 0.5f),
        cardColor = listOf(Color.Green, Color.White),
        imageHolder = R.drawable.nino_holder_picture
    ),
    // 11-PEGASUS
    ItemData(
        id = 11,
        imageCard = R.drawable.pegasus_profile_card,
        imageRes = R.drawable.pegasus_picture,
        title = "Pegasus",
        description1 = "Miraculous: GLASSES\n"
                + "Transformation: FULL GALLOP\n"
                + "Ability: VOYAGE\n"
                + "Power: MIGRATION\n"
                + "Weapon: HORSESHOE SHAPED BOOMERANG\n"
                + "Kwami: KAALKI",
        imageJewel = R.drawable.pegasus_jewel,
        jewelDes = "A pair of glasses inhabited by the kwami Kaalki, triggered by \"Voyage\", the glasses grant the power to create portals allowing to teleport anywhere instantly.",
        imageAbility = listOf(R.drawable.pegasus_voyage_gif),
        description2 = "Voyage is Pegasus signature, using his horseshoe weapon he can open portals to any destination.",
        imageCivilian = R.drawable.max_picture,
        civilianDes = "Max Kanté, an intelligent, tech-savvy student, he is son of Claudie Kanté a famous astronaut and close friend with Kim. \n" + "He is logical, analytical and thoughtful thinker who excels in technology, gaming and robotics.",
        kwamiImage = listOf(R.drawable.kwami_kaalki),
        kwamiDes = "Kaalki, kwami of Teleportation. Connected to the Horse Miraculous, she is refined, polite and somewhat elitist, preferred to be called a \"noble steed\" rather than a horse and often demands high standards of her wielder. Provides the power to teleport to any location instantly.",
        route = "character11-pegasus",
        backgroundRes = R.drawable.background_pegasus,
        buttonColor = Color(0xFFA52A2A).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFA52A2A), Color.White),
        imageHolder = R.drawable.max_holder_picture
    ),
    // 12-ROOSTER BOLD
    ItemData(
        id = 12,
        imageCard = R.drawable.rooster_bold_profile_card,
        imageRes = R.drawable.rooster_bold_picture,
        title = "Rooster Bold",
        description1 = "Miraculous: THUMB ARMOR RING\n"
                + "Transformation: SUNRISE\n"
                + "Ability: SUBLIMATION\n"
                + "Power: PRETENSION\n"
                + "Weapon: FOUNTAIN PEN\n"
                + "Kwami: ORIKKO",
        imageJewel = R.drawable.rooster_bold_jewel,
        jewelDes = "A thumb ring armor inhabited by the kwami Orikko, triggered by \"Sublimation\", the thumb ring armor grants the power to acquire nearly any ability the user desires within limitations.",
        imageAbility = listOf(R.drawable.rooster_bold_sublimation_gif),
        description2 = "Sublimation is Rooster Bold's signature, allows him to grant himself any specific superpower he desires such as flight, invisibility or super strength.",
        imageCivilian = R.drawable.marc_picture,
        civilianDes = "Marc Anciel, a shy creative student, talented writer who collaborates with Nathaniel as well as being his boyfriend. \n" + "He is incredibly bashful, nervous and self-conscious about his writing, yet he possesses a  vivid imagination and kind heart.",
        kwamiImage = listOf(R.drawable.kwami_orikko),
        kwamiDes = "Orikko, kwami of Pretension. Connected to the Rooster Miraculous, he is prideful and gets easily offended, he will refuse to cooperate if insulted. Provides the power to grant any single unique superpower chosen, excluding those belonging to other kwamis.",
        route = "character12-rooster bold",
        backgroundRes = R.drawable.background_rooster_bold,
        buttonColor = Color(0xFFFF4500).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFFF4500), Color.White),
        imageHolder = R.drawable.marc_holder_picture
    ),
    // 13-PIGELLA
    ItemData(
        id = 13,
        imageCard = R.drawable.pigella_profile_card,
        imageRes = R.drawable.pigella_picture,
        title = "Pigella",
        description1 = "Miraculous: ANKLE BRACELET\n"
                + "Transformation: REJOICE\n"
                + "Ability: GIFT\n"
                + "Power: JUBILATION\n"
                + "Weapon: TAMBOURINE\n"
                + "Kwami: DAIZZI",
        imageJewel = R.drawable.pigella_jewel,
        jewelDes = "A pearl anklet inhabited by the kwami Daizzi, triggered by \"Gift\", the pearl anklet grants the power to summon a small box that reveals an orb showing the target their greatest joy or desire.",
        imageAbility = listOf(R.drawable.pigella_gift_gif),
        description2 = "Gift is Pigella's signature, allows her to create a magical box revealing a target's deepest desire, trapping them in a happy dream to calm, disarm, or subdue them.",
        imageCivilian = R.drawable.rose_picture,
        civilianDes = "Rose Lavillant, a cheerful, optimistic and naive student. She is Juleka's girlfriend and singer of the band Kitty Section. \\n\" + \"She is incredibly sweet, kind-hearted and often focusing on positivity, sometimes can be overly trusting.",
        kwamiImage = listOf(R.drawable.kwami_daizzi),
        kwamiDes = "Daizzi, kwami of Jubilation. Connected to the Pig Miraculous, she is highly affectionate, cheerful and loves spreading happiness. Provides the power to reveal a target's deepest desire.",
        route = "character13-pigella",
        backgroundRes = R.drawable.background_pigella,
        buttonColor = Color(0xFFFF69B4).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFFF69B4), Color.White),
        imageHolder = R.drawable.rose_holder_picture
    ),
    // 14-CAPRIKID
    ItemData(
        id = 14,
        imageCard = R.drawable.caprikid_profile_card,
        imageRes = R.drawable.caprikid_picture,
        title = "Caprikid",
        description1 = "Miraculous: DIADEM\n"
                + "Transformation: BLEAT IT\n"
                + "Ability: GENESIS\n"
                + "Power: PASSION\n"
                + "Weapon: PAINT BRUSH\n"
                + "Kwami: ZIGGI",
        imageJewel = R.drawable.caprikid_jewel,
        jewelDes = "A pair of horn-shaped clips inhabited by the kwami Ziggi, triggered by \"Genesis\", the pair of horn-shaped clips grant the power to create any non-magical object desired.",
        imageAbility = listOf(R.drawable.caprikid_genesis_gif),
        description2 = "Genesis is Caprikid's signature, he waves his paintbrush staff to bring objects to life, which are limited in number or duration.",
        imageCivilian = R.drawable.nathaniel_picture,
        civilianDes = "Nathaniel Kurtzberg, a talented, introverted, imaginative and artistic student who often draws in his sketchbook. \\n\" + \"He is Marc's boyfriend an creative partner co-creating comic books.",
        kwamiImage = listOf(R.drawable.kwami_ziggi),
        kwamiDes = "Ziggi, kwami of Passion. Connected to the Goat Miraculous, he is kind, emotional and helpful, but tends to panic under stress and gets upset if people are rude or don't say \"please\". Provides the power to enable the wearer to create any object.",
        route = "character14-caprikid",
        backgroundRes = R.drawable.background_caprikid,
        buttonColor = Color.Gray.copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFF00BFFF), Color.White),
        imageHolder = R.drawable.nathaniel_holder_picture
    ),
    // 15-MISS HOUND
    ItemData(
        id = 15,
        imageCard = R.drawable.miss_hound_profile_card,
        imageRes = R.drawable.miss_hound_picture,
        title = "Miss Hound",
        description1 = "Miraculous: TORC\n"
                + "Transformation: ON THE HUNT\n"
                + "Ability: FETCH\n"
                + "Power: ADORATION\n"
                + "Weapon: BALL\n"
                + "Kwami: BARKK",
        imageJewel = R.drawable.miss_hound_jewel,
        jewelDes = "A collar necklace (torc) inhabited by the kwami Barkk, triggered by \"Fetch\", the torc grants the power to retrieve any object touched by the wielder's thrown ball, regardless of distance.",
        imageAbility = listOf(R.drawable.miss_hound_fetch_gif),
        description2 = "Fetch is Miss Hound's signature, allows her to throw a ball to tag any object or person, which she can then summon back to her from anywhere.",
        imageCivilian = R.drawable.sabrina_picture,
        civilianDes = "Sabrina Raincomprix, initially meek, loyal and manipulative, she is later revealed to be hardworking, intelligent and deeply caring. \\n\" + \"She is portrayed as someone who is willing to do anything to maintain a friendship but eventually realizes her worth and breaks free from an abusive relationship to join the side of good.",
        kwamiImage = listOf(R.drawable.kwami_barkk),
        kwamiDes = "Barkk, kwami of Adoration. Connected to the Dog Miraculous, he is a fighter by nature, loyal and protective. Provides the power to teleport any touched object with their thrown ball into their possession.",
        route = "character15-miss hound",
        backgroundRes = R.drawable.background_miss_hound,
        buttonColor = Color.Yellow.copy(alpha = 0.5f),
        cardColor = listOf(Color.Yellow, Color.White),
        imageHolder = R.drawable.sabrina_holder_picture
    ),
    // 16-POLYMOUSE
    ItemData(
        id = 16,
        imageCard = R.drawable.polymouse_profile_card,
        imageRes = R.drawable.polymouse_picture,
        title = "Polymouse",
        description1 = "Miraculous: PENDANT NECKLACE\n"
                + "Transformation: GET SQUEAKY\n"
                + "Ability: MULTITUDE\n"
                + "Power: MULTIPLICATION\n"
                + "Weapon: JUMP ROPE\n"
                + "Kwami: MULLO",
        imageJewel = R.drawable.polymouse_jewel,
        jewelDes = "A pendant necklace inhabited by the kwami Mullo, triggered bu \"Multitude\", the pendant necklace grants the power to shrink and create multiple smaller clones of themselves.",
        imageAbility = listOf(R.drawable.polymouse_multitude_gif),
        description2 = "Multitude is Polymouse's signature, by jumping with her jump rope she creates numerous miniature clones of herself.",
        imageCivilian = R.drawable.mylene_picture,
        civilianDes = "Mylène Haprèle, initially shy and easily frightened she grows in courage and helps others. \\n\" + \"She is Ivan's girlfriend who calls her \"little mouse\" and she is supported by her friends in the class.",
        kwamiImage = listOf(R.drawable.kwami_mullo),
        kwamiDes = "Mullo, kwami of Multiplication. Connected to the Mouse Miraculous, she is mischievous and energetic, enjoys scaring others but is compassionate and supportive of her holders. Provides the power of allowing it's holders to shrink down and divide into multiple miniature versions of themselves.",
        route = "character16-polymouse",
        backgroundRes = R.drawable.background_polymouse,
        buttonColor = Color(0xFFFFC0CB).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFFFC0CB), Color.White),
        imageHolder = R.drawable.mylene_holder_picture
    ),
    // 17-MINOTAUROX
    ItemData(
        id = 17,
        imageCard = R.drawable.minotaurox_profile_card,
        imageRes = R.drawable.minotaurox_picture,
        title = "Minotaurox",
        description1 = "Miraculous: NOSE RING\n"
                + "Transformation: MAKE WAY\n"
                + "Ability: RESISTANCE\n"
                + "Power: DETERMINATION\n"
                + "Weapon: MALLET\n"
                + "Kwami: STOMPP",
        imageJewel = R.drawable.minotaurox_jewel,
        jewelDes = "A nose ring inhabited by the kwami Stompp, triggered by \"Resistance\", the nose ring grants the powers of superhuman strength and endurance, making the wielder immune to other superpowers.",
        imageAbility = listOf(R.drawable.minotaurox_resistance_gif),
        description2 = "Resistance is Minotaurox's signature, makes him completely immune to any superpowers or magical abilities used against him, offering superior, invulnerable protection against threats.",
        imageCivilian = R.drawable.ivan_picture,
        civilianDes = "Ivan Bruel, shy, sensitive and fiercly protective, known of being Mylène's boyfriend he is specially protective over her. \\n\" + \"He often acts as a source of quiet strength who evolved from a victim of bullying into a loyal ally.",
        kwamiImage = listOf(R.drawable.kwami_stompp),
        kwamiDes = "Stompp, kwami of Determination. Connected to the Bull Miraculous, he is generally stubborn straightforward, reflecting the determination aspect of his power. Provides the power to allow the user to become invincible against outside influences.",
        route = "character17-minotaurox",
        backgroundRes = R.drawable.background_minotaurox,
        buttonColor = Color.Blue.copy(alpha = 0.5f),
        cardColor = listOf(Color.Blue, Color.White),
        imageHolder = R.drawable.ivan_holder_picture
    ),
    // 18-KING MONKEY
    ItemData(
        id = 18,
        imageCard = R.drawable.king_monkey_profile_card,
        imageRes = R.drawable.king_monkey_picture,
        title = "King Monkey",
        description1 = "Miraculous: DIADEM\n"
                + "Transformation: BLEAT IT\n"
                + "Ability: UPROAR\n"
                + "Power: PASSION\n"
                + "Weapon: PAINT BRUSH\n"
                + "Kwami: XUPPU",
        imageJewel = R.drawable.king_monkey_jewel,
        jewelDes = "A headband inhabited by the kwami Xuppu, triggered by  \"Uproar\", the headband grants the power to crate a toy-like object that causes a target's powers to malfunction causing chaotic and comedic disruption.",
        imageAbility = listOf(R.drawable.king_monkey_uproar_gif),
        description2 = "Uproar is King Monkey's signature, allows him to disrupt and scramble a target's superpowers, causing them to go haywire by summoning a chaotic toy item.",
        imageCivilian = R.drawable.kim_picture,
        civilianDes = "Kim Lê Chiến, a competitive athletic student, initially a conceited prank-lover, he matures into a loyal friend. \\n\" + \"He is heavily into sports, particularly swimming, he is Ondine's boyfriend and close friends with Max and Alix.",
        kwamiImage = listOf(R.drawable.kwami_xuppu),
        kwamiDes = "Xuppu, kwami of Derision. Connected to the Monkey Miraculous, he is cheerful, cheeky, talkative and immature; he enjoys making faces and mocking others even wielders. Provides the power to create a toy that when hitting a target cause it's abilities to go haywire.",
        route = "character18-king monkey",
        backgroundRes = R.drawable.background_king_monkey,
        buttonColor = Color(0xFFFF5722).copy(alpha = 0.5f),
        cardColor = listOf(Color(0xFFFF5722), Color.White),
        imageHolder = R.drawable.kim_holder_picture
    ),
)

// MAIN ACTIVITY
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            P2_Characters_nd_NavigationTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("main") {
                            MainScreen(navController)
                        }

                        composable(
                            "details/{route}",
                            arguments = listOf(
                                navArgument("route") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->

                            val route = backStackEntry.arguments?.getString("route") ?: ""
                            val item = itemList.find { it.route == route }

                            item?.let {
                                DetailsScreen(
                                    imageCard =it.imageCard,
                                    imageRes = it.imageRes,
                                    title = it.title,
                                    description1 = it.description1,
                                    imageJewel = it.imageJewel,
                                    jewelDes = it.jewelDes,
                                    imageAbility = it.imageAbility,
                                    description2 = it.description2,
                                    imageCivilian = it.imageCivilian,
                                    civilianDes = it.civilianDes,
                                    kwamiImage = it.kwamiImage,
                                    kwamiDes = it.kwamiDes,
                                    route = it.route,
                                    backgroundRes = it.backgroundRes,
                                    buttonColor = it.buttonColor,
                                    cardColor = it.cardColor,
                                    imageHolder = it.imageHolder,
                                    navController = navController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// MAIN SCREEN
@Composable
fun MainScreen(navController: NavController) {

    // BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        // IMAGE: BACKGROUND
        Image(
            painter = painterResource(id = R.drawable.background_mlb),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        // BACKGROUND: OPACITY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            // TITLE: MIRACULOUS RUN GAME CHARACTERS
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        // TEXT: STROKE
                        Text(
                            text = "Miraculous Run-Game Wiki",
                            fontSize = 39.sp,
                            fontFamily = lobster_mlb_font,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 36.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                drawStyle = Stroke(width = 15f),
                                color = Color.Black,
                            )
                        )
                        // TEXT: FILL
                        Text(
                            text = "Miraculous Run-Game Wiki",
                            fontSize = 38.sp,
                            fontFamily = lobster_mlb_font,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 36.sp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
            // PROFILE LIST
            items(itemList) { item ->
                ProfileCard(item = item, navController = navController)
            }
        }
    }
}

// CHARACTERS CARDS
@Composable
fun ProfileCard(item: ItemData, navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
            navController.navigate("details/${item.route}")
        }
    ) {
        // CHARACTER PROFILE CARD IMAGE
        Box(contentAlignment = Alignment.Center) {

            Box(
                modifier = Modifier
                    .size(114.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .blur(50.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(item.cardColor[0], item.cardColor[1])
                        )
                    )
            )
                // IMAGE: CHARACTER
                Image(
                    painter = painterResource(id = item.imageCard),
                    contentDescription = "Character Image",
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

        }

        Spacer(modifier = Modifier.height(10.dp))

        // CHARACTER NAME
        Box {
            // TEXT: STROKE
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontFamily = poppins_semibold_mlb_font,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    drawStyle = Stroke(width = 10f),
                    color = Color.Black,
                )
            )
            // TEXT: FILL
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontFamily = poppins_semibold_mlb_font,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

// DETAILS SCREEN
@Composable
fun DetailsScreen(
    imageCard: Int,
    imageRes: Int,
    title: String,
    description1: String,
    imageJewel: Int,
    jewelDes: String,
    imageAbility: List<Int>,
    description2: String,
    imageCivilian: Int,
    civilianDes: String,
    kwamiImage: List<Int>,
    kwamiDes: String,
    route: String,
    backgroundRes: Int,
    buttonColor: Color,
    navController: NavController,
    cardColor: List<Color>,
    imageHolder: Int
) {

    val isSingle = imageAbility.size == 1

    var currentImageId by remember(imageRes) { mutableStateOf(imageRes) }

    // BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        // IMAGE: BACKGROUND
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        // BACKGROUND: OPACITY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Box {
                    // BUTTON: TOP
                    Button(
                        onClick = {
                            (navController.popBackStack())
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        )
                    ) {
                        // TEXT: BUTTON
                        Text(
                            text = "❮❮❮❮",
                            fontSize = 16.sp,
                            fontFamily = lobster_mlb_font,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    // TITLE: CHARACTER NAME
                    // TEXT: STROKE
                    Text(
                        text = title,
                        fontSize = 39.sp,
                        fontFamily = lobster_mlb_font,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            drawStyle = Stroke(width = 15f),
                            color = Color.Black
                        )
                    )
                    // TEXT: FILL
                    Text(
                        text = title,
                        fontSize = 38.sp,
                        fontFamily = lobster_mlb_font,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                // CHARACTER IMAGE
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            currentImageId = if (currentImageId == imageRes) {
                                imageCivilian
                            } else {
                                imageRes
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // IMAGE: BLUR STROKE
                    Image(
                        painter = painterResource(id = currentImageId),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(360.dp)
                            .blur(8.dp)
                    )
                    // IMAGE: CHARACTER
                    Image(
                        painter = painterResource(id = currentImageId),
                        contentDescription = "Character Picture",
                        modifier = Modifier
                            .size(360.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // DESCRIPTION 1
                Text(
                    text = buildAnnotatedString {
                        val lines = description1.split("\n")

                        lines.forEachIndexed { index, line ->
                            val parts = line.split(":", limit = 2)

                            if (parts.size == 2) {
                                // TEXT: BEFORE ":"
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append(parts[0] + ": ")
                                }
                                // TEXT: AFTER ":"
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) {
                                    append(parts[1])
                                }
                            } else {
                                append(line)
                            }
                            if (index != lines.lastIndex) append("\n")
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = poppins_semibold_mlb_font,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // JEWEL IMAGE
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // IMAGE: BLUR STROKE
                    Image(
                        painter = painterResource(id = imageJewel),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(160.dp)
                            .blur(10.dp)
                    )
                    // IMAGE: JEWEL
                    Image(
                        painter = painterResource(id = imageJewel),
                        contentDescription = "Jewel Picture",
                        modifier = Modifier
                            .size(160.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // JEWEL DESCRIPTION
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Miraculous \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(jewelDes)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = poppins_semibold_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ABILITY IMAGE GIF
                LazyRow(
                    horizontalArrangement = if (isSingle) {
                        Arrangement.Center
                    } else {
                        Arrangement.spacedBy(12.dp)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(imageAbility) { abilityRes ->
                        Box(
                            modifier = Modifier
                                .height(240.dp)
                                .aspectRatio(1.5f),
                            contentAlignment = Alignment.Center
                        ) {
                            // IMAGE: BLUR STROKE
                            GlideImage(
                                imageModel = { abilityRes },
                                imageOptions = ImageOptions(contentDescription = null,
                                contentScale = ContentScale.Fit
                                ),
                                modifier = Modifier
                                    .matchParentSize()
                                    .blur(10.dp)
                            )
                            // IMAGE: GIF ABILITY
                            GlideImage(
                                imageModel = { abilityRes },
                                imageOptions = ImageOptions(contentDescription = "Ability GIF",
                                contentScale = ContentScale.Fit
                                ),
                                modifier = Modifier.matchParentSize()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // DESCRIPTION 2
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Ability \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(description2)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = poppins_semibold_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // HOLDER IMAGE
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // IMAGE: BLUR STROKE
                    Image(
                        painter = painterResource(id = imageHolder),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(360.dp)
                            .blur(10.dp)
                    )
                    // IMAGE: HOLDER
                    Image(
                        painter = painterResource(id = imageHolder),
                        contentDescription = "Civilian Picture",
                        modifier = Modifier
                            .size(360.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // HOLDER DESCRIPTION
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Holder \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(civilianDes)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = poppins_semibold_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // KWAMI IMAGE
                LazyRow(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(kwamiImage) { kwamiRes ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            // IMAGE: BLUR STROKE
                            Image(
                                painter = painterResource(id = kwamiRes),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.Black),
                                modifier = Modifier
                                    .size(204.dp)
                                    .blur(10.dp)
                            )
                            // IMAGE: KWAMI
                            Image(
                                painter = painterResource(id = kwamiRes),
                                contentDescription = "Kwami Picture",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(204.dp)
                                    .padding(5.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // KWAMI DESCRIPTION
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Kwami \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(kwamiDes)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = poppins_semibold_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // BUTTON: BOTTOM
                Button(
                    onClick = {
                        (navController.popBackStack())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    )
                ) {
                    // TEXT: BUTTON
                    Text(
                        text = "❮❮❮❮",
                        fontSize = 16.sp,
                        fontFamily = lobster_mlb_font,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

        }
    }
}

// PREVIEWS
// MAIN SCREEN
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    P2_Characters_nd_NavigationTheme {
        MainScreen(navController = rememberNavController())
    }
}

// DETAILS SCREEN
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    P2_Characters_nd_NavigationTheme {
        DetailsScreen(
            imageCard = R.drawable.marinette_picture,
            imageRes = R.drawable.marinette_picture,
            title = "Character's name",
            description1 = "Abilities",
            imageJewel = R.drawable.ladybug_jewel,
            jewelDes = "Jewel description",
            imageAbility = listOf(R.drawable.lady_dragon_xiong_gif),
            description2 = "Content description",
            imageCivilian = R.drawable.marinette_picture,
            civilianDes = "Civilian description",
            kwamiImage = listOf(R.drawable.kwami_pollen),
            kwamiDes = "Kwami description",
            route = "main",
            backgroundRes = R.drawable.background_mlb,
            buttonColor = Color.Red.copy(alpha = 0.5f),
            cardColor = listOf(Color.Red, Color.White),
            imageHolder = R.drawable.marinette_picture,
            navController = rememberNavController(),
        )
    }
}