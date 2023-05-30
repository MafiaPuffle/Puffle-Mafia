package com.example.pufflemafia.adaptors.tokenAdapters;

import android.content.Context;

import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class PossibleTokenUIAdaptor extends SelectableTokenUIAdaptor{
    private PlayerManager.PlayerMangerListType listType;
    private int playerIndex;

    public PossibleTokenUIAdaptor(Vector<Token> dataSet, Context context, PlayerManager.PlayerMangerListType listType, int playerIndex) {
        super(dataSet, context);
        this.listType = listType;
        this.playerIndex = playerIndex;
    }

    @Override
    protected void onItemClick(Token token){
        PlayerManager.AddTokenToPlayer(this.listType, this.playerIndex, token);
    }
}
