package com.ssafy.ssarijileo.api.friend.dto;

import com.ssafy.ssarijileo.common.model.AlarmUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FriendRequestEvent {

	AlarmUser user;

	// 보낸사람 닉네임
	String fromUserNickname;

	// 친구PK
	Long friendId;
}
