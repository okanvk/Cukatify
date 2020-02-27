package com.okanciftci.cukatify.exceptions;

public class FileStorageResponse {
    private String fileName;

    public FileStorageResponse(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
