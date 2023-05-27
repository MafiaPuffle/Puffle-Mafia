package com.example.pufflemafia.app.game;

import androidx.annotation.NonNull;

import com.example.pufflemafia.app.data.Token;

import java.util.Comparator;

// Helper class used to sort players by their role priority
class SortPlayerByTokensApplied implements Comparator<Player> {
    public int compare(@NonNull Player a, @NonNull Player b)
    {
//        int sizeOfA = a.getAllTokensOnPlayer().size();
//        int sizeOfB = b.getAllTokensOnPlayer().size();

        int A_numOfClearOnNight = a.getNumberOfTokensOnPlayerOfType(Token.TokenTypes.CLEAR_ON_NIGHT);
        int A_numOfClearOnDeath = a.getNumberOfTokensOnPlayerOfType(Token.TokenTypes.CLEAR_ON_DEATH);
        int A_numOfClearNever = a.getNumberOfTokensOnPlayerOfType(Token.TokenTypes.CLEAR_NEVER);

        int B_numOfClearOnNight = b.getNumberOfTokensOnPlayerOfType(Token.TokenTypes.CLEAR_ON_NIGHT);
        int B_numOfClearOnDeath = b.getNumberOfTokensOnPlayerOfType(Token.TokenTypes.CLEAR_ON_DEATH);
        int B_numOfClearNever = b.getNumberOfTokensOnPlayerOfType(Token.TokenTypes.CLEAR_NEVER);

        // move a under b
        if(A_numOfClearOnNight < B_numOfClearOnNight) {
            return 1;
        }
        // move a above b
        else if(A_numOfClearOnNight > B_numOfClearOnNight){
            return -1;
        }
        // a and b have same number of clearOnNight tokens
        else {
            // move a under b
            if(A_numOfClearOnDeath < B_numOfClearOnDeath){
                return 1;
            }
            // move a above b
            else if(A_numOfClearOnDeath > B_numOfClearOnDeath){
                return -1;
            }
            // a and b have same number of clearOnDeath tokens
            else {
                // move a under b
                if(A_numOfClearNever < B_numOfClearNever){
                    return 1;
                }
                // move a above b
                else if(A_numOfClearNever > B_numOfClearNever){
                    return -1;
                }
                // a and b have same number of clearNever tokens
                else {
                    return 0;
                }
            }
        }

    }
}
