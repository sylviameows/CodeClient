package dev.dfonline.codeclient.switcher;

import dev.dfonline.codeclient.CodeClient;
import dev.dfonline.codeclient.location.*;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateSwitcher extends GenericSwitcher {
    public StateSwitcher() {
        super(Text.literal("Mode Switcher"), GLFW.GLFW_KEY_F3, GLFW.GLFW_KEY_F4);
    }

    @Override
    protected void init() {
        footer = Text.literal("[ F4 ]").formatted(Formatting.AQUA).append(Text.literal(" Next").formatted(Formatting.WHITE));
        selected = 0;
        if(CodeClient.lastLocation instanceof Plot) {
            if(CodeClient.lastLocation instanceof Play) selected = 0;
            if(CodeClient.lastLocation instanceof Build) selected = 1;
            if(CodeClient.lastLocation instanceof Dev) selected = 2;
        }
        if(CodeClient.lastLocation instanceof Spawn) {
            if(CodeClient.location instanceof Creator) selected = 0;
            if(CodeClient.location instanceof Play) selected = 2;
        }
        super.init();
    }

    @Override
    List<Option> getOptions() {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(Text.literal("Play"), Items.DIAMOND.getDefaultStack(), () -> joinMode("play")));
        options.add(new Option(Text.literal("Build"), Items.GRASS_BLOCK.getDefaultStack(), () -> joinMode("build")));
        options.add(new Option(Text.literal("Code"), Items.COMMAND_BLOCK.getDefaultStack(), () -> joinMode("dev")));
        return options;
    }

    private void joinMode(String mode) {
        CodeClient.MC.getNetworkHandler().sendCommand(mode);
    }
}
