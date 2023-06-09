package com.example.ssarijileo.api.reservation.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.ssarijileo.api.singing.client.SingingClient;
import com.example.ssarijileo.api.singing.dto.SingingDto;
import org.springframework.stereotype.Service;

import com.example.ssarijileo.api.reservation.dto.ReservationDto;
import com.example.ssarijileo.api.reservation.repository.ReservationRepository;
import com.example.ssarijileo.common.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final SingingClient singingClient;

	@Override
	public List<ReservationDto> findReservationBySessionId(String sessionId) {
		return reservationRepository.get(sessionId).orElseThrow(NotFoundException::new);
	}

	@Override
	public void createReservation(String sessionId) {
		reservationRepository.set(sessionId, new ArrayList<ReservationDto>());
	}

	@Override
	public void insertReservation(ReservationDto reservationDto) {
		List<ReservationDto> list = reservationRepository.get(reservationDto.getSessionId()).orElseThrow(
			NotFoundException::new);
		list.add(reservationDto);
		reservationRepository.set(reservationDto.getSessionId(), list);
	}

	@Override
	public void deleteReservation(ReservationDto reservationDto) {
		List<ReservationDto> list = reservationRepository.get(reservationDto.getSessionId()).orElseThrow(
			NotFoundException::new);
		list.add(reservationDto);
		reservationRepository.set(reservationDto.getSessionId(), list);
	}

	@Override
	public void insertSing(SingingDto singingDto) {
		singingDto.setState("I");
		singingClient.insertSinging(singingDto);
	}

	@Override
	public void deleteSing(SingingDto singingDto) {
		singingDto.setState("C");
		LocalTime localTime = LocalTime.ofSecondOfDay(singingDto.getTime());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");

		log.info("time(sec) = {}", singingDto.getTime());
		log.info("time(format) = {}", localTime.format(formatter));

		singingDto.setSingingTime(localTime.format(formatter));
		singingClient.deleteSinging(singingDto);
	}
}
