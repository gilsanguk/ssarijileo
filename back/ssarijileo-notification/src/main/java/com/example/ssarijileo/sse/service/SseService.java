package com.example.ssarijileo.sse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.ssarijileo.api.friend.dto.FriendInviteEvent;
import com.example.ssarijileo.api.friend.dto.FriendRequestEvent;

public interface SseService {

	SseEmitter connection(String userId);

	void sendFriendRequest(FriendRequestEvent event);

	void sendFriendInvite(FriendInviteEvent event);
}
