package com.example.ssarijileo.api.friend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.ssarijileo.api.friend.dto.FriendResponseDto;
import com.example.ssarijileo.api.friend.dto.MyFriendDto;

public interface FriendRepository {

	Optional<List<FriendResponseDto>> findAllFriend(String nickname);

	Optional<List<MyFriendDto>> findFriendByNickname(String nickname);
}
