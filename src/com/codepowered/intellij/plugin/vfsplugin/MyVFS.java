package com.codepowered.intellij.plugin.vfsplugin;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.util.io.FileAttributes;
import com.intellij.openapi.vfs.NonPhysicalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.NewVirtualFileSystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MyVFS extends NewVirtualFileSystem implements NonPhysicalFileSystem {

    private final String prefix;
    private MyVFDirectory root = new MyRootVF(this);

    public MyVFS() {
        PluginManager.getLogger().info(toString());
        prefix = getProtocol() + "://";
    }

    @Nullable
    @Override
    public VirtualFile findFileByPathIfCached(@NotNull String path) {
        return findFileByPath(path);
    }

    @NotNull
    @Override
    public String getProtocol() {
        return "leon";
    }

    @Nullable
    @Override
    public VirtualFile findFileByPath(@NotNull String path) {
        int idx = path.indexOf('/');
        String host = "";
        if (idx >= 0) {
            host = path.substring(0, idx);
            path = path.substring(idx + 1);
        }
        return root.resolve(path);
    }

    @Override
    public void refresh(boolean asynchronous) {

    }

    @Nullable
    @Override
    public VirtualFile refreshAndFindFileByPath(@NotNull String path) {
        refresh(false);
        return findFileByPath(path);
    }

    @Override
    public int getRank() {
        return 0;
    }

    @Override
    public void deleteFile(Object requestor, @NotNull VirtualFile vFile) throws IOException {

    }

    @Override
    public void moveFile(Object requestor, @NotNull VirtualFile vFile, @NotNull VirtualFile newParent) throws IOException {

    }

    @Override
    public void renameFile(Object requestor, @NotNull VirtualFile vFile, @NotNull String newName) throws IOException {

    }

    @Nullable
    @Override
    public FileAttributes getAttributes(@NotNull VirtualFile file) {
        return null;
    }

    @NotNull
    @Override
    public VirtualFile createChildFile(Object requestor, @NotNull VirtualFile vDir, @NotNull String fileName) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean exists(@NotNull VirtualFile file) {
        return true;
    }

    @NotNull
    @Override
    public String[] list(@NotNull VirtualFile file) {
        ArrayList<VirtualFile> children = new ArrayList<>(Arrays.asList(file.getChildren()));
        return children.stream().map(VirtualFile::getName).collect(Collectors.toList()).toArray(new String[children.size()]);
    }

    @Override
    public boolean isDirectory(@NotNull VirtualFile file) {
        return file.isDirectory();
    }

    @Override
    public long getTimeStamp(@NotNull VirtualFile file) {
        return 0;
    }

    @Override
    public void setTimeStamp(@NotNull VirtualFile file, long timeStamp) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean isWritable(@NotNull VirtualFile file) {
        return false;
    }

    @Override
    public void setWritable(@NotNull VirtualFile file, boolean writableFlag) throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public VirtualFile createChildDirectory(Object requestor, @NotNull VirtualFile vDir, @NotNull String dirName) throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public VirtualFile copyFile(Object requestor, @NotNull VirtualFile virtualFile, @NotNull VirtualFile newParent, @NotNull String copyName) throws IOException {
        throw new IOException();
    }

    @NotNull
    @Override
    public byte[] contentsToByteArray(@NotNull VirtualFile file) throws IOException {
        return new byte[0];
    }

    @NotNull
    @Override
    public InputStream getInputStream(@NotNull VirtualFile file) throws IOException {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return -1;
            }
        };
    }

    @NotNull
    @Override
    public OutputStream getOutputStream(@NotNull VirtualFile file, Object requestor, long modStamp, long timeStamp) throws IOException {
        throw new IOException();
    }

    @Override
    public long getLength(@NotNull VirtualFile file) {
        return 0;
    }

    @NotNull
    @Override
    protected String extractRootPath(@NotNull String path) {
        path = path.substring(prefix.length());
        int idx = path.indexOf('/');
        return prefix + (idx >= 0 ? path.substring(0, idx) : path) + "/";
    }

    MyVFDirectory getRoot() {
        return root;
    }
}
