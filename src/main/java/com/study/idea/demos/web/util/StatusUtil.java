package com.study.idea.demos.web.util;

import lombok.Getter;

public class StatusUtil {
    @Getter
    public enum ErrorCode {
        OK(200, "成功"),
        UNKNOWN_ERROR(500, "未知错误"),
        PARAMETER_ERROR(5001, "参数错误"),
        NO_PERMISSION(5002, "没有权限"),
        NOT_EXISTS(5003, "不存在"),
        ALREADY_EXISTS(5004, "已存在"),
        BANNED(5005, "被封禁"),
        TIMEOUT(5006, "超时"),
        TOO_MANY_REQUESTS(5007, "请求太频繁"),
        TOO_MANY_CONTAINS(5008, "内容过多");
        private final int code;
        private final String message;
        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}