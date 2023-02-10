package com.ssafy.ssarijileo.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.ssafy.ssarijileo.kafka.event.FriendInviteEvent;
import com.ssafy.ssarijileo.sse.service.SseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FriendInviteConsumer {

	private final SseService sseService;

	@KafkaListener(topics = "alarm")
	public void consumeAlarmEvent(FriendInviteEvent event, Acknowledgment ack) {
		log.info("Friend Invite Alarm consumed from kafka topic: {}", event);
		sseService.sendFriendInvite(event);
		ack.acknowledge();
	}
}