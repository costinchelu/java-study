package demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class RoomService {

	private final Map<Room, Boolean> roomAvailability;

	{
		roomAvailability = new HashMap<>();
		roomAvailability.put(new Room("1.1", 2), true);
		roomAvailability.put(new Room("1.2", 2), true);
		roomAvailability.put(new Room("1.3", 5), true);
		roomAvailability.put(new Room("2.1", 3), true);
		roomAvailability.put(new Room("2.2", 4), false);
	}

	public String findAvailableRoomId(BookingRequest bookingRequest) {
		return roomAvailability.entrySet().stream()
				.filter(Map.Entry::getValue).map(Map.Entry::getKey)
				.filter(room -> room.getCapacity() == bookingRequest.getGuestCount())
				.findFirst()
				.map(Room::getId)
				.orElseThrow(BusinessException::new);
	}
	
	public final List<Room> getAvailableRooms() {
		return roomAvailability.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
	public int getRoomCount() {
		return roomAvailability.size();
	}

	public void bookRoom(String roomId) {
		Room room = roomAvailability.entrySet().stream()
			.filter(entry -> entry.getKey().getId().equals(roomId) && entry.getValue())
			.findFirst()
			.map(Map.Entry::getKey)
			.orElseThrow(BusinessException::new);
		
		roomAvailability.put(room, true);		
	}
	
	public void unbookRoom(String roomId) {
		Room room = roomAvailability.entrySet().stream()
			.filter(entry -> entry.getKey().getId().equals(roomId) && !entry.getValue())
			.findFirst()
			.map(Map.Entry::getKey)
			.orElseThrow(BusinessException::new);
		
		roomAvailability.put(room, false);		
	}
	
}
