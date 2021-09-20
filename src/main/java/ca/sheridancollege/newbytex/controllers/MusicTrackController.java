package ca.sheridancollege.newbytex.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.sheridancollege.newbytex.dto.MusicTrackRequestDTO;
import ca.sheridancollege.newbytex.dto.MusicTrackResponseDTO;
import ca.sheridancollege.newbytex.mapper.MusicTrackMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/track")
public class MusicTrackController {

	private final MusicTrackMapper trackMapper;

	@PostMapping("/createTrack")
	public ResponseEntity<MusicTrackResponseDTO> createTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) {
		return ResponseEntity.ok(trackMapper.createTrack(trackRequest));

	}

	@PostMapping("/updateTrack")
	public ResponseEntity<MusicTrackResponseDTO> updateTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) {
		return ResponseEntity.ok(trackMapper.updateTrack(trackRequest));
	}

	@GetMapping("/getAllTracks")
	public ResponseEntity<List<MusicTrackResponseDTO>> getAllTracks() {
		return ResponseEntity.ok(trackMapper.findAllTracks());
	}

	@PostMapping("/getTrack")
	public ResponseEntity<MusicTrackResponseDTO> getTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) {
		return ResponseEntity.ok(trackMapper.findTrack(trackRequest));
	}

	@PostMapping("/deleteTrack")
	public Boolean deleteTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) {
		return trackMapper.deleteTrack(trackRequest);
	}
}
