package com.example.pufflemafia.app.data.prompts;

import androidx.core.util.Pair;

import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.states.Night;

import java.util.Vector;

public class OptionsPrompt extends Prompt{

    private Vector<Pair<String, Prompt>> options;

    public Vector<Pair<String, Prompt>> getOptions() {
        return options;
    }
    public int numberOfOptions(){
        return options.size();
    }
    public void addOption(String option, Prompt result){
        Pair<String, Prompt> p = new Pair<>(option, result);
        options.add(p);
    }

    public void chooseOption(int index){
        PromptsManager.QuePrompt(options.get(index).second);  // gets the Prompt in the Pair at the given index
        PromptsManager.GetNextPrompt();
    }

    public OptionsPrompt(){
        options = new Vector<Pair<String, Prompt>>();
    }
}
