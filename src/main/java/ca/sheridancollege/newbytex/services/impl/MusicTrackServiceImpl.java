package ca.sheridancollege.newbytex.services.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.sheridancollege.newbytex.beans.Track;
import ca.sheridancollege.newbytex.repositories.MusicTrackRepository;
import ca.sheridancollege.newbytex.repositories.UserRepository;
import ca.sheridancollege.newbytex.security.JwtProvider;
import ca.sheridancollege.newbytex.services.MusicTrackService;
import lombok.RequiredArgsConstructor;

@Service("trackServiceImpl")
@RequiredArgsConstructor
public class MusicTrackServiceImpl implements MusicTrackService {

	private final MusicTrackRepository trackRepository;

	@Override
	public List<Track> findAllTracks() {
		return trackRepository.findAll();
	}

	@Override
	public Track findTrack(long id) {
		return trackRepository.findOneById(id);
	}

	@Override
	public Track updateTrack(String artist, String title, String filename, long id, String releaseDate,
			boolean isFavourited) {

		Track updatedTrack = trackRepository.findOneById(id);
		if (updatedTrack != null) {
			if (artist.isBlank() && artist.isEmpty()) {
				updatedTrack.setArtist(artist);
			}
			if (title.isBlank() && title.isEmpty()) {
				updatedTrack.setTitle(title);
			}
			if (filename.isBlank() && filename.isEmpty()) {
				updatedTrack.setFileName(filename);
			}
			if (releaseDate.isBlank() && releaseDate.isEmpty()) {
				updatedTrack.setReleaseDate(releaseDate);
			}

			updatedTrack.setIsFavourited(false);

			updatedTrack = trackRepository.save(updatedTrack);
		}

		return updatedTrack;
	}

	@Override
	public Boolean deleteTrack(long id) {
		Track track = trackRepository.findOneById(id);
		if (track != null) {
			trackRepository.delete(track);
			return true;
		}
		return false;
	}

	@Override
	public Track createTrack(String artist, String title, String filename, String releaseDate, boolean isFavourited) {
		Track track = new Track();
		track.setArtist(artist);
		track.setTitle(title);
		track.setIsFavourited(false);
		track.setFileName(filename);
		track.setReleaseDate(releaseDate);
		return trackRepository.save(track);
	}
}
