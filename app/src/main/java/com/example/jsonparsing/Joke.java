package com.example.jsonparsing;

import org.json.JSONObject;

import java.util.Objects;

public class Joke {
    private String category;
    private String type;
    private String setup;
    private String delivery;
    private String joke;
    private Boolean nsfw;
    private Boolean religious;
    private Boolean political;
    private Boolean racist;
    private Boolean sexist;
    private Boolean explicit;
    private int id;
    private Boolean safe;
    private String lang;

    public Joke(String category, String type, String setup, String delivery, String joke, Boolean nsfw, Boolean religious, Boolean political, Boolean racist, Boolean sexist, Boolean explicit, int id, Boolean safe, String lang) {
        this.category = category;
        this.type = type;
        this.setup = setup;
        this.delivery = delivery;
        this.joke = joke;
        this.nsfw = nsfw;
        this.religious = religious;
        this.political = political;
        this.racist = racist;
        this.sexist = sexist;
        this.explicit = explicit;
        this.id = id;
        this.safe = safe;
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", setup='" + setup + '\'' +
                ", delivery='" + delivery + '\'' +
                ", joke='" + joke + '\'' +
                ", nsfw=" + nsfw +
                ", religious=" + religious +
                ", political=" + political +
                ", racist=" + racist +
                ", sexist=" + sexist +
                ", explicit=" + explicit +
                ", id=" + id +
                ", safe=" + safe +
                ", lang='" + lang + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke1 = (Joke) o;
        return id == joke1.id && Objects.equals(category, joke1.category) && Objects.equals(type, joke1.type) && Objects.equals(setup, joke1.setup) && Objects.equals(delivery, joke1.delivery) && Objects.equals(joke, joke1.joke) && Objects.equals(nsfw, joke1.nsfw) && Objects.equals(religious, joke1.religious) && Objects.equals(political, joke1.political) && Objects.equals(racist, joke1.racist) && Objects.equals(sexist, joke1.sexist) && Objects.equals(explicit, joke1.explicit) && Objects.equals(safe, joke1.safe) && Objects.equals(lang, joke1.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, type, setup, delivery, joke, nsfw, religious, political, racist, sexist, explicit, id, safe, lang);
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getJoke() {
        return joke;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public Boolean getReligious() {
        return religious;
    }

    public Boolean getPolitical() {
        return political;
    }

    public Boolean getRacist() {
        return racist;
    }

    public Boolean getSexist() {
        return sexist;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public int getId() {
        return id;
    }

    public Boolean getSafe() {
        return safe;
    }

    public String getLang() {
        return lang;
    }
}


//{
//        "category": "Dark",
//        "type": "twopart",
//        "setup": "What do Ted Bundy and the Space Shuttle Columbia have in common?",
//        "delivery": "They both left bodies in four states.",
//        "flags": {
//        "nsfw": false,
//        "religious": false,
//        "political": false,
//        "racist": false,
//        "sexist": false,
//        "explicit": true
//        },
//        "id": 154,
//        "safe": false,
//        "lang": "en"
//        }