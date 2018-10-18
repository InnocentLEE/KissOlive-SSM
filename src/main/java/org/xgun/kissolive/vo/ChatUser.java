package org.xgun.kissolive.vo;

import java.security.Principal;

/**
 * Created by GvG on 2018/10/13.
 */
public final class ChatUser implements Principal {

    private final String name;

    public ChatUser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

