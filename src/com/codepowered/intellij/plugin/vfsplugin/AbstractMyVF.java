package com.codepowered.intellij.plugin.vfsplugin;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWithId;
import com.intellij.openapi.vfs.newvfs.NewVirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link NewVirtualFile} and {@link VirtualFileWithId} are required to make the files show up in project.
 */
public abstract class AbstractMyVF extends NewVirtualFile implements VirtualFileWithId {

    private final MyVFDirectory parent;
    private final String name;
    private static final AtomicInteger ID = new AtomicInteger();
    private final int id;
    private final String path;

    AbstractMyVF(MyVFDirectory parent, @NotNull String name) {
        this.parent = parent;
        this.name = name;
        id = ID.incrementAndGet();
        path = makePath();
    }

    protected String makePath() {
        return getParent().getPath() + (getParent() == getFileSystem().getRoot() ? "" : "/") + getName();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    public abstract AbstractMyVF resolve(String path);

    @NotNull
    public MyVFS getFileSystem() {
        return parent.getFileSystem();
    }

    @NotNull
    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public NewVirtualFile getParent() {
        return parent;
    }


    @Override
    public long getTimeStamp() {
        return 0;
    }

    @Override
    public long getLength() {
        return 0;
    }

    @Override
    public void refresh(boolean asynchronous, boolean recursive, @Nullable Runnable postRunnable) {

    }


    @Nullable
    @Override
    public NewVirtualFile getCanonicalFile() {
        return this;
    }


    @Nullable
    @Override
    public NewVirtualFile refreshAndFindChild(@NotNull String name) {
        return findChild(name);
    }

    @Nullable
    @Override
    public NewVirtualFile findChildIfCached(@NotNull String name) {
        return findChild(name);
    }

    @Override
    public void setTimeStamp(long time) throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public CharSequence getNameSequence() {
        return getName();
    }

    @Override
    public void setWritable(boolean writable) throws IOException {
        throw new IOException();
    }

    @Override
    public void markDirty() {

    }

    @Override
    public void markDirtyRecursively() {

    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void markClean() {

    }

    @NotNull
    @Override
    public Collection<VirtualFile> getCachedChildren() {
        return Arrays.asList(getChildren());
    }

    @NotNull
    @Override
    public Iterable<VirtualFile> iterInDbChildren() {
        return getCachedChildren();
    }
}
