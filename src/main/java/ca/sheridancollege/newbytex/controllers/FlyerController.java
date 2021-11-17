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
import org.springframework.web.bind.annotation.RestController;
import ca.sheridancollege.newbytex.dto.FlyerRequestDTO;
import ca.sheridancollege.newbytex.dto.FlyerResponseDTO;
import ca.sheridancollege.newbytex.mapper.FlyerMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flyer")
public class FlyerController {

	private final FlyerMapper flyerMapper;

	@GetMapping("/getAllFlyers")
	public ResponseEntity<List<FlyerResponseDTO>> getAllFlyers() {
		return ResponseEntity.ok(flyerMapper.getAllflyers());
	}

	@PostMapping("/getFlyer")
	public ResponseEntity<FlyerResponseDTO> getFlyer(@Valid @RequestBody FlyerRequestDTO flyerRequest,
			BindingResult bindingResult) throws IOException {
		return ResponseEntity.ok(flyerMapper.findflyer(flyerRequest));
	}

	@PostMapping("/deleteflyer")
	public Boolean deleteFlyer(@Valid @RequestBody FlyerRequestDTO flyerRequest, BindingResult bindingResult) {
		return flyerMapper.deleteflyer(flyerRequest);
	}
	
	@PostMapping(value = "/createflyer", consumes = {
			"multipart/form-data"
	})
	public ResponseEntity<FlyerResponseDTO> addFlyer(@Valid @ModelAttribute FlyerRequestDTO flyerRequest,
			BindingResult bindingResult) throws IOException {
		return ResponseEntity.ok(flyerMapper.createflyer(flyerRequest));
	}
	 
	
	@GetMapping("/streamFlyer/{id}")
	public void streamFlyer(@Valid @PathVariable String id, HttpServletResponse response)
			throws Exception {
		FlyerResponseDTO Flyer = flyerMapper.findflyer(id);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		FileCopyUtils.copy(Flyer.getStream(), response.getOutputStream());
	}
}
