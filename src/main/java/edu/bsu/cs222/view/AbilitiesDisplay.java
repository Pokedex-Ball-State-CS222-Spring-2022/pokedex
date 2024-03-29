package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Ability;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class AbilitiesDisplay extends DisplayCreator implements MenuDisplay {
    private final VBox abilityRows = new VBox(SMALL_SPACING);

    public AbilitiesDisplay() {
        abilityRows.setPadding(DEFAULT_INSETS);
    }

    public Parent getInitialDisplay() {
        abilityRows.getChildren().addAll(
                createText("Abilities", BIG_HEADER_FONT),
                createText("Hidden Abilities", BIG_HEADER_FONT)
        );
        return abilityRows;
    }

    public Parent display(Pokemon pokemon) {
        abilityRows.getChildren().remove(0, abilityRows.getChildren().size());
        abilityRows.getChildren().addAll(
                createText("Abilities", BIG_HEADER_FONT),
                convertAbilitiesIntoText(pokemon.getAbilities()),
                createText("Hidden Abilities", BIG_HEADER_FONT),
                convertAbilitiesIntoText(pokemon.getHiddenAbilities())
        );
        return wrapScrollPaneAround(abilityRows);
    }

    private Parent convertAbilitiesIntoText(List<Ability> abilities) {
        VBox abilityText = new VBox(SMALL_SPACING);
        for (Ability ability : abilities) {
            abilityText.getChildren().addAll(
                    createText(ability.getAbilityName(), HEADER_FONT),
                    createEffectText(ability.getEffect())
            );
        }
        return abilityText;
    }

    private Text createEffectText(String effect) {
        String properEffectString = encodeStringInUTF8(effect);
        Text text = createText(properEffectString, DEFAULT_FONT);
        text.setWrappingWidth(750);
        return text;
    }

    private String encodeStringInUTF8(String effect) {
        // Some characters won't show up correctly unless we re-encode into the UTF_8 charset
        // For example: Pokémon will show up as PokÃ©mon
        byte[] stringBytes = effect.getBytes();
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    public String getDisplayName() {
        return "Abilities";
    }
}
