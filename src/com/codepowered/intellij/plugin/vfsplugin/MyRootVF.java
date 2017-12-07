package com.codepowered.intellij.plugin.vfsplugin;

import org.jetbrains.annotations.NotNull;

public class MyRootVF extends MyVFDirectory {

    private final MyVFS fileSystem;


    MyRootVF(MyVFS fileSystem) {
        super(null, "");
        this.fileSystem = fileSystem;
    }

    @NotNull
    @Override
    public MyVFS getFileSystem() {
        return fileSystem;
    }

    @Override
    protected String makePath() {
        return "/";
    }
}
