package com.game.gb5.service;

import com.game.gb5.dao.CharacterSetDao;
import com.game.gb5.dao.ScouterDao;
import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.player.Player;
import com.game.gb5.domain.scouting.Scouter;
import com.game.gb5.domain.scouting.report.EmptyScoutingReport;
import com.game.gb5.domain.scouting.report.ScoutingReport;
import com.game.gb5.dto.scouting.ScouterDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoutingService {
	@Autowired
	private CharacterSetDao characterSetDao;
	@Autowired
	private ScouterDao scouterDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public Scouter getScouterById(Long id) {
		return scouterDao.getScouterById(id);
	}
	
	public ScoutingReport makeNewScoutingReport(Scouter scouter, Player player) {
		if (player.getTicketList().hasInstantAcquisitionReportTicket()) {
			CharacterSet characterSet = characterSetDao.getCharacterSetById(scouter.getCharacterSet().getId());
			ScoutingReport scoutingReport = scouter.makeScoutingReport(characterSet);
			player.getTicketList().consumeInstantAcquisitionReportTicket();
			return scoutingReport;
		} else {
			return new EmptyScoutingReport("리포트 티켓이 부족합니다.");
		}
	}
	
	private ScouterDto convertToDto(Scouter scouter) {
		//ScouterDto scouterDto = modelMapper.map(post, PostDto.class);
		//postDto.setSubmissionDate(post.getSubmissionDate(),
		//			userService.getCurrentUser().getPreference().getTimezone());
		//return postDto;
		return null;
	}
}
