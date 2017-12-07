package com.codepowered.intellij.plugin.vfsplugin;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.NewVirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class MyVFFile extends AbstractMyVF {

    MyVFFile(MyVFDirectory parent, String name) {
        super(parent, name);
    }

    @Override
    public VirtualFile[] getChildren() {
        return null;
    }

    @NotNull
    @Override
    public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public byte[] contentsToByteArray() throws IOException {
        return new byte[0];
    }

    AbstractMyVF resolve(String path) {

        if ("".equals(path))
            return this;

        return null;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return -1;
            }
        };
    }

    @Nullable
    @Override
    public NewVirtualFile findChild(@NotNull String name) {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public long getModificationStamp() {
        return 0L;
    }
}
