package com.ssafy.ssarijileo.api.song.service;

import java.util.Set;

public interface FavoriteSongService {
    void subscribe(String userId, Long songId);
    void unsubscribe(String userId, Long songId);
    Boolean hasSubscribed(String userId, Long songId);
}
