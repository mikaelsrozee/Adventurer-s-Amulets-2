package com.eagle.adventurersamulets.patchouli;

import com.eagle.adventurersamulets.api.AdvAmAPI;
import com.eagle.adventurersamulets.api.recipe.RecipePestle;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.common.util.ItemStackUtil;

import java.util.Objects;

@SuppressWarnings("unused")
public class PestleRecipeProcessor implements IComponentProcessor {

    private RecipePestle recipe;
    private String text;

    @Override
    public void setup(IVariableProvider<String> variables) {
        String recipeId = variables.get("recipe");
        String text = variables.get("text");

        recipe = AdvAmAPI.getPestleRecipeFromId(recipeId);
        this.text = text;
    }

    @Override
    public String process(String key) {
        switch (key) {
            case "input":
                ItemStack input = null;
                if (recipe.getInput() instanceof ItemStack) {
                    input = ((ItemStack) recipe.getInput()).copy();
                } else if (recipe.getInput() instanceof Item) {
                    input = new ItemStack((Item) recipe.getInput());
                } else if (recipe.getInput() instanceof Block) {
                    input = new ItemStack((Block) recipe.getInput());
                } else if (recipe.getInput() instanceof String) {
                    input = OreDictionary.getOres((String) recipe.getInput()).get(0);
                }
                return ItemStackUtil.serializeStack(Objects.requireNonNull(input));
            case "output":
                return ItemStackUtil.serializeStack(recipe.getOutput());
            case "name":
                return recipe.getOutput().getDisplayName();
            case "text":
                return text;
        }

        return null;
    }
}
