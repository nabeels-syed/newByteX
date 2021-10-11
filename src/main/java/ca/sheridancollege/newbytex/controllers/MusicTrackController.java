package ca.sheridancollege.newbytex.controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.sheridancollege.newbytex.beans.MusicTrack;
import ca.sheridancollege.newbytex.dto.MusicTrackRequestDTO;
import ca.sheridancollege.newbytex.dto.MusicTrackResponseDTO;
import ca.sheridancollege.newbytex.mapper.MusicTrackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<MusicTrackResponseDTO> getTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) throws IOException {
		return ResponseEntity.ok(trackMapper.findTrack(trackRequest));
	}

	@PostMapping("/deleteTrack")
	public Boolean deleteTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest, BindingResult bindingResult) {
		return trackMapper.deleteTrack(trackRequest);
	}
	
	@PostMapping(value = "/createTrack", consumes = {
			"multipart/form-data"
	})
	public ResponseEntity<MusicTrackResponseDTO> addTrack(@Valid @ModelAttribute MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) throws IOException {
		return ResponseEntity.ok(trackMapper.createTrack(trackRequest));
	}


	@PostMapping("/updateTrack")
	public ResponseEntity<MusicTrackResponseDTO> updateTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest,
			BindingResult bindingResult) {
		return ResponseEntity.ok(trackMapper.updateTrack(trackRequest));
	}

	
//	@PostMapping("/streamTrack")
//	public void streamTrack(@Valid @RequestBody MusicTrackRequestDTO trackRequest, HttpServletResponse response)
//			throws Exception {
//		FileCopyUtils.copy(trackMapper.convertToEntity(trackMapper.findTrack(trackRequest)).getStream(),
//				response.getOutputStream());
//	}
	 
	@GetMapping("/streamTrack/{id}")
	public void streamTrack(@Valid @PathVariable String id, HttpServletResponse response)
			throws Exception {
		MusicTrackResponseDTO musicTrack = trackMapper.findTrack(id);
		FileCopyUtils.copy(musicTrack.getStream(), response.getOutputStream());
	}
}
