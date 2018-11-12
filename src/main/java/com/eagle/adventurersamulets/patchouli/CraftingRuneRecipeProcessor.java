package com.eagle.adventurersamulets.patchouli;

import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.recipe.RecipeCraftingRune;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.common.util.ItemStackUtil;

import java.util.Objects;

@SuppressWarnings("unused")
public class CraftingRuneRecipeProcessor implements IComponentProcessor {

    private RecipeCraftingRune recipe;
    private String text;

    @Override
    public void setup(IVariableProvider<String> variables) {
        String recipeId = variables.get("recipe");
        String text = variables.get("text");

        recipe = AdvAmAPI.getCraftingRuneRecipeFromId(recipeId);
        this.text = text;
    }

    @Override
    public String process(String key) {
        if (key.startsWith("input")) {
            int id = Integer.parseInt(key.substring(5));
            Object[] inputArray = recipe.getInput();
            ItemStack input = null;
            if (inputArray[id] instanceof Block) {
                input = new ItemStack((Block) inputArray[id]);
            } else if (inputArray[id] instanceof String) {
                input = OreDictionary.getOres((String) inputArray[id]).get(0);
            }
            return ItemStackUtil.serializeStack(Objects.requireNonNull(input));
        } else if (key.startsWith("output")) {
            int id = Integer.parseInt(key.substring(6));
            ItemStack output = new ItemStack(recipe.getOutput().getBlock());

            if (id >= recipe.getOutputQuantity())
                return null;

            return ItemStackUtil.serializeStack(output);
        } else if (key.equals("name")){
            return recipe.getOutput().getBlock().getLocalizedName();
        }

        return null;
    }

}
