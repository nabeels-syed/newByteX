package ca.sheridancollege.newbytex.controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.sheridancollege.newbytex.dto.MusicTrackRequestDTO;
import ca.sheridancollege.newbytex.dto.MusicTrackResponseDTO;
import ca.sheridancollege.newbytex.mapper.MusicTrackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/track")
public class MusicTrackController {

	private final MusicTrackMapper trackMapper;

	@GetMapping("/getAllTracks")
	public ResponseEntity<List<MusicTrackResponseDTO>> getAllTracks() {
		return ResponseEntity.ok(trackMapper.getAllTracks());
	}

	@PostMapping("/getTrack")
	public ResponseEntity<MusicTrackResponseDTO> getTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest)
			throws IOException {
		return ResponseEntity.ok(trackMapper.findTrack(trackRequest));
	}

	@PostMapping("/deleteTrack")
	public Boolean deleteTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest, Authentication authentication) {

		if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
			return trackMapper.deleteTrack(trackRequest);
		} else {
			return false;
		}
	}

	@PostMapping(value = "/createTrack", consumes = { "multipart/form-data" })
	public ResponseEntity<MusicTrackResponseDTO> addTrack(@Valid @ModelAttribute MusicTrackRequestDTO trackRequest,
			Authentication authentication) throws IOException {

		if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
			return ResponseEntity.ok(trackMapper.createTrack(trackRequest));
		} else {
			return ResponseEntity.ok(new MusicTrackResponseDTO());
		}
	}

	@PostMapping("/updateTrack")
	public ResponseEntity<MusicTrackResponseDTO> updateTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			Authentication authentication) {
		if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
			return ResponseEntity.ok(trackMapper.updateTrack(trackRequest));
		} else {
			return null;
		}
	}

	@GetMapping("/streamTrack/{id}")
	public void streamTrack(@Valid @PathVariable String id, HttpServletResponse response) throws Exception {
		MusicTrackResponseDTO musicTrack = trackMapper.findTrack(id);
		response.setContentType("audio/mpeg");
		FileCopyUtils.copy(musicTrack.getStream(), response.getOutputStream());
	}
}
