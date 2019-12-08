package com.okanciftci.cukatify.common;

public enum ResponseEnum {
    OK(200, "OK", Boolean.TRUE),
    BADREQUEST(400, "BADREQUEST", Boolean.FALSE),
    UNAUTHORIZED(401, "UNAUTHORIZED", Boolean.FALSE),
    FORBIDDEN(403, "FORBIDDEN", Boolean.FALSE),
    NOTFOUND(404, "NOTFOUND", Boolean.FALSE),
    INTERNAL_ERROR(500, "INTERNAL_ERROR", Boolean.FALSE);

    private Integer httpStatusCode;
    private String description;
    private Boolean isSuccess;

    private ResponseEnum(Integer httpStatusCode, String description, Boolean isSuccess) {
        this.httpStatusCode = httpStatusCode;
        this.description = description;
        this.isSuccess = isSuccess;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}