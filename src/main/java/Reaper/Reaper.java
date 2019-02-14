package Reaper;

import Reaper.Cards.*;
import Reaper.Character.The_Reaper;
import Reaper.Relics.*;
import Reaper.Variables.*;
import Reaper.Patches.*;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class Reaper implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber {
    public static final Logger logger = LogManager.getLogger(Reaper.class.getName());

    //Card Stuff
    public static final Color REAPER_PURPLE = CardHelper.getColor(102.0f, 51.0f, 153.0f);

    //Card Backgrounds images
    private static final String ATTACK_REAPER_PURPLE = "TheReaperResource/images/cards/attacks/Background.png";
    private static final String SKILL_REAPER_PURPLE = "TheReaperResource/images/cards/skills/Background.png";
    private static final String POWER_REAPER_PURPLE = "TheReaperResource/images/cards/powers/Background.png";
    private static final String ENERGY_ORB_REAPER_PURPLE = "TheReaperResource/images/cards/Orb.png";
    private static final String CARD_ENERGY_ORB = "TheReaperResource/images/cards/OrbSmall.png";
    private static final String ATTACK_REAPER_PURPLE_PORTRAIT = "TheReaperResource/images/cards/attacks/BackgroundPortrait.png";
    private static final String SKILL_REAPER_PURPLE_PORTRAIT = "TheReaperResource/images/cards/skills/BackgroundPortrait.png";
    private static final String POWER_REAPER_PURPLE_PORTRAIT = "TheReaperResource/images/cards/powers/BackgroundPortrait.png";
    private static final String ENERGY_ORB_REAPER_PURPLE_PORTRAIT = "TheReaperResource/images/cards/OrbPortrait.png";

    //Character images
    private static final String THE_REAPER_BUTTON = "TheReaperResource/images/charselect/ReaperCharacterButton.png";
    private static final String THE_REAPER_PORTRAIT = "TheReaperResource/images/charselect/ReaperCharacterPortraitBG.png";
    public static final String THE_REAPER_SHOULDER_1 = "TheReaperResource/images/characters/shoulder.png";
    public static final String THE_REAPER_SHOULDER_2 = "TheReaperResource/images/characters/shoulder2.png";
    public static final String THE_REAPER_CORPSE = "TheReaperResource/images/characters/corpse.png";


    // Atlas and JSON files for the Animations
    public static final String THE_REAPER_SKELETON_ATLAS = "TheReaperResource/images/characters/skeleton.atlas";
    public static final String THE_REAPER_SKELETON_JSON = "TheReaperResource/images/characters/skeleton.json";
    private static final String Assets = "TheReaperResource/images";

    //Making the Reaper Constructor
    public Reaper() {
        BaseMod.subscribe(this);
        System.out.println("Reaper Mod Starting");

        BaseMod.addColor(AbstractCardEnum.COLOR_PURPLE, REAPER_PURPLE, REAPER_PURPLE, REAPER_PURPLE,
                REAPER_PURPLE, REAPER_PURPLE, REAPER_PURPLE, REAPER_PURPLE,
                ATTACK_REAPER_PURPLE, SKILL_REAPER_PURPLE, POWER_REAPER_PURPLE, ENERGY_ORB_REAPER_PURPLE,
                ATTACK_REAPER_PURPLE_PORTRAIT, SKILL_REAPER_PURPLE_PORTRAIT, POWER_REAPER_PURPLE_PORTRAIT,
                ENERGY_ORB_REAPER_PURPLE_PORTRAIT, CARD_ENERGY_ORB);
    }

    public static void initialize() {
        logger.info("Initializing Reaper Mod");
        new Reaper();
    }

    //Make the Character
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + The_Reaper.Enums.THE_REAPER.toString());

        BaseMod.addCharacter(new The_Reaper("The Reaper", The_Reaper.Enums.THE_REAPER),
                THE_REAPER_BUTTON, THE_REAPER_PORTRAIT, The_Reaper.Enums.THE_REAPER);

        logger.info("Added " + The_Reaper.Enums.THE_REAPER.toString());
    }

    //Make the Relics
    @Override
    public void receiveEditRelics(){
        logger.info("Adding Reaper relics");

        //Starter Relics
        BaseMod.addRelicToCustomPool(new Reapers_Magic(), AbstractCardEnum.COLOR_PURPLE);

        //Normal Relics
        BaseMod.addRelicToCustomPool(new Roses_Mark(), AbstractCardEnum.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool(new Alternate_Dimension(), AbstractCardEnum.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool(new Deaths_Scythe(), AbstractCardEnum.COLOR_PURPLE);

        logger.info("Done Adding Reaper relics");
    }

    //Make the Keywords
    public void receiveEditKeywords(){
        Gson gson = new Gson();
        String json = Gdx.files.internal("TheReaperResource/strings/keyword-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        if (keywords != null) {
            for (com.evacipated.cardcrawl.mod.stslib.Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    //Receive the Strings
    public void receiveEditStrings(){
        String relicStrings = Gdx.files.internal("TheReaperResource/strings/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        BaseMod.loadCustomStringsFile(PowerStrings.class, "TheReaperResource/strings/power-strings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "TheReaperResource/strings/character-strings.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "TheReaperResource/strings/card-strings.json");
    }

    //Make the cards
    @Override
    public void receiveEditCards() {
        logger.info("Adding Reaper Cards");

        BaseMod.addDynamicVariable(new ReaperCustomVariables());
        BaseMod.addDynamicVariable(new ReaperSecondMagicNumber());

        //Basic Cards
        //Attacks
        BaseMod.addCard(new ReaperStrike());
        //Skills
        BaseMod.addCard(new ReaperDefend());
        BaseMod.addCard(new RosePetal());

        //Common Cards
        //Attacks
        BaseMod.addCard(new ScytheSlash());
        BaseMod.addCard(new HealingTouch());
        BaseMod.addCard(new HiddenStab());
        //Skills
        BaseMod.addCard(new ForbiddenArt());
        BaseMod.addCard(new WeaknessArts());
        BaseMod.addCard(new NaturalBlock());
        BaseMod.addCard(new Sheath());
        BaseMod.addCard(new DefensiveRose());

        //Uncommon Cards
        //Attacks
        BaseMod.addCard(new IntoShadows());
        BaseMod.addCard(new SilentSlash());
        BaseMod.addCard(new Cyclone());
        BaseMod.addCard(new QuickBlow());
        //Skills
        BaseMod.addCard(new DeathsDoor());
        BaseMod.addCard(new Illusion());
        BaseMod.addCard(new Hidden());
        BaseMod.addCard(new Stealthy());
        //Powers


        //RareCards
        //Attacks
        BaseMod.addCard(new FearChains());
        BaseMod.addCard(new DeathsTornado());
        BaseMod.addCard(new ReverseGravity());
        BaseMod.addCard(new BloodyThreads());
        BaseMod.addCard(new CruelSlash());
        //Skills
        BaseMod.addCard(new Blink());
        BaseMod.addCard(new FastMovement());
        BaseMod.addCard(new RoseGarden());
        BaseMod.addCard(new ClippedWings());
        BaseMod.addCard(new MadMagic());
        BaseMod.addCard(new DeathsMark());
        BaseMod.addCard(new TrueDodge());
        //Powers
        BaseMod.addCard(new DarkMagic());

        logger.info("Done Adding Reaper Cards.");
    }

    //Paths
    public static String getResourcePath(String Resource) {
        return Assets + "/" + Resource;
    }

    //Make ID
    public static String makeID(String idText) {
        return "reaper:" + idText;
    }
}
