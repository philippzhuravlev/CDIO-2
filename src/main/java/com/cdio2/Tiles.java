package com.cdio2;
import java.util.ResourceBundle;

class Tiles {
    public static String sendMessage(Integer tileOn, ResourceBundle messages) { 
        String messageKey;
        switch (tileOn) {
            case 2: messageKey = "tower"; break;
            case 3: messageKey = "crater"; break;
            case 4: messageKey = "palace"; break;
            case 5: messageKey = "cold_desert"; break;
            case 6: messageKey = "walled_city"; break;
            case 7: messageKey = "monastery"; break;
            case 8: messageKey = "black_cave"; break;
            case 9: messageKey = "huts"; break;
            case 10: messageKey = "werewall"; break;
            case 11: messageKey = "pit"; break;
            case 12: messageKey = "goldmine"; break;
            default: messageKey = "unknown"; break;
        }
        return messages.getString(messageKey);
    }
}
