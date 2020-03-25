package com.game.gb5.common.utils.dotenv;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvPropertyLoader {

    private Dotenv dotenv;

    public DotenvPropertyLoader() {
        dotenv = Dotenv.configure().ignoreIfMissing().load();
    }

    public Object getValue(String key) {
        return dotenv.get(key);
    }
}
