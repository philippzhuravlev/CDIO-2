package com.cdio2;


class Player {
    private String name;
    private Integer gold;

    Player(String name, Integer gold) {
        this.name = name;
        this.gold = gold;
    }

    // player getters
    public String getName() {
        return this.name;
    }

    public Integer getGold() {
        return this.gold;
    }

    // player setters
    public void setName(String nameSet) {
        this.name = nameSet;
    }

    public void setGold(Integer goldSet) {
        this.gold = goldSet;
    }
}

class Wallet {
    private String owner;
    private Integer gold;

    public Wallet(String owner, Integer gold) {
        this.owner = owner;
        this.gold = gold;
    }

    // wallet getters
    public String getOwner() {
        return this.owner;
    }

    public Integer getGold() {
        return this.gold;
    }

    // wallet setters
    public void setOwner(String ownerSet) {
        this.owner = ownerSet;
    }

    public void setGold(Integer goldSet) {
        this.gold = goldSet;
    }

    public void addGold(Integer goldAdded) { // one-liners to save space
        switch (goldAdded) {
            case 2: gold += 250; break;
            case 3: gold -= 100; break;
            case 4: gold += 100; break;
            case 5: gold -= 20; break;
            case 6: gold += 180; break;
            case 7: gold += 0; break;
            case 8: gold -= 70; break;
            case 9: gold += 60; break;
            case 10: gold -= 80; break;
            case 11: gold -= 50; break;
            case 12: gold +=650; break;
            default: break;
        }
        
        // Wallet cannot go under 0
        if (this.gold < 0) {
            this.gold = 0;
        }
    }
}
