package com.example.ssarijileo.api.reservation.service;

import java.util.List;

import com.example.ssarijileo.api.reservation.dto.ReservationDto;
import com.example.ssarijileo.api.singing.dto.SingingDto;

public interface ReservationService {

	List<ReservationDto> findReservationBySessionId(String sessionId);

	void insertReservation(ReservationDto reservationDto);

	void deleteReservation(ReservationDto reservationDto);

	void insertSing(SingingDto singingDto);

	void deleteSing(SingingDto singingDto);
}
