package com.codepowered.intellij.plugin.vfsplugin;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.NewVirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicReference;

public class MyVFDirectory extends AbstractMyVF {

    private final AtomicReference<NewVirtualFile[]> children = new AtomicReference<>();

    MyVFDirectory(MyVFDirectory parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @NotNull
    @Override
    public OutputStream getOutputStream(Object requestor, long newModificationStamp, long newTimeStamp) throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public byte[] contentsToByteArray() throws IOException {
        throw new IOException();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        throw new IOException();
    }

    @Override
    public NewVirtualFile[] getChildren() {
        NewVirtualFile[] children = this.children.get();
        if (children == null) {
            MyVFFile f = new MyVFFile(this, "fileY.java");
            if (getParent() == null || getParent().getParent() == null || getParent().getParent().getParent() == null)
                children = new NewVirtualFile[]{new MyVFDirectory(this, "dirX"), f};
            else
                children = new NewVirtualFile[]{f};
            if (!this.children.compareAndSet(null, children))
                children = this.children.get();
        }
        return children;
    }

    @Nullable
    @Override
    public NewVirtualFile findChild(@NotNull String name) {
        for (NewVirtualFile virtualFile : getChildren())
            if (name.equals(virtualFile.getName()))
                return virtualFile;
        return null;
    }

    public AbstractMyVF resolve(String path) {

        if ("".equals(path))
            return this;

        String prefix = "";

        while ("".equals(prefix)) {
            int idx = path.indexOf('/');
            if (idx < 0) {
                prefix = path;
                path = "";
            } else {
                prefix = path.substring(0, idx);
                path = path.substring(idx + 1);
            }

        }

        for (VirtualFile virtualFile : getChildren()) {
            if (virtualFile.getName().equals(prefix))
                return ((AbstractMyVF) virtualFile).resolve(path);
        }

        return null;
    }
}
