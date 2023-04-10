package com.example.ssarijileo.api.friend.service;

import com.example.ssarijileo.api.friend.dto.FriendDto;

public interface FriendService {

	void requestFriend(FriendDto friendDto);

	void inviteFriend(FriendDto friendDto);
}
