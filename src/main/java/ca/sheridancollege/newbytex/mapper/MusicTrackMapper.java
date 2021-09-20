package ca.sheridancollege.newbytex.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ca.sheridancollege.newbytex.beans.Track;
import ca.sheridancollege.newbytex.dto.MusicTrackRequestDTO;
import ca.sheridancollege.newbytex.dto.MusicTrackResponseDTO;
import ca.sheridancollege.newbytex.services.MusicTrackService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MusicTrackMapper {

	private final ModelMapper trackMapper;
	private final MusicTrackService trackService;

	private Track convertToEntity(MusicTrackRequestDTO trackRequestDto) {
		return trackMapper.map(trackRequestDto, Track.class);
	}

	public MusicTrackResponseDTO convertToResponseDto(Track track) {
		return trackMapper.map(track, MusicTrackResponseDTO.class);
	}

	public MusicTrackResponseDTO findTrack(MusicTrackRequestDTO trackRequest) {
		return convertToResponseDto(trackService.findTrack(convertToEntity(trackRequest).getId()));
	}

	public List<MusicTrackResponseDTO> findAllTracks() {
		return trackService.findAllTracks().stream().map(this::convertToResponseDto).collect(Collectors.toList());
	}

	public MusicTrackResponseDTO updateTrack(MusicTrackRequestDTO trackRequest) {
		Track track = convertToEntity(trackRequest);
		return convertToResponseDto(trackService.updateTrack(track.getArtist(), track.getTitle(), track.getFileName(),
				track.getId(), track.getReleaseDate(), track.getIsFavourited()));
	}

	public Boolean deleteTrack(MusicTrackRequestDTO trackRequest) {
		return trackService.deleteTrack(convertToEntity(trackRequest).getId());
	}

	public MusicTrackResponseDTO createTrack(MusicTrackRequestDTO trackRequest) {
		Track track = convertToEntity(trackRequest);
		return convertToResponseDto(trackService.createTrack(track.getArtist(), track.getTitle(), track.getFileName(),
				track.getReleaseDate(), track.getIsFavourited()));
	}
}
