package reaper.Character;

import reaper.Cards.*;
import reaper.Reaper;
import reaper.Relics.*;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

import static reaper.Patches.AbstractCardEnum.COLOR_PURPLE;
import static reaper.Reaper.*;

public class The_Reaper extends CustomPlayer {

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_REAPER;
        @SpireEnum(name = "REAPER_PURPLE_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor COLOR_PURPLE;
        @SpireEnum(name = "REAPER_PURPLE_COLOR")@SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    //Stats
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 55;
    public static final int MAX_HP = 55;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 6;
    public static final int ORB_SLOTS = 3;

    //Strings
    private static final String ID = "reaper:TheReaper";
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    //Orb
    public static final String[] orbTextures = {
            "TheReaperResource/images/characters/orb/layer1.png",
            "TheReaperResource/images/characters/orb/layer2.png",
            "TheReaperResource/images/characters/orb/layer3.png",
            "TheReaperResource/images/characters/orb/layer4.png",
            "TheReaperResource/images/characters/orb/layer5.png",
            "TheReaperResource/images/characters/orb/layer6.png",
            "TheReaperResource/images/characters/orb/layer1d.png",
            "TheReaperResource/images/characters/orb/layer2d.png",
            "TheReaperResource/images/characters/orb/layer3d.png",
            "TheReaperResource/images/characters/orb/layer4d.png",
            "TheReaperResource/images/characters/orb/layer5d.png",};

    public The_Reaper(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "TheReaperResource/images/characters/orb/vfx.png", null,
                new SpriterAnimation(
                        "TheReaperResource/images/characters/spriter/theReaperAnimation.scml"));

        //Initialize the class
        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_REAPER_SHOULDER_1, // campfire pose
                THE_REAPER_SHOULDER_2, // another campfire pose
                THE_REAPER_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        //Animations
        loadAnimation(
                THE_REAPER_SKELETON_ATLAS,
                THE_REAPER_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        //Text Bubble
        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values
    }

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        Reaper.logger.info("Begin loading starter Deck Strings");

        retVal.add(ReaperStrike.CARD_ID);
        retVal.add(ReaperStrike.CARD_ID);
        retVal.add(ReaperStrike.CARD_ID);
        retVal.add(ReaperStrike.CARD_ID);
        retVal.add(ReaperStrike.CARD_ID);
        retVal.add(ReaperDefend.CARD_ID);
        retVal.add(ReaperDefend.CARD_ID);
        retVal.add(ReaperDefend.CARD_ID);
        retVal.add(ReaperDefend.CARD_ID);
        retVal.add(RosePetal.CARD_ID);

        return retVal;
    }

    // Starting Relics
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(Reapers_Magic.ID);
        UnlockTracker.markRelicAsSeen(Reapers_Magic.ID);

        return retVal;
    }


    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_PURPLE;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return Reaper.REAPER_PURPLE;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new ReaperStrike();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new The_Reaper(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return Reaper.REAPER_PURPLE;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return Reaper.REAPER_PURPLE;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }
}