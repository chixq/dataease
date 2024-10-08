package io.dataease.websocket.util;


import io.dataease.auth.bo.TokenUserBO;
import io.dataease.utils.AuthUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.concurrent.CopyOnWriteArraySet;

public class WsUtil {

    private static final CopyOnWriteArraySet<Long> ONLINE_USERS = new CopyOnWriteArraySet();

    public static boolean onLine() {
        TokenUserBO user = AuthUtils.getUser();
        if (ObjectUtils.isNotEmpty(user) && ObjectUtils.isNotEmpty(user.getUserId()))
            return onLine(user.getUserId());
        return false;
    }

    public static boolean onLine(Long userId) {
        return ONLINE_USERS.add(userId);
    }

    public static boolean offLine() {
        TokenUserBO user = AuthUtils.getUser();
        if (ObjectUtils.isNotEmpty(user) && ObjectUtils.isNotEmpty(user.getUserId()))
            return offLine(user.getUserId());
        return false;
    }

    public static boolean offLine(Long userId) {
        return ONLINE_USERS.remove(userId);
    }

    public static boolean isOnLine(Long userId) {
        return ONLINE_USERS.contains(userId);
    }


}
